package com.zerobase.travel.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.zerobase.travel.dto.request.ReportingRequestDto;
import com.zerobase.travel.entity.ReportingEntity;
import com.zerobase.travel.exception.BizException;
import com.zerobase.travel.exception.errorcode.ReportingErrorCode;
import com.zerobase.travel.repository.ReportingRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ReportingServiceTest {

    @Mock
    private ReportingRepository reportingRepository;

    @InjectMocks
    private ReportingService reportingService;

    @Test
    void reportingUser() {

        //given
        long postId = 1L;
        long senderUserId = 2L;

        ReportingRequestDto.ReportUser reportUser = ReportingRequestDto.ReportUser.builder()
            .receiverUserId(3L)
            .comment("이사람 이상해요.")
            .build();

        ArgumentCaptor<ReportingEntity> captor = ArgumentCaptor.forClass(ReportingEntity.class);

        //when
        reportingService.reportingUser(reportUser, postId, senderUserId);

        //then
        verify(reportingRepository, times(1)).save(captor.capture());
        assertEquals(1L, captor.getValue().getPostId());
        assertEquals(2L, captor.getValue().getSenderUserId());
        assertEquals(3L, captor.getValue().getReceiverUserId());
        assertEquals("이사람 이상해요.", captor.getValue().getComment());

    }


    @Test
    @DisplayName("유저 신고 실패 - 이미 평점을 주었음")
    void reportingUserFailBy이미_평점을_주었음() {

        //given
        long postId = 1L;
        long senderUserId = 2L;

        ReportingRequestDto.ReportUser reportUser = ReportingRequestDto.ReportUser.builder()
            .receiverUserId(1L)
            .build();

        //when
        given(reportingRepository.existsByPostIdAndSenderUserIdAndReceiverUserId(anyLong(), anyLong(), anyLong()))
            .willReturn(true);

        BizException ex = assertThrows(BizException.class, () -> reportingService.reportingUser(reportUser, postId, senderUserId));

        //then
        assertEquals(ReportingErrorCode.ALREADY_REPORTING, ex.getErrorCode());
    }
}