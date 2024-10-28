package com.zerobase.user.controller;

import com.zerobase.user.dto.request.JoinDTO;
import com.zerobase.user.dto.response.ResponseResult;
import com.zerobase.user.dto.response.ServiceResult;
import com.zerobase.user.service.JoinService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JoinController {

    private final JoinService joinService;

    @PostMapping("/join")
    public ResponseEntity<?> joinProcess(@RequestBody @Valid JoinDTO joinDTO) {

        ServiceResult serviceResult = joinService.joinProcess(joinDTO);
        return ResponseResult.result(serviceResult);
    }
}