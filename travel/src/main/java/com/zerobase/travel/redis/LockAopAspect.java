package com.zerobase.travel.redis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Aspect
@Component
@Slf4j
public class LockAopAspect {
    private final LockService lockService;

    @Around("@annotation(com.zerobase.travel.redis.PostLock) && args(postId)")
    public Object aroundMethod(
        ProceedingJoinPoint pjp, long postId) throws Throwable {
        // lock 취득시도
        lockService.lock(postId);

        try{
            return pjp.proceed();
        }finally{
            // lock 해체
            lockService.unlock(postId);

        }
    }

}


