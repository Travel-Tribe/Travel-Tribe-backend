package com.zerobase.travel.redis;

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

    public void lock(String postId) {
        RLock lock = redisson.getLock(getLockName(postId));
        log.debug("Trying Lock for PostId : {}", postId);
        try{
            boolean isLock = lock.tryLock();
            if(!isLock){
                log.debug("Lock failed to get for PostId : {}", postId);
            }
        }catch (Exception e){
            log.error("Redis Lock Exception", e);
        }

    }

    public void unlock(String postId) {
        RLock lock = redisson.getLock(getLockName(postId));
        log.debug("Trying Unlock for PostId : {}", postId);
        lock.unlock();
    }

    private static String getLockName(String lockName) {
        return "TravelPartyPostId :" + lockName;
    }

}
