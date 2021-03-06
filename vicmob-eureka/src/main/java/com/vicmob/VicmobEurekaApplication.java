package com.vicmob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 服务注册中心启动类
 * @author ziv
 * @date 2019-01-24
 */
@SpringBootApplication
@EnableEurekaServer
public class VicmobEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(VicmobEurekaApplication.class, args);
    }

}

