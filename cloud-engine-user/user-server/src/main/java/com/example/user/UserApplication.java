package com.example.user;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.ImportResource;

@Log4j2
@ComponentScans(value = {
        @ComponentScan(value = "com.example.user.*")}
)
@EnableFeignClients
@ImportResource("classpath:/META-INF/cloud-config.xml")
@SpringBootApplication
public class UserApplication {

   public static ApplicationContext applicationContext ;

    public static void main(String[] args) {
        applicationContext =  SpringApplication.run(UserApplication.class, args);
        log.info("UserApplication success ! ");
    }
}
