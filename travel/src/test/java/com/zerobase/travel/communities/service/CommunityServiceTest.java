package com.zerobase.travel.communities.service;

import com.zerobase.travel.communities.entity.CommunityEntity;
import com.zerobase.travel.communities.repository.CommunityRepository;
import com.zerobase.travel.communities.type.CommunityDto;
import com.zerobase.travel.communities.type.CommunityStatus;
import com.zerobase.travel.communities.type.CustomException;
import com.zerobase.travel.communities.type.ErrorCode;
import com.zerobase.travel.exception.BizException;
import com.zerobase.travel.exception.errorcode.CommunityErrorCode;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommunityServiceTest {

    @InjectMocks
    private CommunityService communityService;

    @Mock
    private CommunityRepository communityRepository;

    private CommunityEntity mockEntity;

    @BeforeEach
    void setUp() {
        mockEntity = CommunityEntity.builder()
            .communityId(1L)
            .userId("testUser")
            .title("Test Title")
            .content("Test Content")
            .status(CommunityStatus.POSTED)
            .createdAt(LocalDateTime.now())
            .build();
    }

    @Test
    void createPost_shouldReturnCommunityDto() {
        when(communityRepository.save(any(CommunityEntity.class))).thenReturn(mockEntity);

        CommunityDto result = communityService.createPost("Test Title", "Test Content", "testUser");

        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo("Test Title");
        assertThat(result.getContent()).isEqualTo("Test Content");
        assertThat(result.getCommunityId()).isEqualTo(1L);
        verify(communityRepository, times(1)).save(any(CommunityEntity.class));
    }

    @Test
    void getPost_shouldReturnCommunityDto_whenPostExists() {
        when(communityRepository.findByCommunityId(1L)).thenReturn(Optional.of(mockEntity));

        CommunityDto result = communityService.getPost(1L);

        assertThat(result).isNotNull();
        assertThat(result.getCommunityId()).isEqualTo(1L);
        verify(communityRepository, times(1)).findByCommunityId(1L);
    }

    @Test
    void getPost_shouldThrowException_whenPostIsDeleted() {
        mockEntity.setStatus(CommunityStatus.DELETED);
        when(communityRepository.findByCommunityId(1L)).thenReturn(Optional.of(mockEntity));

        BizException exception = assertThrows(BizException.class, () -> communityService.getPost(1L));
        assertThat(exception.getErrorCode()).isEqualTo(CommunityErrorCode.COMMUNITY_DELETED);
    }

    @Test
    void getPosts_shouldReturnPageOfCommunityDtos() {
        Pageable pageable = PageRequest.of(0, 8);
        Page<CommunityEntity> page = new PageImpl<>(List.of(mockEntity));
        when(communityRepository.findAllByStatus(pageable, CommunityStatus.POSTED)).thenReturn(page);

        Page<CommunityDto> result = communityService.getPosts(pageable);

        assertThat(result).isNotNull();
        assertThat(result.getTotalElements()).isEqualTo(1);
        verify(communityRepository, times(1)).findAllByStatus(pageable, CommunityStatus.POSTED);
    }

    @Test
    void deletePost_shouldSetStatusDeleted_whenUserIsAuthorized() {
        when(communityRepository.findById(1L)).thenReturn(Optional.of(mockEntity));

        communityService.deletePost(1L, "testUser");

        assertThat(mockEntity.getStatus()).isEqualTo(CommunityStatus.DELETED);
        verify(communityRepository, times(1)).save(mockEntity);
    }

    @Test
    void deletePost_shouldThrowException_whenUserIsUnauthorized() {
        when(communityRepository.findById(1L)).thenReturn(Optional.of(mockEntity));

        CustomException exception = assertThrows(CustomException.class, () -> communityService.deletePost(1L, "wrongUser"));
        assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.USER_UNAUTHORIZED_REQUEST);
    }

    @Test
    void updatePost_shouldReturnUpdatedDto_whenUserIsAuthorized() {
        when(communityRepository.findByCommunityId(1L)).thenReturn(Optional.of(mockEntity));
        when(communityRepository.save(any())).thenReturn(mockEntity);

        CommunityDto result = communityService.updatePost(1L, "Updated Title", "Updated Content", "testUser");

        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo("Updated Title");
        assertThat(result.getContent()).isEqualTo("Updated Content");

        verify(communityRepository, times(1)).save(mockEntity);
    }

    @Test
    void updatePost_shouldThrowException_whenUserIsUnauthorized() {
        when(communityRepository.findByCommunityId(1L)).thenReturn(Optional.of(mockEntity));

        BizException exception = assertThrows(BizException.class, () -> communityService.updatePost(1L, "Updated Title", "Updated Content", "wrongUser"));

        assertThat(exception.getErrorCode()).isEqualTo(CommunityErrorCode.USER_UNAUTHORIZED_REQUEST);
    }
}