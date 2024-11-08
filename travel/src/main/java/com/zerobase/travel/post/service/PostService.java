package com.zerobase.travel.post.service;

import static com.zerobase.travel.exception.errorcode.BasicErrorCode.PERMISSION_DENIED_ERROR;
import static com.zerobase.travel.exception.errorcode.BasicErrorCode.POST_NOT_FOUND_ERROR;
import static com.zerobase.travel.exception.errorcode.BasicErrorCode.USER_INFO_CALL_ERROR;
import static com.zerobase.travel.exception.errorcode.BasicErrorCode.USER_NOT_FOUND_ERROR;

import com.zerobase.travel.exception.BizException;
import com.zerobase.travel.post.dto.request.DayDTO;
import com.zerobase.travel.post.dto.request.DayDetailDTO;
import com.zerobase.travel.post.dto.request.ItineraryVisitDTO;
import com.zerobase.travel.post.dto.request.PostDTO;
import com.zerobase.travel.post.dto.request.PostSearchCriteria;
import com.zerobase.travel.post.dto.response.ResponsePostDTO;
import com.zerobase.travel.post.dto.response.ResponsePostsDTO;
import com.zerobase.travel.post.dto.response.UserInfoResponseDTO;
import com.zerobase.travel.post.entity.DayDetailEntity;
import com.zerobase.travel.post.entity.DayEntity;
import com.zerobase.travel.post.entity.ItineraryVisitEntity;
import com.zerobase.travel.post.entity.PostEntity;
import com.zerobase.travel.post.entity.UserClient;
import com.zerobase.travel.post.repository.PostRepository;
import com.zerobase.travel.post.specification.PostSpecification;
import com.zerobase.travel.post.type.PostStatus;
import feign.FeignException;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserClient userClient; // FeignClient 주입
    private final UserClientService userClientService;
    private final GeometryFactory geometryFactory = new GeometryFactory();

    @Transactional
    public void createPost(PostDTO postDTO, String userEmail) {

        // 이메일을 기반으로 userId 조회 -> 이쪽에서 이제 feignClient나 restTemplate쓰자!
        // FeignClient를 사용하여 User 서비스에서 사용자 정보 조회
        UserInfoResponseDTO userInfo = userClientService.getUserInfo(userEmail);
        log.info(userInfo.toString());
        Long userId = userInfo.getId();

        PostEntity postEntity = PostEntity.builder()
            .title(postDTO.getTitle())
            .userId(userId)
            .travelStartDate(postDTO.getTravelStartDate())
            .travelEndDate(postDTO.getTravelEndDate())
            .maxParticipants(postDTO.getMaxParticipants())
            .travelCountry(postDTO.getTravelCountry())
            .continent(postDTO.getContinent())
            .region(postDTO.getRegion())
            .accommodationFee(postDTO.getAccommodationFee())
            .transportationFee(postDTO.getTransportationFee())
            .airplaneFee(postDTO.getAirplaneFee())
            .foodFee(postDTO.getFoodFee())
            .limitMinAge(postDTO.getLimitMinAge())
            .limitMaxAge(postDTO.getLimitMaxAge())
            .limitSex(postDTO.getLimitSex())
            .limitSmoke(postDTO.getLimitSmoke())
            .deadline(postDTO.getDeadline())
            .status(PostStatus.POSTING)
            .build();

        // Days 처리
        if (postDTO.getDays() != null) {
            for (DayDTO dayDTO : postDTO.getDays()) {
                DayEntity dayEntity = DayEntity.builder().build();
                postEntity.addDay(dayEntity);

                // DayDetails 처리
                if (dayDTO.getDayDetails() != null) {
                    for (DayDetailDTO dayDetailDTO : dayDTO.getDayDetails()) {
                        DayDetailEntity dayDetailEntity = DayDetailEntity.builder()
                            .title(dayDetailDTO.getTitle())
                            .description(dayDetailDTO.getDescription())
                            .fileAddress(dayDetailDTO.getFileAddress())
                            .day(dayEntity)
                            .build();
                        dayEntity.addDayDetail(dayDetailEntity);
                    }
                }

                // ItineraryVisits 처리
                if (dayDTO.getItineraryVisits() != null) {
                    for (ItineraryVisitDTO visitDTO : dayDTO.getItineraryVisits()) {
                        ItineraryVisitEntity visitEntity = ItineraryVisitEntity.builder()
                            .orderNumber(visitDTO.getOrderNumber())
                            .day(dayEntity)
                            .build();

                        // POINT 타입 설정
                        if (visitDTO.getLatitude() != null && visitDTO.getLongitude() != null) {
                            Coordinate coordinate = new Coordinate(visitDTO.getLongitude(),
                                visitDTO.getLatitude());
                            Point point = geometryFactory.createPoint(coordinate);
                            visitEntity.setPoint(point);
                        }

                        dayEntity.addItineraryVisit(visitEntity);
                    }
                }
            }
        }

        // Post 저장
        postRepository.save(postEntity);
    }

    @Transactional
    public void updatePost(Long postId, PostDTO postDTO, String userEmail) {
        // 게시글 조회
        PostEntity existingPost = postRepository.findById(postId)
            .orElseThrow(() -> new BizException(POST_NOT_FOUND_ERROR));

        // 사용자 정보 조회
        UserInfoResponseDTO userInfo;
        try {
            userInfo = userClientService.getUserInfo(userEmail);
            log.info("User Info: {}", userInfo);
        } catch (FeignException e) {
            log.error("userClient 호출 실패: {}", e.getMessage());
            throw new BizException(USER_INFO_CALL_ERROR);
        }

        if (userInfo == null) {
            throw new BizException(USER_NOT_FOUND_ERROR);
        }

        Long userId = userInfo.getId();

        // 게시글 소유자 확인
        if (!existingPost.getUserId().equals(userId)) {
            throw new BizException(PERMISSION_DENIED_ERROR);
        }

        // 게시글 업데이트
        existingPost.setTitle(postDTO.getTitle());
        existingPost.setTravelStartDate(postDTO.getTravelStartDate());
        existingPost.setTravelEndDate(postDTO.getTravelEndDate());
        existingPost.setMaxParticipants(postDTO.getMaxParticipants());
        existingPost.setTravelCountry(postDTO.getTravelCountry());
        existingPost.setContinent(postDTO.getContinent());
        existingPost.setRegion(postDTO.getRegion());
        existingPost.setAccommodationFee(postDTO.getAccommodationFee());
        existingPost.setTransportationFee(postDTO.getTransportationFee());
        existingPost.setAirplaneFee(postDTO.getAirplaneFee());
        existingPost.setFoodFee(postDTO.getFoodFee());
        existingPost.setLimitMinAge(postDTO.getLimitMinAge());
        existingPost.setLimitMaxAge(postDTO.getLimitMaxAge());
        existingPost.setLimitSex(postDTO.getLimitSex());
        existingPost.setLimitSmoke(postDTO.getLimitSmoke());
        existingPost.setDeadline(postDTO.getDeadline());
        existingPost.setStatus(PostStatus.POSTING); // 필요에 따라 상태 변경

        // Days 업데이트
        // 기존의 Days 및 관련 엔티티를 모두 삭제하고 새로 추가하는 방식으로 간단하게 처리
        existingPost.getDays().clear();
        postDTO.getDays().forEach(dayDTO -> {
            DayEntity dayEntity = DayEntity.builder().build();
            existingPost.addDay(dayEntity);

            if (dayDTO.getDayDetails() != null) {
                dayDTO.getDayDetails().forEach(dayDetailDTO -> {
                    DayDetailEntity dayDetailEntity = DayDetailEntity.builder()
                        .title(dayDetailDTO.getTitle())
                        .description(dayDetailDTO.getDescription())
                        .fileAddress(dayDetailDTO.getFileAddress())
                        .day(dayEntity)
                        .build();
                    dayEntity.addDayDetail(dayDetailEntity);
                });
            }

            if (dayDTO.getItineraryVisits() != null) {
                dayDTO.getItineraryVisits().forEach(visitDTO -> {
                    ItineraryVisitEntity visitEntity = ItineraryVisitEntity.builder()
                        .orderNumber(visitDTO.getOrderNumber())
                        .day(dayEntity)
                        .build();

                    if (visitDTO.getLatitude() != null && visitDTO.getLongitude() != null) {
                        Coordinate coordinate = new Coordinate(visitDTO.getLongitude(),
                            visitDTO.getLatitude());
                        Point point = geometryFactory.createPoint(coordinate);
                        visitEntity.setPoint(point);
                    }

                    dayEntity.addItineraryVisit(visitEntity);
                });
            }
        });
    }


    @Transactional
    public void deletePost(Long postId, String userEmail) {
        // 게시글 조회
        PostEntity existingPost = postRepository.findById(postId)
            .orElseThrow(() -> new BizException(POST_NOT_FOUND_ERROR));

        // 사용자 정보 조회
        UserInfoResponseDTO userInfo;
        try {
            userInfo = userClientService.getUserInfo(userEmail);
            log.info("User Info: {}", userInfo);
        } catch (FeignException e) {
            log.error("userClient 호출 실패: {}", e.getMessage());
            throw new BizException(USER_INFO_CALL_ERROR);
        }

        if (userInfo == null) {
            throw new BizException(USER_NOT_FOUND_ERROR);
        }

        Long userId = userInfo.getId();

        // 게시글 소유자 확인
        if (!existingPost.getUserId().equals(userId)) {
            throw new BizException(PERMISSION_DENIED_ERROR);
        }

        // 게시글 삭제
        postRepository.delete(existingPost);
    }

    @Transactional
    public ResponsePostDTO findPost(Long postId, String userEmail) {
        // 게시글 조회
        PostEntity existingPost = postRepository.findById(postId)
            .orElseThrow(() -> new BizException(POST_NOT_FOUND_ERROR));

        // 사용자 정보 조회
        UserInfoResponseDTO userInfo;
        try {
            userInfo = userClientService.getUserInfo(userEmail);
            log.info("User Info: {}", userInfo);
        } catch (FeignException e) {
            log.error("userClient 호출 실패: {}", e.getMessage());
            throw new BizException(USER_INFO_CALL_ERROR);
        }

        if (userInfo == null) {
            throw new BizException(USER_NOT_FOUND_ERROR);
        }

        Long userId = userInfo.getId();

        // ResponsePostDTO 빌드
        return ResponsePostDTO.builder()
            .userId(userId)
            .title(existingPost.getTitle())
            .travelStartDate(existingPost.getTravelStartDate())
            .travelEndDate(existingPost.getTravelEndDate())
            .maxParticipants(existingPost.getMaxParticipants())
            .travelCountry(existingPost.getTravelCountry())
            .continent(existingPost.getContinent())
            .region(existingPost.getRegion())
            .accommodationFee(existingPost.getAccommodationFee())
            .transportationFee(existingPost.getTransportationFee())
            .airplaneFee(existingPost.getAirplaneFee())
            .foodFee(existingPost.getFoodFee())
            .limitMinAge(existingPost.getLimitMinAge())
            .limitMaxAge(existingPost.getLimitMaxAge())
            .limitSex(existingPost.getLimitSex())
            .limitSmoke(existingPost.getLimitSmoke())
            .deadline(existingPost.getDeadline())
            .days(existingPost.getDays().stream().map(dayEntity -> DayDTO.builder()
                .dayDetails(
                    dayEntity.getDayDetails().stream().map(dayDetailEntity -> DayDetailDTO.builder()
                        .title(dayDetailEntity.getTitle())
                        .description(dayDetailEntity.getDescription())
                        .fileAddress(dayDetailEntity.getFileAddress())
                        .build()).collect(Collectors.toList()))
                .itineraryVisits(dayEntity.getItineraryVisits().stream()
                    .map(visitEntity -> ItineraryVisitDTO.builder()
                        .latitude(visitEntity.getPoint().getY()) // Latitude는 Y 좌표
                        .longitude(visitEntity.getPoint().getX()) // Longitude는 X 좌표
                        .orderNumber(visitEntity.getOrderNumber())
                        .build()).collect(Collectors.toList()))
                .build()).collect(Collectors.toList()))
            .build();
    }


    @Transactional(readOnly = true)
    public Page<ResponsePostsDTO> searchPosts(PostSearchCriteria criteria, String userEmail,
        Pageable pageable) {
        // 사용자 정보 조회
        UserInfoResponseDTO userInfo;
        try {
            userInfo = userClientService.getUserInfo(userEmail);
            log.info("User Info: {}", userInfo);
        } catch (FeignException e) {
            log.error("userClient 호출 실패: {}", e.getMessage());
            throw new BizException(USER_INFO_CALL_ERROR);
        }

        if (userInfo == null) {
            throw new BizException(USER_NOT_FOUND_ERROR);
        }

        Page<PostEntity> posts = postRepository.findAll(PostSpecification.getPosts(criteria),
            pageable);
        return posts.map(this::mapToDTO);
    }

    private ResponsePostsDTO mapToDTO(PostEntity existingPost) {
        return ResponsePostsDTO.builder()
            .postId(existingPost.getPostId())
            .userId(existingPost.getUserId())
            .title(existingPost.getTitle())
            .travelStartDate(existingPost.getTravelStartDate())
            .travelEndDate(existingPost.getTravelEndDate())
            .maxParticipants(existingPost.getMaxParticipants())
            .travelCountry(existingPost.getTravelCountry().name())
            .continent(existingPost.getContinent().name())
            .region(existingPost.getRegion())
            .accommodationFee(existingPost.getAccommodationFee())
            .transportationFee(existingPost.getTransportationFee())
            .airplaneFee(existingPost.getAirplaneFee())
            .foodFee(existingPost.getFoodFee())
            .limitMinAge(existingPost.getLimitMinAge())
            .limitMaxAge(existingPost.getLimitMaxAge())
            .limitSex(existingPost.getLimitSex().name())
            .limitSmoke(existingPost.getLimitSmoke().name())
            .preferenceType(existingPost.getPreferenceType())
            .deadline(existingPost.getDeadline())
            .days(existingPost.getDays().stream().map(dayEntity -> DayDTO.builder()
                .dayDetails(
                    dayEntity.getDayDetails().stream().map(dayDetailEntity -> DayDetailDTO.builder()
                        .title(dayDetailEntity.getTitle())
                        .description(dayDetailEntity.getDescription())
                        .fileAddress(dayDetailEntity.getFileAddress())
                        .build()).collect(Collectors.toList()))
                .itineraryVisits(dayEntity.getItineraryVisits().stream()
                    .map(visitEntity -> ItineraryVisitDTO.builder()
                        .latitude(visitEntity.getPoint().getY())
                        .longitude(visitEntity.getPoint().getX())
                        .orderNumber(visitEntity.getOrderNumber())
                        .build()).collect(Collectors.toList()))
                .build()).collect(Collectors.toList()))
            .build();
    }
}
