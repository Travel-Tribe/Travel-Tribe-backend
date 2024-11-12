package com.zerobase.travel.api;

import com.zerobase.travel.api.UserApiRequestDto.UpdateUserRating;
import com.zerobase.travel.common.response.ResponseMessage;
import com.zerobase.travel.post.dto.response.UserInfoResponseDTO;
import com.zerobase.travel.post.entity.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserApi {

    private final UserClient userFeignClient;

    public ResponseMessage<Void> updateUserRating(long userId, double rating) {
        return userFeignClient.updateUserRating(userId, new UpdateUserRating(rating));
    }

    public ResponseEntity<ResponseMessage<UserInfoResponseDTO>> getUserInfoByUserEmail(String userEmail) {
        return userFeignClient.getUserInfoByUserEmail(userEmail);
    }


}
