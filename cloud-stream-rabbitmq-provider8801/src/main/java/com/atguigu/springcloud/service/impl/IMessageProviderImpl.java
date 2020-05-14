package com.atguigu.springcloud.service.impl;


import com.atguigu.springcloud.service.IMessageProvider;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @ClassName IMessageProviderImpl
 * @Description TODO
 * @Author xsh
 * @Date 2020-05-13 14:43
 * @Version 1.0
 **/

@EnableBinding(Source.class)  // 定义消息的通信管道
public class IMessageProviderImpl implements IMessageProvider {

    @Resource
    private MessageChannel output; // 消息发送管道


    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        System.out.println("*************"+serial);
        return serial;
    }
}
