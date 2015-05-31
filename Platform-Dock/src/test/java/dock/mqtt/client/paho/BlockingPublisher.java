package dock.mqtt.client.paho;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;

import dock.mqtt.client.common.Settings;

/**
 * @author Alexis Hassler
 */
public class BlockingPublisher {
	public static void main(String[] args) throws MqttException {
		
		final MqttClient client = new MqttClient(Settings.SERVER_URL,
				"BlockingPublisher");
		client.connect();
		
		MqttTopic topic = client.getTopic(Settings.TOPIC_NAME);
		MqttMessage message = new MqttMessage();
		
		message.setPayload("Hello from the Paho blocking client".getBytes());
		message.setRetained(true);
		topic.publish(message);
		System.out.println("Publishing succeeded");
		client.disconnect();
		client.close();
		
	}
	
}