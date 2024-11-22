package com.zerobase.travel.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.zerobase.travel.api.UserApi;
import com.zerobase.travel.communities.type.CustomException;
import com.zerobase.travel.communities.type.ErrorCode;
import com.zerobase.travel.dto.ParticipationDto;
import com.zerobase.travel.entity.ParticipationEntity;
import com.zerobase.travel.post.dto.response.UserInfoResponseDTO;
import com.zerobase.travel.post.entity.PostEntity;
import com.zerobase.travel.post.repository.PostRepository;
import com.zerobase.travel.post.type.Gender;
import com.zerobase.travel.post.type.Smoking;
import com.zerobase.travel.post.type.UserStatus;
import com.zerobase.travel.repository.ParticipationRepository;
import com.zerobase.travel.type.DepositStatus;
import com.zerobase.travel.type.ParticipationStatus;
import com.zerobase.travel.type.RatingStatus;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ParticipationServiceTest {

    @Mock
    private ParticipationRepository participationRepository;

    @Mock
    private PostRepository postRepository;

    @Mock
    private UserApi userApi;

    @InjectMocks
    private ParticipationService participationService;

    private static final String USER_ID = "testUser";
    private static final String USER_EMAIL = "testUser@gmail.com";
    private static final Long POST_ID = 1L;

    private UserInfoResponseDTO createMockUserInfoResponse() {
        return UserInfoResponseDTO.builder()
            .id(1L)
            .username("TestUser")
            .nickname("Tester")
            .phone("123-456-7890")
            .email(USER_EMAIL)
            .status(UserStatus.ACTIVE)
            .mbti(null) // replace with actual MBTI if applicable
            .smoking(Smoking.NO)
            .introduction("This is a test user.")
            .gender(Gender.MALE)
            .birth(LocalDate.of(1990, 1, 1))
            .ratingAvg(4.5)
            .build();
    }

    @Test
    void validateParticipationApplicant_shouldThrowException_whenUserParticipationLimitExceeded() {
        when(participationRepository.countByUserIdAndParticipationStatusIn(
            USER_ID, List.of(ParticipationStatus.JOIN, ParticipationStatus.JOIN_READY)))
            .thenReturn(2);

        CustomException exception = assertThrows(CustomException.class, () ->
            participationService.validateParticipationApplicant(POST_ID, USER_ID, USER_EMAIL));

        assertEquals(ErrorCode.USER_PARTICIPATION_LIMIT, exception.getErrorCode());
        verify(participationRepository).countByUserIdAndParticipationStatusIn(
            USER_ID, List.of(ParticipationStatus.JOIN, ParticipationStatus.JOIN_READY));
    }

    @Test
    void validateParticipationApplicant_shouldThrowException_whenPostParticipationLimitEquals() {
        when(participationRepository.countByUserIdAndParticipationStatusIn(
            USER_ID, List.of(ParticipationStatus.JOIN, ParticipationStatus.JOIN_READY)))
            .thenReturn(1);

        PostEntity postEntity = PostEntity.builder().maxParticipants(5).build();
        when(postRepository.findByPostId(POST_ID)).thenReturn(Optional.of(postEntity));

        when(participationRepository.countByPostEntityPostIdAndParticipationStatusIn(
            POST_ID, List.of(ParticipationStatus.JOIN, ParticipationStatus.JOIN_READY)))
            .thenReturn(5);

        CustomException exception = assertThrows(CustomException.class, () ->
            participationService.validateParticipationApplicant(POST_ID, USER_ID, USER_EMAIL));

        assertEquals(ErrorCode.POST_PARTICIPATION_LIMIT, exception.getErrorCode());
        verify(postRepository).findByPostId(POST_ID);
        verify(participationRepository).countByPostEntityPostIdAndParticipationStatusIn(
            POST_ID, List.of(ParticipationStatus.JOIN, ParticipationStatus.JOIN_READY));
    }
/*
    @Test
    void validateParticipationApplicant_shouldThrowException_whenUserProfileDoesNotMeetRequirements() {
        PostEntity postEntity = PostEntity.builder()
            .maxParticipants(5)
            .limitSex(LimitSex.FEMALE) // Example limit on gender
            .limitSmoke(LimitSmoke.NON_SMOKER)
            .build();

        UserInfoResponseDTO userInfo = createMockUserInfoResponse();

        when(postRepository.findByPostId(POST_ID)).thenReturn(Optional.of(postEntity));
        when(userApi.getUserInfoByUserEmail(USER_EMAIL)).thenReturn(ResponseEntity.ok(responseMessage));

        CustomException exception = assertThrows(CustomException.class, () ->
            participationService.validateParticipationApplicant(POST_ID, USER_ID, USER_EMAIL));

        assertEquals(ErrorCode.POST_PARTICIPATION_LIMIT, exception.getErrorCode());
    }

 */



    @Test
    void createParticipationReady_shouldReturnParticipationDto_whenValid() {
        ParticipationEntity savedEntity = ParticipationEntity.builder()
            .postEntity(PostEntity.builder().postId(POST_ID).build())
            .userId(USER_ID)
            .participationStatus(ParticipationStatus.JOIN_READY)
            .ratingStatus(RatingStatus.NOT_RATED)
            .depositStatus(DepositStatus.UNPAID)
            .build();

        when(participationRepository.save(any(ParticipationEntity.class))).thenReturn(savedEntity);

        ParticipationDto result = participationService.createParticipationReady(POST_ID, USER_ID);

        assertNotNull(result);
        assertEquals(USER_ID, result.getUserId());
        assertEquals(POST_ID, result.getPostId());
        assertEquals("참가준비", result.getParticipationStatus());
    }
}
