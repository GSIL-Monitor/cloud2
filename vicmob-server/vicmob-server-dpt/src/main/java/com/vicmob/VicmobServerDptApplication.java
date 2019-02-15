package com.vicmob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * 炫销宝服务启动类
 * @author ziv
 * @date 2019-01-24
 */
@SpringBootApplication
@EnableDiscoveryClient
@RefreshScope
public class VicmobServerDptApplication {

    public static void main(String[] args) {
        SpringApplication.run(VicmobServerDptApplication.class, args);
    }

}

