package com.zerobase.travel.post.dto.request;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class SQLScriptGenerator {
    public static void main(String[] args) {
        try {
            FileWriter writer = new FileWriter("generated_script.sql");

            // 변수 초기화
            int totalPosts = 50;
            int userId = 1;
            Random random = new Random();

            for (int i = 1; i <= totalPosts; i++) {
                // 게시물 정보 생성
                String mbti = generateMBTI(random);
                String title = "여행 제목 " + i;
                String travelStartDate = "2024-12-01";
                String travelEndDate = "2024-12-10";
                int maxParticipants = 6; // 1부터 10명 사이
                //random.nextInt(10) + 1
                String travelCountry = "KR";
                String continent = "ASIA";
                String region = "지역 " + i;
                int accommodationFee = random.nextInt(500000) + 100000;
                int otherExpenses = random.nextInt(200000) + 50000;
                int airplaneFee = random.nextInt(300000) + 100000;
                int limitMaxAge = random.nextInt(30) + 30; // 30부터 60세 사이
                int limitMinAge = random.nextInt(10) + 20; // 20부터 30세 사이
                String limitSex = "MALE";
                //generateLimitSex(random);
                String limitSmoke = "SMOKER";
                //generateLimitSmoke(random);
                String deadline = "2024-11-01";
                String status = "RECRUITING";

                // 게시물 INSERT 문 생성
                writer.write("-- 게시물 " + i + "\n");
                writer.write("INSERT INTO post (\n");
                writer.write("    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,\n");
                writer.write("    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,\n");
                writer.write("    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status\n");
                writer.write(") VALUES (\n");
                writer.write("    " + userId + ", '" + mbti + "', '" + title + "', '" + travelStartDate + "', '" + travelEndDate + "', " + maxParticipants + ",\n");
                writer.write("    '" + travelCountry + "', '" + continent + "', '" + region + "', " + accommodationFee + ", " + otherExpenses + ", " + airplaneFee + ",\n");
                writer.write("    " + limitMaxAge + ", " + limitMinAge + ", '" + limitSex + "', '" + limitSmoke + "', '" + deadline + "', '" + status + "'\n");
                writer.write(");\n\n");

                // 삽입된 게시물의 ID를 변수에 저장
                writer.write("SET @post_id" + i + " = LAST_INSERT_ID();\n\n");

                // 일차 정보 생성 (예: 5일 일정)
                int totalDays = 5;
                for (int day = 1; day <= totalDays; day++) {
                    writer.write("INSERT INTO day (post_id) VALUES (@post_id" + i + "); SET @day_id" + i + "_" + day + " = LAST_INSERT_ID();\n");
                }
                writer.write("\n");

                // 각 일차별 상세 정보 및 방문 장소 생성
                for (int day = 1; day <= totalDays; day++) {
                    // Day Detail 생성
                    int totalDetails = random.nextInt(3) + 1; // 각 일차별 1~3개의 상세 정보
                    writer.write("-- Day " + day + " 상세 정보\n");
                    writer.write("INSERT INTO day_detail (day_id, title, description, file_address) VALUES\n");
                    for (int detail = 1; detail <= totalDetails; detail++) {
                        String detailTitle = "활동 " + detail;
                        String description = "설명 " + detail;
                        String fileAddress = "http://example.com/image" + i + "_" + day + "_" + detail + ".jpg";
                        writer.write("    (@day_id" + i + "_" + day + ", '" + detailTitle + "', '" + description + "', '" + fileAddress + "')");
                        if (detail < totalDetails) {
                            writer.write(",\n");
                        } else {
                            writer.write(";\n\n");
                        }
                    }

                    // 방문 장소 생성
                    int totalVisits = random.nextInt(3) + 1; // 각 일차별 1~3개의 방문 장소
                    writer.write("-- Day " + day + " 방문 장소\n");
                    writer.write("INSERT INTO itinerary_visit (day_id, point, order_number) VALUES\n");
                    for (int visit = 1; visit <= totalVisits; visit++) {
                        double latitude = 35.0 + random.nextDouble();  // 예시 위도
                        double longitude = 128.0 + random.nextDouble(); // 예시 경도
                        int orderNumber = visit;
                        writer.write("    (@day_id" + i + "_" + day + ", ST_GeomFromText('POINT(" + latitude + " " + longitude + ")', 4326), " + orderNumber + ")");
                        if (visit < totalVisits) {
                            writer.write(",\n");
                        } else {
                            writer.write(";\n\n");
                        }
                    }
                }

                writer.write("\n");
                userId++; // 다음 사용자 ID로 증가
            }

            writer.close();
            System.out.println("SQL 스크립트 생성이 완료되었습니다.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // MBTI 랜덤 생성
    private static String generateMBTI(Random random) {
        String[] mbtiList = {
            "INTJ", "INTP", "ENTJ", "ENTP",
            "INFJ", "INFP", "ENFJ", "ENFP",
            "ISTJ", "ISFJ", "ESTJ", "ESFJ",
            "ISTP", "ISFP", "ESTP", "ESFP"
        };
        return mbtiList[random.nextInt(mbtiList.length)];
    }

    // 성별 제한 랜덤 생성
    private static String generateLimitSex(Random random) {
        String[] sexOptions = {"MALE"};
        //FEMALE, UNRELATED
        return sexOptions[random.nextInt(sexOptions.length)];
    }

    // 흡연 제한 랜덤 생성
    private static String generateLimitSmoke(Random random) {
        String[] smokeOptions = {"SMOKER"};
        // "NON_SMOKER", "UNRELATED"
        return smokeOptions[random.nextInt(smokeOptions.length)];
    }
}

