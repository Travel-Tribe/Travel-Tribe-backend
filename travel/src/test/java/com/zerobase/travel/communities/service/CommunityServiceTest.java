package com.zerobase.travel.communities.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.zerobase.travel.communities.entity.CommunityEntity;
import com.zerobase.travel.communities.repository.CommunityRepository;
import com.zerobase.travel.communities.service.CommunityService;
import com.zerobase.travel.communities.type.CommunityDto;
import com.zerobase.travel.communities.type.CustomException;
import com.zerobase.travel.communities.type.ErrorCode;
import com.zerobase.travel.typeCommon.Continent;
import com.zerobase.travel.typeCommon.Country;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CommunityServiceTest {

    @Mock
    private CommunityRepository communityRepository;

    @InjectMocks
    private CommunityService communityService;

    @Test
    void createPost() {
        // Given input
        String userId = "user1";
        CommunityEntity entity = CommunityEntity.builder()
            .userId(userId)
            .continent(Continent.ASIA)
            .country(Country.KR)
            .region("Busan")
            .title("Sample Title")
            .content("Sample Content")
            .build();

        // Mock return for any CommunityEntity
        given(communityRepository.save(any(CommunityEntity.class))).willReturn(entity);

        // When
        CommunityDto result = communityService.createPost(
            Continent.ASIA, Country.KR, "Busan", "Sample Title", "Sample Content", userId);

        // Then
        assertThat(result.getUserId()).isEqualTo(userId);
        assertThat(result.getContinent()).isEqualTo(Continent.ASIA);
        assertThat(result.getCountry()).isEqualTo(Country.KR);
        assertThat(result.getRegion()).isEqualTo("Busan");
        assertThat(result.getTitle()).isEqualTo("Sample Title");
        assertThat(result.getContent()).isEqualTo("Sample Content");

        // Verify that the repository's save method was called once with any CommunityEntity
        verify(communityRepository, times(1)).save(any(CommunityEntity.class));
    }

    @Test
    void getPost() {
        // Given
        Long communityId = 1L;
        CommunityEntity entity = CommunityEntity.builder()
            .communityId(communityId)
            .title("Sample Title")
            .build();

        given(communityRepository.findByCommunityId(communityId)).willReturn(Optional.of(entity));

        // When
        CommunityDto result = communityService.getPost(communityId);

        // Then
        assertThat(result.getCommunityId()).isEqualTo(communityId);
        assertThat(result.getTitle()).isEqualTo("Sample Title");
        verify(communityRepository, times(1)).findByCommunityId(communityId);
    }

    @Test
    void getPost_nonExistent() {
        // Given
        Long communityId = 1L;
        given(communityRepository.findByCommunityId(communityId)).willReturn(Optional.empty());

        // When & Then
        assertThatThrownBy(() -> communityService.getPost(communityId))
            .isInstanceOf(CustomException.class)
            .extracting("errorCode")
            .isEqualTo(ErrorCode.COMMUNITY_NON_EXISTENT);

        verify(communityRepository, times(1)).findByCommunityId(communityId);
    }

    @Test
    void getPosts() {
        // Given
        Pageable pageable = PageRequest.of(0, 8);
        List<CommunityEntity> entities = List.of(
            CommunityEntity.builder().communityId(1L).title("Sample 1").build(),
            CommunityEntity.builder().communityId(2L).title("Sample 2").build()
        );
        Page<CommunityEntity> page = new PageImpl<>(entities, pageable, entities.size());
        given(communityRepository.findAll(pageable)).willReturn(page);

        // When
        Page<CommunityDto> result = communityService.getPosts(pageable);

        // Then
        assertThat(result.getContent()).hasSize(2);
        assertThat(result.getContent().get(0).getTitle()).isEqualTo("Sample 1");
        assertThat(result.getContent().get(1).getTitle()).isEqualTo("Sample 2");
        verify(communityRepository, times(1)).findAll(pageable);
    }

    @Test
    void deletePost() {
        // Given
        long communityId = 1L;
        String userId = "user1";
        CommunityEntity entity = CommunityEntity.builder().communityId(communityId).userId(userId).build();
        given(communityRepository.findByUserId(userId)).willReturn(Optional.of(entity));

        // When
        communityService.deletePost(communityId, userId);

        // Then
        verify(communityRepository, times(1)).deleteByCommunityId(communityId);
    }

    @Test
    void deletePost_Unauthorized() {
        // Given
        long communityId = 1L;
        String userId = "unauthorizedUser";
        given(communityRepository.findByUserId(userId)).willReturn(Optional.empty());

        // When & Then
        assertThatThrownBy(() -> communityService.deletePost(communityId, userId))
            .isInstanceOf(CustomException.class)
            .extracting("errorCode")
            .isEqualTo(ErrorCode.USER_UNAUTHORIZED_REQUEST);

        verify(communityRepository, times(1)).findByUserId(userId);
    }

    @Test
    void updatePost() {
        // Given
        long communityId = 1L;
        String userId = "user1";

        CommunityEntity entity = CommunityEntity.builder()
            .communityId(communityId)
            .userId(userId)
            .continent(Continent.ASIA)
            .country(Country.KR)
            .region("Seoul")
            .title("Old Title")
            .content("Old Content")
            .build();

        // Mock the find and save methods of the repository
        given(communityRepository.findByCommunityId(communityId)).willReturn(Optional.of(entity));
        given(communityRepository.save(any(CommunityEntity.class))).willReturn(entity);

        // When
        CommunityDto updatedDto = communityService.updatePost(
            communityId, Continent.ASIA, Country.KR, "Busan", "Updated Title", "Updated Content", userId);

        // Then
        assertThat(updatedDto.getTitle()).isEqualTo("Updated Title");
        assertThat(updatedDto.getContent()).isEqualTo("Updated Content");
        assertThat(updatedDto.getRegion()).isEqualTo("Busan");
        assertThat(updatedDto.getContinent()).isEqualTo(Continent.ASIA);
        assertThat(updatedDto.getCountry()).isEqualTo(Country.KR);

        // Verify that find and save were called exactly once
        verify(communityRepository, times(1)).findByCommunityId(communityId);
        verify(communityRepository, times(1)).save(any(CommunityEntity.class));
    }

    @Test
    void updatePost_Unauthorized() {
        // Given
        long communityId = 1L;
        String userId = "unauthorizedUser";
        CommunityEntity entity = CommunityEntity.builder().communityId(communityId).userId("differentUser").build();

        given(communityRepository.findByCommunityId(communityId)).willReturn(Optional.of(entity));

        // When & Then
        assertThatThrownBy(() -> communityService.updatePost(
            communityId, Continent.ASIA, Country.KR, "Busan", "Updated Title", "Updated Content", userId))
            .isInstanceOf(CustomException.class)
            .extracting("errorCode")
            .isEqualTo(ErrorCode.USER_UNAUTHORIZED_REQUEST);

        verify(communityRepository, times(1)).findByCommunityId(communityId);
    }
}
