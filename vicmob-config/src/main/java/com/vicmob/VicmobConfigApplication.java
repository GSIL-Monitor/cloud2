package com.vicmob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 服务中心启动类
 * @author ziv
 * @date 2019-01-24
 */
@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
public class VicmobConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(VicmobConfigApplication.class, args);
    }

}

