package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName PaymentService
 * @Description TODO
 * @Author xsh
 * @Date 2020-04-24 14:47
 * @Version 1.0
 **/
public interface PaymentService {

    int create(Payment payment);

    Payment getPaymentById(@Param(value = "id") Long id);

}
