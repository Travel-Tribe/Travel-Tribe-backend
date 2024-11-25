package com.zerobase.travel.controller;

import com.zerobase.travel.common.response.ResponseMessage;
import com.zerobase.travel.dto.request.ReportingRequestDto;
import com.zerobase.travel.service.ReportingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ReportingController {

    private final ReportingService reportingService;
    
    @PostMapping("/posts/{postId}/reporting")
    public ResponseEntity<ResponseMessage<Void>> reportingUser(
        @RequestHeader("X-User-Id") long userId,
        @PathVariable long postId,
        @RequestBody ReportingRequestDto.ReportUser request
    ) {

        reportingService.reportingUser(request, postId, userId);
        return ResponseEntity.ok(ResponseMessage.success());
    }

}
