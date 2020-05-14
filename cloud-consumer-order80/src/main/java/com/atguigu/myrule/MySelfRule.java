package com.atguigu.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName MySelfRule
 * @Description TODO
 * @Author xsh
 * @Date 2020-04-25 17:14
 * @Version 1.0
 **/
@Configuration
public class MySelfRule {

    @Bean
    public IRule myRule() {
        // 随机
        return new RandomRule();
    }
}
