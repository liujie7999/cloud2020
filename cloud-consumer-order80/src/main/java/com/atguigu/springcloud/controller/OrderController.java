package com.atguigu.springcloud.controller;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class OrderController {
    private static final String PAYMENT_URL = "http://CLOUD-PROVIDER-SERVICE";
    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        log.info("*******消费者启动创建订单*******");
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }

    @GetMapping("/consumer/payment/list")
    public CommonResult<Payment> listPayment(){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/list",CommonResult.class);
    }


    @GetMapping("/consumer/payment/getEntity/{id}")
    public CommonResult<Payment> getForEntity(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> forEntity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        if ((forEntity.getStatusCode().is2xxSuccessful())){
            return forEntity.getBody();
        }else {
            return new CommonResult<>(444,"调用失败");
        }
    }


    /**
     *      新增方法getPaymentLB()
     *      首先获取集群中的实例,然后判断是否为空,把获取到的list集群传给刚写的方法中获取到负载均衡获取到的当前实例
     *      然后获取到实例地址,最后restTemplate去调用服务就会用到我们之前写的负载均衡去调用
     */
    @Resource
    private LoadBalancer loadBalancer;

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping(value = "/consumer/payment/lb")
    public  String getPaymentLB(){
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if(instances==null||instances.size()<0){
            return null;
        }
        ServiceInstance instances1 = loadBalancer.instances(instances);
        URI uri = instances1.getUri();
        return restTemplate.getForObject(uri+"/payment/lb",String.class);
    }


}
