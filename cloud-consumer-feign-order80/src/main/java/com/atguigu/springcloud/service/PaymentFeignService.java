package com.atguigu.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.atguigu.springcloud.entities.CommonResult;

/**
 * 这里只实现接口,并不实现方法,因为我们是去8001/8002集群去调用服务
 */
@FeignClient(value = "cloud-payment-service")
@Component
public interface PaymentFeignService {

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id);
}

