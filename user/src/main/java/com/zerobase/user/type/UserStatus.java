package com.zerobase.user.type;

import lombok.Getter;

@Getter
public enum UserStatus {
    ACTIVE("활동중"),
    INACTIVE("활동정지"),
    DEACTIVATED("탈퇴");

    private final String status;

    UserStatus(String status) {
        this.status = status;
    }

    public String getUserStatus() {
        return status;
    }
}

