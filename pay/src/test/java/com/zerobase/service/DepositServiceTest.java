package com.zerobase.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.zerobase.entity.DepositEntity;
import com.zerobase.model.DepositDto;
import com.zerobase.model.type.DepositStatus;
import com.zerobase.repository.DepositRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class DepositServiceTest {

    @Mock
    private DepositRepository depositRepository;

    @InjectMocks
    private DepositService depositService;

    private static final Long POST_ID = 1L;
    private static final Long PARTICIPATION_ID = 2L;
    private static final String USER_ID = "testUser";

    @Test
    void createDepositOrder_ShouldReturnDepositDto() {
        // Given :
        DepositEntity depositEntity = DepositEntity.builder()
            .postId(POST_ID)
            .participationId(PARTICIPATION_ID)
            .userId(USER_ID)
            .depositStatus(DepositStatus.BEFORE_PAYMENT)
            .build();

        when(depositRepository.save(any(DepositEntity.class))).thenReturn(
            depositEntity);

        // When
        DepositDto result = depositService.createDepositOrder(POST_ID,
            PARTICIPATION_ID, USER_ID);

        // Then
        assertNotNull(result);
        assertEquals(POST_ID, result.getPostId());
        assertEquals(PARTICIPATION_ID, result.getParticipationId());
        assertEquals(USER_ID, result.getUserId());
        assertEquals(DepositStatus.BEFORE_PAYMENT, result.getDepositStatus());
        verify(depositRepository, times(1)).save(any(DepositEntity.class));
    }

}