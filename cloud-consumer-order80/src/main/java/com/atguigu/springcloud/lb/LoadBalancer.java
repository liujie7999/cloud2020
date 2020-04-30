package com.atguigu.springcloud.lb;
/**
 *      80项目增加一个接口和一个实现类
 */

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBalancer {

    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}

