package com.jorge.rabbitmq.exchange.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 *
 * 消费者
 * Created by jorgezhong on 2019/3/1 17:19.
 */
public class TopicExchangeConsumer {
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        //1.创建一个ConnectionFactory，并进行配置
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.4.189");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        //支持自动重连
        connectionFactory.setAutomaticRecoveryEnabled(true);
        //每3s可以重连一次
        connectionFactory.setNetworkRecoveryInterval(3000);

        //2. 通过ConnectionFactory创建连接
        Connection connection = connectionFactory.newConnection();

        //3. 通过Connection创建一个Channel
        Channel channel = connection.createChannel();


        //4. 声明
        String exchangeName = "test_topic_exchange";
        String exchangeType = "topic";

        String queueName = "test_topic_queue";
//        String routingKey = "count.direct";
        //设置路由规则
        String routingKey = "user.*";

        //声明了一个交换机
        channel.exchangeDeclare(exchangeName, exchangeType,true,true,false,null);

        //声明（创建）一个队列
        channel.queueDeclare(queueName, true, false, false, null);

        //5. 建立一个exchange和queue通过routingKey绑定的绑定关系
        channel.queueBind(queueName, exchangeName, routingKey);

        //5. 创建消费者,监听队列
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);

        //6. 设置Channel(队列名称，是否自动ack，consumer)
        channel.basicConsume(queueName, true, queueingConsumer);

        while (true) {

            //7. 获取消息
            //Delivery : RabbitMQ对消息做了封装，表示消息对象
            //阻塞式获取下一个消息，可设置超时时间
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            String msg = new String(delivery.getBody());
            System.out.println("收到消息：" + msg);

        }

    }
}
