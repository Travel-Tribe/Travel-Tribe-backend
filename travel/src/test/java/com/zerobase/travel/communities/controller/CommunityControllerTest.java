package com.zerobase.travel.communities.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zerobase.travel.communities.service.CommunityManagementService;
import com.zerobase.travel.communities.type.RequestCreateCommunity;
import com.zerobase.travel.communities.type.RequestPostCommunity;
import com.zerobase.travel.communities.type.ResponseCommunityDto;
import com.zerobase.travel.post.dto.response.PagedResponseDTO;
import com.zerobase.travel.typeCommon.Continent;
import com.zerobase.travel.typeCommon.Country;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.http.MediaType;

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

        // given mock return value
        sampleCommunityResponse = ResponseCommunityDto.builder()
            .communityId(1L)
            .userId(SAMPLE_USER_ID)
            .continent(Continent.ASIA)
            .country(Country.KR)
            .region("Seoul")
            .title("Sample Community")
            .content("Sample content")
            .files(Collections.emptyList())
            .build();
    }


    @Test
    @WithMockUser
    void createCommunity() throws Exception {

        // given request

        List<String> files = List.of("file1", "file2");

        RequestCreateCommunity request = RequestCreateCommunity.builder()
            .continent(Continent.ASIA)
            .country(Country.KR)
            .region("Seoul")
            .title("Sample Title")
            .content("Sample Content")
            .files(files)
            .build();


        //when

        when(communityManagementService.createPost(any(RequestCreateCommunity.class),anyString()))
            .thenReturn(sampleCommunityResponse);


        mockMvc.perform(
            post("/communities")
                .header(USER_ID_HEADER, SAMPLE_USER_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                .with(csrf())
            )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.communityId").value(1))
            .andExpect(jsonPath("$.userId").value(SAMPLE_USER_ID))
            .andExpect(jsonPath("$.title").value("Sample Community"))
            .andExpect(jsonPath("$.content").value("Sample content"));
    }

    @Test
    @WithMockUser
    void getCommunities() throws Exception {
        // Given a pageable request
        Pageable pageable = PageRequest.of(0, 8, Sort.by(Sort.Direction.DESC, "communityId"));

        // Given a sample paged response from the service
        PagedResponseDTO<ResponseCommunityDto> pagedResponse = PagedResponseDTO.<ResponseCommunityDto>builder()
            .content(List.of(sampleCommunityResponse))
            .pageNumber(pageable.getPageNumber())
            .pageSize(pageable.getPageSize())
            .totalElements(1)
            .totalPages(1)
            .last(true)
            .build();

        // Mock the service response
        when(communityManagementService.getPosts(any(Pageable.class))).thenReturn(pagedResponse);

        // Perform the GET request with pagination parameters
        mockMvc.perform(get("/communities")
                .param("page", "0")
                .param("size", "8")
                .param("sort", "communityId,desc")
                .with(csrf())) // Add CSRF token
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.content[0].communityId").value(1))
            .andExpect(jsonPath("$.content[0].userId").value(SAMPLE_USER_ID))
            .andExpect(jsonPath("$.content[0].title").value("Sample Community"))
            .andExpect(jsonPath("$.content[0].content").value("Sample content"))
            .andExpect(jsonPath("$.pageNumber").value(0))
            .andExpect(jsonPath("$.pageSize").value(8))
            .andExpect(jsonPath("$.totalElements").value(1))
            .andExpect(jsonPath("$.totalPages").value(1))
            .andExpect(jsonPath("$.last").value(true));

        // Verify that the service method was called with the correct pageable
        verify(communityManagementService, times(1)).getPosts(pageable);
    }


    @Test
    @WithMockUser
    void deleteCommunity() throws Exception {
        // Given
        long communityId = 1L;

        // Mock the behavior of the service
        doNothing().when(communityManagementService).deletePost(eq(communityId), eq(SAMPLE_USER_ID));

        // Perform the delete request
        mockMvc.perform(
                delete("/communities/{communityId}", communityId)
                    .header(USER_ID_HEADER, SAMPLE_USER_ID)
                    .with(csrf()) // Add CSRF token for security
            )
            .andExpect(status().isOk()); // Expect 200 OK status

        // Verify that the service method was called with correct parameters
        verify(communityManagementService, times(1)).deletePost(communityId, SAMPLE_USER_ID);
    }



    @Test
    @WithMockUser
    void updateCommunity() throws Exception {
        // Given an update request
        RequestPostCommunity request = RequestPostCommunity.builder()
            .communityId(1L)
            .title("Updated Title")
            .content("Updated Content")
            .continent(Continent.ASIA)
            .country(Country.KR)
            .region("Busan")
            .build();

        // Mocking the service's response to return a sample community response
        given(communityManagementService.updatePost(any(RequestPostCommunity.class), anyString()))
            .willReturn(sampleCommunityResponse);

        // Perform the PUT request
        mockMvc.perform(
            put("/communities/{communityId}", 1L)
                .header(USER_ID_HEADER, SAMPLE_USER_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                .with(csrf()))  // Add CSRF token for PUT request
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.communityId").value(1L))
            .andExpect(jsonPath("$.userId").value(SAMPLE_USER_ID))
            .andExpect(jsonPath("$.title").value("Sample Community"))
            .andExpect(jsonPath("$.content").value("Sample content"));

        // Verify that the service method was called with the correct parameters
        verify(communityManagementService, times(1)).updatePost(any(RequestPostCommunity.class), eq(SAMPLE_USER_ID));
    }

}
