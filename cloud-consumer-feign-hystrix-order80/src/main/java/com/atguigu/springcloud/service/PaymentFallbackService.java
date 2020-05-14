package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @ClassName PaymentFallbackService
 * @Description TODO
 * @Author xsh
 * @Date 2020-04-27 15:08
 * @Version 1.0
 **/
@Component
public class PaymentFallbackService implements PaymentHystrixService {


    @Override
    public String paymentInfo_OK(Integer id) {
        return "PaymentFallbackService fall back-paymentInfo_OK";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "PaymentFallbackService fall back-paymentInfo_TimeOut";
    }
}
