package com.jorge.cas;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;

/**
 * Project <springcloud-learning>
 * Created by jorgezhong on 2019/3/12 17:56.
 */
public interface Publisher {


    /**
     *
     * 根据推送映射关系，没有的话新增一个topic -- client映射
     *
     * @param topic
     * @param payload
     */
    boolean publish(String topic,byte[] payload,int qos);

    /**
     *
     * 异步根据推送映射关系，没有的话新增一个topic -- client映射
     *
     * @param topic
     * @param payload
     * @param listener 异步回调监听器
     */
    void publishAsync(String topic, byte[] payload, int qos, IMqttActionListener listener);

}
