package com.template.service.rabbit.fanout.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.template.service.utils.ConnectionUtil;

/**
 * Project Name:ssh-rabbitMQ
 * File Name:Producer
 * Package Name:com.template.service.rabbit.fanout.producer
 * Date:2019/5/6
 * Author:liujie
 * Description:发布/订阅模式的生成者
 * Copyright (c) 2019, 重庆云凯科技有限公司 All Rights Reserved.
 */


public class Producer {

    private static final String FANOUT_EXCHANGE = "fanout_exchange";

    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();

        Channel channel = connection.createChannel();

        channel.exchangeDeclare(FANOUT_EXCHANGE, "fanout");

        for (int i = 0; i < 10; i++) {
            String message = "发布订阅模式:" + i;
            channel.basicPublish(FANOUT_EXCHANGE, "", null, message.getBytes());
            System.out.println("生产者发送了消息：【" + message + "】");
        }

        channel.close();
        connection.close();
    }
}
