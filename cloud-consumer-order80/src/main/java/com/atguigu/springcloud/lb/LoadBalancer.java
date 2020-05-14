package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @ClassName LoadBalance
 * @Description 模拟 ILoadBalancer 的接口，选择哪一个负载算法和机器
 * @Author xsh
 * @Date 2020-04-25 17:46
 * @Version 1.0
 **/
public interface LoadBalancer {
      //获取eureka上的活着的机器总数
      ServiceInstance instances(List<ServiceInstance> serviceInstances);


}
