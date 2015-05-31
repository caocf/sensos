package dock.mqtt.client.paho;

import org.eclipse.paho.client.mqttv3.*;

import dock.mqtt.client.common.Settings;

/**
 * @author Alexis Hassler
 * @link   https://github.com/hasalex/mqtt-example
 */
public class BlockingConsumer {
	public static void main(String[] args) throws MqttException {
		
		final MqttClient client = new MqttClient(Settings.SERVER_URL,
				"BlockingConsumer");
		client.connect();
		client.setCallback(new SubscriptionCallback() {
			
			@Override
			protected void postMessageArrived() throws MqttException {
				client.disconnect();
				client.close();
			}
		});
		
		client.subscribe(Settings.TOPIC_NAME);
		
	}
}