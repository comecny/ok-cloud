package com.example.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 参数校验拦截器
 */
@Component
public class ParamFilter implements GlobalFilter, Ordered {

    private static final Logger logger = LoggerFactory.getLogger(ParamFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        /**
         * 网关参数校验，初步想法 -> osgi
         */
        logger.info("param filter started ");
        return chain.filter(exchange);

    }

    @Override
    public int getOrder() {
        return 2;
    }
}
