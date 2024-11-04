package com.zerobase.user.controller;

import static org.springframework.http.HttpStatus.OK;

import com.zerobase.user.dto.response.ResponseMessage;
import com.zerobase.user.dto.response.UserInfoResponseDTO;
import com.zerobase.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internal/api/v1/users")
@RequiredArgsConstructor
public class InternalUserController {

    private final UserService userService;

    // 프로필 조회
    @GetMapping("/{userEmail}")
    public ResponseEntity<UserInfoResponseDTO> getUserEmail(@PathVariable String userEmail) {
        UserInfoResponseDTO byUserWithEmail = userService.findByUserWithEmail(userEmail);
        return ResponseEntity.status(OK).body(byUserWithEmail);
    }
}
