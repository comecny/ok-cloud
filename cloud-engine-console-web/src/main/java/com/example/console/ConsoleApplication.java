package com.example.console;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@Log4j2
@SpringBootApplication
public class ConsoleApplication {
   public static ApplicationContext applicationContext;
    public static void main(String[] args) {
        applicationContext =  SpringApplication.run(ConsoleApplication.class, args);
        log.info("ConsoleApplication success ! ");
    }
}