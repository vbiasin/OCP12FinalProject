package com.mysmarthome.mysmarthomeweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@RefreshScope
@EnableEurekaClient
@EnableFeignClients
public class MySmartHomeWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(MySmartHomeWebApplication.class, args);
    }

}
