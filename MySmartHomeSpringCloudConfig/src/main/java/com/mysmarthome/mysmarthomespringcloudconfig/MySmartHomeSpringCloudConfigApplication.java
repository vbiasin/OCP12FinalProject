package com.mysmarthome.mysmarthomespringcloudconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@EnableConfigServer
public class MySmartHomeSpringCloudConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(MySmartHomeSpringCloudConfigApplication.class, args);
    }

}
