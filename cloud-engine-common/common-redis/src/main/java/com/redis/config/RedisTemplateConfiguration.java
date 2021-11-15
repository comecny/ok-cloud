package com.redis.config;

import com.redis.base.Flag;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import static com.redis.base.Flag.SEPARATOR;

/**
 * redisconf配置类
 *
 */
@Configuration
public class RedisTemplateConfiguration implements ApplicationContextAware , InitializingBean {

    private ApplicationContext applicationContext ;

    public RedisTemplate<Object, Object> redisTemplateUpdate(RedisTemplate<Object, Object> redisTemplate) {
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
        return redisTemplate;
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
