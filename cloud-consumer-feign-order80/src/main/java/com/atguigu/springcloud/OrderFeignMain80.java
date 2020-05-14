package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ClassName OrderFeignMain80
 * @Description TODO
 * @Author xsh
 * @Date 2020-04-27 9:52
 * @Version 1.0
 **/
@SpringBootApplication
@EnableFeignClients
public class OrderFeignMain80 {

    public static void main(String[] args) {
        SpringApplication.run(OrderFeignMain80.class,args);
    }



}
