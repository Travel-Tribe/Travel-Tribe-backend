package com.zerobase.user.type;

import lombok.Getter;

@Getter
public enum Gender {
    MALE("남자"), FEMALE("여자"), OTHER("무관");

    private final String gender;

    Gender(String gender) {
        this.gender = gender;
    }
}
