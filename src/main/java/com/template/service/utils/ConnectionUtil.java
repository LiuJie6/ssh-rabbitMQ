package com.template.service.utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Project Name:ssh-rabbitMQ
 * File Name:ConnectionUtil
 * Package Name:com.template.service.utils
 * Date:2019/4/16
 * Author:liujie
 * Description:获取MQ连接
 * Copyright (c) 2019, 重庆云凯科技有限公司 All Rights Reserved.
 */
public class ConnectionUtil {

    private static final String HOST = "10.0.101.44";
    private static final int PORT = 5672;
    private static final String USERNAME = "lj";
    private static final String PASSWORD = "yk@1234";

    public static Connection getConnection() throws Exception {
        //定义连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //设置服务地址
        connectionFactory.setHost(HOST);
        //端口
        connectionFactory.setPort(PORT);
        //设置账户信息
        connectionFactory.setUsername(USERNAME);
        connectionFactory.setPassword(PASSWORD);
        //通过工厂获取连接并返回
        return connectionFactory.newConnection();
    }
}
