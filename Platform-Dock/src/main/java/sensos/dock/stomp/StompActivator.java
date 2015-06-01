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
package sensos.dock.stomp;

import org.fusesource.stomp.jms.*;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.jms.*;

@Singleton
@Startup
public class StompActivator {

	@PostConstruct
	public void applicationStartup() {

		try {

			String user = env("ACTIVEMQ_USER", "admin");
			String password = env("ACTIVEMQ_PASSWORD", "password");
			String host = env("ACTIVEMQ_HOST", "localhost");
			int port = Integer.parseInt(env("ACTIVEMQ_PORT", "61613"));
			String destination = "/topic/event";

			StompJmsConnectionFactory factory = new StompJmsConnectionFactory();
			factory.setBrokerURI("tcp://" + host + ":" + port);

			Connection connection = factory.createConnection(user, password);
			connection.start();
			Session session = connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);
			Destination dest = new StompJmsDestination(destination);

			MessageConsumer consumer = session.createConsumer(dest);
			long start = System.currentTimeMillis();
			long count = 1;
			System.out.println("Waiting for messages...");
			while (true) {
				Message msg = consumer.receive();
				if (msg instanceof TextMessage) {
					String body = ((TextMessage) msg).getText();
					if ("SHUTDOWN".equals(body)) {
						long diff = System.currentTimeMillis() - start;
						System.out.println(String.format(
								"Received %d in %.2f seconds", count,
								(1.0 * diff / 1000.0)));
						break;
					} else {
						if (count != msg.getIntProperty("id")) {
							System.out.println("mismatch: " + count + "!="
									+ msg.getIntProperty("id"));
						}
						count = msg.getIntProperty("id");

						if (count == 0) {
							start = System.currentTimeMillis();
						}
						if (count % 1000 == 0) {
							System.out.println(String.format(
									"Received %d messages.", count));
						}
						count++;
					}

				} else {
					System.out.println("Unexpected message type: "
							+ msg.getClass());
				}
			}
			connection.close();

		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	private static String env(String key, String defaultValue) {
		String rc = System.getenv(key);
		if (rc == null)
			return defaultValue;
		return rc;
	}

	@SuppressWarnings("unused")
	private static String arg(String[] args, int index, String defaultValue) {
		if (index < args.length)
			return args[index];
		else
			return defaultValue;
	}

}
