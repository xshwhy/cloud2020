package com.atguigu.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



/**
 * @ClassName FeignConfig
 * @Description TODO
 * @Author xsh
 * @Date 2020-04-27 11:05
 * @Version 1.0
 **/
@Configuration
public class FeignConfig {

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

}
