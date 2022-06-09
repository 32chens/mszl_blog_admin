package com.chenlf.blog.common.aop;

import com.alibaba.fastjson.JSON;
import com.chenlf.blog.vo.ResponseStates;
import com.chenlf.blog.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.Duration;

/**
 * 
 * @author ChenLF
 * @date 2022/06/09 22:18
 **/
@Component
@Aspect
@Slf4j
public class CacheAspect {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Pointcut("@annotation(com.chenlf.blog.common.aop.CacheAnnotation)")
    public void pt(){}

    @Around("pt()")
    public Object around(ProceedingJoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();
        String className = joinPoint.getClass().getSimpleName();
        String methodName = signature.getName();

        String params = "";
        Object[] args = joinPoint.getArgs();
        Class[] paramTypes = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            if (args[i] != null){
                params += args[i];
                paramTypes[i] = args[i].getClass();
            }else{
                paramTypes[i] = null;
            }
        }
        try {
//        Method method = ((MethodSignature) signature).getMethod();
            Method method = signature.getDeclaringType().getMethod(methodName, paramTypes);
            CacheAnnotation cacheAnnotation = method.getAnnotation(CacheAnnotation.class);
            long expire = cacheAnnotation.expire();
            String name = cacheAnnotation.name();
            String redisKey = name + "::" + className+"::"+methodName+"::"+params;
            String redisValue = redisTemplate.opsForValue().get(redisKey);
            if (StringUtils.isNotEmpty(redisValue)){
                log.info("走了缓存~~~,{},{}",className,methodName);
                return JSON.parseObject(redisValue, Result.class);
            }
            Object proceed = joinPoint.proceed();
            redisTemplate.opsForValue().set(redisKey,JSON.toJSONString(proceed), Duration.ofMillis(expire));
            log.info("首次执行，存入缓存~~~ {},{}",className,methodName);
            return proceed;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return Result.fail(ResponseStates.SYSTEM_EXCEPTION);
    }

}
