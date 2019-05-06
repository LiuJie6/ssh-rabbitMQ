package com.template.service.rabbit.fanout.consumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import com.template.service.utils.ConnectionUtil;

/**
 * Project Name:ssh-rabbitMQ
 * File Name:Consumer1
 * Package Name:com.template.service.rabbit.fanout.consumer
 * Date:2019/5/6
 * Author:liujie
 * Description:
 * Copyright (c) 2019, 重庆云凯科技有限公司 All Rights Reserved.
 */


public class Consumer1 {

    private static final String FANOUT_EXCHANGE = "fanout_exchange";

    private static final String FANOUT_QUEUE1 = "fanout_queue_test1";

    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare(FANOUT_QUEUE1, false, false, false, null);

        channel.queueBind(FANOUT_QUEUE1, FANOUT_EXCHANGE, "");

        QueueingConsumer consumer = new QueueingConsumer(channel);
        //对队列进行监听，手动返回完成状态(用于在队列上注册消费者，没有这句话就无法消费)
        channel.basicConsume(FANOUT_QUEUE1, false, consumer);
        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println("消费者1接收到了信息：【" + message + "】");

            Thread.sleep(10);
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }
    }
}
