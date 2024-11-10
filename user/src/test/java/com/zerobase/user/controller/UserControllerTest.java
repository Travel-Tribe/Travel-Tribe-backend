package com.zerobase.user.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zerobase.user.dto.request.JoinDTO;
import com.zerobase.user.service.ProfileService;
import com.zerobase.user.service.ReissueService;
import com.zerobase.user.service.UserService;
import com.zerobase.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser; // 추가된 임포트
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@WebMvcTest(
    controllers = UserController.class,
    excludeAutoConfiguration = {
        DataSourceAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class
    }
)
class UserControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    UserService userService;

    @MockBean
    ProfileService profileService;

    @MockBean
    UserRepository userRepository;

    @MockBean
    ReissueService reissueService;

    @MockBean
    JpaMetamodelMappingContext jpaMappingContext;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("회원 가입 테스트 - 성공")
    @WithMockUser // @WithMockUser 추가
    void joinProcessTest_Success() throws Exception {
        // 1. 입력 데이터 준비
        JoinDTO joinDTO = JoinDTO.builder()
            .username("testuser")
            .nickname("테스트유저")
            .phone("01012345678")
            .password("password123")
            .email("testuser@example.com")
            .build();

        // 2. 서비스 메소드 모킹
        doNothing().when(userService).joinProcess(any(JoinDTO.class));

        // 3. POST 요청 수행 (CSRF 토큰 추가)
        mvc.perform(post("/api/v1/users")
                .with(csrf()) // CSRF 토큰 추가
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(joinDTO)))
            // 4. 응답 검증
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.result").value("SUCCESS"))
            .andExpect(jsonPath("$.errors").doesNotExist())
            .andExpect(jsonPath("$.data").doesNotExist());

        // 추가 검증: userService.joinProcess가 정확한 인자로 호출되었는지 확인
        verify(userService, times(1)).joinProcess(any(JoinDTO.class));
    }

    @Test
    @DisplayName("회원 가입 테스트 - 이메일 형식 오류")
    @WithMockUser // @WithMockUser 추가
    void joinProcessTest_InvalidEmail() throws Exception {
        // 1. 입력 데이터 준비 (잘못된 이메일 형식)
        JoinDTO joinDTO = JoinDTO.builder()
            .username("testuser")
            .nickname("테스트유저")
            .phone("01012345678")
            .password("password123")
            .email("invalid-email-format") // 잘못된 이메일
            .build();

        // 2. POST 요청 수행 (CSRF 토큰 추가)
        mvc.perform(post("/api/v1/users")
                .with(csrf()) // CSRF 토큰 추가
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(joinDTO)))
            // 3. 응답 검증
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.result").value("FAIL"))
            .andExpect(jsonPath("$.errors").isArray())
            .andExpect(jsonPath("$.errors[0].errorCode").value("E01011")) // @Email 어노테이션의 message 값
            .andExpect(jsonPath("$.data").doesNotExist());

        // 4. 서비스 메소드 호출이 없었는지 검증
        verify(userService, times(0)).joinProcess(any(JoinDTO.class));
    }

    @Test
    @DisplayName("회원 가입 테스트 - 이미 존재하는 이메일")
    @WithMockUser // @WithMockUser 추가
    void joinProcessTest_EmailAlreadyExists() throws Exception {
        // 1. 입력 데이터 준비
        JoinDTO joinDTO = JoinDTO.builder()
            .username("testuser")
            .nickname("테스트유저")
            .phone("01012345678")
            .password("password123")
            .email("existinguser@example.com")
            .build();

        // 2. 서비스 메소드 모킹 - 이메일 중복 시 예외 발생
        doThrow(new DataIntegrityViolationException("이미 존재하는 이메일입니다."))
            .when(userService).joinProcess(any(JoinDTO.class));

        // 3. POST 요청 수행 (CSRF 토큰 추가)
        mvc.perform(post("/api/v1/users")
                .with(csrf()) // CSRF 토큰 추가
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(joinDTO)))
            // 4. 응답 검증
            .andExpect(status().isConflict()) // 409 Conflict 기대
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.result").value("FAIL"))
            .andExpect(jsonPath("$.errors").isArray())
            .andExpect(jsonPath("$.errors[0].errorCode").value("E01003")) // 예외 처리에 따라 다를 수 있음
            .andExpect(jsonPath("$.data").doesNotExist());

        // 추가 검증: userService.joinProcess가 정확한 인자로 호출되었는지 확인
        verify(userService, times(1)).joinProcess(any(JoinDTO.class));
    }
}
