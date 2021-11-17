package com.example.remote.openfeign.handler;

import com.example.remote.factory.RemoteFactory;
import org.springframework.stereotype.Component;

/**
 * fegin中只提供获取接口实例 ，深度定制feign的执行器不好写 暂时不支持了
 */
@Component
public class FeignHandler extends RemoteFactory {

    @Override
    public <T> T instanceService(Class<T> clazz){
        try {
           return instance(clazz);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    };

}
