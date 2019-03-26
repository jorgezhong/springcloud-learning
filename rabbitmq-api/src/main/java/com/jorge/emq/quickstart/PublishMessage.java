package com.jorge.emq.quickstart;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class PublishMessage {
    private static int QOS = 1;
    /**
     * 注:此处的TCP端口默认是1883
     */
    private static String HOST = "tcp://192.168.4.189:1883";
    private static String userName = "admin";
    private static String password = "public";

    private static MqttClient connect(String clientId, String userName, String password) throws MqttException {
        MemoryPersistence persistence = new MemoryPersistence();
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(false);
        options.setUserName(userName);
        options.setPassword(password.toCharArray());
        options.setConnectionTimeout(10);
        options.setKeepAliveInterval(20);

        MqttClient client = new MqttClient(HOST, clientId, persistence);
        client.setCallback(new PushCallback());
        client.connect(options);
        return client;
    }

    private static void publish(MqttClient client, String msg, String topic) throws MqttException {
        MqttMessage message = new MqttMessage(msg.getBytes());
        message.setQos(QOS);
        message.setRetained(false);
        client.publish(topic, message);
    }

    public static void start(String msg) throws MqttException {
        /*String msg = "Hello !";*/
        String clientId = "ServerId_01";
        String topic = "MQTT_TOPIC";

        //// TODO: 2019/3/12 Publisher管理client 根据project key
        MqttClient client = connect(clientId, userName, password);
        if (client != null) {

            publish(client, msg, topic);

            System.out.println("Start-----Public Message:" + msg);
        }
        if (client != null) {
            client.disconnect();
        }
    }



    public static void main(String[] args) throws MqttException {

        start("i am jorgezhong");
    }
}
