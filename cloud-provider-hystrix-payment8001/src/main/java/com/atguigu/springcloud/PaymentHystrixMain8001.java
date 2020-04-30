package com.atguigu.springcloud;

/**
 * Hystrix
 *      是一个用于处理分布式系统的延迟和容错的开源库, 在分布式系统里,许多依赖不可避免的会调用失败，比如超时、异常等
 * Hystrix能够保证在一个依赖出问题的情况下，不会导致整体服务失败，避免级联故障,以提高分布式系统的弹性。
 * “    断路器”本身是一种开关装置,当某个服务单元发生故障之后，通过断路器的故障监控(类似熔断保险丝)
 * ，    向调用方返回一个符合预期的、可处理的备选响应(FallBack) ，而不是长时间的等待或者抛出调用方无法处理的异常，
 *      这样就保证了服务调用方的线程不会被长时间、不必要地占用,从而避免了故障在分布式系统中的蔓延,乃至雪崩。
 * *    但是停更了…,不影响先学一波,毕竟设计理论与思想最重要嘛,大家都是抄作业,像阿里的sentinel…会不会我到时候也自研一个哈哈~
 */

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@SpringBootApplication
@EnableCircuitBreaker
@EnableHystrix      //主启动开启Hystrix
public class PaymentHystrixMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentHystrixMain8001.class,args);
    }

    //主启动类添加代码
    /**
     * 此配置是为了服务监控而配置，与服务容错本身无关,SpringCloud升级后的坑
     * ServletRegistrationBean因为springboot的默认路径不是"/hystrix.stream"，
     * 只要在自己的项目里配置上下面的servlet就可以了
     */
    @Bean
    public ServletRegistrationBean getServlet(){
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }
}

