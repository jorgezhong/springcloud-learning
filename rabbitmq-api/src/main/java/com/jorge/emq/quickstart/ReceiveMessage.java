package com.jorge.emq.quickstart;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class ReceiveMessage {
    private static int QOS = 1;
    /**
     * 注:此处的TCP端口默认是1883
     */
    private static String HOST = "tcp://192.168.4.189:1883";
    private static String userName = "admin";
    private static String password = "public";
 
    private static MqttClient connect(String clientId) throws MqttException {
        MemoryPersistence persistence = new MemoryPersistence();
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(false);
        options.setUserName(userName);
        options.setPassword(password.toCharArray());
        options.setKeepAliveInterval(20);
        options.setConnectionTimeout(10);
 
        MqttClient client = new MqttClient(HOST, clientId, persistence);
        client.setCallback(new PushCallback());
        client.connect(options);
        return client;
    }
 
    public static void receive(MqttClient client, String topic) throws MqttException {
        int[] Qos = {QOS};
        String[] topics = {topic};
        client.subscribe(topics, Qos);
    }
 
    public static void start() throws MqttException {
        String clientId = "ClientId_01";
        String topic = "MQTT_TOPIC";
 
        MqttClient client = connect(clientId);
        if (client != null) {
            receive(client, topic);
        }
    }

    public static void main(String[] args) throws MqttException {
        start();
    }
}
