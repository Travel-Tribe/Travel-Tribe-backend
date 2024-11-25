package com.zerobase.travel.communities.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.zerobase.travel.communities.type.CommunityDto;
import com.zerobase.travel.communities.type.CommunityFileDto;
import com.zerobase.travel.communities.type.RequestCreateCommunity;
import com.zerobase.travel.communities.type.RequestUpdateCommunity;
import com.zerobase.travel.communities.type.ResponseCommunityDto;
import com.zerobase.travel.post.dto.response.PagedResponseDTO;
import com.zerobase.travel.typeCommon.Continent;
import com.zerobase.travel.typeCommon.Country;
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

@ExtendWith(MockitoExtension.class)
class CommunityManagementServiceTest {

    @Mock
    private CommunityService communityService;

    @Mock
    private CommunityFileService communityFileService;

    @InjectMocks
    private CommunityManagementService communityManagementService;

    private CommunityDto sampleCommunityDto;
    private List<CommunityFileDto> sampleFiles;
    private RequestCreateCommunity createRequest;

    @BeforeEach
    void setUp() {
        sampleCommunityDto = CommunityDto.builder()
            .communityId(1L)
            .userId("user1")
            .title("Sample Title")
            .content("Sample Content")
            .build();

        sampleFiles = List.of(new CommunityFileDto(1L, "file1.jpg"),
            new CommunityFileDto(2L, "file2.jpg"));

    }

    @Test
    void createPost() {
        // Given
        given(communityService.createPost(any(), any(), any())).willReturn(sampleCommunityDto);
        given(communityFileService.saveFiles(anyLong(), any())).willReturn(sampleFiles);

        // When
        ResponseCommunityDto response = communityManagementService.createPost(createRequest, "user1");

        // Then
        assertThat(response.getCommunityId()).isEqualTo(1L);
        assertThat(response.getTitle()).isEqualTo("Sample Title");
        assertThat(response.getFiles()).hasSize(2);

        verify(communityService, times(1)).createPost(any(), any(),  eq("user1"));
        verify(communityFileService, times(1)).saveFiles(anyLong(), any());
    }

    @Test
    void getPosts() {
        // Given
        Pageable pageable = PageRequest.of(0, 10);
        Page<CommunityDto> communityPage = new PageImpl<>(List.of(sampleCommunityDto), pageable, 1);
        given(communityService.getPosts(pageable)).willReturn(communityPage);
        given(communityFileService.getFiles(anyLong())).willReturn(sampleFiles);

        // When
        PagedResponseDTO<ResponseCommunityDto> response = communityManagementService.getPosts(pageable);

        // Then
        assertThat(response.getContent()).hasSize(1);
        assertThat(response.getContent().get(0).getTitle()).isEqualTo("Sample Title");
        assertThat(response.getContent().get(0).getFiles()).hasSize(2);

        verify(communityService, times(1)).getPosts(pageable);
        verify(communityFileService, times(1)).getFiles(anyLong());
    }

    @Test
    void getPost() {
        // Given
        long communityId = 1L;
        given(communityService.getPost(communityId)).willReturn(sampleCommunityDto);
        given(communityFileService.getFiles(communityId)).willReturn(sampleFiles);

        // When
        ResponseCommunityDto response = communityManagementService.getPost(communityId);

        // Then
        assertThat(response.getCommunityId()).isEqualTo(1L);
        assertThat(response.getTitle()).isEqualTo("Sample Title");
        assertThat(response.getFiles()).hasSize(2);

        verify(communityService, times(1)).getPost(communityId);
        verify(communityFileService, times(1)).getFiles(communityId);
    }

    @Test
    void deletePost() {
        // Given
        long communityId = 1L;
        String userId = "user1";

        // When
        communityManagementService.deletePost(communityId, userId);

        // Then
        verify(communityService, times(1)).deletePost(communityId, userId);
        verify(communityFileService, times(1)).deleteAllByCommunityId(communityId);
    }

    @Test
    void updatePost() {
        // Given
        RequestUpdateCommunity request = new RequestUpdateCommunity();

        request.setCommunityId(1L);
        request.setTitle("Updated Title");
        request.setContent("Updated Content");
        request.setContinent(Continent.ASIA);
        request.setCountry(Country.KR);
        request.setRegion("Busan");
        request.setFiles(List.of("updated_file1.jpg", "updated_file2.jpg"));


        CommunityDto updatedCommunityDto = CommunityDto.builder()
            .communityId(1L)
            .userId("user1")
            .title("Updated Title")
            .content("Updated Content")
            .build();

        List<CommunityFileDto> updatedFiles = List.of(
            new CommunityFileDto(1L, "updated_file1.jpg"),
            new CommunityFileDto(2L, "updated_file2.jpg")
        );

        given(communityService.updatePost(anyLong(), any(), any(), any()))
            .willReturn(updatedCommunityDto);
        given(communityFileService.saveFiles(anyLong(), any())).willReturn(updatedFiles);

        // When
        ResponseCommunityDto response = communityManagementService.updatePost(request, "user1");

        // Then
        assertThat(response.getTitle()).isEqualTo("Updated Title");
        assertThat(response.getContent()).isEqualTo("Updated Content");
        assertThat(response.getFiles()).hasSize(2);

        verify(communityService, times(1)).updatePost(anyLong(), any(), any(), eq("user1"));
        verify(communityFileService, times(1)).deleteAllByCommunityId(anyLong());
        verify(communityFileService, times(1)).saveFiles(anyLong(), any());
    }
}
