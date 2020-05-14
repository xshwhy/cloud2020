package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName PaymentDao
 * @Description TODO
 * @Author xsh
 * @Date 2020-04-24 14:39
 * @Version 1.0
 **/
@Mapper
public interface PaymentDao {

    int create(Payment payment);

    Payment getPaymentById(@Param(value = "id") Long id);
}
