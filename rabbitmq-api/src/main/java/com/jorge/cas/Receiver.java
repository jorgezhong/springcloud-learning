package com.jorge.cas;

/**
 * Project <springcloud-learning>
 * Created by jorgezhong on 2019/3/12 18:03.
 */
public interface Receiver {

    /**
     * 向EMQ注册一个新的监听器
     *
     * @param topic
     * @param qos
     * @param handler 监听回调逻辑
     * @return 成功失败
     */
    boolean register(String topic, int qos, MQTTMsgHandler handler);

}
