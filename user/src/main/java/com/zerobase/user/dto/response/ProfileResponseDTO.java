package com.zerobase.user.dto.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.zerobase.user.entity.LangAbilityEntity;
import com.zerobase.user.entity.ProfileEntity;
import com.zerobase.user.entity.VisitedCountryEntity;
import com.zerobase.user.type.MBTI;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@Builder
@AllArgsConstructor
@JsonDeserialize(builder = ProfileResponseDTO.ProfileResponseDTOBuilder.class)
public class ProfileResponseDTO {

    private Long id;
    private Long userId;
    private String introduction;
    private MBTI mbti;
    private String smoking;
    private String gender;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate birth;
    private Double ratingAvg;
    private String fileAddress;
    private List<String> langAbilities;
    private List<String> visitedCountries;

    // 정적 팩토리 메서드 추가
    public static ProfileResponseDTO fromEntity(ProfileEntity profileEntity) {
        return new ProfileResponseDTO(
            profileEntity.getId(),
            profileEntity.getUser().getId(),
            profileEntity.getIntroduction(),
            profileEntity.getMbti(),
            profileEntity.getSmoking().getSmoke(),
            profileEntity.getGender().getGender(),
            profileEntity.getBirth(),
            profileEntity.getRatingAvg(),
            profileEntity.getFileAddress(),
            profileEntity.getLangAbilities().stream()
                .map(LangAbilityEntity::getLang)
                .toList(),
            profileEntity.getVisitedCountries().stream()
                .map(VisitedCountryEntity::getCountryName)
                .toList()
        );
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class ProfileResponseDTOBuilder {

    }
}

