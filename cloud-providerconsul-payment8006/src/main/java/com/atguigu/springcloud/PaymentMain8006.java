package com.atguigu.springcloud;
/**
 *      consul安裝教程
 *      1.1 下载并解压到文件夹
 *      1.2 在路径下输入cmd进入命令行
 *      1.3 在命令行输入consul,如果出现下面一堆东西,则安装成功
 *      1.4 输入 consul agent -dev 启动 Consul
 *      1.5 在我们本地8500端口可以看见Consul自带的UI界面
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@SpringBootApplication
@EnableDiscoveryClient
public class PaymentMain8006 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8006.class,args);
    }
}
