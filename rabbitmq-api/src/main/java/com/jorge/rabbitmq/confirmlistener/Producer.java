package com.jorge.rabbitmq.confirmlistener;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Project <springcloud-learning>
 * Created by jorgezhong on 2019/3/7 16:02.
 */
public class Producer {

    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.4.189");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();

        //指定消息的投递模式：消息确认模式
        channel.confirmSelect();

        String exchangeName = "test_confirm_exchange";
        String routingKey = "confirmlistener.save";
        String msg = "Hello RabbitMQ Send confirmlistener message";
        channel.basicPublish(exchangeName,routingKey,null,msg.getBytes());

        //添加一个确认监听
        channel.addConfirmListener(new ConfirmListener() {
            /**
             * 成功的是哦胡嗲用该方法
             * @param deliveryTag 消息的唯一标签
             * @param multiple 是否批量的
             * @throws IOException
             */
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("--------ack---------");
            }

            /**
             * 失败的时候调用该方法
             * @param deliveryTag 消息的唯一标签
             * @param multiple 是否批量的
             * @throws IOException
             */
            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {

                System.out.println("--------no ack---------");
            }
        });

    }

}
