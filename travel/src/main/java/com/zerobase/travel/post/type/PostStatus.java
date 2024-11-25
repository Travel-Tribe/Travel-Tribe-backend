package com.zerobase.travel.post.type;

import lombok.Getter;

@Getter
public enum PostStatus {
    RECRUITING("모집중"),
    RECRUITMENT_COMPLETED("모집완료"),
    VOTING("투표중"),
    DELETED("삭제완료"),
    PAYMENT_PENDING("결제대기중");

    private final String postStatus;

    PostStatus(String postStatus) {
        this.postStatus = postStatus;
    }

    public String value() {
        return postStatus;
    }
}
