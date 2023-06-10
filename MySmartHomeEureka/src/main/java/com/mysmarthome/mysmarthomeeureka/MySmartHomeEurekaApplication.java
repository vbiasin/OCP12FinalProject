package com.mysmarthome.mysmarthomeeureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MySmartHomeEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MySmartHomeEurekaApplication.class, args);
    }

}
