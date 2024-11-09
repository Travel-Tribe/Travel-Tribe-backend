package com.zerobase.user.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMbtiChangedEvent {
    private Long userId;
    private String newMbti;
}
