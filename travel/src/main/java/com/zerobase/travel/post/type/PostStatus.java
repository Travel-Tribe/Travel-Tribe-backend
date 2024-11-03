package com.zerobase.travel.post.type;

public enum PostStatus {
    POSTING("게시중"),
    VOTING("투표중"),
    DELETED("삭제완료");

    private final String postStatus;

    PostStatus(String postStatus) {
        this.postStatus = postStatus;
    }

    public String value() {
        return postStatus;
    }
}
