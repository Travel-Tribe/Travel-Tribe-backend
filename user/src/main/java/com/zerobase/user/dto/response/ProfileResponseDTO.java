package com.zerobase.user.dto.response;

import com.zerobase.user.entity.LangAbilityEntity;
import com.zerobase.user.entity.ProfileEntity;
import com.zerobase.user.entity.VisitedCountryEntity;
import com.zerobase.user.type.Gender;
import com.zerobase.user.type.MBTI;
import com.zerobase.user.type.Smoking;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProfileResponseDTO {

    private Long id;
    private Long userId;
    private String introduction;
    private MBTI mbti;
    private Smoking smoking;
    private Gender gender;
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
            profileEntity.getSmoking(),
            profileEntity.getGender(),
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
}

