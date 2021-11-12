package com.example.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

@EnableFeignClients(basePackages = "com.example.api.service.auth")
@ImportResource("classpath:/META-INF/cloud-config.xml")
@SpringBootApplication
public class UserApplication {

   public static ApplicationContext applicationContext ;

    public static void main(String[] args) {
        applicationContext =  SpringApplication.run(UserApplication.class, args);
    }
}
