package com.atguigu.springcloud.controller;

import cn.hutool.core.util.RandomUtil;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName PaymentController
 * @Description TODO
 * @Author xsh
 * @Date 2020-04-24 14:52
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "/payment")
@Slf4j(topic = "PaymentController")
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;



    @PostMapping(value = "/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("=========插入结果:" + result);

        if (result > 0) {
            return new CommonResult(200, "插入数据库成功,serverPort:" + serverPort, result);
        }
        return new CommonResult(444, "插入数据库失败", null);
    }

    @GetMapping(value = "/get/{id}")
    public CommonResult getPaymentById(@PathVariable(value = "id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        if (StringUtils.isEmpty(payment)) {
            return new CommonResult(444, "没有对应记录,查询ID" + id, null);
        }
        return new CommonResult(200, "查询成功,serverPort:" + serverPort, payment);
    }

    @GetMapping(value = "/info")
    public String getPaymentById() {
        return "1111";
    }

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping(value = "/discovery")
    public Map<String, Object> discovery() {
        // 获取服务清单列表
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("*****service:" + service);
        }
        // 一个微服务下所有实例
        List<ServiceInstance> instances1 = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances1) {
            log.info("*****instance:" + instance.getServiceId()
                    + "\t" + instance.getHost() + "\t" + instance.getPort()
                    + "\t" + instance.getUri());
        }

        Map<String, Object> map = new HashMap<>();
        services.forEach(serviceId -> {
            List<ServiceInstance> instances = discoveryClient.getInstances(serviceId);
            map.put(serviceId, instances);
        });
        log.info("map" + map);
        return map;
//        return this.discoveryClient;

    }

    @GetMapping(value = "/lb")
    public String getPaymentLB() {
        return  serverPort;
    }

    @GetMapping(value = "/feign/timeout")
    public String paymentFeignTimeout() {

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }

    @GetMapping(value = "/getRandom")
    public Integer getRandom() {
//        int i = RandomUtils.nextInt();
        int i = RandomUtil.randomInt(1,2);
        return i;
    }

    @GetMapping(value = "/zipkin")
    public String paymentZipkin() {

        return "哈哈哈 zipkin";
    }


}
