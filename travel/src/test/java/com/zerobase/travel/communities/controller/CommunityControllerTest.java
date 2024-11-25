package com.zerobase.travel.communities.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zerobase.travel.communities.service.CommunityManagementService;
import com.zerobase.travel.communities.type.RequestCreateCommunity;
import com.zerobase.travel.communities.type.RequestUpdateCommunity;
import com.zerobase.travel.communities.type.ResponseCommunityDto;
import com.zerobase.travel.post.dto.response.PagedResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = CommunityController.class)
class CommunityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommunityManagementService communityManagementService;

    @InjectMocks
    private CommunityController communityController;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String USER_ID_HEADER = "X-User-Id";
    private static final String SAMPLE_USER_ID = "user123";
    private ResponseCommunityDto sampleCommunityResponse;

    @BeforeEach
    void setUp() {
        sampleCommunityResponse = ResponseCommunityDto.builder()
            .communityId(1L)
            .userId(SAMPLE_USER_ID)
            .title("Sample Community")
            .content("Sample content")
            .files(Collections.emptyList())
            .build();
    }

    @Test
    @WithMockUser
    void createCommunity() throws Exception {
        RequestCreateCommunity request = new RequestCreateCommunity();
        request.setTitle("New Community");
        request.setContent("New content");

        when(communityManagementService.createPost(any(RequestCreateCommunity.class), anyString()))
            .thenReturn(sampleCommunityResponse);

        mockMvc.perform(post("/api/v1/communities")
                .header(USER_ID_HEADER, SAMPLE_USER_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                .with(csrf()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.communityId").value(1L))
            .andExpect(jsonPath("$.data.userId").value(SAMPLE_USER_ID))
            .andExpect(jsonPath("$.data.title").value("Sample Community"));

        verify(communityManagementService, times(1)).createPost(any(RequestCreateCommunity.class), eq(SAMPLE_USER_ID));
    }

    @Test
    @WithMockUser
    void getCommunities() throws Exception {
        Pageable pageable = PageRequest.of(0, 8, Sort.by(Sort.Direction.DESC, "communityId"));
        PagedResponseDTO<ResponseCommunityDto> pagedResponse = PagedResponseDTO.<ResponseCommunityDto>builder()
            .content(List.of(sampleCommunityResponse))
            .pageNumber(pageable.getPageNumber())
            .pageSize(pageable.getPageSize())
            .totalElements(1)
            .totalPages(1)
            .last(true)
            .build();

        when(communityManagementService.getPosts(any(Pageable.class))).thenReturn(pagedResponse);

        mockMvc.perform(get("/api/v1/communities")
                .param("page", "0")
                .param("size", "8")
                .param("sort", "communityId,desc")
                .with(csrf()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.content[0].communityId").value(1))
            .andExpect(jsonPath("$.data.pageNumber").value(0))
            .andExpect(jsonPath("$.data.pageSize").value(8))
            .andExpect(jsonPath("$.data.totalElements").value(1));

        verify(communityManagementService, times(1)).getPosts(pageable);
    }

    @Test
    @WithMockUser
    void getCommunity() throws Exception {
        when(communityManagementService.getPost(anyLong())).thenReturn(sampleCommunityResponse);

        mockMvc.perform(get("/api/v1/communities/{communityId}", 1L)
                .with(csrf()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.communityId").value(1L))
            .andExpect(jsonPath("$.data.userId").value(SAMPLE_USER_ID))
            .andExpect(jsonPath("$.data.title").value("Sample Community"));

        verify(communityManagementService, times(1)).getPost(1L);
    }

    @Test
    @WithMockUser
    void deleteCommunity() throws Exception {
        doNothing().when(communityManagementService).deletePost(anyLong(), anyString());

        mockMvc.perform(delete("/api/v1/communities/{communityId}", 1L)
                .header(USER_ID_HEADER, SAMPLE_USER_ID)
                .with(csrf()))
            .andExpect(status().isOk());

        verify(communityManagementService, times(1)).deletePost(1L, SAMPLE_USER_ID);
    }

    @Test
    @WithMockUser
    void updateCommunity() throws Exception {
        RequestUpdateCommunity request = new RequestUpdateCommunity();
        request.setCommunityId(1L);
        request.setTitle("Updated Title");
        request.setContent("Updated Content");

        when(communityManagementService.updatePost(anyLong(), any(RequestUpdateCommunity.class), anyString()))
            .thenReturn(sampleCommunityResponse);

        mockMvc.perform(put("/api/v1/communities/{communityId}", 1L)
                .header(USER_ID_HEADER, SAMPLE_USER_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                .with(csrf()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.communityId").value(1L))
            .andExpect(jsonPath("$.data.userId").value(SAMPLE_USER_ID))
            .andExpect(jsonPath("$.data.title").value("Sample Community"));

        verify(communityManagementService, times(1)).updatePost(eq(1L), any(RequestUpdateCommunity.class), eq(SAMPLE_USER_ID));
    }
}