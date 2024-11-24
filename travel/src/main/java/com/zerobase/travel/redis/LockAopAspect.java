package com.zerobase.travel.redis;

import com.zerobase.travel.exception.BizException;
import com.zerobase.travel.exception.errorcode.ParticipationErrorCode;
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

    @Around("@annotation(com.zerobase.travel.redis.PostLock)")
    public Object aroundMethod(
        ProceedingJoinPoint pjp) throws Throwable {

        long postId;
        try {
            postId = (long) pjp.getArgs()[0];
        } catch (Exception e) {
            throw new BizException(ParticipationErrorCode.LOCK_PARAM_PROCESSING_ERROR);
        }

        // lock 취득시도
        log.info("Asepct interception triggered");
        lockService.lock(postId);

        try {
            return pjp.proceed();
        } finally {
            // lock 해체
            lockService.unlock(postId);
        }
    }

}


