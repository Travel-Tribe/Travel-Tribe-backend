package com.zerobase.travel.post.service;

import com.zerobase.travel.post.kafka.UserMbtiChangedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMbtiChangedEventListener {

    private final PostService postService;

    @KafkaListener(topics = "${app.kafka.topics.user-mbti-changed}", groupId = "travel-service-group")
    public void handleUserMbtiChangedEvent(UserMbtiChangedEvent event) {
        Long userId = event.getUserId();
        String newMbti = event.getNewMbti();

        // 게시글의 MBTI 업데이트
        postService.updatePostsMbti(userId, newMbti);
    }
}
