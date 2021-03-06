package dock.mqtt.client.fuse;

import org.fusesource.mqtt.client.*;

import dock.mqtt.client.common.Settings;

/**
 * 
 * 
 * 
 * @author Alexis Hassler
 */
public class BlockingConsumer {

	public static void main(String[] args) throws Exception {
		
		MQTT mqtt = new MQTT();
		mqtt.setHost(Settings.SERVER_URL);
		mqtt.setClientId("BlockingConsumer");
		BlockingConnection connection = mqtt.blockingConnection();
		connection.connect();
		connection.subscribe(new Topic[] { new Topic(Settings.TOPIC_NAME, QoS.AT_MOST_ONCE) });
		
		while (true) {
		
			Message message = connection.receive();
			System.out.println("Hey, message arrived on topic "
					+ message.getTopic() + " : "
					+ new String(message.getPayload(), "UTF-8"));
			message.ack();
			
		}
		
	}
	
}