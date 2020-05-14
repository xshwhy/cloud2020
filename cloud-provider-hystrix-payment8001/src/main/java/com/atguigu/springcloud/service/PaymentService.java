package com.atguigu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName PaymentService
 * @Description TODO
 * @Author xsh
 * @Date 2020-04-27 11:45
 * @Version 1.0
 **/
@Service
public class PaymentService {

    /**
     * 正常访问,肯定ok
     * @param id
     * @return
     */
    public String paymentInfo_OK(Integer id) {

        return "线程池:"+Thread.currentThread().getName()+" payment_Ok,id:"+id+"\t"+"哈哈";
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
            //设置这个线程的超时时间是3s，3s内是正常的业务逻辑，超过3s调用fallbackMethod指定的方法进行处理
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    })
    public String paymentInfo_TimeOut(Integer id) {

        int timeNumber = 3000;
        try {
            TimeUnit.MILLISECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池:"+Thread.currentThread().getName()+" payment_TimeOut,id:"+id+"\t"+"哈哈"+"耗时秒"+timeNumber;
    }

    public String paymentInfo_TimeOutHandler(Integer id) {

        return "线程池:"+Thread.currentThread().getName()+" 系统繁忙稍后再试,id:"+id+"\t"+"降级";
    }

    //服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),   //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),  //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),    //时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),    //失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if(id < 0){
            throw new RuntimeException("******id 不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();  //UUID.randomUUID();

        return Thread.currentThread().getName()+"\t"+"调用成功，流水号："+serialNumber;
    }
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "id 不能负数，请稍后再试，o(╥﹏╥)o  id："+id;
    }



}
