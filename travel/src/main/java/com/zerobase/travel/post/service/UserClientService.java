package com.zerobase.travel.post.service;

import static com.zerobase.travel.common.response.ResponseMessage.Result.SUCCESS;
import static com.zerobase.travel.exception.errorcode.BasicErrorCode.USER_INFO_CALL_ERROR;

import com.zerobase.travel.common.response.ResponseMessage;
import com.zerobase.travel.exception.BizException;
import com.zerobase.travel.post.dto.response.UserInfoResponseDTO;
import com.zerobase.travel.post.entity.UserClient;
import com.zerobase.travel.post.type.MBTI;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserClientService {

    private final UserClient userClient;

    public UserInfoResponseDTO getUserInfo(String userEmail) {
        ResponseMessage<UserInfoResponseDTO> response = userClient.getUserInfoByEmail(userEmail);
        if (response.getResult().equals(SUCCESS.toString())) {
            return response.getData();
        } else {
            // 에러 처리 로직 추가
            throw new BizException(USER_INFO_CALL_ERROR);
        }
    }

    public MBTI getUserMbti(Long userId){
        ResponseMessage<MBTI> response = userClient.getUserMbti(userId);
        if (response.getResult().equals(SUCCESS.toString())) {
            return response.getData();
        }else{
            throw new BizException(USER_INFO_CALL_ERROR);
        }
    }
}
