package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @ClassName ReceiveMessageListenerController
 * @Description TODO
 * @Author xsh
 * @Date 2020-05-13 15:23
 * @Version 1.0
 **/
@Slf4j
@Component
@EnableBinding(Sink.class)  // 该注解表示Sink消息通道
public class ReceiveMessageListenerController {

    @Value("${server.port}")
    private String serverPort;

    // 自带 消费者
    @StreamListener(Sink.INPUT)
    public void input(Message<String> message) {
        log.info("1111111111111");

        log.info("消费者1号,------>接收的消息:"+message.getPayload()+"\t port:"+serverPort);

    }


}
