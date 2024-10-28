package com.zerobase.user.controller;

import com.zerobase.user.dto.response.ResponseResult;
import com.zerobase.user.dto.response.ServiceResult;
import com.zerobase.user.service.ReissueService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ReissueController {

    private final ReissueService reissueService;

    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(HttpServletRequest request, HttpServletResponse response) {
        ServiceResult serviceResult = reissueService.reissue(request, response);
        return ResponseResult.result(serviceResult);
    }
}
