package com.zerobase.travel.post.type;

public enum LimitSex {
    MALE("남"),
    FEMALE("여"),
    UNRELATED("무관");

    private final String sex;

    LimitSex(String sex) {
        this.sex = sex;
    }

    public String value() {
        return sex;
    }

}
