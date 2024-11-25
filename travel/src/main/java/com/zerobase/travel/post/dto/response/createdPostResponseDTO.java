package com.zerobase.travel.post.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class createdPostResponseDTO {

    private long postId;
    private long participationId;
}
