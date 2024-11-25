package com.zerobase.travel.communities.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.zerobase.travel.communities.entity.CommunityEntity;
import com.zerobase.travel.communities.entity.CommunityFileEntity;
import com.zerobase.travel.communities.repository.CommunityFileRepository;
import com.zerobase.travel.communities.type.CommunityFileDto;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CommunityFileServiceTest {

    @Mock
    private CommunityFileRepository communityFileRepository;

    @InjectMocks
    private CommunityFileService communityFileService;

    @Test
    void getFiles() {
        // Given input
        Long communityId = 1L;

        // Given mock return
        List<CommunityFileEntity> mockFileEntities = List.of(
            CommunityFileEntity.builder().communityFileId(1L).fileName("file1.jpg")
                .communityEntity(CommunityEntity.builder().communityId(communityId).build())
                .build(),
            CommunityFileEntity.builder().communityFileId(2L).fileName("file2.jpg")
                .communityEntity(CommunityEntity.builder().communityId(communityId).build())
                .build()
        );

        given(communityFileRepository.findAllByCommunityEntity_CommunityId(communityId)).willReturn(mockFileEntities);

        // When
        List<CommunityFileDto> result = communityFileService.getFiles(communityId);

        // Then
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getFileName()).isEqualTo("file1.jpg");
        assertThat(result.get(1).getFileName()).isEqualTo("file2.jpg");

        verify(communityFileRepository, times(1)).findAllByCommunityEntity_CommunityId(communityId);
    }

    @Test
    void saveFiles() {
        // Given
        Long communityId = 1L;
        List<String> fileNames = List.of("file1.jpg", "file2.jpg");

        // Mock entities created during save
        List<CommunityFileEntity> mockFileEntities = fileNames.stream()
            .map(name -> CommunityFileEntity.builder()
                .fileName(name)
                .communityEntity(CommunityEntity.builder().communityId(communityId).build())
                .build())
            .toList();

        given(communityFileRepository.saveAll(any())).willReturn(mockFileEntities);

        // When
        List<CommunityFileDto> result = communityFileService.saveFiles(communityId, fileNames);

        // Then
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getCommunityId()).isEqualTo(communityId);
        assertThat(result.get(0).getFileName()).isEqualTo("file1.jpg");
        assertThat(result.get(1).getCommunityId()).isEqualTo(communityId);
        assertThat(result.get(1).getFileName()).isEqualTo("file2.jpg");

        verify(communityFileRepository, times(1)).saveAll(any());
    }

    @Test
    void deleteAllByCommunityId() {
        // Given
        long communityId = 1L;

        // When
        communityFileService.deleteAllByCommunityId(communityId);

        // Then
        verify(communityFileRepository, times(1)).deleteAllByCommunityEntity_CommunityId(communityId);
    }
}
