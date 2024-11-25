package com.zerobase.travel.exception.errorcode;

import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.FieldError;

@Getter
@RequiredArgsConstructor
public enum ValidErrorCode implements ErrorCode {

    NO_TITLE_ERROR("ERROR-POST-00001", "제목은 필수입니다."),
    NO_DESCRIPTION_ERROR("ERROR-POST-00002", "설명은 필수입니다."),
    MIN_DAY_DETAILS_ERROR("ERROR-POST-00003", "상세 일정은 최소 1개 이상."),
    MIN_ITINERARY_VISITS_ERROR("ERROR-POST-00004", "여행 일정 방문지는 최소 1개 이상."),
    LATITUDE_ERROR("ERROR-POST-00005", "위도는 필수입니다."),
    LONGITUDE_ERROR("ERROR-POST-00006", "경도는 필수입니다."),
    ORDER_NUMBER_ERROR("ERROR-POST-00007", "점좌표 순서는 필수입니다."),
    NO_START_DATE_ERROR("ERROR-POST-00008", "여행 시작 날짜는 필수입니다."),
    INVALID_START_DATE_ERROR("ERROR-POST-00009", "여행 시작 날짜는 현재보다 이전일 수 없습니다."),
    NO_END_DATE_ERROR("ERROR-POST-00010", "여행 종료 날짜는 필수입니다."),
    INVALID_END_DATE_ERROR("ERROR-POST-00011", "여행 종료 날짜는 미래여야 합니다."),
    NO_MAX_PARTICIPANTS_ERROR("ERROR-POST-00012", "최대 참여 인원은 필수입니다."),
    INVALID_MAX_PARTICIPANTS_ERROR("ERROR-POST-00013", "최대 참여 인원은 최소 1명 이상이어야 합니다."),
    NO_TRAVEL_COUNTRY_ERROR("ERROR-POST-00014", "여행 국가는 필수입니다."),
    NO_CONTINENT_ERROR("ERROR-POST-00015", "대륙은 필수입니다."),
    NO_REGION_ERROR("ERROR-POST-00016", "지역은 필수입니다."),
    NO_ACCOMMODATION_FEE_ERROR("ERROR-POST-00017", "숙박비는 필수입니다."),
    INVALID_ACCOMMODATION_FEE_ERROR("ERROR-POST-00018", "숙박비는 0 이상이어야 합니다."),
    NO_OTHER_EXPENSES_ERROR("ERROR-POST-00019", "기타 경비는 필수입니다."),
    INVALID_OTHER_EXPENSES_ERROR("ERROR-POST-00020", "기타 경비는 0 이상이어야 합니다."),
    NO_AIRPLANE_FEE_ERROR("ERROR-POST-00021", "비행기표 금액은 필수입니다."),
    INVALID_AIRPLANE_FEE_ERROR("ERROR-POST-00022", "비행기표 금액은 0 이상이어야 합니다."),
    NO_MAX_AGE_ERROR("ERROR-POST-00023", "최대 나이는 필수입니다."),
    INVALID_MAX_AGE_ERROR("ERROR-POST-00024", "최대 나이는 0 이상이어야 합니다."),
    NO_MIN_AGE_ERROR("ERROR-POST-00025", "최소 나이는 필수입니다."),
    INVALID_MIN_AGE_ERROR("ERROR-POST-00026", "최소 나이는 0 이상이어야 합니다."),
    NO_LIMIT_SEX_ERROR("ERROR-POST-00027", "성별 제한은 필수입니다."),
    NO_LIMIT_SMOKE_ERROR("ERROR-POST-00028", "흡연 제한은 필수입니다."),
    NO_DEADLINE_ERROR("ERROR-POST-00029", "마감 날짜는 필수입니다."),
    INVALID_DEADLINE_ERROR("ERROR-POST-00030", "마감 날짜는 현재보다 이후여야 합니다."),
    MIN_DAYS_ERROR("ERROR-POST-00031", "여행 일정은 최소 1일 이상이어야 합니다.");

    private final String errorCode;
    private final String errorMessage;

    public static ErrorCode findErrorCode(FieldError fieldError) {

        return Arrays.stream(ValidErrorCode.values())
            .filter(v -> v.getErrorCode().equals(fieldError.getDefaultMessage())) // USER-ERROR-VALID-00002
            .findAny()
            .orElseThrow(() -> new RuntimeException());
    }

}
