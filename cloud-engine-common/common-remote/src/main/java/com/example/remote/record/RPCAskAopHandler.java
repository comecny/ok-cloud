package com.example.remote.record;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * rpc应答日志处理器
 */
@Component
@Aspect
@Order(-1)
public class RPCAskAopHandler {

    private static final Logger logger = LoggerFactory.getLogger(RPCAskAopHandler.class);

    @Pointcut("@annotation(com.example.remote.record.annotation.RPCAsk)")
    public void pointCut(){}

    @Around(value = "pointCut()", argNames = "pjp,joinPoint")
    public Object around(ProceedingJoinPoint pjp, JoinPoint joinPoint){
        logger.info("rpc recv : ");
        return joinPoint;
    }

    @AfterReturning(value = "pointcut()")
    public void afterReturning(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        logger.info("rpc send : ");
    }
}
