package dock.mqtt.client.fuse;

import org.fusesource.mqtt.client.FutureConnection;
import org.fusesource.mqtt.client.MQTT;
import org.fusesource.mqtt.client.QoS;

import dock.mqtt.client.common.Settings;

/**
 * @author Alexis Hassler
 */
public class FuturePublisher {

	public static void main(String[] args) throws Exception {

		MQTT mqtt = new MQTT();
		mqtt.setHost(Settings.SERVER_URL);
		mqtt.setClientId("FuturePublisher");
		FutureConnection connection = mqtt.futureConnection();
		connection.connect().await();

		connection.publish(Settings.TOPIC_NAME,
				"Hello from the Fuse future client".getBytes(),
				QoS.AT_MOST_ONCE, true).await();

		System.out.println("Publishing succeeded");
		connection.disconnect().await();
		
	}
}