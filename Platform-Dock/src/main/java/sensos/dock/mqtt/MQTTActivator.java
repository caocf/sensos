/**
 *  Sensos IoT Platform.
 *  Copyright (C) 2015 sensos
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package sensos.dock.mqtt;

import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.fusesource.hawtbuf.Buffer;
import org.fusesource.hawtbuf.UTF8Buffer;
import org.fusesource.mqtt.client.Callback;
import org.fusesource.mqtt.client.CallbackConnection;
import org.fusesource.mqtt.client.Listener;
import org.fusesource.mqtt.client.MQTT;
import org.fusesource.mqtt.client.QoS;
import org.fusesource.mqtt.client.Topic;

import sensos.dock.common.Settings;


/**
 * MQTT is activated as a part of tomee.xml
 * 
 * @author sensos
 *
 */
@Singleton
@Startup
public class MQTTActivator {

	MQTT mqtt = new MQTT();
	final CallbackConnection connection = mqtt.callbackConnection();
	protected Logger l = Logger.getLogger(this.getClass().getName());

	public MQTTActivator() { }

	// The @Startup annotation ensures that this method is
	// called when the application starts up.
	@PostConstruct
	public void applicationStartup() {

		l.log(Level.INFO, this.getClass().getName() + " invoked.");

            try {
                mqtt.setHost(Settings.SERVER_URL);
            } catch (URISyntaxException ex) {
                Logger.getLogger(MQTTActivator.class.getName()).log(Level.SEVERE, null, ex);
            }
		
		mqtt.setClientId("CallbackConsumer");

		
		connection.listener(new Listener() {
			@Override
			public void onConnected() {
				System.out.println("OK, connected");
			}

			@Override
			public void onDisconnected() {
				System.out.println("Disconnected");
			}

			@Override
			public void onPublish(UTF8Buffer topic, Buffer message, Runnable ack) {
				System.out.println("Hey, message arrived on topic "
						+ topic.toString() + " : " + message.utf8());
				ack.run();
			}

			@Override
			public void onFailure(Throwable value) {
				System.out.println("onFailure");
			}
		});

		connection.connect(new Callback<Void>() {
			@Override
			public void onSuccess(Void value) {
				System.out.println("Connection succeeded");
				connection.subscribe(new Topic[] { new Topic(
						Settings.TOPIC_NAME, QoS.AT_MOST_ONCE) },
						new Callback<byte[]>() {

							@Override
							public void onSuccess(byte[] payload) {
								System.out
										.println("Mmmh, subscription succeeded");

							}

							@Override
							public void onFailure(Throwable value) {
								System.out.println("Pfff, subscription failed");

							}

						});

			}

			@Override
			public void onFailure(Throwable value) {
				System.out.println("Tooh, connection failed");
			}
		});

	}

	@PreDestroy
	public void applicationShutdown() {

		MQTTActivator.close(connection);
		
	}


	private static void close(CallbackConnection connection) {
		connection.disconnect(new Callback<Void>() {
			@Override
			public void onSuccess(Void value) {
				System.out.println("Closing successed");
			}

			@Override
			public void onFailure(Throwable value) {
				System.out.println("Closing failed");
			}
		});
		
	}
	
}
