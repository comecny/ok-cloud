package com.example.getway.bootstrap;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ImportResource("classpath:/META-INF/cloud-config.xml")
@SpringBootApplication
public class GatewayApplication {

    public static ApplicationContext applicationContext ;

    private static final Logger logger = LoggerFactory.getLogger(GatewayApplication.class);

    public static void main(String[] args) {
        applicationContext = SpringApplication.run(GatewayApplication.class, args);
        logger.info("started up Gateway ");
    }

}
