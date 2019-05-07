package com.template.service.rabbit.direct.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.template.service.utils.ConnectionUtil;

/**
 * Project Name:ssh-rabbitMQ
 * File Name:Producer
 * Package Name:com.template.service.rabbit.direct.producer
 * Date:2019/5/7
 * Author:liujie
 * Description:路由模式生产者
 * Copyright (c) 2019, 重庆云凯科技有限公司 All Rights Reserved.
 */
public class Producer {

    private static final String EXCHANGE_NAME = "direct_exchange";

    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();

        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "direct");

        String message = "hello rabbit";

        channel.basicPublish(EXCHANGE_NAME, "direct_test", null, message.getBytes());

        System.out.println("生产者发送了消息：【" + message + "】");

        channel.close();
        connection.close();
    }
}
