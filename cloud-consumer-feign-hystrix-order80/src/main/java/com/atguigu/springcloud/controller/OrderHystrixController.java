package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName PaymentHystrixController
 * @Description TODO
 * @Author xsh
 * @Date 2020-04-27 14:02
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "/consumer")
@Slf4j(topic = "PaymentHystrixController")
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping(value = "/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable(value = "id") Integer id){
        String result = paymentHystrixService.paymentInfo_OK(1);

        return result;
    }

    /*@HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod",commandProperties = {
            //设置这个线程的超时时间是3s，3s内是正常的业务逻辑，超过3s调用fallbackMethod指定的方法进行处理
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")
    })*/
    @HystrixCommand
    @GetMapping(value = "/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable(value = "id") Integer id) {
//        int age =  10/0;
        String result = paymentHystrixService.paymentInfo_TimeOut(1);
        return result;
    }

    public String paymentTimeOutFallbackMethod(@PathVariable(value = "id") Integer id) {
        return "消费者,对方支付系统繁忙";
    }

    // 全局fallback方法
    public String payment_Global_FallbackMethod() {
        return "Global异常处理信息,请稍后再试";
    }


}
