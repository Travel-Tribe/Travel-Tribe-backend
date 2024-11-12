package com.zerobase.travel.post.type;

import lombok.Getter;

@Getter
public enum LimitSmoke {
    SMOKER("흡연"),
    NON_SMOKER("비흡연"),
    UNRELATED("무관");

    private final String smoke;

    LimitSmoke(String smoke) {
        this.smoke = smoke;
    }

    public String value() {
        return smoke;
    }

}
