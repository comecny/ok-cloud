package com.redis.config;

import io.lettuce.core.event.DefaultEventPublisherOptions;
import io.lettuce.core.metrics.DefaultCommandLatencyCollector;
import io.lettuce.core.metrics.DefaultCommandLatencyCollectorOptions;
import io.lettuce.core.resource.DefaultClientResources;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * redis 连接池监控配置
 */
@AutoConfigureBefore(RedisAutoConfiguration.class)
@Configuration(proxyBeanMethods = false)
public class LettuceConfiguration {
    /**
     * 每 10s 采集一次命令统计
     * @return
     */
    @Bean
    public DefaultClientResources getDefaultClientResources() {
        return DefaultClientResources.builder()
                .commandLatencyRecorder(
                        new DefaultCommandLatencyCollector(
                                //开启 CommandLatency 事件采集，并且配置每次采集后都清空数据
                                DefaultCommandLatencyCollectorOptions.builder().enable().resetLatenciesAfterEvent(true).build()
                        )
                )
                .commandLatencyPublisherOptions(
                        //每 10s 采集一次命令统计
                        DefaultEventPublisherOptions.builder().eventEmitInterval(Duration.ofSeconds(10)).build()
                ).build();
    }
}
