package com.zerobase.travel.service;

import com.zerobase.travel.dto.request.ReportingRequestDto.ReportUser;
import com.zerobase.travel.entity.ReportingEntity;
import com.zerobase.travel.repository.ReportingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportingService {

    private final ReportingRepository reportingRepository;

    public void reportingUser(ReportUser request, long postId, long senderUserId) {

        validationReportingUser();

        ReportingEntity reportingEntity = ReportingEntity.builder()
            .postId(postId)
            .senderUserId(senderUserId)
            .receiverUserId(request.getReceiverUserId())
            .comment(request.getComment())
            .build();

        reportingRepository.save(reportingEntity);
    }

    //TODO 김용민 validationRegisterRating 작성하기
    private void validationReportingUser() {
        //신고한 사람, 신고 당한 사림이 해당 여행에 참여 하였는지
        // join 테이블 생성시 작성

        //신고 이력이 있는지

        //여행이 종료 되었는지 ???
    }

}
