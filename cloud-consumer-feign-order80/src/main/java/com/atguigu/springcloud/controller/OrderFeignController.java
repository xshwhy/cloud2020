package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName OrderFeignController
 * @Description TODO
 * @Author xsh
 * @Date 2020-04-27 10:01
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "/consumer")
@Slf4j(topic = "OrderFeignController")
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable(value = "id") Long id) {

        return paymentFeignService.getPaymentById(id);

    }

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout() {
        // openfeign-ribbon 客户端一般默认1秒
      return paymentFeignService.paymentFeignTimeout();
    }

}
