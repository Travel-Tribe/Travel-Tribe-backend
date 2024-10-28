package com.zerobase.user.type;

public enum UserStatus {
    ACTIVE("활동중"),
    INACTIVE("활동정지"),
    DEACTIVATED("탈퇴");

    private final String description;

    UserStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

