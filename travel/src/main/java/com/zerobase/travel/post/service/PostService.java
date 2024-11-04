package com.zerobase.travel.post.service;

import com.zerobase.travel.post.dto.request.DayDTO;
import com.zerobase.travel.post.dto.request.DayDetailDTO;
import com.zerobase.travel.post.dto.request.ItineraryVisitDTO;
import com.zerobase.travel.post.dto.request.PostDTO;
import com.zerobase.travel.post.entity.DayDetailEntity;
import com.zerobase.travel.post.entity.DayEntity;
import com.zerobase.travel.post.entity.ItineraryVisitEntity;
import com.zerobase.travel.post.entity.PostEntity;
import com.zerobase.travel.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final GeometryFactory geometryFactory = new GeometryFactory();

    @Transactional
    public void createPost(PostDTO postDTO, String userEmail) {

        // 이메일을 기반으로 userId 조회 -> 이쪽에서 이제 feignClient나 restTemplate쓰자!
        //Long userId = userService.findUserIdByEmail(userEmail);

        PostEntity postEntity = PostEntity.builder()
            .title(postDTO.getTitle())
            //.userId(userId)
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
            .preferenceType(postDTO.getPreferenceType())
            .deadline(postDTO.getDeadline())
            .status(postDTO.getStatus())
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
}
