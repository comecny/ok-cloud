package com.example.remote.openfeign.handler;
import com.example.remote.factory.RemoteFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientBuilder;
import org.springframework.stereotype.Component;


@Component
public class FeignHandler extends RemoteFactory {

    public <T> T instanceService(Class<T> clazz){
        try {
            FeignClient annotation = clazz.getAnnotation(FeignClient.class);
            String value = annotation.value();
            return new FeignClientBuilder(applicationContext).forType(clazz, "CLOUD-AUTH-SERVER").build();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    };



}
