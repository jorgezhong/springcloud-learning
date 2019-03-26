package com.jorge.rabbitmq.message;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * 消费者
 * Created by jorgezhong on 2019/3/1 17:19.
 */
public class Consumer {
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        //1.创建一个ConnectionFactory，并进行配置
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.4.189");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        //2. 通过ConnectionFactory创建连接
        Connection connection = connectionFactory.newConnection();

        //3. 通过Connection创建一个Channel
        Channel channel = connection.createChannel();

        //4. 声明（创建）一个队列
        String queueName = "test001";
        channel.queueDeclare(queueName, true, false, false, null);

        //5. 创建消费者,监听队列
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);

        //6. 设置Channel(队列名称，是否自动ack，consumer)
        channel.basicConsume(queueName, true, queueingConsumer);

        while (true) {

            //7. 获取消息
            //Delivery : RabbitMQ对消息做了封装，表示消息对象
            //阻塞获取下一个消息，可设置超时时间
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            String msg = new String(delivery.getBody());
            System.out.println("消费端：" + msg);

            //使用Delivery获取消息Properties
            AMQP.BasicProperties properties = delivery.getProperties();
            Map<String, Object> headers = properties.getHeaders();

            headers.forEach((s, o) -> System.out.println("key:" + s + ", value:" + o.toString()));

        }

    }
}
