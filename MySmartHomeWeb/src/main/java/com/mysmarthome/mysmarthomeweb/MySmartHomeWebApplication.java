package com.mysmarthome.mysmarthomeweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@RefreshScope
@EnableDiscoveryClient
@EnableFeignClients
@EnableJpaRepositories(basePackages = "com.mysmarthome.mysmarthomeweb.DAO")
@EntityScan(basePackages = "com.mysmarthome.mysmarthomeweb.Entites")
public class MySmartHomeWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(MySmartHomeWebApplication.class, args);
    }

}
