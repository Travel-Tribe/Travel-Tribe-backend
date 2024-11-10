package com.zerobase.travel.redis;

import com.zerobase.travel.communities.type.CustomException;
import com.zerobase.travel.communities.type.ErrorCode;
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

    public void lock(Long postId) {
        RLock lock = redisson.getLock(getLockName(postId));
        log.debug("Trying Lock for PostId : {}", postId);
        try{
            boolean isLock = lock.tryLock();
            if(!isLock){
                log.debug("Lock failed to get for PostId : {}", postId);
                throw new CustomException(ErrorCode.PARTICIPATION_LOCK);
            }
        }catch (Exception e){
            log.error("Redis Lock Exception", e);
        }

    }

    public void unlock(Long postId) {
        RLock lock = redisson.getLock(getLockName(postId));
        log.debug("Trying Unlock for PostId : {}", postId);
        lock.unlock();
    }

    private static String getLockName(Long lockName) {
        return "TravelPartyPostId :" + lockName;
    }

}
