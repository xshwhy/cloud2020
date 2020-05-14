package com.atguigu.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @ClassName PaymentHystrixService
 * @Description TODO
 * @Author xsh
 * @Date 2020-04-27 13:57
 * @Version 1.0
 **/
@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT",fallback = PaymentFallbackService.class)
public interface PaymentHystrixService {

    @GetMapping(value = "/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable(value = "id") Integer id);

    @GetMapping(value = "/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable(value = "id") Integer id);

}
