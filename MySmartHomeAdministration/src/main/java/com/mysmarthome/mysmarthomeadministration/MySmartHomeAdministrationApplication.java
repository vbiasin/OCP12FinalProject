package com.mysmarthome.mysmarthomeadministration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
@SpringBootApplication
@RefreshScope
@EnableEurekaClient

public class MySmartHomeAdministrationApplication {

    public static void main(String[] args) throws Exception {

        SpringApplication.run(MySmartHomeAdministrationApplication.class, args);
    }
}
