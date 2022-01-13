package com.redis.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * redisconf配置类
 *
 */
@Configuration
public class RedisTemplateConfiguration implements ApplicationContextAware , InitializingBean {

    private ApplicationContext applicationContext ;

    public void redisTemplateUpdate(RedisTemplate<Object, Object> redisTemplate) {
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        RedisTemplate<Object, Object> redisTemplate = (RedisTemplate<Object, Object>) applicationContext.getBean("redisTemplate");
        redisTemplateUpdate(redisTemplate);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
