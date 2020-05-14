package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @ClassName EurekaMain7001
 * @Description TODO
 * @Author xsh
 * @Date 2020-04-25 10:06
 * @Version 1.0
 **/
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@EnableEurekaServer
public class EurekaMain7001 {

    public static void main(String[] args) {
        SpringApplication.run(EurekaMain7001.class,args);
    }
}
