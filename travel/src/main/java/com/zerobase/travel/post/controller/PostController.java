package com.zerobase.travel.post.controller;

import static com.zerobase.travel.exception.errorcode.BasicErrorCode.INVALID_CONTINENT_VALUE;
import static com.zerobase.travel.exception.errorcode.BasicErrorCode.INVALID_COUNTRY_VALUE;
import static com.zerobase.travel.exception.errorcode.BasicErrorCode.INVALID_MBTI_VALUE;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import com.zerobase.travel.common.response.ResponseMessage;
import com.zerobase.travel.exception.BizException;
import com.zerobase.travel.post.application.PostFacade;
import com.zerobase.travel.post.constants.RepresentativeCountries;
import com.zerobase.travel.post.dto.request.PostDTO;
import com.zerobase.travel.post.dto.request.PostSearchCriteria;
import com.zerobase.travel.post.dto.response.PagedResponseDTO;
import com.zerobase.travel.post.dto.response.ResponsePostDTO;
import com.zerobase.travel.post.dto.response.ResponsePostsDTO;
import com.zerobase.travel.post.dto.response.createdPostResponseDTO;
import com.zerobase.travel.post.service.PostService;
import com.zerobase.travel.typeCommon.Continent;
import com.zerobase.travel.typeCommon.Country;
import jakarta.validation.Valid;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;
    private final PostFacade postFacade;

    // 여행참여 게시글 등록
    @PostMapping
    public ResponseEntity<ResponseMessage<createdPostResponseDTO>> createPost(
        @Valid @RequestBody PostDTO postDTO,
        @RequestHeader("X-User-Email") String userEmail) { // 이메일 추출
        log.info("email: " + userEmail);
        createdPostResponseDTO post = postFacade.createPost(postDTO, userEmail);
        return ResponseEntity.status(CREATED).body(ResponseMessage.success(post));
    }

    // 여행참여 게시글 수정
    @PutMapping("/{postId}")
    public ResponseEntity<ResponseMessage<Void>> updatePost(@PathVariable Long postId,
        @Valid @RequestBody PostDTO postDTO,
        @RequestHeader("X-User-Email") String userEmail) {
        log.info("email: " + userEmail);
        postService.updatePost(postId, postDTO, userEmail);
        return ResponseEntity.status(OK).body(ResponseMessage.success());
    }

    // 여행참여 게시글 삭제
    @DeleteMapping("/{postId}")
    public ResponseEntity<ResponseMessage<Void>> deletePost(@PathVariable Long postId,
        @RequestHeader("X-User-Email") String userEmail) {
        log.info("email: " + userEmail);
        postService.deletePost(postId, userEmail);
        return ResponseEntity.status(OK).body(ResponseMessage.success());
    }

    // 여행참여 게시글 조회
    @GetMapping("/{postId}")
    public ResponseEntity<ResponseMessage<ResponsePostDTO>> findPost(@PathVariable Long postId) {
        ResponsePostDTO responsePostDTO = postService.findPost(postId);
        return ResponseEntity.status(OK).body(ResponseMessage.success(responsePostDTO));
    }

    // **게시글 리스트 검색**
    // 게시글 리스트 검색
    @GetMapping
    public ResponseEntity<ResponseMessage<PagedResponseDTO<ResponsePostsDTO>>> searchPosts(
        @RequestParam(required = false) String title,
        @RequestParam(required = false) String content,
        @RequestParam(required = false) String continent,
        @RequestParam(required = false) String country,
        @RequestParam(required = false) String mbti,
        @RequestParam(required = false) String region,
        @PageableDefault(size = 8, sort = "postId", direction = Sort.Direction.DESC) Pageable pageable
    ) {

        // 검색 수행: 서비스 계층으로 요청 전달
        PagedResponseDTO<ResponsePostsDTO> pagedResponse = postService.searchPosts(
            title, content, continent, country, mbti, region, pageable
        );

        return ResponseEntity.status(OK).body(ResponseMessage.success(pagedResponse));
    }
}

