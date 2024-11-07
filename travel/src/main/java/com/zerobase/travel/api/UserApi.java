package com.zerobase.travel.api;

import com.zerobase.travel.api.UserApiRequestDto.UpdateUserRating;
import com.zerobase.travel.common.response.ResponseMessage;
import com.zerobase.travel.post.entity.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserApi {

    private final UserClient userFeignClient;

    public ResponseMessage<Void> updateUserRating(long userId, double rating) {
        return userFeignClient.getUserInfoByEmail(userId, new UpdateUserRating(rating));
    }


}
