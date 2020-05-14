package com.atguigu.springcloud.controller;


import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @ClassName OrderController
 * @Description TODO
 * @Author xsh
 * @Date 2020-04-24 16:02
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "/consumer")
@Slf4j(topic = "OrderController")
public class OrderController {

    //    public static final String PAYMENT_URL = "http://localhost:8001";
    //通过在eureka上注册过的微服务名称调用
    private static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private LoadBalancer loadBalancer;

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private DiscoveryClient discoveryClient;


    @PostMapping("/payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }


    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable(value = "id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    }

    @GetMapping(value = "/payment/getForEntity/{id}")
    public CommonResult<Payment> getForEntity(@PathVariable(value = "id") Long id) {

        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);

        if (entity.getStatusCode().is2xxSuccessful()) {
            log.info(entity.getStatusCode() + "\t" + entity.getHeaders());
            return entity.getBody();
        }
        return new CommonResult<>(444, "操作失败");


    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB() {

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");

        if (instances == null || instances.size() <= 0) {
            return  null;
        }
        ServiceInstance serviceInstance = loadBalancer.instances(instances);
        URI uri = serviceInstance.getUri();
        return restTemplate.getForObject(uri+"/payment/lb",String.class);
    }

    @GetMapping(value = "/payment/zipkin")
    public String paymentZipkin() {
        String result = restTemplate.getForObject("http://localhost:8001" + "/payment/zipkin", String.class);
        return result;
    }


}
