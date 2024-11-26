package com.zerobase.travel.service;

import com.zerobase.travel.api.UserApi;
import com.zerobase.travel.common.response.ResponseMessage;
import com.zerobase.travel.dto.ParticipationDto;
import com.zerobase.travel.entity.ParticipationEntity;
import com.zerobase.travel.exception.BizException;
import com.zerobase.travel.exception.errorcode.ParticipationErrorCode;
import com.zerobase.travel.post.dto.response.UserInfoResponseDTO;
import com.zerobase.travel.post.entity.PostEntity;
import com.zerobase.travel.post.repository.PostRepository;
import com.zerobase.travel.post.type.Gender;
import com.zerobase.travel.post.type.LimitSex;
import com.zerobase.travel.post.type.LimitSmoke;
import com.zerobase.travel.post.type.PostStatus;
import com.zerobase.travel.post.type.Smoking;
import com.zerobase.travel.repository.ParticipationRepository;
import com.zerobase.travel.type.DepositStatus;
import com.zerobase.travel.type.ParticipationStatus;
import com.zerobase.travel.type.RatingStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ParticipationServiceTest {

    @InjectMocks
    private ParticipationService participationService;

    @Mock
    private ParticipationRepository participationRepository;

    @Mock
    private PostRepository postRepository;

    @Mock
    private UserApi userApi;

    private ParticipationEntity mockParticipationEntity;
    private PostEntity mockPostEntity;
    private UserInfoResponseDTO mockUserInfo;

    @BeforeEach
    void setUp() {
        mockPostEntity = PostEntity.builder()
            .postId(1L)
            .maxParticipants(5)
            .status(PostStatus.RECRUITING)
            .limitSmoke(LimitSmoke.SMOKER)
            .limitSex(LimitSex.MALE)
            .limitMinAge(10)
            .limitMaxAge(40)
            .build();

        mockParticipationEntity = ParticipationEntity.builder()
            .participationId(1L)
            .postEntity(mockPostEntity)
            .userId("testUser")
            .participationStatus(ParticipationStatus.JOIN_READY)
            .ratingStatus(RatingStatus.NOT_RATED)
            .depositStatus(DepositStatus.UNPAID)
            .build();

        mockUserInfo = UserInfoResponseDTO.builder()
            .id(1L)
            .email("test@example.com")
            .gender(Gender.MALE)
            .smoking(Smoking.YES)
            .birth(LocalDate.of(1990, 1, 1))
            .build();
    }

    @Test
    void validateParticipationApplicant_shouldThrowException_whenPostDoesNotExist() {
        // Given
        when(postRepository.findByPostId(anyLong())).thenReturn(Optional.empty());

        // When & Then
        BizException exception = assertThrows(BizException.class,
            () -> participationService.validateParticipationApplicant(1L, "userId", "email@example.com"));

        assertThat(exception.getErrorCode()).isEqualTo(ParticipationErrorCode.POST_NOT_EXISTING);
    }

    @Test
    void validateParticipationApplicant_shouldPass_whenAllConditionsAreMet() {


        // Given


        ResponseMessage<UserInfoResponseDTO > dto = new ResponseMessage<>();
        dto.setData(mockUserInfo);

        ResponseEntity responseEntity = ResponseEntity.ok(dto);



        when(postRepository.findByPostId(anyLong())).thenReturn(Optional.of(mockPostEntity));
        when(userApi.getUserInfoByUserEmail(anyString()))
            .thenReturn(responseEntity);
        when(participationRepository.findAllByUserIdAndParticipationStatusIn(anyString(), anyList()))
            .thenReturn(List.of());

        // When
        participationService.validateParticipationApplicant(1L, "userId", "email@example.com");

        // Then
        verify(postRepository).findByPostId(anyLong());
        verify(userApi).getUserInfoByUserEmail(anyString());
    }

    @Test
    void createParticipationReady_shouldCreateAndReturnParticipation() {
        // Given
        when(participationRepository.save(any(ParticipationEntity.class)))
            .thenReturn(mockParticipationEntity);

        // When
        ParticipationDto result = participationService.createParticipationReady(1L, "testUser");

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getUserId()).isEqualTo("testUser");
        verify(participationRepository).save(any(ParticipationEntity.class));
    }

    @Test
    void getParticipationByPostIdAndUserId_shouldThrowException_whenNotFound() {
        // Given
        when(participationRepository.findByPostEntityPostIdAndUserId(anyLong(), anyString()))
            .thenReturn(Optional.empty());

        // When & Then
        BizException exception = assertThrows(BizException.class,
            () -> participationService.getParticipationByPostIdAndUserId(1L, "userId"));

        assertThat(exception.getErrorCode()).isEqualTo(ParticipationErrorCode.PARTICIPATION_NOT_EXIST);
    }

    @Test
    void checkStatusParticipation_shouldThrowException_whenStatusDoesNotMatch() {
        // Given
        List<Enum<?>> expectedStatuses = List.of(ParticipationStatus.JOIN);

        // When & Then
        BizException exception = assertThrows(BizException.class,
            () -> participationService.checkStatusParticipation(mockParticipationEntity, expectedStatuses));

        assertThat(exception.getErrorCode()).isEqualTo(ParticipationErrorCode.PARTICIPATION_STATUS_ERROR);
    }

    @Test
    void saveParticipations_shouldSaveAllParticipationEntities() {
        // Given
        List<ParticipationEntity> participationEntities = List.of(mockParticipationEntity);

        // When
        participationService.saveParticipations(participationEntities);

        // Then
        verify(participationRepository).saveAll(participationEntities);
    }

    @Test
    void setDateToReturnDeposit_shouldSetReturnDate() {
        // Given
        LocalDate returnDate = LocalDate.now();

        // When
        participationService.setDateToReturnDeposit(mockParticipationEntity, returnDate);

        // Then
        assertThat(mockParticipationEntity.getDepositReturnDate()).isEqualTo(returnDate);
    }
}
