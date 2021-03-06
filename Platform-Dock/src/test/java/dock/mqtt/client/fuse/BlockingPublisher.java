package dock.mqtt.client.fuse;

import org.fusesource.mqtt.client.BlockingConnection;
import org.fusesource.mqtt.client.MQTT;
import org.fusesource.mqtt.client.QoS;

import dock.mqtt.client.common.Settings;

/**
 * @author Alexis Hassler
 */
public class BlockingPublisher {
	
	public static void main(String[] args) throws Exception {
		
		MQTT mqtt = new MQTT();
		mqtt.setHost(Settings.SERVER_URL);
		mqtt.setClientId("BlockingPublisher");
		BlockingConnection connection = mqtt.blockingConnection();
		connection.connect();
		connection.publish(Settings.TOPIC_NAME, "Hello from the Fuse blocking client".getBytes(), QoS.AT_MOST_ONCE, true);
		System.out.println("Publishing succeeded");
		connection.disconnect();
		
	}
	
}