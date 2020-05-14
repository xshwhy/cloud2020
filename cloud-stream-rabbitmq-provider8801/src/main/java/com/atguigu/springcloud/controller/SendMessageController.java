package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.IMessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName SendMessageController
 * @Description TODO
 * @Author xsh
 * @Date 2020-05-13 14:57
 * @Version 1.0
 **/
@RestController
public class SendMessageController {

    @Resource
    private IMessageProvider messageProvider;


    @GetMapping(value = "/sendMessage")
    public String sendMessage() {
        return messageProvider.send();
    }
}
