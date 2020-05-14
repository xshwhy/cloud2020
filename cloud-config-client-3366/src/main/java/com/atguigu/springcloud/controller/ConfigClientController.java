package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ConfigClientController
 * @Description TODO
 * @Author xsh
 * @Date 2020-05-07 14:16
 * @Version 1.0
 **/
@RestController
@RefreshScope
public class ConfigClientController {

    @Value("${server.port}")
    private String serverPort;

    @Value("${config.info}")
    private String configInfo;  //要访问的3344上的信息

    @GetMapping("/configInfo")
    public String getConfigInfo(){

        return "serverPort"+serverPort+"\t"+"configInfo"+configInfo;
    }
}
