package com.zerobase.travel.service;

import com.zerobase.travel.dto.request.ReportingRequestDto.ReportUser;
import com.zerobase.travel.entity.ReportingEntity;
import com.zerobase.travel.exception.BizException;
import com.zerobase.travel.exception.errorcode.ReportingErrorCode;
import com.zerobase.travel.exception.errorcode.VoteErrorCode;
import com.zerobase.travel.repository.ParticipationRepository;
import com.zerobase.travel.repository.ReportingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportingService {

    private final ReportingRepository reportingRepository;

    private final ParticipationRepository participationRepository;

    public void reportingUser(ReportUser request, long postId, long senderUserId) {

        validationReportingUser(request, postId, senderUserId);

        ReportingEntity reportingEntity = ReportingEntity.builder()
            .postId(postId)
            .senderUserId(senderUserId)
            .receiverUserId(request.getReceiverUserId())
            .comment(request.getComment())
            .build();

        reportingRepository.save(reportingEntity);
    }

    private void validationReportingUser(ReportUser request, long postId, long senderUserId) {
        //신고한 사람, 신고 당한 사림이 해당 여행에 참여 하였는지
        if (participationRepository.findByPostEntityPostIdAndUserId(postId, String.valueOf(request.getReceiverUserId())).isEmpty()) {
            throw new BizException(VoteErrorCode.UNJOIN_TRAVEL);
        }

        if (participationRepository.findByPostEntityPostIdAndUserId(postId, String.valueOf(senderUserId)).isEmpty()) {
            throw new BizException(VoteErrorCode.UNJOIN_TRAVEL);
        }

        //신고 이력이 있는지
        if (reportingRepository.existsByPostIdAndSenderUserIdAndReceiverUserId(postId, senderUserId, request.getReceiverUserId())) {
            throw new BizException(ReportingErrorCode.ALREADY_REPORTING);
        }
        
    }

}
