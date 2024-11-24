package com.zerobase.travel.redis;

import com.zerobase.travel.exception.BizException;
import com.zerobase.travel.exception.errorcode.ParticipationErrorCode;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class LockService {
    private final RedissonClient redisson;
    private final int RETRY_COUNT = 5;
    private final int RETRY_TIME = 500;
    private final int RETENTION_TIME = 5000;



    public void lock(long postId) {
        RLock lock = redisson.getLock(getLockName(postId));

        try{
            for (int trialTime = 0; trialTime < RETRY_COUNT; trialTime++) {
                log.info("Trying Lock for PostId : {} for {} try", postId, trialTime);
                boolean isLock = lock.tryLock(RETRY_TIME,RETENTION_TIME, TimeUnit.MILLISECONDS);
                if(isLock) {
                    log.info("Lock success to get for PostId : {}", postId);
                    return;
                }
            }
            log.info("Lock failed to get for PostId : {}", postId);
            throw new BizException(ParticipationErrorCode.PARTICIPATION_CONTENTION);
        }catch (Exception e){
            log.error("Redis Lock Exception", e);
            throw new BizException(ParticipationErrorCode.LOCK_ACQUISITION_ERROR);
        }
    }

    public void unlock(long postId) {
        RLock lock = redisson.getLock(getLockName(postId));
        log.info("Trying Unlock for PostId : {}", postId);
        lock.unlock();
    }

    private static String getLockName(long lockName) {
        return "PostId :" + lockName;
    }

}
