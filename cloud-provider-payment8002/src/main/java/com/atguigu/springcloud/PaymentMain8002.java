package com.atguigu.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ClassName PaymentMain8001
 * @Description TODO
 * @Author xsh
 * @Date 2020-04-24 14:16
 * @Version 1.0
 **/
@SpringBootApplication
@MapperScan(value = "com.atguigu.springcloud.dao")
@EnableEurekaClient
public class PaymentMain8002 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8002.class,args);
    }
}
