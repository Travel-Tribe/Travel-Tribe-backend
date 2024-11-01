package com.zerobase.user.type;

public enum Role {
    ROLE_USER("사용자"),
    ROLE_ANONYMOUS("익명"),
    ROLE_ADMIN("관리자");

    private final String role;

    Role(String role) {
        this.role = role;
    }

    public String value() {
        return role;
    }

    // role 문자열을 Role enum으로 변환하는 메서드
    public static Role fromString(String roleString) {
        for (Role role : Role.values()) {
            if (role.name().equalsIgnoreCase(roleString)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid role: " + roleString);
    }
}

