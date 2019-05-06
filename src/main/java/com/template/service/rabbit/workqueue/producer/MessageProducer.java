package com.template.service.rabbit.workqueue.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.template.service.utils.ConnectionUtil;

/**
 * Project Name:ssh-rabbitMQ
 * File Name:MessageProducer
 * Package Name:com.template.service.rabbit.workqueue.producer
 * Date:2019/4/15
 * Author:liujie
 * Description:
 * Copyright (c) 2019, 重庆云凯科技有限公司 All Rights Reserved.
 */


public class MessageProducer {

    private static final String QUEUE_NAME = "test_queue_work";

    public static void main(String[] args) throws Exception {
        //获取mq连接
        Connection connection = ConnectionUtil.getConnection();
        //创建通道
        Channel channel = connection.createChannel();
        //申明队列(queueName,durable(是否持久化)，exclusive（是否排外），autoDelete（自动删除），arguments（）)
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        for (int i = 0; i < 100; i++) {
            String message = "第" + (i + 1) + "条消息";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println("[x] send '" + message + "'");

            Thread.sleep(10);
        }
        channel.close();
        connection.close();
    }
}
