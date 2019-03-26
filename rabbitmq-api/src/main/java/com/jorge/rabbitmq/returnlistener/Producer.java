package com.jorge.rabbitmq.returnlistener;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Project <springcloud-learning>
 * Created by jorgezhong on 2019/3/7 17:03.
 */
@SuppressWarnings("all")
public class Producer {


    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.4.189");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();

        String exchangeName = "test_return_exchange";
        String routingKey = "return.save";
        String routingKeyError = "abc.save";

        String msg = "Hello RabbitMQ Return Message";

        channel.addReturnListener(new ReturnListener() {
            /**
             * 不可达消息监听处理
             * @param replyCode 响应码
             * @param replyText 文本
             * @param exchange exchange
             * @param routingKey1 routingKey
             * @param properties properties属性
             * @param body 消息体
             * @throws IOException
             */
            @Override
            public void handleReturn(int replyCode, String replyText, String exchange,
                                     String routingKey1, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("----------handler return----------");
                System.out.println("replyCode = " + replyCode);
                System.out.println("replyText = " + replyText);
                System.out.println("exchange = " + exchange);
                System.out.println("routingKey1 = " + routingKey1);
                System.out.println("properties = " + properties);
                System.out.println("body = " + new String(body));

            }
        });

        /**
         * mandatory为false:不走监听回调handleReturn,直接被MQ删除
         * mandatory为true:走handleReturn回调
         *
         */
//        channel.basicPublish(exchangeName, routingKey, false, null, msg.getBytes());
        channel.basicPublish(exchangeName, routingKeyError,true, null, msg.getBytes());
    }

}
