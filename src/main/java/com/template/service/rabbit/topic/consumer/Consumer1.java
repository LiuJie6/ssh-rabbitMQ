package com.template.service.rabbit.topic.consumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import com.template.service.utils.ConnectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Project Name:ssh-rabbitMQ
 * File Name:Consumer1
 * Package Name:com.template.service.rabbit.topic.consumer
 * Date:2019/5/6
 * Author:liujie
 * Description:
 * Copyright (c) 2019, 重庆云凯科技有限公司 All Rights Reserved.
 */
public class Consumer1 {

    private static final Logger logger = LoggerFactory.getLogger(Consumer1.class);

    private static final String QUEUE_NAME = "topic_queue_test_1";

    private static final String EXCHANGE_NAME = "YYT-TOPIC";

    public static void main(String[] args) throws Exception {
        //获取连接
        Connection connection = ConnectionUtil.getConnection();
        //创建信道
        Channel channel = connection.createChannel();
        //申明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //绑定队列到交换机并指明routingKey
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "yyt.1.pass");

        //申明队列的消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);

        //监听队列，手动返回完成状态
        channel.basicConsume(QUEUE_NAME, false, consumer);

        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            logger.info("消费者1接收到消息：" + message);
            Thread.sleep(10);
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }
    }
}
