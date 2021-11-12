package test.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

@EnableFeignClients(basePackages = "com.example.api.service.auth")
@ImportResource("classpath:/META-INF/cloud-config.xml")
@SpringBootTest
public class UserTestApplication {

   public static ApplicationContext applicationContext ;

    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "DEV");
        applicationContext =  SpringApplication.run(UserTestApplication.class, args);
    }
}
