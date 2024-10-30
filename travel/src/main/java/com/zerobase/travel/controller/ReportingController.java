package com.zerobase.travel.controller;

import com.zerobase.travel.common.response.ResponseMessage;
import com.zerobase.travel.dto.request.ReportingRequestDto;
import com.zerobase.travel.service.ReportingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ReportingController {

    private final ReportingService reportingService;

    //TODO 김용민 Response 공통 생기면 반영하기
    @PostMapping("/posts/{postId}/reporting")
    public ResponseEntity<ResponseMessage<Void>> reportingUser(
        @PathVariable long postId,
        @RequestBody ReportingRequestDto.ReportUser request
    ) {

        //TODO 김용민 추후 스프링 시큐리티 개발시 authentic에서 가져오기
        long userId = 1L;

        reportingService.reportingUser(request, postId, userId);

        return ResponseEntity.ok(ResponseMessage.success());
    }

}
