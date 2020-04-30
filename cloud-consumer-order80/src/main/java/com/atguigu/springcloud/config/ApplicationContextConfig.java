package com.atguigu.springcloud.config;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {
    @Bean

    /**
     *      80项目注掉@LoadBalanced
     *      注释掉,毕竟我们要用自己写的
     */
    // @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
