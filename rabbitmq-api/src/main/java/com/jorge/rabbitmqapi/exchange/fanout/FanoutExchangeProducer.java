package com.jorge.rabbitmqapi.exchange.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 生产者
 * Created by jorgezhong on 2019/3/1 17:19.
 */
public class FanoutExchangeProducer {

    public static void main(String[] args) throws IOException, TimeoutException {

        //1. 创建ConnectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.4.189");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        //2. 创还能Connection
        Connection connection = connectionFactory.newConnection();

        //3. 创建Channel
        Channel channel = connection.createChannel();

        //4. 声明
        String exchangeName = "test_fanout_exchange";
        //不设置路由键
        String routingKey = "";

        for (int i = 0; i < 10; i++) {

            String msg = "Hello World RabbitMQ , Topic Exchange Message...";

            //5. 发送
            channel.basicPublish(exchangeName, routingKey, null, msg.getBytes());
        }

        channel.close();
        connection.close();

    }

}
