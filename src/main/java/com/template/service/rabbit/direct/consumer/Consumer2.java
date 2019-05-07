package com.template.service.rabbit.direct.consumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import com.template.service.utils.ConnectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Project Name:ssh-rabbitMQ
 * File Name:Consumer2
 * Package Name:com.template.service.rabbit.direct.consumer
 * Date:2019/5/7
 * Author:liujie
 * Description:
 * Copyright (c) 2019, 重庆云凯科技有限公司 All Rights Reserved.
 */


public class Consumer2 {

    private static final Logger logger = LoggerFactory.getLogger(Consumer1.class);

    private static final String QUEUE_NAME = "direct_queue_2";

    private static final String EXCHANGE_NAME = "direct_exchange";

    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();

        Channel channel = connection.createChannel();

        //申明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        //绑定队列到交换机
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "direct_test1");

        //申明消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);

        //在队列上注册消费者并监听
        channel.basicConsume(QUEUE_NAME, false, consumer);

        //获取消息
        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            logger.info("消费者2收到消息：【" + message + "】");
        }
    }
}
