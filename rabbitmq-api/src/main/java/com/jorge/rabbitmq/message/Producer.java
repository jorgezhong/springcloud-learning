package com.jorge.rabbitmq.message;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

/**
 * 生产者
 * Created by jorgezhong on 2019/3/1 17:19.
 */
public class Producer {

    public static void main(String[] args) throws IOException, TimeoutException {
        //1.创建一个ConnectionFactory，并进行配置
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.4.189");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        //2. 通过ConnectionFactory创建连接
        Connection connection = connectionFactory.newConnection();

        //3. 通过Connection创建一个Channel
        Channel channel = connection.createChannel();


        //自定义属性容器
        HashMap<String, Object> headers = new HashMap<>();
        headers.put("myAttribute1", "111");
        headers.put("myAttribute2", "222");

        /**
         * 定义消息的properties
         * deliveryMode : 1为非持久化，2为持久化
         * expiration：消息存货时间
         * headers：自定义一些属性
         */
        AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder()
                .deliveryMode(2)
                .contentEncoding("UTF-8")
                .expiration("10000")
                .headers(headers).build();

        //4. 通过Channel发送数据
        for (int i = 0; i < 5; i++) {
            String msg = "Hello RabbitMQ";
            //exchange设置配空字符串，RabbitMQ使用默认exchange:(AMQP default),
            //其规则是用routingKey匹配同名的queue，匹配到了则往对应的队列发送数据，否则发送失败
            //properties和Body组成消息
            channel.basicPublish("", "test001", properties, msg.getBytes());
        }

        //5. 记得关闭连接
        channel.close();
        connection.close();
    }

}
