package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName PaymentController
 * @Description TODO
 * @Author xsh
 * @Date 2020-04-27 11:51
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "/payment/hystrix")
@Slf4j(topic = "PaymentController")
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/ok/{id}")
    public String paymentInfo_OK(@PathVariable(value = "id") Integer id) {
        String result = paymentService.paymentInfo_OK(id);
        log.info("result"+result);
        return result;
    }

    @GetMapping(value = "/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable(value = "id") Integer id) {
        String result = paymentService.paymentInfo_TimeOut(id);
        log.info("result"+result);
        return result;
    }

    //服务熔断
    @GetMapping("/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        String result = paymentService.paymentCircuitBreaker(id);
        log.info("****result："+result);
        return result;
    }


}
