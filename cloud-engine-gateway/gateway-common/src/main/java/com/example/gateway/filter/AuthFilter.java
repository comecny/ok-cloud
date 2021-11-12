package com.example.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;

@Component
public class AuthFilter implements GlobalFilter, Ordered {

    private static final Logger logger = LoggerFactory.getLogger(AuthFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        /**
         * 1、判断当前接口是否需要认证
         * 2、获取Jsessionid 判断是否符合
         * 3、不符合异常抛出 ，符合则放行交给下一个拦截器
         */
        logger.info("auth filter started");
        Flux<DataBuffer> body = exchange.getRequest().getBody();
        body.subscribe(buffer -> {
            byte[] bytes = new byte[buffer.readableByteCount()];
            buffer.read(bytes);
            DataBufferUtils.release(buffer);
            try {
                String bodyString = new String(bytes, "utf-8");
                logger.info("bodyString",bodyString);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        });
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 1;
    }

}
