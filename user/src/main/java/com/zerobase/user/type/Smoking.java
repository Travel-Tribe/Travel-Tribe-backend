package com.zerobase.user.type;

import lombok.Getter;

@Getter
public enum Smoking {
    YES("흡연자"), NO("비흡연자");

    private final String smoke;

    Smoking(String smoke) {
        this.smoke = smoke;
    }
}
