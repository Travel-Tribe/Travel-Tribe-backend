-- 게시물 1
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    1, 'INTP', '여행 제목 1', '2024-12-01', '2024-12-10', 7,
    'KR', 'ASIA', '지역 1', 330587, 56390, 273771,
    54, 25, 'FEMALE', 'NON_SMOKER', '2024-11-01', 'RECRUITING'
);

SET @post_id1 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id1); SET @day_id1_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id1); SET @day_id1_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id1); SET @day_id1_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id1); SET @day_id1_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id1); SET @day_id1_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id1_1, '활동 1', '설명 1', 'http://example.com/image1_1_1.jpg'),
    (@day_id1_1, '활동 2', '설명 2', 'http://example.com/image1_1_2.jpg'),
    (@day_id1_1, '활동 3', '설명 3', 'http://example.com/image1_1_3.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id1_1, ST_GeomFromText('POINT(35.15581847389539 128.4714887248374)', 4326), 1),
    (@day_id1_1, ST_GeomFromText('POINT(35.368486447327875 128.9849431607306)', 4326), 2);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id1_2, '활동 1', '설명 1', 'http://example.com/image1_2_1.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id1_2, ST_GeomFromText('POINT(35.62603973452646 128.0016253215384)', 4326), 1),
    (@day_id1_2, ST_GeomFromText('POINT(35.17417672194845 128.63110076225905)', 4326), 2);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id1_3, '활동 1', '설명 1', 'http://example.com/image1_3_1.jpg'),
    (@day_id1_3, '활동 2', '설명 2', 'http://example.com/image1_3_2.jpg'),
    (@day_id1_3, '활동 3', '설명 3', 'http://example.com/image1_3_3.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id1_3, ST_GeomFromText('POINT(35.497101977983334 128.36243257848457)', 4326), 1),
    (@day_id1_3, ST_GeomFromText('POINT(35.833040633183785 128.91765794491837)', 4326), 2),
    (@day_id1_3, ST_GeomFromText('POINT(35.20871534845577 128.1155436984658)', 4326), 3);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id1_4, '활동 1', '설명 1', 'http://example.com/image1_4_1.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id1_4, ST_GeomFromText('POINT(35.54705810606953 128.55696081145496)', 4326), 1),
    (@day_id1_4, ST_GeomFromText('POINT(35.27496989062846 128.102553673325)', 4326), 2);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id1_5, '활동 1', '설명 1', 'http://example.com/image1_5_1.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id1_5, ST_GeomFromText('POINT(35.85268064055325 128.42326915394634)', 4326), 1),
    (@day_id1_5, ST_GeomFromText('POINT(35.316188940240664 128.85992822386848)', 4326), 2),
    (@day_id1_5, ST_GeomFromText('POINT(35.371344610731924 128.2795285199271)', 4326), 3);


-- 게시물 2
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    2, 'ESTJ', '여행 제목 2', '2024-12-01', '2024-12-10', 10,
    'KR', 'ASIA', '지역 2', 392000, 214747, 258531,
    51, 26, 'MALE', 'SMOKER', '2024-11-01', 'RECRUITING'
);

SET @post_id2 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id2); SET @day_id2_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id2); SET @day_id2_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id2); SET @day_id2_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id2); SET @day_id2_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id2); SET @day_id2_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id2_1, '활동 1', '설명 1', 'http://example.com/image2_1_1.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id2_1, ST_GeomFromText('POINT(35.650555718919065 128.6189678724163)', 4326), 1),
    (@day_id2_1, ST_GeomFromText('POINT(35.57990769835085 128.85192444822707)', 4326), 2);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id2_2, '활동 1', '설명 1', 'http://example.com/image2_2_1.jpg'),
    (@day_id2_2, '활동 2', '설명 2', 'http://example.com/image2_2_2.jpg'),
    (@day_id2_2, '활동 3', '설명 3', 'http://example.com/image2_2_3.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id2_2, ST_GeomFromText('POINT(35.61249042917433 128.93351558349374)', 4326), 1),
    (@day_id2_2, ST_GeomFromText('POINT(35.940995623369695 128.4905380317511)', 4326), 2),
    (@day_id2_2, ST_GeomFromText('POINT(35.54595293246957 128.19138693950677)', 4326), 3);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id2_3, '활동 1', '설명 1', 'http://example.com/image2_3_1.jpg'),
    (@day_id2_3, '활동 2', '설명 2', 'http://example.com/image2_3_2.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id2_3, ST_GeomFromText('POINT(35.81054566012138 128.5162464856561)', 4326), 1),
    (@day_id2_3, ST_GeomFromText('POINT(35.931164742646345 128.79100744861802)', 4326), 2);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id2_4, '활동 1', '설명 1', 'http://example.com/image2_4_1.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id2_4, ST_GeomFromText('POINT(35.62113809657374 128.25841908724007)', 4326), 1),
    (@day_id2_4, ST_GeomFromText('POINT(35.28001962379294 128.17741481337654)', 4326), 2),
    (@day_id2_4, ST_GeomFromText('POINT(35.30684491628964 128.77892477331628)', 4326), 3);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id2_5, '활동 1', '설명 1', 'http://example.com/image2_5_1.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id2_5, ST_GeomFromText('POINT(35.92622300073775 128.61024709980916)', 4326), 1);


-- 게시물 3
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    3, 'ISTP', '여행 제목 3', '2024-12-01', '2024-12-10', 6,
    'KR', 'ASIA', '지역 3', 389732, 67843, 126092,
    56, 28, 'FEMALE', 'NON_SMOKER', '2024-11-01', 'RECRUITING'
);

SET @post_id3 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id3); SET @day_id3_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id3); SET @day_id3_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id3); SET @day_id3_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id3); SET @day_id3_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id3); SET @day_id3_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id3_1, '활동 1', '설명 1', 'http://example.com/image3_1_1.jpg'),
    (@day_id3_1, '활동 2', '설명 2', 'http://example.com/image3_1_2.jpg'),
    (@day_id3_1, '활동 3', '설명 3', 'http://example.com/image3_1_3.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id3_1, ST_GeomFromText('POINT(35.100045734056756 128.33030328770482)', 4326), 1);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id3_2, '활동 1', '설명 1', 'http://example.com/image3_2_1.jpg'),
    (@day_id3_2, '활동 2', '설명 2', 'http://example.com/image3_2_2.jpg'),
    (@day_id3_2, '활동 3', '설명 3', 'http://example.com/image3_2_3.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id3_2, ST_GeomFromText('POINT(35.60358034184264 128.28931600182486)', 4326), 1),
    (@day_id3_2, ST_GeomFromText('POINT(35.454836403209875 128.54444704332604)', 4326), 2);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id3_3, '활동 1', '설명 1', 'http://example.com/image3_3_1.jpg'),
    (@day_id3_3, '활동 2', '설명 2', 'http://example.com/image3_3_2.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id3_3, ST_GeomFromText('POINT(35.9297640151719 128.44136491497898)', 4326), 1),
    (@day_id3_3, ST_GeomFromText('POINT(35.54745631057815 128.20976440373084)', 4326), 2);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id3_4, '활동 1', '설명 1', 'http://example.com/image3_4_1.jpg'),
    (@day_id3_4, '활동 2', '설명 2', 'http://example.com/image3_4_2.jpg'),
    (@day_id3_4, '활동 3', '설명 3', 'http://example.com/image3_4_3.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id3_4, ST_GeomFromText('POINT(35.20595437487514 128.19719117102835)', 4326), 1);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id3_5, '활동 1', '설명 1', 'http://example.com/image3_5_1.jpg'),
    (@day_id3_5, '활동 2', '설명 2', 'http://example.com/image3_5_2.jpg'),
    (@day_id3_5, '활동 3', '설명 3', 'http://example.com/image3_5_3.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id3_5, ST_GeomFromText('POINT(35.43857992079335 128.17242837731916)', 4326), 1),
    (@day_id3_5, ST_GeomFromText('POINT(35.1734063714513 128.63972362266054)', 4326), 2),
    (@day_id3_5, ST_GeomFromText('POINT(35.47561378639744 128.2083998138898)', 4326), 3);


-- 게시물 4
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    4, 'ISFP', '여행 제목 4', '2024-12-01', '2024-12-10', 10,
    'KR', 'ASIA', '지역 4', 163117, 105352, 396557,
    55, 25, 'MALE', 'NON_SMOKER', '2024-11-01', 'RECRUITING'
);

SET @post_id4 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id4); SET @day_id4_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id4); SET @day_id4_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id4); SET @day_id4_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id4); SET @day_id4_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id4); SET @day_id4_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id4_1, '활동 1', '설명 1', 'http://example.com/image4_1_1.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id4_1, ST_GeomFromText('POINT(35.445706664104776 128.9046425563125)', 4326), 1),
    (@day_id4_1, ST_GeomFromText('POINT(35.0565570618246 128.8953823352067)', 4326), 2);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id4_2, '활동 1', '설명 1', 'http://example.com/image4_2_1.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id4_2, ST_GeomFromText('POINT(35.37559876702432 128.86010573294962)', 4326), 1);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id4_3, '활동 1', '설명 1', 'http://example.com/image4_3_1.jpg'),
    (@day_id4_3, '활동 2', '설명 2', 'http://example.com/image4_3_2.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id4_3, ST_GeomFromText('POINT(35.19571004524459 128.8718404833655)', 4326), 1),
    (@day_id4_3, ST_GeomFromText('POINT(35.741630314663034 128.35676111082577)', 4326), 2),
    (@day_id4_3, ST_GeomFromText('POINT(35.361561596579946 128.23490941611703)', 4326), 3);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id4_4, '활동 1', '설명 1', 'http://example.com/image4_4_1.jpg'),
    (@day_id4_4, '활동 2', '설명 2', 'http://example.com/image4_4_2.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id4_4, ST_GeomFromText('POINT(35.80256515013645 128.54699848036412)', 4326), 1),
    (@day_id4_4, ST_GeomFromText('POINT(35.15562150644878 128.95050100179907)', 4326), 2);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id4_5, '활동 1', '설명 1', 'http://example.com/image4_5_1.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id4_5, ST_GeomFromText('POINT(35.46214423997466 128.9557378476787)', 4326), 1),
    (@day_id4_5, ST_GeomFromText('POINT(35.69246264500702 128.59330026685487)', 4326), 2);


-- 게시물 5
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    5, 'INFP', '여행 제목 5', '2024-12-01', '2024-12-10', 8,
    'KR', 'ASIA', '지역 5', 276015, 222944, 140211,
    47, 23, 'UNRELATED', 'NON_SMOKER', '2024-11-01', 'RECRUITING'
);

SET @post_id5 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id5); SET @day_id5_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id5); SET @day_id5_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id5); SET @day_id5_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id5); SET @day_id5_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id5); SET @day_id5_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id5_1, '활동 1', '설명 1', 'http://example.com/image5_1_1.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id5_1, ST_GeomFromText('POINT(35.81515698426051 128.10218227015898)', 4326), 1);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id5_2, '활동 1', '설명 1', 'http://example.com/image5_2_1.jpg'),
    (@day_id5_2, '활동 2', '설명 2', 'http://example.com/image5_2_2.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id5_2, ST_GeomFromText('POINT(35.243658213276234 128.78021490432496)', 4326), 1),
    (@day_id5_2, ST_GeomFromText('POINT(35.49919630603718 128.3895194273186)', 4326), 2),
    (@day_id5_2, ST_GeomFromText('POINT(35.27067822622384 128.23925337482174)', 4326), 3);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id5_3, '활동 1', '설명 1', 'http://example.com/image5_3_1.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id5_3, ST_GeomFromText('POINT(35.52816426919266 128.83246759714865)', 4326), 1),
    (@day_id5_3, ST_GeomFromText('POINT(35.32608876552781 128.52546734641734)', 4326), 2),
    (@day_id5_3, ST_GeomFromText('POINT(35.59979514289624 128.47843340668865)', 4326), 3);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id5_4, '활동 1', '설명 1', 'http://example.com/image5_4_1.jpg'),
    (@day_id5_4, '활동 2', '설명 2', 'http://example.com/image5_4_2.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id5_4, ST_GeomFromText('POINT(35.82220975470903 128.68054252971552)', 4326), 1),
    (@day_id5_4, ST_GeomFromText('POINT(35.1250957432306 128.60838054810398)', 4326), 2),
    (@day_id5_4, ST_GeomFromText('POINT(35.352044924104064 128.74775662989427)', 4326), 3);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id5_5, '활동 1', '설명 1', 'http://example.com/image5_5_1.jpg'),
    (@day_id5_5, '활동 2', '설명 2', 'http://example.com/image5_5_2.jpg'),
    (@day_id5_5, '활동 3', '설명 3', 'http://example.com/image5_5_3.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id5_5, ST_GeomFromText('POINT(35.77641378412242 128.83081454130996)', 4326), 1),
    (@day_id5_5, ST_GeomFromText('POINT(35.269573901674605 128.77417227786017)', 4326), 2),
    (@day_id5_5, ST_GeomFromText('POINT(35.08762126417218 128.32094739346212)', 4326), 3);


-- 게시물 6
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    6, 'ESTP', '여행 제목 6', '2024-12-01', '2024-12-10', 1,
    'KR', 'ASIA', '지역 6', 561440, 202761, 369461,
    36, 25, 'FEMALE', 'SMOKER', '2024-11-01', 'RECRUITING'
);

SET @post_id6 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id6); SET @day_id6_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id6); SET @day_id6_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id6); SET @day_id6_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id6); SET @day_id6_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id6); SET @day_id6_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id6_1, '활동 1', '설명 1', 'http://example.com/image6_1_1.jpg'),
    (@day_id6_1, '활동 2', '설명 2', 'http://example.com/image6_1_2.jpg'),
    (@day_id6_1, '활동 3', '설명 3', 'http://example.com/image6_1_3.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id6_1, ST_GeomFromText('POINT(35.07721825267851 128.98308569982302)', 4326), 1),
    (@day_id6_1, ST_GeomFromText('POINT(35.87753450686463 128.2773733870489)', 4326), 2);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id6_2, '활동 1', '설명 1', 'http://example.com/image6_2_1.jpg'),
    (@day_id6_2, '활동 2', '설명 2', 'http://example.com/image6_2_2.jpg'),
    (@day_id6_2, '활동 3', '설명 3', 'http://example.com/image6_2_3.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id6_2, ST_GeomFromText('POINT(35.631838842776375 128.43474449467837)', 4326), 1);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id6_3, '활동 1', '설명 1', 'http://example.com/image6_3_1.jpg'),
    (@day_id6_3, '활동 2', '설명 2', 'http://example.com/image6_3_2.jpg'),
    (@day_id6_3, '활동 3', '설명 3', 'http://example.com/image6_3_3.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id6_3, ST_GeomFromText('POINT(35.681273433801486 128.7283849532186)', 4326), 1),
    (@day_id6_3, ST_GeomFromText('POINT(35.8013682702974 128.65378902586482)', 4326), 2),
    (@day_id6_3, ST_GeomFromText('POINT(35.70846278535928 128.09248376151433)', 4326), 3);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id6_4, '활동 1', '설명 1', 'http://example.com/image6_4_1.jpg'),
    (@day_id6_4, '활동 2', '설명 2', 'http://example.com/image6_4_2.jpg'),
    (@day_id6_4, '활동 3', '설명 3', 'http://example.com/image6_4_3.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id6_4, ST_GeomFromText('POINT(35.32537032864719 128.06893775855772)', 4326), 1),
    (@day_id6_4, ST_GeomFromText('POINT(35.94606213860719 128.0613350910954)', 4326), 2),
    (@day_id6_4, ST_GeomFromText('POINT(35.07783810357024 128.22346763947317)', 4326), 3);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id6_5, '활동 1', '설명 1', 'http://example.com/image6_5_1.jpg'),
    (@day_id6_5, '활동 2', '설명 2', 'http://example.com/image6_5_2.jpg'),
    (@day_id6_5, '활동 3', '설명 3', 'http://example.com/image6_5_3.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id6_5, ST_GeomFromText('POINT(35.70532989776082 128.2814410143448)', 4326), 1),
    (@day_id6_5, ST_GeomFromText('POINT(35.608084446403325 128.73068570331313)', 4326), 2);


-- 게시물 7
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    7, 'ENTP', '여행 제목 7', '2024-12-01', '2024-12-10', 2,
    'KR', 'ASIA', '지역 7', 374191, 215908, 170452,
    52, 23, 'FEMALE', 'SMOKER', '2024-11-01', 'RECRUITING'
);

SET @post_id7 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id7); SET @day_id7_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id7); SET @day_id7_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id7); SET @day_id7_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id7); SET @day_id7_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id7); SET @day_id7_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id7_1, '활동 1', '설명 1', 'http://example.com/image7_1_1.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id7_1, ST_GeomFromText('POINT(35.81270289420877 128.5579980865164)', 4326), 1);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id7_2, '활동 1', '설명 1', 'http://example.com/image7_2_1.jpg'),
    (@day_id7_2, '활동 2', '설명 2', 'http://example.com/image7_2_2.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id7_2, ST_GeomFromText('POINT(35.043613983480256 128.06949409812438)', 4326), 1),
    (@day_id7_2, ST_GeomFromText('POINT(35.32275718436465 128.5888614719048)', 4326), 2);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id7_3, '활동 1', '설명 1', 'http://example.com/image7_3_1.jpg'),
    (@day_id7_3, '활동 2', '설명 2', 'http://example.com/image7_3_2.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id7_3, ST_GeomFromText('POINT(35.101295511462176 128.42503627390963)', 4326), 1),
    (@day_id7_3, ST_GeomFromText('POINT(35.594823058774374 128.79231874002102)', 4326), 2);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id7_4, '활동 1', '설명 1', 'http://example.com/image7_4_1.jpg'),
    (@day_id7_4, '활동 2', '설명 2', 'http://example.com/image7_4_2.jpg'),
    (@day_id7_4, '활동 3', '설명 3', 'http://example.com/image7_4_3.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id7_4, ST_GeomFromText('POINT(35.88502846897175 128.02986750617146)', 4326), 1),
    (@day_id7_4, ST_GeomFromText('POINT(35.05562212890733 128.280608560604)', 4326), 2),
    (@day_id7_4, ST_GeomFromText('POINT(35.867531555142996 128.67815088573917)', 4326), 3);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id7_5, '활동 1', '설명 1', 'http://example.com/image7_5_1.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id7_5, ST_GeomFromText('POINT(35.59418662168794 128.79416771975167)', 4326), 1),
    (@day_id7_5, ST_GeomFromText('POINT(35.29017521437198 128.2859102477773)', 4326), 2);


-- 게시물 8
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    8, 'INTJ', '여행 제목 8', '2024-12-01', '2024-12-10', 2,
    'KR', 'ASIA', '지역 8', 380929, 170902, 380768,
    52, 23, 'MALE', 'UNRELATED', '2024-11-01', 'RECRUITING'
);

SET @post_id8 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id8); SET @day_id8_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id8); SET @day_id8_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id8); SET @day_id8_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id8); SET @day_id8_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id8); SET @day_id8_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id8_1, '활동 1', '설명 1', 'http://example.com/image8_1_1.jpg'),
    (@day_id8_1, '활동 2', '설명 2', 'http://example.com/image8_1_2.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id8_1, ST_GeomFromText('POINT(35.15868228828499 128.974637924157)', 4326), 1),
    (@day_id8_1, ST_GeomFromText('POINT(35.22198963160341 128.81197429147088)', 4326), 2),
    (@day_id8_1, ST_GeomFromText('POINT(35.88343023343064 128.96335624356303)', 4326), 3);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id8_2, '활동 1', '설명 1', 'http://example.com/image8_2_1.jpg'),
    (@day_id8_2, '활동 2', '설명 2', 'http://example.com/image8_2_2.jpg'),
    (@day_id8_2, '활동 3', '설명 3', 'http://example.com/image8_2_3.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id8_2, ST_GeomFromText('POINT(35.36799320317967 128.5242351639169)', 4326), 1),
    (@day_id8_2, ST_GeomFromText('POINT(35.282035080160554 128.16174942113466)', 4326), 2),
    (@day_id8_2, ST_GeomFromText('POINT(35.2069640391511 128.89872701956781)', 4326), 3);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id8_3, '활동 1', '설명 1', 'http://example.com/image8_3_1.jpg'),
    (@day_id8_3, '활동 2', '설명 2', 'http://example.com/image8_3_2.jpg'),
    (@day_id8_3, '활동 3', '설명 3', 'http://example.com/image8_3_3.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id8_3, ST_GeomFromText('POINT(35.60845041597317 128.0700632133493)', 4326), 1),
    (@day_id8_3, ST_GeomFromText('POINT(35.37689201389785 128.97240384972014)', 4326), 2);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id8_4, '활동 1', '설명 1', 'http://example.com/image8_4_1.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id8_4, ST_GeomFromText('POINT(35.401898678522286 128.24541410047595)', 4326), 1),
    (@day_id8_4, ST_GeomFromText('POINT(35.15270317724682 128.07010460382156)', 4326), 2);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id8_5, '활동 1', '설명 1', 'http://example.com/image8_5_1.jpg'),
    (@day_id8_5, '활동 2', '설명 2', 'http://example.com/image8_5_2.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id8_5, ST_GeomFromText('POINT(35.38261019865001 128.3274792769894)', 4326), 1),
    (@day_id8_5, ST_GeomFromText('POINT(35.30185272378299 128.47727040248665)', 4326), 2),
    (@day_id8_5, ST_GeomFromText('POINT(35.89755374571799 128.39602334301821)', 4326), 3);


-- 게시물 9
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    9, 'ISTP', '여행 제목 9', '2024-12-01', '2024-12-10', 8,
    'KR', 'ASIA', '지역 9', 211405, 68088, 290623,
    45, 21, 'UNRELATED', 'NON_SMOKER', '2024-11-01', 'RECRUITING'
);

SET @post_id9 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id9); SET @day_id9_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id9); SET @day_id9_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id9); SET @day_id9_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id9); SET @day_id9_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id9); SET @day_id9_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id9_1, '활동 1', '설명 1', 'http://example.com/image9_1_1.jpg'),
    (@day_id9_1, '활동 2', '설명 2', 'http://example.com/image9_1_2.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id9_1, ST_GeomFromText('POINT(35.41160873841303 128.27519357415028)', 4326), 1),
    (@day_id9_1, ST_GeomFromText('POINT(35.309932139971224 128.87343367546873)', 4326), 2);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id9_2, '활동 1', '설명 1', 'http://example.com/image9_2_1.jpg'),
    (@day_id9_2, '활동 2', '설명 2', 'http://example.com/image9_2_2.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id9_2, ST_GeomFromText('POINT(35.37883993178532 128.53729516941613)', 4326), 1),
    (@day_id9_2, ST_GeomFromText('POINT(35.585948872105604 128.74913799100446)', 4326), 2);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id9_3, '활동 1', '설명 1', 'http://example.com/image9_3_1.jpg'),
    (@day_id9_3, '활동 2', '설명 2', 'http://example.com/image9_3_2.jpg'),
    (@day_id9_3, '활동 3', '설명 3', 'http://example.com/image9_3_3.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id9_3, ST_GeomFromText('POINT(35.75843499476431 128.34118651944712)', 4326), 1),
    (@day_id9_3, ST_GeomFromText('POINT(35.009573352622915 128.83666318912648)', 4326), 2),
    (@day_id9_3, ST_GeomFromText('POINT(35.59770383976414 128.00409615477784)', 4326), 3);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id9_4, '활동 1', '설명 1', 'http://example.com/image9_4_1.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id9_4, ST_GeomFromText('POINT(35.231379707724955 128.05268612667325)', 4326), 1),
    (@day_id9_4, ST_GeomFromText('POINT(35.164001599648536 128.0711678449941)', 4326), 2),
    (@day_id9_4, ST_GeomFromText('POINT(35.499559482798546 128.04827319659927)', 4326), 3);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id9_5, '활동 1', '설명 1', 'http://example.com/image9_5_1.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id9_5, ST_GeomFromText('POINT(35.48001569059049 128.70611548761883)', 4326), 1),
    (@day_id9_5, ST_GeomFromText('POINT(35.27864552789381 128.50677240380762)', 4326), 2),
    (@day_id9_5, ST_GeomFromText('POINT(35.81480735208588 128.95469034365092)', 4326), 3);


-- 게시물 10
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    10, 'INTP', '여행 제목 10', '2024-12-01', '2024-12-10', 6,
    'KR', 'ASIA', '지역 10', 404021, 187120, 202869,
    43, 21, 'FEMALE', 'NON_SMOKER', '2024-11-01', 'RECRUITING'
);

SET @post_id10 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id10); SET @day_id10_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id10); SET @day_id10_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id10); SET @day_id10_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id10); SET @day_id10_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id10); SET @day_id10_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id10_1, '활동 1', '설명 1', 'http://example.com/image10_1_1.jpg'),
    (@day_id10_1, '활동 2', '설명 2', 'http://example.com/image10_1_2.jpg'),
    (@day_id10_1, '활동 3', '설명 3', 'http://example.com/image10_1_3.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id10_1, ST_GeomFromText('POINT(35.751582446839016 128.22117372065523)', 4326), 1),
    (@day_id10_1, ST_GeomFromText('POINT(35.790547573105805 128.01965445494966)', 4326), 2);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id10_2, '활동 1', '설명 1', 'http://example.com/image10_2_1.jpg'),
    (@day_id10_2, '활동 2', '설명 2', 'http://example.com/image10_2_2.jpg'),
    (@day_id10_2, '활동 3', '설명 3', 'http://example.com/image10_2_3.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id10_2, ST_GeomFromText('POINT(35.15876836094538 128.69425845987746)', 4326), 1),
    (@day_id10_2, ST_GeomFromText('POINT(35.86305907460485 128.04260168747385)', 4326), 2);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id10_3, '활동 1', '설명 1', 'http://example.com/image10_3_1.jpg'),
    (@day_id10_3, '활동 2', '설명 2', 'http://example.com/image10_3_2.jpg'),
    (@day_id10_3, '활동 3', '설명 3', 'http://example.com/image10_3_3.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id10_3, ST_GeomFromText('POINT(35.51597365709094 128.47819752706158)', 4326), 1),
    (@day_id10_3, ST_GeomFromText('POINT(35.72813761527326 128.3313365211193)', 4326), 2);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id10_4, '활동 1', '설명 1', 'http://example.com/image10_4_1.jpg'),
    (@day_id10_4, '활동 2', '설명 2', 'http://example.com/image10_4_2.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id10_4, ST_GeomFromText('POINT(35.4403562652553 128.007102289715)', 4326), 1),
    (@day_id10_4, ST_GeomFromText('POINT(35.5273638515638 128.83194092306178)', 4326), 2),
    (@day_id10_4, ST_GeomFromText('POINT(35.01793060434685 128.06387430915277)', 4326), 3);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id10_5, '활동 1', '설명 1', 'http://example.com/image10_5_1.jpg'),
    (@day_id10_5, '활동 2', '설명 2', 'http://example.com/image10_5_2.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id10_5, ST_GeomFromText('POINT(35.58886193549544 128.46337772715623)', 4326), 1);


-- 게시물 11
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    11, 'INTP', '여행 제목 11', '2024-12-01', '2024-12-10', 6,
    'KR', 'ASIA', '지역 11', 290671, 84767, 208251,
    52, 22, 'MALE', 'UNRELATED', '2024-11-01', 'RECRUITING'
);

SET @post_id11 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id11); SET @day_id11_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id11); SET @day_id11_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id11); SET @day_id11_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id11); SET @day_id11_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id11); SET @day_id11_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id11_1, '활동 1', '설명 1', 'http://example.com/image11_1_1.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id11_1, ST_GeomFromText('POINT(35.83133242254941 128.38062174149846)', 4326), 1);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id11_2, '활동 1', '설명 1', 'http://example.com/image11_2_1.jpg'),
    (@day_id11_2, '활동 2', '설명 2', 'http://example.com/image11_2_2.jpg'),
    (@day_id11_2, '활동 3', '설명 3', 'http://example.com/image11_2_3.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id11_2, ST_GeomFromText('POINT(35.67268039711405 128.30613626853278)', 4326), 1),
    (@day_id11_2, ST_GeomFromText('POINT(35.19605633641044 128.57717500286523)', 4326), 2);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id11_3, '활동 1', '설명 1', 'http://example.com/image11_3_1.jpg'),
    (@day_id11_3, '활동 2', '설명 2', 'http://example.com/image11_3_2.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id11_3, ST_GeomFromText('POINT(35.06909973730809 128.14870391144765)', 4326), 1),
    (@day_id11_3, ST_GeomFromText('POINT(35.673570519972294 128.9246827715294)', 4326), 2),
    (@day_id11_3, ST_GeomFromText('POINT(35.38813959862851 128.9281919522829)', 4326), 3);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id11_4, '활동 1', '설명 1', 'http://example.com/image11_4_1.jpg'),
    (@day_id11_4, '활동 2', '설명 2', 'http://example.com/image11_4_2.jpg'),
    (@day_id11_4, '활동 3', '설명 3', 'http://example.com/image11_4_3.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id11_4, ST_GeomFromText('POINT(35.98634121994795 128.68167678841513)', 4326), 1),
    (@day_id11_4, ST_GeomFromText('POINT(35.95631123541386 128.3883861007746)', 4326), 2);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id11_5, '활동 1', '설명 1', 'http://example.com/image11_5_1.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id11_5, ST_GeomFromText('POINT(35.96837882896883 128.88818824264487)', 4326), 1),
    (@day_id11_5, ST_GeomFromText('POINT(35.48044916379273 128.40458017099175)', 4326), 2);


-- 게시물 12
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    12, 'ENFP', '여행 제목 12', '2024-12-01', '2024-12-10', 6,
    'KR', 'ASIA', '지역 12', 547935, 213101, 297985,
    33, 23, 'MALE', 'UNRELATED', '2024-11-01', 'RECRUITING'
);

SET @post_id12 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id12); SET @day_id12_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id12); SET @day_id12_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id12); SET @day_id12_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id12); SET @day_id12_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id12); SET @day_id12_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id12_1, '활동 1', '설명 1', 'http://example.com/image12_1_1.jpg'),
    (@day_id12_1, '활동 2', '설명 2', 'http://example.com/image12_1_2.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id12_1, ST_GeomFromText('POINT(35.972845428273104 128.89927944374512)', 4326), 1),
    (@day_id12_1, ST_GeomFromText('POINT(35.39215427599624 128.70099852763522)', 4326), 2);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id12_2, '활동 1', '설명 1', 'http://example.com/image12_2_1.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id12_2, ST_GeomFromText('POINT(35.06070768965764 128.7815945795158)', 4326), 1),
    (@day_id12_2, ST_GeomFromText('POINT(35.78552318155145 128.19777157730482)', 4326), 2),
    (@day_id12_2, ST_GeomFromText('POINT(35.364523793264176 128.27600997707083)', 4326), 3);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id12_3, '활동 1', '설명 1', 'http://example.com/image12_3_1.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id12_3, ST_GeomFromText('POINT(35.68311902533201 128.9442450550838)', 4326), 1),
    (@day_id12_3, ST_GeomFromText('POINT(35.521460617251044 128.38285818640853)', 4326), 2);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id12_4, '활동 1', '설명 1', 'http://example.com/image12_4_1.jpg'),
    (@day_id12_4, '활동 2', '설명 2', 'http://example.com/image12_4_2.jpg'),
    (@day_id12_4, '활동 3', '설명 3', 'http://example.com/image12_4_3.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id12_4, ST_GeomFromText('POINT(35.3747668765413 128.70815441374378)', 4326), 1),
    (@day_id12_4, ST_GeomFromText('POINT(35.70771928507457 128.54065253013263)', 4326), 2),
    (@day_id12_4, ST_GeomFromText('POINT(35.0258722569425 128.86064708705584)', 4326), 3);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id12_5, '활동 1', '설명 1', 'http://example.com/image12_5_1.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id12_5, ST_GeomFromText('POINT(35.378417626767245 128.51795312539977)', 4326), 1),
    (@day_id12_5, ST_GeomFromText('POINT(35.52289479548904 128.59294429247873)', 4326), 2),
    (@day_id12_5, ST_GeomFromText('POINT(35.50098348922823 128.79852899326497)', 4326), 3);


-- 게시물 13
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    13, 'ISFP', '여행 제목 13', '2024-12-01', '2024-12-10', 9,
    'KR', 'ASIA', '지역 13', 506733, 225848, 244234,
    30, 25, 'UNRELATED', 'NON_SMOKER', '2024-11-01', 'RECRUITING'
);

SET @post_id13 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id13); SET @day_id13_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id13); SET @day_id13_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id13); SET @day_id13_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id13); SET @day_id13_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id13); SET @day_id13_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id13_1, '활동 1', '설명 1', 'http://example.com/image13_1_1.jpg'),
    (@day_id13_1, '활동 2', '설명 2', 'http://example.com/image13_1_2.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id13_1, ST_GeomFromText('POINT(35.136612408987894 128.39610808220104)', 4326), 1),
    (@day_id13_1, ST_GeomFromText('POINT(35.3622634816219 128.1380812522417)', 4326), 2),
    (@day_id13_1, ST_GeomFromText('POINT(35.239044761869714 128.32256930522985)', 4326), 3);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id13_2, '활동 1', '설명 1', 'http://example.com/image13_2_1.jpg'),
    (@day_id13_2, '활동 2', '설명 2', 'http://example.com/image13_2_2.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id13_2, ST_GeomFromText('POINT(35.55815885344847 128.0407485127535)', 4326), 1);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id13_3, '활동 1', '설명 1', 'http://example.com/image13_3_1.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id13_3, ST_GeomFromText('POINT(35.21029336938462 128.9653984999254)', 4326), 1);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id13_4, '활동 1', '설명 1', 'http://example.com/image13_4_1.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id13_4, ST_GeomFromText('POINT(35.42610861555136 128.19200742716313)', 4326), 1),
    (@day_id13_4, ST_GeomFromText('POINT(35.96931214653168 128.31523058303037)', 4326), 2);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id13_5, '활동 1', '설명 1', 'http://example.com/image13_5_1.jpg'),
    (@day_id13_5, '활동 2', '설명 2', 'http://example.com/image13_5_2.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id13_5, ST_GeomFromText('POINT(35.323452793161366 128.4236350290881)', 4326), 1),
    (@day_id13_5, ST_GeomFromText('POINT(35.01541565721852 128.69336151413071)', 4326), 2);


-- 게시물 14
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    14, 'ENFP', '여행 제목 14', '2024-12-01', '2024-12-10', 5,
    'KR', 'ASIA', '지역 14', 232988, 197638, 104041,
    57, 20, 'UNRELATED', 'NON_SMOKER', '2024-11-01', 'RECRUITING'
);

SET @post_id14 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id14); SET @day_id14_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id14); SET @day_id14_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id14); SET @day_id14_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id14); SET @day_id14_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id14); SET @day_id14_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id14_1, '활동 1', '설명 1', 'http://example.com/image14_1_1.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id14_1, ST_GeomFromText('POINT(35.07686201829828 128.58197545691888)', 4326), 1);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id14_2, '활동 1', '설명 1', 'http://example.com/image14_2_1.jpg'),
    (@day_id14_2, '활동 2', '설명 2', 'http://example.com/image14_2_2.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id14_2, ST_GeomFromText('POINT(35.05315965769129 128.10082804128245)', 4326), 1),
    (@day_id14_2, ST_GeomFromText('POINT(35.36692018918464 128.78503694881564)', 4326), 2),
    (@day_id14_2, ST_GeomFromText('POINT(35.92074882923981 128.16111181767855)', 4326), 3);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id14_3, '활동 1', '설명 1', 'http://example.com/image14_3_1.jpg'),
    (@day_id14_3, '활동 2', '설명 2', 'http://example.com/image14_3_2.jpg'),
    (@day_id14_3, '활동 3', '설명 3', 'http://example.com/image14_3_3.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id14_3, ST_GeomFromText('POINT(35.50527761462767 128.16059202992716)', 4326), 1),
    (@day_id14_3, ST_GeomFromText('POINT(35.387486206832214 128.74337495032285)', 4326), 2),
    (@day_id14_3, ST_GeomFromText('POINT(35.71968605935028 128.40240939158406)', 4326), 3);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id14_4, '활동 1', '설명 1', 'http://example.com/image14_4_1.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id14_4, ST_GeomFromText('POINT(35.364372913563464 128.45362779451517)', 4326), 1),
    (@day_id14_4, ST_GeomFromText('POINT(35.182988240432095 128.67355723155748)', 4326), 2),
    (@day_id14_4, ST_GeomFromText('POINT(35.854949894888136 128.95274090417374)', 4326), 3);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id14_5, '활동 1', '설명 1', 'http://example.com/image14_5_1.jpg'),
    (@day_id14_5, '활동 2', '설명 2', 'http://example.com/image14_5_2.jpg'),
    (@day_id14_5, '활동 3', '설명 3', 'http://example.com/image14_5_3.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id14_5, ST_GeomFromText('POINT(35.36017696537104 128.41903189778745)', 4326), 1),
    (@day_id14_5, ST_GeomFromText('POINT(35.77819907612729 128.99790667316333)', 4326), 2);


-- 게시물 15
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    15, 'ENTP', '여행 제목 15', '2024-12-01', '2024-12-10', 8,
    'KR', 'ASIA', '지역 15', 598135, 182207, 121523,
    37, 21, 'MALE', 'NON_SMOKER', '2024-11-01', 'RECRUITING'
);

SET @post_id15 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id15); SET @day_id15_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id15); SET @day_id15_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id15); SET @day_id15_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id15); SET @day_id15_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id15); SET @day_id15_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id15_1, '활동 1', '설명 1', 'http://example.com/image15_1_1.jpg'),
    (@day_id15_1, '활동 2', '설명 2', 'http://example.com/image15_1_2.jpg'),
    (@day_id15_1, '활동 3', '설명 3', 'http://example.com/image15_1_3.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id15_1, ST_GeomFromText('POINT(35.47383131038452 128.87543034007547)', 4326), 1),
    (@day_id15_1, ST_GeomFromText('POINT(35.86696775134003 128.05386546181907)', 4326), 2);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id15_2, '활동 1', '설명 1', 'http://example.com/image15_2_1.jpg'),
    (@day_id15_2, '활동 2', '설명 2', 'http://example.com/image15_2_2.jpg'),
    (@day_id15_2, '활동 3', '설명 3', 'http://example.com/image15_2_3.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id15_2, ST_GeomFromText('POINT(35.885227570133424 128.60178344531934)', 4326), 1),
    (@day_id15_2, ST_GeomFromText('POINT(35.16067343270252 128.18282536316454)', 4326), 2);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id15_3, '활동 1', '설명 1', 'http://example.com/image15_3_1.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id15_3, ST_GeomFromText('POINT(35.61632967971326 128.84550479176227)', 4326), 1);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id15_4, '활동 1', '설명 1', 'http://example.com/image15_4_1.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id15_4, ST_GeomFromText('POINT(35.284537595611795 128.6413508300898)', 4326), 1);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id15_5, '활동 1', '설명 1', 'http://example.com/image15_5_1.jpg'),
    (@day_id15_5, '활동 2', '설명 2', 'http://example.com/image15_5_2.jpg'),
    (@day_id15_5, '활동 3', '설명 3', 'http://example.com/image15_5_3.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id15_5, ST_GeomFromText('POINT(35.16988842763031 128.758913679777)', 4326), 1),
    (@day_id15_5, ST_GeomFromText('POINT(35.96113059299376 128.02076504023336)', 4326), 2),
    (@day_id15_5, ST_GeomFromText('POINT(35.134919118170146 128.7470782422121)', 4326), 3);


-- 게시물 16
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    16, 'ENFP', '여행 제목 16', '2024-12-01', '2024-12-10', 1,
    'KR', 'ASIA', '지역 16', 563904, 93463, 254671,
    58, 24, 'UNRELATED', 'UNRELATED', '2024-11-01', 'RECRUITING'
);

SET @post_id16 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id16); SET @day_id16_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id16); SET @day_id16_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id16); SET @day_id16_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id16); SET @day_id16_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id16); SET @day_id16_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id16_1, '활동 1', '설명 1', 'http://example.com/image16_1_1.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id16_1, ST_GeomFromText('POINT(35.423092551120746 128.77205385586782)', 4326), 1),
    (@day_id16_1, ST_GeomFromText('POINT(35.71588196297366 128.72546448459178)', 4326), 2),
    (@day_id16_1, ST_GeomFromText('POINT(35.68964615958278 128.17322703006656)', 4326), 3);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id16_2, '활동 1', '설명 1', 'http://example.com/image16_2_1.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id16_2, ST_GeomFromText('POINT(35.16284504972293 128.7681237871774)', 4326), 1);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id16_3, '활동 1', '설명 1', 'http://example.com/image16_3_1.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id16_3, ST_GeomFromText('POINT(35.291448575663146 128.41162206661258)', 4326), 1);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id16_4, '활동 1', '설명 1', 'http://example.com/image16_4_1.jpg'),
    (@day_id16_4, '활동 2', '설명 2', 'http://example.com/image16_4_2.jpg'),
    (@day_id16_4, '활동 3', '설명 3', 'http://example.com/image16_4_3.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id16_4, ST_GeomFromText('POINT(35.44255841840979 128.14654380883636)', 4326), 1),
    (@day_id16_4, ST_GeomFromText('POINT(35.33262212157592 128.90906232678023)', 4326), 2),
    (@day_id16_4, ST_GeomFromText('POINT(35.971393285713376 128.93668895877116)', 4326), 3);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id16_5, '활동 1', '설명 1', 'http://example.com/image16_5_1.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id16_5, ST_GeomFromText('POINT(35.456875689422716 128.8925406573895)', 4326), 1);


-- 게시물 17
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    17, 'ENTJ', '여행 제목 17', '2024-12-01', '2024-12-10', 5,
    'KR', 'ASIA', '지역 17', 185027, 94411, 392647,
    44, 28, 'FEMALE', 'NON_SMOKER', '2024-11-01', 'RECRUITING'
);

SET @post_id17 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id17); SET @day_id17_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id17); SET @day_id17_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id17); SET @day_id17_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id17); SET @day_id17_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id17); SET @day_id17_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id17_1, '활동 1', '설명 1', 'http://example.com/image17_1_1.jpg'),
    (@day_id17_1, '활동 2', '설명 2', 'http://example.com/image17_1_2.jpg'),
    (@day_id17_1, '활동 3', '설명 3', 'http://example.com/image17_1_3.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id17_1, ST_GeomFromText('POINT(35.17381508713538 128.55766699616893)', 4326), 1),
    (@day_id17_1, ST_GeomFromText('POINT(35.737694011057535 128.58601961682334)', 4326), 2),
    (@day_id17_1, ST_GeomFromText('POINT(35.685554908116494 128.29495931330698)', 4326), 3);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id17_2, '활동 1', '설명 1', 'http://example.com/image17_2_1.jpg'),
    (@day_id17_2, '활동 2', '설명 2', 'http://example.com/image17_2_2.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id17_2, ST_GeomFromText('POINT(35.57621057757775 128.3528853893366)', 4326), 1);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id17_3, '활동 1', '설명 1', 'http://example.com/image17_3_1.jpg'),
    (@day_id17_3, '활동 2', '설명 2', 'http://example.com/image17_3_2.jpg'),
    (@day_id17_3, '활동 3', '설명 3', 'http://example.com/image17_3_3.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id17_3, ST_GeomFromText('POINT(35.47799538085065 128.16258177760724)', 4326), 1),
    (@day_id17_3, ST_GeomFromText('POINT(35.109623944367776 128.60837479405922)', 4326), 2);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id17_4, '활동 1', '설명 1', 'http://example.com/image17_4_1.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id17_4, ST_GeomFromText('POINT(35.04037932961117 128.16952774847425)', 4326), 1),
    (@day_id17_4, ST_GeomFromText('POINT(35.787984883971596 128.5694491005015)', 4326), 2),
    (@day_id17_4, ST_GeomFromText('POINT(35.787664956209255 128.2134717682971)', 4326), 3);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id17_5, '활동 1', '설명 1', 'http://example.com/image17_5_1.jpg'),
    (@day_id17_5, '활동 2', '설명 2', 'http://example.com/image17_5_2.jpg'),
    (@day_id17_5, '활동 3', '설명 3', 'http://example.com/image17_5_3.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id17_5, ST_GeomFromText('POINT(35.80999363504912 128.93696657182483)', 4326), 1),
    (@day_id17_5, ST_GeomFromText('POINT(35.2759418722823 128.88465293820187)', 4326), 2),
    (@day_id17_5, ST_GeomFromText('POINT(35.019029805779205 128.1068015749213)', 4326), 3);


-- 게시물 18
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    18, 'ENTP', '여행 제목 18', '2024-12-01', '2024-12-10', 4,
    'KR', 'ASIA', '지역 18', 552882, 99146, 382001,
    47, 22, 'UNRELATED', 'NON_SMOKER', '2024-11-01', 'RECRUITING'
);

SET @post_id18 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id18); SET @day_id18_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id18); SET @day_id18_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id18); SET @day_id18_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id18); SET @day_id18_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id18); SET @day_id18_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id18_1, '활동 1', '설명 1', 'http://example.com/image18_1_1.jpg'),
    (@day_id18_1, '활동 2', '설명 2', 'http://example.com/image18_1_2.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id18_1, ST_GeomFromText('POINT(35.590928572406064 128.18164172112992)', 4326), 1),
    (@day_id18_1, ST_GeomFromText('POINT(35.61648581666568 128.09554138867045)', 4326), 2);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id18_2, '활동 1', '설명 1', 'http://example.com/image18_2_1.jpg'),
    (@day_id18_2, '활동 2', '설명 2', 'http://example.com/image18_2_2.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id18_2, ST_GeomFromText('POINT(35.47267876637112 128.69839292108273)', 4326), 1);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id18_3, '활동 1', '설명 1', 'http://example.com/image18_3_1.jpg'),
    (@day_id18_3, '활동 2', '설명 2', 'http://example.com/image18_3_2.jpg'),
    (@day_id18_3, '활동 3', '설명 3', 'http://example.com/image18_3_3.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id18_3, ST_GeomFromText('POINT(35.73441050947995 128.7511437608656)', 4326), 1),
    (@day_id18_3, ST_GeomFromText('POINT(35.27219296810568 128.65529534615905)', 4326), 2),
    (@day_id18_3, ST_GeomFromText('POINT(35.61357766024453 128.76531698129745)', 4326), 3);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id18_4, '활동 1', '설명 1', 'http://example.com/image18_4_1.jpg'),
    (@day_id18_4, '활동 2', '설명 2', 'http://example.com/image18_4_2.jpg'),
    (@day_id18_4, '활동 3', '설명 3', 'http://example.com/image18_4_3.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id18_4, ST_GeomFromText('POINT(35.14402948423503 128.60161542906357)', 4326), 1);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id18_5, '활동 1', '설명 1', 'http://example.com/image18_5_1.jpg'),
    (@day_id18_5, '활동 2', '설명 2', 'http://example.com/image18_5_2.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id18_5, ST_GeomFromText('POINT(35.7901405847934 128.0171573398632)', 4326), 1),
    (@day_id18_5, ST_GeomFromText('POINT(35.99086124244171 128.7071116819614)', 4326), 2);


-- 게시물 19
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    19, 'ENTP', '여행 제목 19', '2024-12-01', '2024-12-10', 5,
    'KR', 'ASIA', '지역 19', 489710, 212303, 318880,
    51, 25, 'UNRELATED', 'UNRELATED', '2024-11-01', 'RECRUITING'
);

SET @post_id19 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id19); SET @day_id19_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id19); SET @day_id19_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id19); SET @day_id19_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id19); SET @day_id19_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id19); SET @day_id19_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id19_1, '활동 1', '설명 1', 'http://example.com/image19_1_1.jpg'),
    (@day_id19_1, '활동 2', '설명 2', 'http://example.com/image19_1_2.jpg'),
    (@day_id19_1, '활동 3', '설명 3', 'http://example.com/image19_1_3.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id19_1, ST_GeomFromText('POINT(35.654132727105285 128.50640597913466)', 4326), 1),
    (@day_id19_1, ST_GeomFromText('POINT(35.72788757442686 128.02636885353758)', 4326), 2);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id19_2, '활동 1', '설명 1', 'http://example.com/image19_2_1.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id19_2, ST_GeomFromText('POINT(35.857038786241375 128.46437330100215)', 4326), 1);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id19_3, '활동 1', '설명 1', 'http://example.com/image19_3_1.jpg'),
    (@day_id19_3, '활동 2', '설명 2', 'http://example.com/image19_3_2.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id19_3, ST_GeomFromText('POINT(35.05021955423169 128.51393321894614)', 4326), 1),
    (@day_id19_3, ST_GeomFromText('POINT(35.42210181176178 128.25118237160487)', 4326), 2),
    (@day_id19_3, ST_GeomFromText('POINT(35.771290557719354 128.79870482844342)', 4326), 3);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id19_4, '활동 1', '설명 1', 'http://example.com/image19_4_1.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id19_4, ST_GeomFromText('POINT(35.688662682351065 128.2086767184406)', 4326), 1),
    (@day_id19_4, ST_GeomFromText('POINT(35.06418609678779 128.0692831355032)', 4326), 2);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id19_5, '활동 1', '설명 1', 'http://example.com/image19_5_1.jpg'),
    (@day_id19_5, '활동 2', '설명 2', 'http://example.com/image19_5_2.jpg'),
    (@day_id19_5, '활동 3', '설명 3', 'http://example.com/image19_5_3.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id19_5, ST_GeomFromText('POINT(35.19368774345987 128.99188880528038)', 4326), 1),
    (@day_id19_5, ST_GeomFromText('POINT(35.398260849698296 128.94096135155405)', 4326), 2),
    (@day_id19_5, ST_GeomFromText('POINT(35.08196888825493 128.09993553134996)', 4326), 3);


-- 게시물 20
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    20, 'ENFP', '여행 제목 20', '2024-12-01', '2024-12-10', 10,
    'KR', 'ASIA', '지역 20', 298237, 150353, 307915,
    49, 27, 'FEMALE', 'SMOKER', '2024-11-01', 'RECRUITING'
);

SET @post_id20 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id20); SET @day_id20_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id20); SET @day_id20_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id20); SET @day_id20_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id20); SET @day_id20_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id20); SET @day_id20_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id20_1, '활동 1', '설명 1', 'http://example.com/image20_1_1.jpg'),
    (@day_id20_1, '활동 2', '설명 2', 'http://example.com/image20_1_2.jpg'),
    (@day_id20_1, '활동 3', '설명 3', 'http://example.com/image20_1_3.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id20_1, ST_GeomFromText('POINT(35.55281756707797 128.7320536157485)', 4326), 1),
    (@day_id20_1, ST_GeomFromText('POINT(35.045072153555566 128.83596385156136)', 4326), 2),
    (@day_id20_1, ST_GeomFromText('POINT(35.56648594358055 128.141709525938)', 4326), 3);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id20_2, '활동 1', '설명 1', 'http://example.com/image20_2_1.jpg'),
    (@day_id20_2, '활동 2', '설명 2', 'http://example.com/image20_2_2.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id20_2, ST_GeomFromText('POINT(35.09011732146151 128.21924303140128)', 4326), 1),
    (@day_id20_2, ST_GeomFromText('POINT(35.328267677268094 128.55108356918973)', 4326), 2);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id20_3, '활동 1', '설명 1', 'http://example.com/image20_3_1.jpg'),
    (@day_id20_3, '활동 2', '설명 2', 'http://example.com/image20_3_2.jpg'),
    (@day_id20_3, '활동 3', '설명 3', 'http://example.com/image20_3_3.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id20_3, ST_GeomFromText('POINT(35.56786553443586 128.57115017371166)', 4326), 1),
    (@day_id20_3, ST_GeomFromText('POINT(35.76689582365738 128.97472074610718)', 4326), 2);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id20_4, '활동 1', '설명 1', 'http://example.com/image20_4_1.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id20_4, ST_GeomFromText('POINT(35.96647328511407 128.7140382453067)', 4326), 1),
    (@day_id20_4, ST_GeomFromText('POINT(35.70293520405161 128.33319205572207)', 4326), 2),
    (@day_id20_4, ST_GeomFromText('POINT(35.532270631239335 128.56561966569015)', 4326), 3);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id20_5, '활동 1', '설명 1', 'http://example.com/image20_5_1.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id20_5, ST_GeomFromText('POINT(35.657626840311615 128.7898603070813)', 4326), 1),
    (@day_id20_5, ST_GeomFromText('POINT(35.83170731276157 128.25935779376243)', 4326), 2);


-- 게시물 21
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    21, 'ENTP', '여행 제목 21', '2024-12-01', '2024-12-10', 7,
    'KR', 'ASIA', '지역 21', 290520, 227478, 388087,
    43, 20, 'MALE', 'UNRELATED', '2024-11-01', 'RECRUITING'
);

SET @post_id21 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id21); SET @day_id21_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id21); SET @day_id21_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id21); SET @day_id21_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id21); SET @day_id21_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id21); SET @day_id21_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id21_1, '활동 1', '설명 1', 'http://example.com/image21_1_1.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id21_1, ST_GeomFromText('POINT(35.186763815211684 128.3906999572911)', 4326), 1);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id21_2, '활동 1', '설명 1', 'http://example.com/image21_2_1.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id21_2, ST_GeomFromText('POINT(35.154698152372845 128.03391407504344)', 4326), 1),
    (@day_id21_2, ST_GeomFromText('POINT(35.7548636502459 128.10818576898976)', 4326), 2),
    (@day_id21_2, ST_GeomFromText('POINT(35.50456936326831 128.59350720082168)', 4326), 3);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id21_3, '활동 1', '설명 1', 'http://example.com/image21_3_1.jpg'),
    (@day_id21_3, '활동 2', '설명 2', 'http://example.com/image21_3_2.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id21_3, ST_GeomFromText('POINT(35.930924632948546 128.98819351721113)', 4326), 1),
    (@day_id21_3, ST_GeomFromText('POINT(35.43959381670864 128.6779128918298)', 4326), 2);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id21_4, '활동 1', '설명 1', 'http://example.com/image21_4_1.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id21_4, ST_GeomFromText('POINT(35.93322748179009 128.69266061690135)', 4326), 1);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id21_5, '활동 1', '설명 1', 'http://example.com/image21_5_1.jpg'),
    (@day_id21_5, '활동 2', '설명 2', 'http://example.com/image21_5_2.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id21_5, ST_GeomFromText('POINT(35.291616643897974 128.03742195660791)', 4326), 1),
    (@day_id21_5, ST_GeomFromText('POINT(35.31814571126628 128.21250699131582)', 4326), 2);


-- 게시물 22
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    22, 'ESTP', '여행 제목 22', '2024-12-01', '2024-12-10', 9,
    'KR', 'ASIA', '지역 22', 374658, 200070, 133640,
    50, 26, 'MALE', 'SMOKER', '2024-11-01', 'RECRUITING'
);

SET @post_id22 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id22); SET @day_id22_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id22); SET @day_id22_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id22); SET @day_id22_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id22); SET @day_id22_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id22); SET @day_id22_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id22_1, '활동 1', '설명 1', 'http://example.com/image22_1_1.jpg'),
    (@day_id22_1, '활동 2', '설명 2', 'http://example.com/image22_1_2.jpg'),
    (@day_id22_1, '활동 3', '설명 3', 'http://example.com/image22_1_3.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id22_1, ST_GeomFromText('POINT(35.34822753559356 128.91165556773547)', 4326), 1),
    (@day_id22_1, ST_GeomFromText('POINT(35.739653020880084 128.73852076843409)', 4326), 2);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id22_2, '활동 1', '설명 1', 'http://example.com/image22_2_1.jpg'),
    (@day_id22_2, '활동 2', '설명 2', 'http://example.com/image22_2_2.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id22_2, ST_GeomFromText('POINT(35.627990916590775 128.54677893341892)', 4326), 1),
    (@day_id22_2, ST_GeomFromText('POINT(35.95494832116425 128.008254831016)', 4326), 2),
    (@day_id22_2, ST_GeomFromText('POINT(35.14936613192528 128.69230918228408)', 4326), 3);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id22_3, '활동 1', '설명 1', 'http://example.com/image22_3_1.jpg'),
    (@day_id22_3, '활동 2', '설명 2', 'http://example.com/image22_3_2.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id22_3, ST_GeomFromText('POINT(35.59535889603587 128.93635935803383)', 4326), 1);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id22_4, '활동 1', '설명 1', 'http://example.com/image22_4_1.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id22_4, ST_GeomFromText('POINT(35.79768300001777 128.23509138147523)', 4326), 1),
    (@day_id22_4, ST_GeomFromText('POINT(35.28797496916015 128.5082328963369)', 4326), 2),
    (@day_id22_4, ST_GeomFromText('POINT(35.69910070373784 128.2944412389241)', 4326), 3);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id22_5, '활동 1', '설명 1', 'http://example.com/image22_5_1.jpg'),
    (@day_id22_5, '활동 2', '설명 2', 'http://example.com/image22_5_2.jpg'),
    (@day_id22_5, '활동 3', '설명 3', 'http://example.com/image22_5_3.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id22_5, ST_GeomFromText('POINT(35.51439874906727 128.18160327577505)', 4326), 1);


-- 게시물 23
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    23, 'ESFP', '여행 제목 23', '2024-12-01', '2024-12-10', 10,
    'KR', 'ASIA', '지역 23', 422158, 55449, 186259,
    34, 20, 'MALE', 'NON_SMOKER', '2024-11-01', 'RECRUITING'
);

SET @post_id23 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id23); SET @day_id23_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id23); SET @day_id23_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id23); SET @day_id23_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id23); SET @day_id23_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id23); SET @day_id23_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id23_1, '활동 1', '설명 1', 'http://example.com/image23_1_1.jpg'),
    (@day_id23_1, '활동 2', '설명 2', 'http://example.com/image23_1_2.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id23_1, ST_GeomFromText('POINT(35.80877086526148 128.74774732325767)', 4326), 1);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id23_2, '활동 1', '설명 1', 'http://example.com/image23_2_1.jpg'),
    (@day_id23_2, '활동 2', '설명 2', 'http://example.com/image23_2_2.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id23_2, ST_GeomFromText('POINT(35.16036630500452 128.74755485937743)', 4326), 1),
    (@day_id23_2, ST_GeomFromText('POINT(35.767475639248985 128.96742881924573)', 4326), 2);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id23_3, '활동 1', '설명 1', 'http://example.com/image23_3_1.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id23_3, ST_GeomFromText('POINT(35.53734072516737 128.3603094352968)', 4326), 1);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id23_4, '활동 1', '설명 1', 'http://example.com/image23_4_1.jpg'),
    (@day_id23_4, '활동 2', '설명 2', 'http://example.com/image23_4_2.jpg'),
    (@day_id23_4, '활동 3', '설명 3', 'http://example.com/image23_4_3.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id23_4, ST_GeomFromText('POINT(35.4094404607156 128.2937644714441)', 4326), 1),
    (@day_id23_4, ST_GeomFromText('POINT(35.56916234257629 128.77197148412475)', 4326), 2);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id23_5, '활동 1', '설명 1', 'http://example.com/image23_5_1.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id23_5, ST_GeomFromText('POINT(35.11032080938503 128.38172629225076)', 4326), 1);


-- 게시물 24
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    24, 'ENFJ', '여행 제목 24', '2024-12-01', '2024-12-10', 1,
    'KR', 'ASIA', '지역 24', 540636, 193918, 393567,
    55, 25, 'FEMALE', 'SMOKER', '2024-11-01', 'RECRUITING'
);

SET @post_id24 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id24); SET @day_id24_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id24); SET @day_id24_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id24); SET @day_id24_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id24); SET @day_id24_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id24); SET @day_id24_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id24_1, '활동 1', '설명 1', 'http://example.com/image24_1_1.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id24_1, ST_GeomFromText('POINT(35.38328116623403 128.49696601255437)', 4326), 1);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id24_2, '활동 1', '설명 1', 'http://example.com/image24_2_1.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id24_2, ST_GeomFromText('POINT(35.269677344490816 128.7715676291188)', 4326), 1),
    (@day_id24_2, ST_GeomFromText('POINT(35.98768092433097 128.50001715831618)', 4326), 2),
    (@day_id24_2, ST_GeomFromText('POINT(35.70006351049769 128.67169748624607)', 4326), 3);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id24_3, '활동 1', '설명 1', 'http://example.com/image24_3_1.jpg'),
    (@day_id24_3, '활동 2', '설명 2', 'http://example.com/image24_3_2.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id24_3, ST_GeomFromText('POINT(35.58730886872874 128.53182103691074)', 4326), 1),
    (@day_id24_3, ST_GeomFromText('POINT(35.683157749283986 128.55592119904261)', 4326), 2),
    (@day_id24_3, ST_GeomFromText('POINT(35.39551977264211 128.63145847952916)', 4326), 3);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id24_4, '활동 1', '설명 1', 'http://example.com/image24_4_1.jpg'),
    (@day_id24_4, '활동 2', '설명 2', 'http://example.com/image24_4_2.jpg'),
    (@day_id24_4, '활동 3', '설명 3', 'http://example.com/image24_4_3.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id24_4, ST_GeomFromText('POINT(35.7046272153674 128.89535177507267)', 4326), 1),
    (@day_id24_4, ST_GeomFromText('POINT(35.4398340998996 128.583913208766)', 4326), 2),
    (@day_id24_4, ST_GeomFromText('POINT(35.74118051340372 128.11020390619055)', 4326), 3);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id24_5, '활동 1', '설명 1', 'http://example.com/image24_5_1.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id24_5, ST_GeomFromText('POINT(35.891173872341525 128.09055517585273)', 4326), 1),
    (@day_id24_5, ST_GeomFromText('POINT(35.00707874596103 128.96104602979582)', 4326), 2);


-- 게시물 25
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    25, 'ISFP', '여행 제목 25', '2024-12-01', '2024-12-10', 7,
    'KR', 'ASIA', '지역 25', 300269, 199392, 205093,
    42, 28, 'MALE', 'UNRELATED', '2024-11-01', 'RECRUITING'
);

SET @post_id25 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id25); SET @day_id25_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id25); SET @day_id25_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id25); SET @day_id25_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id25); SET @day_id25_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id25); SET @day_id25_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id25_1, '활동 1', '설명 1', 'http://example.com/image25_1_1.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id25_1, ST_GeomFromText('POINT(35.08920504563761 128.92057758149545)', 4326), 1);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id25_2, '활동 1', '설명 1', 'http://example.com/image25_2_1.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id25_2, ST_GeomFromText('POINT(35.14761231349531 128.61424091976897)', 4326), 1),
    (@day_id25_2, ST_GeomFromText('POINT(35.991187148726176 128.2411987629402)', 4326), 2);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id25_3, '활동 1', '설명 1', 'http://example.com/image25_3_1.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id25_3, ST_GeomFromText('POINT(35.18287769327013 128.62206892839646)', 4326), 1),
    (@day_id25_3, ST_GeomFromText('POINT(35.09282703563259 128.09612889359101)', 4326), 2),
    (@day_id25_3, ST_GeomFromText('POINT(35.77717146642829 128.53509631476945)', 4326), 3);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id25_4, '활동 1', '설명 1', 'http://example.com/image25_4_1.jpg'),
    (@day_id25_4, '활동 2', '설명 2', 'http://example.com/image25_4_2.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id25_4, ST_GeomFromText('POINT(35.19702722141201 128.70762802064272)', 4326), 1),
    (@day_id25_4, ST_GeomFromText('POINT(35.76868228864865 128.96098117426493)', 4326), 2);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id25_5, '활동 1', '설명 1', 'http://example.com/image25_5_1.jpg'),
    (@day_id25_5, '활동 2', '설명 2', 'http://example.com/image25_5_2.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id25_5, ST_GeomFromText('POINT(35.39411182817248 128.2376900014255)', 4326), 1),
    (@day_id25_5, ST_GeomFromText('POINT(35.87619723834697 128.07298822202551)', 4326), 2);


-- 게시물 26
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    26, 'ISFP', '여행 제목 26', '2024-12-01', '2024-12-10', 2,
    'KR', 'ASIA', '지역 26', 578372, 65116, 153703,
    35, 28, 'UNRELATED', 'UNRELATED', '2024-11-01', 'RECRUITING'
);

SET @post_id26 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id26); SET @day_id26_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id26); SET @day_id26_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id26); SET @day_id26_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id26); SET @day_id26_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id26); SET @day_id26_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id26_1, '활동 1', '설명 1', 'http://example.com/image26_1_1.jpg'),
    (@day_id26_1, '활동 2', '설명 2', 'http://example.com/image26_1_2.jpg'),
    (@day_id26_1, '활동 3', '설명 3', 'http://example.com/image26_1_3.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id26_1, ST_GeomFromText('POINT(35.37554708410106 128.75380776401462)', 4326), 1),
    (@day_id26_1, ST_GeomFromText('POINT(35.44496090702626 128.3037405824017)', 4326), 2),
    (@day_id26_1, ST_GeomFromText('POINT(35.126477761094236 128.66472845730894)', 4326), 3);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id26_2, '활동 1', '설명 1', 'http://example.com/image26_2_1.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id26_2, ST_GeomFromText('POINT(35.19984026190806 128.211303531842)', 4326), 1);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id26_3, '활동 1', '설명 1', 'http://example.com/image26_3_1.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id26_3, ST_GeomFromText('POINT(35.40106464222383 128.63266032927882)', 4326), 1);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id26_4, '활동 1', '설명 1', 'http://example.com/image26_4_1.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id26_4, ST_GeomFromText('POINT(35.597665613446615 128.4291251993625)', 4326), 1);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id26_5, '활동 1', '설명 1', 'http://example.com/image26_5_1.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id26_5, ST_GeomFromText('POINT(35.37385614964788 128.56130879184346)', 4326), 1),
    (@day_id26_5, ST_GeomFromText('POINT(35.409453847877835 128.49162116116295)', 4326), 2);


-- 게시물 27
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    27, 'ENTJ', '여행 제목 27', '2024-12-01', '2024-12-10', 4,
    'KR', 'ASIA', '지역 27', 291451, 167994, 236214,
    53, 23, 'FEMALE', 'SMOKER', '2024-11-01', 'RECRUITING'
);

SET @post_id27 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id27); SET @day_id27_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id27); SET @day_id27_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id27); SET @day_id27_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id27); SET @day_id27_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id27); SET @day_id27_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id27_1, '활동 1', '설명 1', 'http://example.com/image27_1_1.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id27_1, ST_GeomFromText('POINT(35.54535334968151 128.75596619124642)', 4326), 1),
    (@day_id27_1, ST_GeomFromText('POINT(35.21077245024208 128.0023209282901)', 4326), 2);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id27_2, '활동 1', '설명 1', 'http://example.com/image27_2_1.jpg'),
    (@day_id27_2, '활동 2', '설명 2', 'http://example.com/image27_2_2.jpg'),
    (@day_id27_2, '활동 3', '설명 3', 'http://example.com/image27_2_3.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id27_2, ST_GeomFromText('POINT(35.70398003495928 128.19842161009876)', 4326), 1),
    (@day_id27_2, ST_GeomFromText('POINT(35.17817155321429 128.70594957526635)', 4326), 2),
    (@day_id27_2, ST_GeomFromText('POINT(35.210555561356465 128.78294073010812)', 4326), 3);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id27_3, '활동 1', '설명 1', 'http://example.com/image27_3_1.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id27_3, ST_GeomFromText('POINT(35.878745392397505 128.15954119524451)', 4326), 1);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id27_4, '활동 1', '설명 1', 'http://example.com/image27_4_1.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id27_4, ST_GeomFromText('POINT(35.74773808487263 128.85569760145057)', 4326), 1),
    (@day_id27_4, ST_GeomFromText('POINT(35.037733721896664 128.8576097043995)', 4326), 2),
    (@day_id27_4, ST_GeomFromText('POINT(35.71369714016157 128.68022698741638)', 4326), 3);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id27_5, '활동 1', '설명 1', 'http://example.com/image27_5_1.jpg'),
    (@day_id27_5, '활동 2', '설명 2', 'http://example.com/image27_5_2.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id27_5, ST_GeomFromText('POINT(35.64812205441287 128.8389441718467)', 4326), 1);


-- 게시물 28
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    28, 'ENFJ', '여행 제목 28', '2024-12-01', '2024-12-10', 7,
    'KR', 'ASIA', '지역 28', 530827, 136966, 373291,
    44, 22, 'MALE', 'SMOKER', '2024-11-01', 'RECRUITING'
);

SET @post_id28 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id28); SET @day_id28_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id28); SET @day_id28_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id28); SET @day_id28_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id28); SET @day_id28_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id28); SET @day_id28_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id28_1, '활동 1', '설명 1', 'http://example.com/image28_1_1.jpg'),
    (@day_id28_1, '활동 2', '설명 2', 'http://example.com/image28_1_2.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id28_1, ST_GeomFromText('POINT(35.997797872342765 128.57857913465463)', 4326), 1),
    (@day_id28_1, ST_GeomFromText('POINT(35.02317621732004 128.629389577965)', 4326), 2),
    (@day_id28_1, ST_GeomFromText('POINT(35.64471117019123 128.33244783174692)', 4326), 3);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id28_2, '활동 1', '설명 1', 'http://example.com/image28_2_1.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id28_2, ST_GeomFromText('POINT(35.08019789973985 128.8056598989735)', 4326), 1);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id28_3, '활동 1', '설명 1', 'http://example.com/image28_3_1.jpg'),
    (@day_id28_3, '활동 2', '설명 2', 'http://example.com/image28_3_2.jpg'),
    (@day_id28_3, '활동 3', '설명 3', 'http://example.com/image28_3_3.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id28_3, ST_GeomFromText('POINT(35.21110942476968 128.57964563373085)', 4326), 1),
    (@day_id28_3, ST_GeomFromText('POINT(35.932887011478314 128.17393556514455)', 4326), 2);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id28_4, '활동 1', '설명 1', 'http://example.com/image28_4_1.jpg'),
    (@day_id28_4, '활동 2', '설명 2', 'http://example.com/image28_4_2.jpg'),
    (@day_id28_4, '활동 3', '설명 3', 'http://example.com/image28_4_3.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id28_4, ST_GeomFromText('POINT(35.88766508140922 128.33102081259628)', 4326), 1),
    (@day_id28_4, ST_GeomFromText('POINT(35.91661536238186 128.97695858769987)', 4326), 2),
    (@day_id28_4, ST_GeomFromText('POINT(35.522537777504844 128.73324980539914)', 4326), 3);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id28_5, '활동 1', '설명 1', 'http://example.com/image28_5_1.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id28_5, ST_GeomFromText('POINT(35.53593852172205 128.33295514200225)', 4326), 1),
    (@day_id28_5, ST_GeomFromText('POINT(35.03036135287182 128.46317602083397)', 4326), 2),
    (@day_id28_5, ST_GeomFromText('POINT(35.54805113643825 128.91605025064112)', 4326), 3);


-- 게시물 29
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    29, 'ISTJ', '여행 제목 29', '2024-12-01', '2024-12-10', 3,
    'KR', 'ASIA', '지역 29', 566775, 167872, 313081,
    37, 29, 'UNRELATED', 'SMOKER', '2024-11-01', 'RECRUITING'
);

SET @post_id29 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id29); SET @day_id29_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id29); SET @day_id29_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id29); SET @day_id29_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id29); SET @day_id29_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id29); SET @day_id29_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id29_1, '활동 1', '설명 1', 'http://example.com/image29_1_1.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id29_1, ST_GeomFromText('POINT(35.634951094308704 128.08514815925906)', 4326), 1),
    (@day_id29_1, ST_GeomFromText('POINT(35.059425720735824 128.4098179063436)', 4326), 2);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id29_2, '활동 1', '설명 1', 'http://example.com/image29_2_1.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id29_2, ST_GeomFromText('POINT(35.90858232987381 128.13661556325332)', 4326), 1);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id29_3, '활동 1', '설명 1', 'http://example.com/image29_3_1.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id29_3, ST_GeomFromText('POINT(35.73099321042407 128.2603207746791)', 4326), 1);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id29_4, '활동 1', '설명 1', 'http://example.com/image29_4_1.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id29_4, ST_GeomFromText('POINT(35.527456746658075 128.3212202516451)', 4326), 1),
    (@day_id29_4, ST_GeomFromText('POINT(35.16506280155474 128.87603183757162)', 4326), 2),
    (@day_id29_4, ST_GeomFromText('POINT(35.00009112024651 128.68593504600253)', 4326), 3);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id29_5, '활동 1', '설명 1', 'http://example.com/image29_5_1.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id29_5, ST_GeomFromText('POINT(35.69754413581733 128.42891984780732)', 4326), 1),
    (@day_id29_5, ST_GeomFromText('POINT(35.80389059445149 128.38552329336719)', 4326), 2),
    (@day_id29_5, ST_GeomFromText('POINT(35.85436077915532 128.67629006412793)', 4326), 3);


-- 게시물 30
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    30, 'ISFP', '여행 제목 30', '2024-12-01', '2024-12-10', 7,
    'KR', 'ASIA', '지역 30', 403080, 159395, 246515,
    43, 27, 'UNRELATED', 'SMOKER', '2024-11-01', 'RECRUITING'
);

SET @post_id30 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id30); SET @day_id30_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id30); SET @day_id30_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id30); SET @day_id30_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id30); SET @day_id30_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id30); SET @day_id30_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id30_1, '활동 1', '설명 1', 'http://example.com/image30_1_1.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id30_1, ST_GeomFromText('POINT(35.19840349248095 128.0084106713405)', 4326), 1),
    (@day_id30_1, ST_GeomFromText('POINT(35.87034848394424 128.71400735402497)', 4326), 2);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id30_2, '활동 1', '설명 1', 'http://example.com/image30_2_1.jpg'),
    (@day_id30_2, '활동 2', '설명 2', 'http://example.com/image30_2_2.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id30_2, ST_GeomFromText('POINT(35.425081438398614 128.9513133239595)', 4326), 1),
    (@day_id30_2, ST_GeomFromText('POINT(35.738593580024904 128.91891018472464)', 4326), 2),
    (@day_id30_2, ST_GeomFromText('POINT(35.77531680284839 128.1194859571726)', 4326), 3);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id30_3, '활동 1', '설명 1', 'http://example.com/image30_3_1.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id30_3, ST_GeomFromText('POINT(35.74557905042009 128.11076071347472)', 4326), 1);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id30_4, '활동 1', '설명 1', 'http://example.com/image30_4_1.jpg'),
    (@day_id30_4, '활동 2', '설명 2', 'http://example.com/image30_4_2.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id30_4, ST_GeomFromText('POINT(35.6475550217041 128.1261437123504)', 4326), 1),
    (@day_id30_4, ST_GeomFromText('POINT(35.03995270885649 128.70572955485775)', 4326), 2),
    (@day_id30_4, ST_GeomFromText('POINT(35.10680520129735 128.01171830020277)', 4326), 3);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id30_5, '활동 1', '설명 1', 'http://example.com/image30_5_1.jpg'),
    (@day_id30_5, '활동 2', '설명 2', 'http://example.com/image30_5_2.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id30_5, ST_GeomFromText('POINT(35.44914528227353 128.7909062535187)', 4326), 1);


-- 게시물 31
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    31, 'ESTJ', '여행 제목 31', '2024-12-01', '2024-12-10', 8,
    'KR', 'ASIA', '지역 31', 597610, 216528, 136721,
    54, 20, 'MALE', 'UNRELATED', '2024-11-01', 'RECRUITING'
);

SET @post_id31 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id31); SET @day_id31_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id31); SET @day_id31_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id31); SET @day_id31_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id31); SET @day_id31_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id31); SET @day_id31_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id31_1, '활동 1', '설명 1', 'http://example.com/image31_1_1.jpg'),
    (@day_id31_1, '활동 2', '설명 2', 'http://example.com/image31_1_2.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id31_1, ST_GeomFromText('POINT(35.03237011600473 128.88200320179988)', 4326), 1),
    (@day_id31_1, ST_GeomFromText('POINT(35.36854330461179 128.31488524121366)', 4326), 2);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id31_2, '활동 1', '설명 1', 'http://example.com/image31_2_1.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id31_2, ST_GeomFromText('POINT(35.539139566821596 128.85195854724154)', 4326), 1),
    (@day_id31_2, ST_GeomFromText('POINT(35.261252229165585 128.20173279587712)', 4326), 2);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id31_3, '활동 1', '설명 1', 'http://example.com/image31_3_1.jpg'),
    (@day_id31_3, '활동 2', '설명 2', 'http://example.com/image31_3_2.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id31_3, ST_GeomFromText('POINT(35.80492613727839 128.36646946883496)', 4326), 1),
    (@day_id31_3, ST_GeomFromText('POINT(35.38635757940492 128.89373393403227)', 4326), 2);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id31_4, '활동 1', '설명 1', 'http://example.com/image31_4_1.jpg'),
    (@day_id31_4, '활동 2', '설명 2', 'http://example.com/image31_4_2.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id31_4, ST_GeomFromText('POINT(35.57627538626754 128.8435240635407)', 4326), 1),
    (@day_id31_4, ST_GeomFromText('POINT(35.297792507696826 128.1143684588729)', 4326), 2),
    (@day_id31_4, ST_GeomFromText('POINT(35.81121503772265 128.0907764173537)', 4326), 3);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id31_5, '활동 1', '설명 1', 'http://example.com/image31_5_1.jpg'),
    (@day_id31_5, '활동 2', '설명 2', 'http://example.com/image31_5_2.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id31_5, ST_GeomFromText('POINT(35.64721359998499 128.0156163120872)', 4326), 1);


-- 게시물 32
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    32, 'ENTJ', '여행 제목 32', '2024-12-01', '2024-12-10', 5,
    'KR', 'ASIA', '지역 32', 248929, 72903, 323713,
    46, 21, 'FEMALE', 'UNRELATED', '2024-11-01', 'RECRUITING'
);

SET @post_id32 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id32); SET @day_id32_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id32); SET @day_id32_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id32); SET @day_id32_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id32); SET @day_id32_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id32); SET @day_id32_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id32_1, '활동 1', '설명 1', 'http://example.com/image32_1_1.jpg'),
    (@day_id32_1, '활동 2', '설명 2', 'http://example.com/image32_1_2.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id32_1, ST_GeomFromText('POINT(35.81378010261453 128.89173391927218)', 4326), 1),
    (@day_id32_1, ST_GeomFromText('POINT(35.966478656538555 128.52488213062603)', 4326), 2),
    (@day_id32_1, ST_GeomFromText('POINT(35.8646160807577 128.5823172216199)', 4326), 3);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id32_2, '활동 1', '설명 1', 'http://example.com/image32_2_1.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id32_2, ST_GeomFromText('POINT(35.473545694180544 128.20132401253935)', 4326), 1);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id32_3, '활동 1', '설명 1', 'http://example.com/image32_3_1.jpg'),
    (@day_id32_3, '활동 2', '설명 2', 'http://example.com/image32_3_2.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id32_3, ST_GeomFromText('POINT(35.4667243611456 128.97264201450554)', 4326), 1),
    (@day_id32_3, ST_GeomFromText('POINT(35.03788677611991 128.0283180091257)', 4326), 2),
    (@day_id32_3, ST_GeomFromText('POINT(35.37273712757204 128.2843139531746)', 4326), 3);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id32_4, '활동 1', '설명 1', 'http://example.com/image32_4_1.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id32_4, ST_GeomFromText('POINT(35.37185367731294 128.1189446383046)', 4326), 1);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id32_5, '활동 1', '설명 1', 'http://example.com/image32_5_1.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id32_5, ST_GeomFromText('POINT(35.160040117206734 128.149033638787)', 4326), 1);


-- 게시물 33
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    33, 'ISTJ', '여행 제목 33', '2024-12-01', '2024-12-10', 5,
    'KR', 'ASIA', '지역 33', 327083, 220791, 254197,
    30, 21, 'MALE', 'SMOKER', '2024-11-01', 'RECRUITING'
);

SET @post_id33 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id33); SET @day_id33_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id33); SET @day_id33_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id33); SET @day_id33_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id33); SET @day_id33_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id33); SET @day_id33_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id33_1, '활동 1', '설명 1', 'http://example.com/image33_1_1.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id33_1, ST_GeomFromText('POINT(35.42583685143171 128.78729477270272)', 4326), 1),
    (@day_id33_1, ST_GeomFromText('POINT(35.97778160957954 128.1604119178176)', 4326), 2);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id33_2, '활동 1', '설명 1', 'http://example.com/image33_2_1.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id33_2, ST_GeomFromText('POINT(35.16685811155213 128.76997779188864)', 4326), 1),
    (@day_id33_2, ST_GeomFromText('POINT(35.8914110150336 128.03808892133486)', 4326), 2);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id33_3, '활동 1', '설명 1', 'http://example.com/image33_3_1.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id33_3, ST_GeomFromText('POINT(35.29345954917268 128.21080381002275)', 4326), 1),
    (@day_id33_3, ST_GeomFromText('POINT(35.79750755605612 128.3349094108576)', 4326), 2);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id33_4, '활동 1', '설명 1', 'http://example.com/image33_4_1.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id33_4, ST_GeomFromText('POINT(35.13781581583716 128.67593553526837)', 4326), 1),
    (@day_id33_4, ST_GeomFromText('POINT(35.20719284323019 128.51711658267965)', 4326), 2);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id33_5, '활동 1', '설명 1', 'http://example.com/image33_5_1.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id33_5, ST_GeomFromText('POINT(35.57633981220843 128.81755057025376)', 4326), 1),
    (@day_id33_5, ST_GeomFromText('POINT(35.462791066891086 128.86176421762946)', 4326), 2),
    (@day_id33_5, ST_GeomFromText('POINT(35.74027947188034 128.88213478272016)', 4326), 3);


-- 게시물 34
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    34, 'INTJ', '여행 제목 34', '2024-12-01', '2024-12-10', 5,
    'KR', 'ASIA', '지역 34', 558697, 92927, 165723,
    48, 23, 'MALE', 'NON_SMOKER', '2024-11-01', 'RECRUITING'
);

SET @post_id34 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id34); SET @day_id34_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id34); SET @day_id34_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id34); SET @day_id34_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id34); SET @day_id34_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id34); SET @day_id34_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id34_1, '활동 1', '설명 1', 'http://example.com/image34_1_1.jpg'),
    (@day_id34_1, '활동 2', '설명 2', 'http://example.com/image34_1_2.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id34_1, ST_GeomFromText('POINT(35.72722482782916 128.89456015230576)', 4326), 1);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id34_2, '활동 1', '설명 1', 'http://example.com/image34_2_1.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id34_2, ST_GeomFromText('POINT(35.92424153858481 128.06934900133328)', 4326), 1),
    (@day_id34_2, ST_GeomFromText('POINT(35.205321203833286 128.26484628131323)', 4326), 2),
    (@day_id34_2, ST_GeomFromText('POINT(35.80519287557919 128.87534859021076)', 4326), 3);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id34_3, '활동 1', '설명 1', 'http://example.com/image34_3_1.jpg'),
    (@day_id34_3, '활동 2', '설명 2', 'http://example.com/image34_3_2.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id34_3, ST_GeomFromText('POINT(35.122728877762576 128.87894257954588)', 4326), 1);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id34_4, '활동 1', '설명 1', 'http://example.com/image34_4_1.jpg'),
    (@day_id34_4, '활동 2', '설명 2', 'http://example.com/image34_4_2.jpg'),
    (@day_id34_4, '활동 3', '설명 3', 'http://example.com/image34_4_3.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id34_4, ST_GeomFromText('POINT(35.35158424508343 128.15066271127012)', 4326), 1);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id34_5, '활동 1', '설명 1', 'http://example.com/image34_5_1.jpg'),
    (@day_id34_5, '활동 2', '설명 2', 'http://example.com/image34_5_2.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id34_5, ST_GeomFromText('POINT(35.21721381303195 128.41829668936427)', 4326), 1),
    (@day_id34_5, ST_GeomFromText('POINT(35.57307530915781 128.1505571844137)', 4326), 2);


-- 게시물 35
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    35, 'ENTP', '여행 제목 35', '2024-12-01', '2024-12-10', 9,
    'KR', 'ASIA', '지역 35', 431397, 163811, 384889,
    47, 22, 'UNRELATED', 'NON_SMOKER', '2024-11-01', 'RECRUITING'
);

SET @post_id35 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id35); SET @day_id35_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id35); SET @day_id35_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id35); SET @day_id35_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id35); SET @day_id35_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id35); SET @day_id35_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id35_1, '활동 1', '설명 1', 'http://example.com/image35_1_1.jpg'),
    (@day_id35_1, '활동 2', '설명 2', 'http://example.com/image35_1_2.jpg'),
    (@day_id35_1, '활동 3', '설명 3', 'http://example.com/image35_1_3.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id35_1, ST_GeomFromText('POINT(35.780434204159434 128.2984558911208)', 4326), 1),
    (@day_id35_1, ST_GeomFromText('POINT(35.4500214991911 128.18376910571044)', 4326), 2),
    (@day_id35_1, ST_GeomFromText('POINT(35.73567074344967 128.8510782352702)', 4326), 3);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id35_2, '활동 1', '설명 1', 'http://example.com/image35_2_1.jpg'),
    (@day_id35_2, '활동 2', '설명 2', 'http://example.com/image35_2_2.jpg'),
    (@day_id35_2, '활동 3', '설명 3', 'http://example.com/image35_2_3.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id35_2, ST_GeomFromText('POINT(35.875418009921866 128.8533523032777)', 4326), 1),
    (@day_id35_2, ST_GeomFromText('POINT(35.569088330196514 128.33998715143068)', 4326), 2);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id35_3, '활동 1', '설명 1', 'http://example.com/image35_3_1.jpg'),
    (@day_id35_3, '활동 2', '설명 2', 'http://example.com/image35_3_2.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id35_3, ST_GeomFromText('POINT(35.5602040927606 128.48756589291406)', 4326), 1);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id35_4, '활동 1', '설명 1', 'http://example.com/image35_4_1.jpg'),
    (@day_id35_4, '활동 2', '설명 2', 'http://example.com/image35_4_2.jpg'),
    (@day_id35_4, '활동 3', '설명 3', 'http://example.com/image35_4_3.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id35_4, ST_GeomFromText('POINT(35.88440473886166 128.66092852105726)', 4326), 1),
    (@day_id35_4, ST_GeomFromText('POINT(35.46451259199127 128.1457658504044)', 4326), 2);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id35_5, '활동 1', '설명 1', 'http://example.com/image35_5_1.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id35_5, ST_GeomFromText('POINT(35.13524722847306 128.23539391282148)', 4326), 1),
    (@day_id35_5, ST_GeomFromText('POINT(35.47230761496116 128.37736632679974)', 4326), 2);


-- 게시물 36
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    36, 'ISTP', '여행 제목 36', '2024-12-01', '2024-12-10', 1,
    'KR', 'ASIA', '지역 36', 351476, 160818, 318072,
    42, 25, 'UNRELATED', 'NON_SMOKER', '2024-11-01', 'RECRUITING'
);

SET @post_id36 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id36); SET @day_id36_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id36); SET @day_id36_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id36); SET @day_id36_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id36); SET @day_id36_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id36); SET @day_id36_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id36_1, '활동 1', '설명 1', 'http://example.com/image36_1_1.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id36_1, ST_GeomFromText('POINT(35.75420020983028 128.43443028287663)', 4326), 1);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id36_2, '활동 1', '설명 1', 'http://example.com/image36_2_1.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id36_2, ST_GeomFromText('POINT(35.359423408152274 128.6701704241833)', 4326), 1),
    (@day_id36_2, ST_GeomFromText('POINT(35.893869753800665 128.86834361271764)', 4326), 2),
    (@day_id36_2, ST_GeomFromText('POINT(35.03725591030223 128.06271041011516)', 4326), 3);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id36_3, '활동 1', '설명 1', 'http://example.com/image36_3_1.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id36_3, ST_GeomFromText('POINT(35.44991775974018 128.5756870270086)', 4326), 1),
    (@day_id36_3, ST_GeomFromText('POINT(35.60723879478556 128.78358807577206)', 4326), 2);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id36_4, '활동 1', '설명 1', 'http://example.com/image36_4_1.jpg'),
    (@day_id36_4, '활동 2', '설명 2', 'http://example.com/image36_4_2.jpg'),
    (@day_id36_4, '활동 3', '설명 3', 'http://example.com/image36_4_3.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id36_4, ST_GeomFromText('POINT(35.220833873580794 128.33984554253698)', 4326), 1),
    (@day_id36_4, ST_GeomFromText('POINT(35.668843258784705 128.4544319389868)', 4326), 2);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id36_5, '활동 1', '설명 1', 'http://example.com/image36_5_1.jpg'),
    (@day_id36_5, '활동 2', '설명 2', 'http://example.com/image36_5_2.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id36_5, ST_GeomFromText('POINT(35.64862338757304 128.69707164288417)', 4326), 1),
    (@day_id36_5, ST_GeomFromText('POINT(35.35552590304448 128.6013014368259)', 4326), 2),
    (@day_id36_5, ST_GeomFromText('POINT(35.66502446817245 128.86085017098966)', 4326), 3);


-- 게시물 37
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    37, 'ENTP', '여행 제목 37', '2024-12-01', '2024-12-10', 5,
    'KR', 'ASIA', '지역 37', 457250, 172124, 297846,
    33, 20, 'MALE', 'NON_SMOKER', '2024-11-01', 'RECRUITING'
);

SET @post_id37 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id37); SET @day_id37_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id37); SET @day_id37_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id37); SET @day_id37_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id37); SET @day_id37_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id37); SET @day_id37_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id37_1, '활동 1', '설명 1', 'http://example.com/image37_1_1.jpg'),
    (@day_id37_1, '활동 2', '설명 2', 'http://example.com/image37_1_2.jpg'),
    (@day_id37_1, '활동 3', '설명 3', 'http://example.com/image37_1_3.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id37_1, ST_GeomFromText('POINT(35.450028111808194 128.1330419010955)', 4326), 1),
    (@day_id37_1, ST_GeomFromText('POINT(35.975782267319914 128.0522922337909)', 4326), 2),
    (@day_id37_1, ST_GeomFromText('POINT(35.129416294882915 128.50060384039514)', 4326), 3);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id37_2, '활동 1', '설명 1', 'http://example.com/image37_2_1.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id37_2, ST_GeomFromText('POINT(35.82540509371581 128.71134314042374)', 4326), 1),
    (@day_id37_2, ST_GeomFromText('POINT(35.25395237668726 128.52357692232852)', 4326), 2),
    (@day_id37_2, ST_GeomFromText('POINT(35.62864951907984 128.48290147547934)', 4326), 3);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id37_3, '활동 1', '설명 1', 'http://example.com/image37_3_1.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id37_3, ST_GeomFromText('POINT(35.644160256155025 128.98546849527327)', 4326), 1),
    (@day_id37_3, ST_GeomFromText('POINT(35.95083076142273 128.66585144126284)', 4326), 2);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id37_4, '활동 1', '설명 1', 'http://example.com/image37_4_1.jpg'),
    (@day_id37_4, '활동 2', '설명 2', 'http://example.com/image37_4_2.jpg'),
    (@day_id37_4, '활동 3', '설명 3', 'http://example.com/image37_4_3.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id37_4, ST_GeomFromText('POINT(35.83476780893006 128.34391509972534)', 4326), 1),
    (@day_id37_4, ST_GeomFromText('POINT(35.6674285452856 128.9749711173441)', 4326), 2),
    (@day_id37_4, ST_GeomFromText('POINT(35.62259003770495 128.86169321459468)', 4326), 3);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id37_5, '활동 1', '설명 1', 'http://example.com/image37_5_1.jpg'),
    (@day_id37_5, '활동 2', '설명 2', 'http://example.com/image37_5_2.jpg'),
    (@day_id37_5, '활동 3', '설명 3', 'http://example.com/image37_5_3.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id37_5, ST_GeomFromText('POINT(35.842424935585655 128.37468171468572)', 4326), 1);


-- 게시물 38
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    38, 'INTJ', '여행 제목 38', '2024-12-01', '2024-12-10', 9,
    'KR', 'ASIA', '지역 38', 417535, 55572, 262104,
    31, 21, 'MALE', 'UNRELATED', '2024-11-01', 'RECRUITING'
);

SET @post_id38 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id38); SET @day_id38_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id38); SET @day_id38_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id38); SET @day_id38_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id38); SET @day_id38_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id38); SET @day_id38_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id38_1, '활동 1', '설명 1', 'http://example.com/image38_1_1.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id38_1, ST_GeomFromText('POINT(35.59269243753921 128.21109671400163)', 4326), 1),
    (@day_id38_1, ST_GeomFromText('POINT(35.74394590240734 128.97983086664865)', 4326), 2),
    (@day_id38_1, ST_GeomFromText('POINT(35.098456258162926 128.1139663274479)', 4326), 3);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id38_2, '활동 1', '설명 1', 'http://example.com/image38_2_1.jpg'),
    (@day_id38_2, '활동 2', '설명 2', 'http://example.com/image38_2_2.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id38_2, ST_GeomFromText('POINT(35.65371461329307 128.19248698696236)', 4326), 1);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id38_3, '활동 1', '설명 1', 'http://example.com/image38_3_1.jpg'),
    (@day_id38_3, '활동 2', '설명 2', 'http://example.com/image38_3_2.jpg'),
    (@day_id38_3, '활동 3', '설명 3', 'http://example.com/image38_3_3.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id38_3, ST_GeomFromText('POINT(35.24739590913598 128.97045834117407)', 4326), 1);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id38_4, '활동 1', '설명 1', 'http://example.com/image38_4_1.jpg'),
    (@day_id38_4, '활동 2', '설명 2', 'http://example.com/image38_4_2.jpg'),
    (@day_id38_4, '활동 3', '설명 3', 'http://example.com/image38_4_3.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id38_4, ST_GeomFromText('POINT(35.45605568793283 128.78551465391098)', 4326), 1),
    (@day_id38_4, ST_GeomFromText('POINT(35.12538080301189 128.5815461343593)', 4326), 2),
    (@day_id38_4, ST_GeomFromText('POINT(35.96595825041923 128.23940691320092)', 4326), 3);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id38_5, '활동 1', '설명 1', 'http://example.com/image38_5_1.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id38_5, ST_GeomFromText('POINT(35.257170406054115 128.00941663185452)', 4326), 1),
    (@day_id38_5, ST_GeomFromText('POINT(35.503575132291346 128.76135817217298)', 4326), 2);


-- 게시물 39
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    39, 'ENFP', '여행 제목 39', '2024-12-01', '2024-12-10', 8,
    'KR', 'ASIA', '지역 39', 487147, 153396, 133232,
    36, 22, 'UNRELATED', 'NON_SMOKER', '2024-11-01', 'RECRUITING'
);

SET @post_id39 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id39); SET @day_id39_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id39); SET @day_id39_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id39); SET @day_id39_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id39); SET @day_id39_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id39); SET @day_id39_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id39_1, '활동 1', '설명 1', 'http://example.com/image39_1_1.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id39_1, ST_GeomFromText('POINT(35.807904172359486 128.89000472548233)', 4326), 1),
    (@day_id39_1, ST_GeomFromText('POINT(35.8148724411002 128.23114193116868)', 4326), 2);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id39_2, '활동 1', '설명 1', 'http://example.com/image39_2_1.jpg'),
    (@day_id39_2, '활동 2', '설명 2', 'http://example.com/image39_2_2.jpg'),
    (@day_id39_2, '활동 3', '설명 3', 'http://example.com/image39_2_3.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id39_2, ST_GeomFromText('POINT(35.579541589805814 128.77355459893275)', 4326), 1),
    (@day_id39_2, ST_GeomFromText('POINT(35.73966176311013 128.76275226166277)', 4326), 2),
    (@day_id39_2, ST_GeomFromText('POINT(35.42404905717781 128.5434665068903)', 4326), 3);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id39_3, '활동 1', '설명 1', 'http://example.com/image39_3_1.jpg'),
    (@day_id39_3, '활동 2', '설명 2', 'http://example.com/image39_3_2.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id39_3, ST_GeomFromText('POINT(35.484852992455124 128.9605785269541)', 4326), 1),
    (@day_id39_3, ST_GeomFromText('POINT(35.955846549339945 128.52791421524182)', 4326), 2),
    (@day_id39_3, ST_GeomFromText('POINT(35.968237089663894 128.59585104737172)', 4326), 3);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id39_4, '활동 1', '설명 1', 'http://example.com/image39_4_1.jpg'),
    (@day_id39_4, '활동 2', '설명 2', 'http://example.com/image39_4_2.jpg'),
    (@day_id39_4, '활동 3', '설명 3', 'http://example.com/image39_4_3.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id39_4, ST_GeomFromText('POINT(35.4782610556006 128.05338584151377)', 4326), 1),
    (@day_id39_4, ST_GeomFromText('POINT(35.976163202630175 128.10810793133317)', 4326), 2),
    (@day_id39_4, ST_GeomFromText('POINT(35.48005686370371 128.59117750294226)', 4326), 3);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id39_5, '활동 1', '설명 1', 'http://example.com/image39_5_1.jpg'),
    (@day_id39_5, '활동 2', '설명 2', 'http://example.com/image39_5_2.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id39_5, ST_GeomFromText('POINT(35.805689564433266 128.27144361385805)', 4326), 1),
    (@day_id39_5, ST_GeomFromText('POINT(35.601590250784895 128.2331668844539)', 4326), 2);


-- 게시물 40
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    40, 'INTP', '여행 제목 40', '2024-12-01', '2024-12-10', 10,
    'KR', 'ASIA', '지역 40', 256522, 212693, 158647,
    54, 23, 'FEMALE', 'UNRELATED', '2024-11-01', 'RECRUITING'
);

SET @post_id40 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id40); SET @day_id40_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id40); SET @day_id40_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id40); SET @day_id40_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id40); SET @day_id40_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id40); SET @day_id40_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id40_1, '활동 1', '설명 1', 'http://example.com/image40_1_1.jpg'),
    (@day_id40_1, '활동 2', '설명 2', 'http://example.com/image40_1_2.jpg'),
    (@day_id40_1, '활동 3', '설명 3', 'http://example.com/image40_1_3.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id40_1, ST_GeomFromText('POINT(35.12160037677286 128.62816448502983)', 4326), 1);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id40_2, '활동 1', '설명 1', 'http://example.com/image40_2_1.jpg'),
    (@day_id40_2, '활동 2', '설명 2', 'http://example.com/image40_2_2.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id40_2, ST_GeomFromText('POINT(35.757171714126336 128.26705719223966)', 4326), 1),
    (@day_id40_2, ST_GeomFromText('POINT(35.39150345428916 128.71673942505214)', 4326), 2);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id40_3, '활동 1', '설명 1', 'http://example.com/image40_3_1.jpg'),
    (@day_id40_3, '활동 2', '설명 2', 'http://example.com/image40_3_2.jpg'),
    (@day_id40_3, '활동 3', '설명 3', 'http://example.com/image40_3_3.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id40_3, ST_GeomFromText('POINT(35.13381193384605 128.12197452433804)', 4326), 1),
    (@day_id40_3, ST_GeomFromText('POINT(35.01812031378009 128.12160423097038)', 4326), 2);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id40_4, '활동 1', '설명 1', 'http://example.com/image40_4_1.jpg'),
    (@day_id40_4, '활동 2', '설명 2', 'http://example.com/image40_4_2.jpg'),
    (@day_id40_4, '활동 3', '설명 3', 'http://example.com/image40_4_3.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id40_4, ST_GeomFromText('POINT(35.06142782677521 128.1787179218524)', 4326), 1),
    (@day_id40_4, ST_GeomFromText('POINT(35.45327247898489 128.0694664524394)', 4326), 2),
    (@day_id40_4, ST_GeomFromText('POINT(35.27991457089991 128.1966179843772)', 4326), 3);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id40_5, '활동 1', '설명 1', 'http://example.com/image40_5_1.jpg'),
    (@day_id40_5, '활동 2', '설명 2', 'http://example.com/image40_5_2.jpg'),
    (@day_id40_5, '활동 3', '설명 3', 'http://example.com/image40_5_3.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id40_5, ST_GeomFromText('POINT(35.99630382116457 128.92884167628796)', 4326), 1);


-- 게시물 41
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    41, 'ISTJ', '여행 제목 41', '2024-12-01', '2024-12-10', 3,
    'KR', 'ASIA', '지역 41', 387547, 175275, 392451,
    31, 24, 'MALE', 'UNRELATED', '2024-11-01', 'RECRUITING'
);

SET @post_id41 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id41); SET @day_id41_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id41); SET @day_id41_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id41); SET @day_id41_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id41); SET @day_id41_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id41); SET @day_id41_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id41_1, '활동 1', '설명 1', 'http://example.com/image41_1_1.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id41_1, ST_GeomFromText('POINT(35.99200605673587 128.70754468844672)', 4326), 1),
    (@day_id41_1, ST_GeomFromText('POINT(35.03452214921277 128.63654289845294)', 4326), 2),
    (@day_id41_1, ST_GeomFromText('POINT(35.460162717883975 128.3782843899054)', 4326), 3);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id41_2, '활동 1', '설명 1', 'http://example.com/image41_2_1.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id41_2, ST_GeomFromText('POINT(35.70017359032434 128.52635981595083)', 4326), 1);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id41_3, '활동 1', '설명 1', 'http://example.com/image41_3_1.jpg'),
    (@day_id41_3, '활동 2', '설명 2', 'http://example.com/image41_3_2.jpg'),
    (@day_id41_3, '활동 3', '설명 3', 'http://example.com/image41_3_3.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id41_3, ST_GeomFromText('POINT(35.78870083129897 128.02604255657982)', 4326), 1),
    (@day_id41_3, ST_GeomFromText('POINT(35.11930090722647 128.59520397432527)', 4326), 2),
    (@day_id41_3, ST_GeomFromText('POINT(35.44007114329638 128.25096140403636)', 4326), 3);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id41_4, '활동 1', '설명 1', 'http://example.com/image41_4_1.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id41_4, ST_GeomFromText('POINT(35.7829359538366 128.51819026925358)', 4326), 1),
    (@day_id41_4, ST_GeomFromText('POINT(35.38449736761939 128.7394879999542)', 4326), 2);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id41_5, '활동 1', '설명 1', 'http://example.com/image41_5_1.jpg'),
    (@day_id41_5, '활동 2', '설명 2', 'http://example.com/image41_5_2.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id41_5, ST_GeomFromText('POINT(35.84062859181371 128.74329631511716)', 4326), 1),
    (@day_id41_5, ST_GeomFromText('POINT(35.32380252848264 128.9161208338553)', 4326), 2),
    (@day_id41_5, ST_GeomFromText('POINT(35.29220483465387 128.23402044733677)', 4326), 3);


-- 게시물 42
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    42, 'ENFJ', '여행 제목 42', '2024-12-01', '2024-12-10', 4,
    'KR', 'ASIA', '지역 42', 297996, 83937, 155733,
    32, 26, 'MALE', 'UNRELATED', '2024-11-01', 'RECRUITING'
);

SET @post_id42 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id42); SET @day_id42_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id42); SET @day_id42_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id42); SET @day_id42_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id42); SET @day_id42_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id42); SET @day_id42_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id42_1, '활동 1', '설명 1', 'http://example.com/image42_1_1.jpg'),
    (@day_id42_1, '활동 2', '설명 2', 'http://example.com/image42_1_2.jpg'),
    (@day_id42_1, '활동 3', '설명 3', 'http://example.com/image42_1_3.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id42_1, ST_GeomFromText('POINT(35.30657150532519 128.82459382357737)', 4326), 1);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id42_2, '활동 1', '설명 1', 'http://example.com/image42_2_1.jpg'),
    (@day_id42_2, '활동 2', '설명 2', 'http://example.com/image42_2_2.jpg'),
    (@day_id42_2, '활동 3', '설명 3', 'http://example.com/image42_2_3.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id42_2, ST_GeomFromText('POINT(35.58018010356502 128.36305045680336)', 4326), 1);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id42_3, '활동 1', '설명 1', 'http://example.com/image42_3_1.jpg'),
    (@day_id42_3, '활동 2', '설명 2', 'http://example.com/image42_3_2.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id42_3, ST_GeomFromText('POINT(35.80329527163931 128.2665687717886)', 4326), 1);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id42_4, '활동 1', '설명 1', 'http://example.com/image42_4_1.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id42_4, ST_GeomFromText('POINT(35.13411391157063 128.28148736655953)', 4326), 1),
    (@day_id42_4, ST_GeomFromText('POINT(35.97772463191118 128.61613669712673)', 4326), 2),
    (@day_id42_4, ST_GeomFromText('POINT(35.04259179896432 128.6503459770434)', 4326), 3);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id42_5, '활동 1', '설명 1', 'http://example.com/image42_5_1.jpg'),
    (@day_id42_5, '활동 2', '설명 2', 'http://example.com/image42_5_2.jpg'),
    (@day_id42_5, '활동 3', '설명 3', 'http://example.com/image42_5_3.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id42_5, ST_GeomFromText('POINT(35.759083752476236 128.7600012556816)', 4326), 1),
    (@day_id42_5, ST_GeomFromText('POINT(35.318722824804375 128.9918234272203)', 4326), 2);


-- 게시물 43
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    43, 'ENFP', '여행 제목 43', '2024-12-01', '2024-12-10', 3,
    'KR', 'ASIA', '지역 43', 312528, 56549, 321410,
    38, 22, 'MALE', 'UNRELATED', '2024-11-01', 'RECRUITING'
);

SET @post_id43 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id43); SET @day_id43_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id43); SET @day_id43_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id43); SET @day_id43_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id43); SET @day_id43_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id43); SET @day_id43_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id43_1, '활동 1', '설명 1', 'http://example.com/image43_1_1.jpg'),
    (@day_id43_1, '활동 2', '설명 2', 'http://example.com/image43_1_2.jpg'),
    (@day_id43_1, '활동 3', '설명 3', 'http://example.com/image43_1_3.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id43_1, ST_GeomFromText('POINT(35.261742784473796 128.378202061703)', 4326), 1),
    (@day_id43_1, ST_GeomFromText('POINT(35.067320974659125 128.04819928727508)', 4326), 2);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id43_2, '활동 1', '설명 1', 'http://example.com/image43_2_1.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id43_2, ST_GeomFromText('POINT(35.99920113827708 128.62488694908384)', 4326), 1),
    (@day_id43_2, ST_GeomFromText('POINT(35.34973413746883 128.64777272034266)', 4326), 2);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id43_3, '활동 1', '설명 1', 'http://example.com/image43_3_1.jpg'),
    (@day_id43_3, '활동 2', '설명 2', 'http://example.com/image43_3_2.jpg'),
    (@day_id43_3, '활동 3', '설명 3', 'http://example.com/image43_3_3.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id43_3, ST_GeomFromText('POINT(35.91377345460872 128.84461723782064)', 4326), 1);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id43_4, '활동 1', '설명 1', 'http://example.com/image43_4_1.jpg'),
    (@day_id43_4, '활동 2', '설명 2', 'http://example.com/image43_4_2.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id43_4, ST_GeomFromText('POINT(35.656980870851 128.55182359382013)', 4326), 1),
    (@day_id43_4, ST_GeomFromText('POINT(35.55725695590195 128.42105485295767)', 4326), 2),
    (@day_id43_4, ST_GeomFromText('POINT(35.96829347586397 128.5907684021042)', 4326), 3);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id43_5, '활동 1', '설명 1', 'http://example.com/image43_5_1.jpg'),
    (@day_id43_5, '활동 2', '설명 2', 'http://example.com/image43_5_2.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id43_5, ST_GeomFromText('POINT(35.472411833395554 128.9705060822136)', 4326), 1),
    (@day_id43_5, ST_GeomFromText('POINT(35.10908155544779 128.37914685554009)', 4326), 2),
    (@day_id43_5, ST_GeomFromText('POINT(35.90064755950326 128.53081785155752)', 4326), 3);


-- 게시물 44
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    44, 'ISTP', '여행 제목 44', '2024-12-01', '2024-12-10', 7,
    'KR', 'ASIA', '지역 44', 481188, 207751, 226130,
    52, 21, 'MALE', 'UNRELATED', '2024-11-01', 'RECRUITING'
);

SET @post_id44 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id44); SET @day_id44_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id44); SET @day_id44_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id44); SET @day_id44_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id44); SET @day_id44_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id44); SET @day_id44_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id44_1, '활동 1', '설명 1', 'http://example.com/image44_1_1.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id44_1, ST_GeomFromText('POINT(35.5683039610407 128.41537681256884)', 4326), 1),
    (@day_id44_1, ST_GeomFromText('POINT(35.651466913578744 128.17522349157693)', 4326), 2);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id44_2, '활동 1', '설명 1', 'http://example.com/image44_2_1.jpg'),
    (@day_id44_2, '활동 2', '설명 2', 'http://example.com/image44_2_2.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id44_2, ST_GeomFromText('POINT(35.82087037951839 128.94678624414627)', 4326), 1),
    (@day_id44_2, ST_GeomFromText('POINT(35.86034907335962 128.64980118916083)', 4326), 2);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id44_3, '활동 1', '설명 1', 'http://example.com/image44_3_1.jpg'),
    (@day_id44_3, '활동 2', '설명 2', 'http://example.com/image44_3_2.jpg'),
    (@day_id44_3, '활동 3', '설명 3', 'http://example.com/image44_3_3.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id44_3, ST_GeomFromText('POINT(35.025174237246844 128.87043701788284)', 4326), 1),
    (@day_id44_3, ST_GeomFromText('POINT(35.40750383834399 128.23413200161608)', 4326), 2),
    (@day_id44_3, ST_GeomFromText('POINT(35.68042091819955 128.38801562615728)', 4326), 3);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id44_4, '활동 1', '설명 1', 'http://example.com/image44_4_1.jpg'),
    (@day_id44_4, '활동 2', '설명 2', 'http://example.com/image44_4_2.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id44_4, ST_GeomFromText('POINT(35.97817219320639 128.51285752150733)', 4326), 1);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id44_5, '활동 1', '설명 1', 'http://example.com/image44_5_1.jpg'),
    (@day_id44_5, '활동 2', '설명 2', 'http://example.com/image44_5_2.jpg'),
    (@day_id44_5, '활동 3', '설명 3', 'http://example.com/image44_5_3.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id44_5, ST_GeomFromText('POINT(35.40563689535125 128.57294019179866)', 4326), 1);


-- 게시물 45
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    45, 'ENTJ', '여행 제목 45', '2024-12-01', '2024-12-10', 8,
    'KR', 'ASIA', '지역 45', 427672, 110303, 190833,
    48, 22, 'UNRELATED', 'SMOKER', '2024-11-01', 'RECRUITING'
);

SET @post_id45 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id45); SET @day_id45_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id45); SET @day_id45_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id45); SET @day_id45_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id45); SET @day_id45_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id45); SET @day_id45_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id45_1, '활동 1', '설명 1', 'http://example.com/image45_1_1.jpg'),
    (@day_id45_1, '활동 2', '설명 2', 'http://example.com/image45_1_2.jpg'),
    (@day_id45_1, '활동 3', '설명 3', 'http://example.com/image45_1_3.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id45_1, ST_GeomFromText('POINT(35.86355807557427 128.3098379457822)', 4326), 1),
    (@day_id45_1, ST_GeomFromText('POINT(35.65820999179268 128.23104928675178)', 4326), 2),
    (@day_id45_1, ST_GeomFromText('POINT(35.86901441856342 128.8475310671169)', 4326), 3);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id45_2, '활동 1', '설명 1', 'http://example.com/image45_2_1.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id45_2, ST_GeomFromText('POINT(35.878241531485756 128.29183847632387)', 4326), 1),
    (@day_id45_2, ST_GeomFromText('POINT(35.194361388382355 128.67240618193603)', 4326), 2);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id45_3, '활동 1', '설명 1', 'http://example.com/image45_3_1.jpg'),
    (@day_id45_3, '활동 2', '설명 2', 'http://example.com/image45_3_2.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id45_3, ST_GeomFromText('POINT(35.797216785199126 128.19887951315144)', 4326), 1),
    (@day_id45_3, ST_GeomFromText('POINT(35.94261022229302 128.67606691062178)', 4326), 2);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id45_4, '활동 1', '설명 1', 'http://example.com/image45_4_1.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id45_4, ST_GeomFromText('POINT(35.94768432587457 128.94064853125704)', 4326), 1),
    (@day_id45_4, ST_GeomFromText('POINT(35.553214784812816 128.3403795532889)', 4326), 2);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id45_5, '활동 1', '설명 1', 'http://example.com/image45_5_1.jpg'),
    (@day_id45_5, '활동 2', '설명 2', 'http://example.com/image45_5_2.jpg'),
    (@day_id45_5, '활동 3', '설명 3', 'http://example.com/image45_5_3.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id45_5, ST_GeomFromText('POINT(35.631218843795686 128.2166869672188)', 4326), 1);


-- 게시물 46
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    46, 'ISFJ', '여행 제목 46', '2024-12-01', '2024-12-10', 8,
    'KR', 'ASIA', '지역 46', 590663, 238755, 247202,
    58, 24, 'FEMALE', 'SMOKER', '2024-11-01', 'RECRUITING'
);

SET @post_id46 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id46); SET @day_id46_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id46); SET @day_id46_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id46); SET @day_id46_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id46); SET @day_id46_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id46); SET @day_id46_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id46_1, '활동 1', '설명 1', 'http://example.com/image46_1_1.jpg'),
    (@day_id46_1, '활동 2', '설명 2', 'http://example.com/image46_1_2.jpg'),
    (@day_id46_1, '활동 3', '설명 3', 'http://example.com/image46_1_3.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id46_1, ST_GeomFromText('POINT(35.15538954787907 128.61596878428756)', 4326), 1);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id46_2, '활동 1', '설명 1', 'http://example.com/image46_2_1.jpg'),
    (@day_id46_2, '활동 2', '설명 2', 'http://example.com/image46_2_2.jpg'),
    (@day_id46_2, '활동 3', '설명 3', 'http://example.com/image46_2_3.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id46_2, ST_GeomFromText('POINT(35.697791760452844 128.9249222284048)', 4326), 1);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id46_3, '활동 1', '설명 1', 'http://example.com/image46_3_1.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id46_3, ST_GeomFromText('POINT(35.501192701170545 128.89029513169456)', 4326), 1),
    (@day_id46_3, ST_GeomFromText('POINT(35.31650151560555 128.91158524756835)', 4326), 2);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id46_4, '활동 1', '설명 1', 'http://example.com/image46_4_1.jpg'),
    (@day_id46_4, '활동 2', '설명 2', 'http://example.com/image46_4_2.jpg'),
    (@day_id46_4, '활동 3', '설명 3', 'http://example.com/image46_4_3.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id46_4, ST_GeomFromText('POINT(35.62768454489315 128.5397687588499)', 4326), 1),
    (@day_id46_4, ST_GeomFromText('POINT(35.513575698174506 128.72603109442645)', 4326), 2);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id46_5, '활동 1', '설명 1', 'http://example.com/image46_5_1.jpg'),
    (@day_id46_5, '활동 2', '설명 2', 'http://example.com/image46_5_2.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id46_5, ST_GeomFromText('POINT(35.561798689284956 128.25631038493046)', 4326), 1);


-- 게시물 47
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    47, 'ENTJ', '여행 제목 47', '2024-12-01', '2024-12-10', 1,
    'KR', 'ASIA', '지역 47', 542642, 156714, 206960,
    30, 25, 'FEMALE', 'UNRELATED', '2024-11-01', 'RECRUITING'
);

SET @post_id47 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id47); SET @day_id47_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id47); SET @day_id47_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id47); SET @day_id47_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id47); SET @day_id47_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id47); SET @day_id47_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id47_1, '활동 1', '설명 1', 'http://example.com/image47_1_1.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id47_1, ST_GeomFromText('POINT(35.16628524962349 128.458022606943)', 4326), 1),
    (@day_id47_1, ST_GeomFromText('POINT(35.84782683249797 128.20674688100317)', 4326), 2),
    (@day_id47_1, ST_GeomFromText('POINT(35.17994142643846 128.93977999043364)', 4326), 3);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id47_2, '활동 1', '설명 1', 'http://example.com/image47_2_1.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id47_2, ST_GeomFromText('POINT(35.7733459241779 128.48101921873575)', 4326), 1),
    (@day_id47_2, ST_GeomFromText('POINT(35.403099115700414 128.92125456760036)', 4326), 2),
    (@day_id47_2, ST_GeomFromText('POINT(35.82780332876293 128.35327156649893)', 4326), 3);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id47_3, '활동 1', '설명 1', 'http://example.com/image47_3_1.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id47_3, ST_GeomFromText('POINT(35.5794069950929 128.68961040921917)', 4326), 1);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id47_4, '활동 1', '설명 1', 'http://example.com/image47_4_1.jpg'),
    (@day_id47_4, '활동 2', '설명 2', 'http://example.com/image47_4_2.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id47_4, ST_GeomFromText('POINT(35.59522516166432 128.7776961389516)', 4326), 1),
    (@day_id47_4, ST_GeomFromText('POINT(35.48996854714149 128.25265343782323)', 4326), 2),
    (@day_id47_4, ST_GeomFromText('POINT(35.01241812383604 128.597197441439)', 4326), 3);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id47_5, '활동 1', '설명 1', 'http://example.com/image47_5_1.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id47_5, ST_GeomFromText('POINT(35.097029013117954 128.38913622519843)', 4326), 1);


-- 게시물 48
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    48, 'ESTP', '여행 제목 48', '2024-12-01', '2024-12-10', 8,
    'KR', 'ASIA', '지역 48', 528201, 126347, 313216,
    57, 26, 'FEMALE', 'UNRELATED', '2024-11-01', 'RECRUITING'
);

SET @post_id48 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id48); SET @day_id48_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id48); SET @day_id48_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id48); SET @day_id48_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id48); SET @day_id48_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id48); SET @day_id48_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id48_1, '활동 1', '설명 1', 'http://example.com/image48_1_1.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id48_1, ST_GeomFromText('POINT(35.638652998324964 128.0562217524127)', 4326), 1),
    (@day_id48_1, ST_GeomFromText('POINT(35.14946898800768 128.15528551115278)', 4326), 2),
    (@day_id48_1, ST_GeomFromText('POINT(35.69328872734049 128.59533063291383)', 4326), 3);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id48_2, '활동 1', '설명 1', 'http://example.com/image48_2_1.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id48_2, ST_GeomFromText('POINT(35.96324904007137 128.4057329319754)', 4326), 1),
    (@day_id48_2, ST_GeomFromText('POINT(35.30381899385458 128.81996778570263)', 4326), 2);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id48_3, '활동 1', '설명 1', 'http://example.com/image48_3_1.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id48_3, ST_GeomFromText('POINT(35.94778032600481 128.5405852535322)', 4326), 1),
    (@day_id48_3, ST_GeomFromText('POINT(35.38261510095778 128.512965151319)', 4326), 2);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id48_4, '활동 1', '설명 1', 'http://example.com/image48_4_1.jpg'),
    (@day_id48_4, '활동 2', '설명 2', 'http://example.com/image48_4_2.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id48_4, ST_GeomFromText('POINT(35.889059852458196 128.55553020096912)', 4326), 1),
    (@day_id48_4, ST_GeomFromText('POINT(35.30735974065303 128.6824045572909)', 4326), 2);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id48_5, '활동 1', '설명 1', 'http://example.com/image48_5_1.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id48_5, ST_GeomFromText('POINT(35.86141670550449 128.15030655701648)', 4326), 1),
    (@day_id48_5, ST_GeomFromText('POINT(35.84061279098474 128.112632841533)', 4326), 2);


-- 게시물 49
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    49, 'ESFJ', '여행 제목 49', '2024-12-01', '2024-12-10', 6,
    'KR', 'ASIA', '지역 49', 240290, 96783, 135784,
    53, 22, 'FEMALE', 'NON_SMOKER', '2024-11-01', 'RECRUITING'
);

SET @post_id49 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id49); SET @day_id49_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id49); SET @day_id49_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id49); SET @day_id49_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id49); SET @day_id49_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id49); SET @day_id49_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id49_1, '활동 1', '설명 1', 'http://example.com/image49_1_1.jpg'),
    (@day_id49_1, '활동 2', '설명 2', 'http://example.com/image49_1_2.jpg'),
    (@day_id49_1, '활동 3', '설명 3', 'http://example.com/image49_1_3.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id49_1, ST_GeomFromText('POINT(35.87044314556434 128.49845880077842)', 4326), 1);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id49_2, '활동 1', '설명 1', 'http://example.com/image49_2_1.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id49_2, ST_GeomFromText('POINT(35.05594402521527 128.71001743717474)', 4326), 1),
    (@day_id49_2, ST_GeomFromText('POINT(35.53297284106715 128.83306337722718)', 4326), 2),
    (@day_id49_2, ST_GeomFromText('POINT(35.681810211590374 128.1000890929639)', 4326), 3);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id49_3, '활동 1', '설명 1', 'http://example.com/image49_3_1.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id49_3, ST_GeomFromText('POINT(35.21842566706136 128.14675467328257)', 4326), 1),
    (@day_id49_3, ST_GeomFromText('POINT(35.08569557095902 128.4263097837847)', 4326), 2),
    (@day_id49_3, ST_GeomFromText('POINT(35.95958001497474 128.2709829423048)', 4326), 3);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id49_4, '활동 1', '설명 1', 'http://example.com/image49_4_1.jpg'),
    (@day_id49_4, '활동 2', '설명 2', 'http://example.com/image49_4_2.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id49_4, ST_GeomFromText('POINT(35.43378584920247 128.84203451868746)', 4326), 1);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id49_5, '활동 1', '설명 1', 'http://example.com/image49_5_1.jpg'),
    (@day_id49_5, '활동 2', '설명 2', 'http://example.com/image49_5_2.jpg'),
    (@day_id49_5, '활동 3', '설명 3', 'http://example.com/image49_5_3.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id49_5, ST_GeomFromText('POINT(35.88905863883979 128.5486149603806)', 4326), 1);


-- 게시물 50
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    50, 'ISTP', '여행 제목 50', '2024-12-01', '2024-12-10', 7,
    'KR', 'ASIA', '지역 50', 352869, 63747, 368372,
    32, 29, 'FEMALE', 'UNRELATED', '2024-11-01', 'RECRUITING'
);

SET @post_id50 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id50); SET @day_id50_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id50); SET @day_id50_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id50); SET @day_id50_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id50); SET @day_id50_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id50); SET @day_id50_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id50_1, '활동 1', '설명 1', 'http://example.com/image50_1_1.jpg'),
    (@day_id50_1, '활동 2', '설명 2', 'http://example.com/image50_1_2.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id50_1, ST_GeomFromText('POINT(35.244039708306055 128.00767577757404)', 4326), 1),
    (@day_id50_1, ST_GeomFromText('POINT(35.74909374519583 128.2333128441303)', 4326), 2);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id50_2, '활동 1', '설명 1', 'http://example.com/image50_2_1.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id50_2, ST_GeomFromText('POINT(35.5266632719173 128.3656442134816)', 4326), 1),
    (@day_id50_2, ST_GeomFromText('POINT(35.66339730989967 128.39855797240182)', 4326), 2),
    (@day_id50_2, ST_GeomFromText('POINT(35.756088821378064 128.6342734835061)', 4326), 3);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id50_3, '활동 1', '설명 1', 'http://example.com/image50_3_1.jpg'),
    (@day_id50_3, '활동 2', '설명 2', 'http://example.com/image50_3_2.jpg'),
    (@day_id50_3, '활동 3', '설명 3', 'http://example.com/image50_3_3.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id50_3, ST_GeomFromText('POINT(35.41098093575206 128.8879470218936)', 4326), 1),
    (@day_id50_3, ST_GeomFromText('POINT(35.86581745497715 128.35485943511597)', 4326), 2);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id50_4, '활동 1', '설명 1', 'http://example.com/image50_4_1.jpg'),
    (@day_id50_4, '활동 2', '설명 2', 'http://example.com/image50_4_2.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id50_4, ST_GeomFromText('POINT(35.3039134219312 128.845373173685)', 4326), 1),
    (@day_id50_4, ST_GeomFromText('POINT(35.54157292637501 128.4310584323272)', 4326), 2),
    (@day_id50_4, ST_GeomFromText('POINT(35.51623885528307 128.00336178780336)', 4326), 3);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id50_5, '활동 1', '설명 1', 'http://example.com/image50_5_1.jpg'),
    (@day_id50_5, '활동 2', '설명 2', 'http://example.com/image50_5_2.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id50_5, ST_GeomFromText('POINT(35.998664212846094 128.72061382963903)', 4326), 1),
    (@day_id50_5, ST_GeomFromText('POINT(35.486502410082736 128.8940979770364)', 4326), 2);


-- 게시물 51
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    51, 'ENFP', '여행 제목 51', '2024-12-01', '2024-12-10', 3,
    'KR', 'ASIA', '지역 51', 177421, 242773, 337305,
    50, 27, 'FEMALE', 'UNRELATED', '2024-11-01', 'RECRUITING'
);

SET @post_id51 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id51); SET @day_id51_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id51); SET @day_id51_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id51); SET @day_id51_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id51); SET @day_id51_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id51); SET @day_id51_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id51_1, '활동 1', '설명 1', 'http://example.com/image51_1_1.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id51_1, ST_GeomFromText('POINT(35.11332144475892 128.60320045691697)', 4326), 1);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id51_2, '활동 1', '설명 1', 'http://example.com/image51_2_1.jpg'),
    (@day_id51_2, '활동 2', '설명 2', 'http://example.com/image51_2_2.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id51_2, ST_GeomFromText('POINT(35.33842378241719 128.41666323271718)', 4326), 1),
    (@day_id51_2, ST_GeomFromText('POINT(35.04959667550504 128.18798449387447)', 4326), 2),
    (@day_id51_2, ST_GeomFromText('POINT(35.042567977176 128.9088375570867)', 4326), 3);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id51_3, '활동 1', '설명 1', 'http://example.com/image51_3_1.jpg'),
    (@day_id51_3, '활동 2', '설명 2', 'http://example.com/image51_3_2.jpg'),
    (@day_id51_3, '활동 3', '설명 3', 'http://example.com/image51_3_3.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id51_3, ST_GeomFromText('POINT(35.57363063106439 128.28164042279664)', 4326), 1),
    (@day_id51_3, ST_GeomFromText('POINT(35.01357854877052 128.0400539946427)', 4326), 2),
    (@day_id51_3, ST_GeomFromText('POINT(35.75974158543576 128.0383898672785)', 4326), 3);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id51_4, '활동 1', '설명 1', 'http://example.com/image51_4_1.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id51_4, ST_GeomFromText('POINT(35.98111717916178 128.79090419566649)', 4326), 1),
    (@day_id51_4, ST_GeomFromText('POINT(35.3048083911807 128.07298261382175)', 4326), 2);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id51_5, '활동 1', '설명 1', 'http://example.com/image51_5_1.jpg'),
    (@day_id51_5, '활동 2', '설명 2', 'http://example.com/image51_5_2.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id51_5, ST_GeomFromText('POINT(35.856461609546365 128.7368596378786)', 4326), 1),
    (@day_id51_5, ST_GeomFromText('POINT(35.08833668658158 128.0393730408086)', 4326), 2),
    (@day_id51_5, ST_GeomFromText('POINT(35.86665558199396 128.01163512049405)', 4326), 3);


-- 게시물 52
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    52, 'ENFJ', '여행 제목 52', '2024-12-01', '2024-12-10', 3,
    'KR', 'ASIA', '지역 52', 381356, 242291, 111987,
    51, 25, 'FEMALE', 'UNRELATED', '2024-11-01', 'RECRUITING'
);

SET @post_id52 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id52); SET @day_id52_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id52); SET @day_id52_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id52); SET @day_id52_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id52); SET @day_id52_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id52); SET @day_id52_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id52_1, '활동 1', '설명 1', 'http://example.com/image52_1_1.jpg'),
    (@day_id52_1, '활동 2', '설명 2', 'http://example.com/image52_1_2.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id52_1, ST_GeomFromText('POINT(35.27226713641812 128.81675596230582)', 4326), 1),
    (@day_id52_1, ST_GeomFromText('POINT(35.0194343086143 128.91088832327458)', 4326), 2);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id52_2, '활동 1', '설명 1', 'http://example.com/image52_2_1.jpg'),
    (@day_id52_2, '활동 2', '설명 2', 'http://example.com/image52_2_2.jpg'),
    (@day_id52_2, '활동 3', '설명 3', 'http://example.com/image52_2_3.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id52_2, ST_GeomFromText('POINT(35.91149041268488 128.6244347928547)', 4326), 1),
    (@day_id52_2, ST_GeomFromText('POINT(35.17322555853526 128.8610540039334)', 4326), 2),
    (@day_id52_2, ST_GeomFromText('POINT(35.620536386755624 128.58843309062644)', 4326), 3);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id52_3, '활동 1', '설명 1', 'http://example.com/image52_3_1.jpg'),
    (@day_id52_3, '활동 2', '설명 2', 'http://example.com/image52_3_2.jpg'),
    (@day_id52_3, '활동 3', '설명 3', 'http://example.com/image52_3_3.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id52_3, ST_GeomFromText('POINT(35.58938680763367 128.5183200993092)', 4326), 1);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id52_4, '활동 1', '설명 1', 'http://example.com/image52_4_1.jpg'),
    (@day_id52_4, '활동 2', '설명 2', 'http://example.com/image52_4_2.jpg'),
    (@day_id52_4, '활동 3', '설명 3', 'http://example.com/image52_4_3.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id52_4, ST_GeomFromText('POINT(35.167508350883054 128.8973360488352)', 4326), 1);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id52_5, '활동 1', '설명 1', 'http://example.com/image52_5_1.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id52_5, ST_GeomFromText('POINT(35.136637951741506 128.61913375431433)', 4326), 1);


-- 게시물 53
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    53, 'ISTP', '여행 제목 53', '2024-12-01', '2024-12-10', 8,
    'KR', 'ASIA', '지역 53', 520662, 182061, 130000,
    48, 27, 'FEMALE', 'UNRELATED', '2024-11-01', 'RECRUITING'
);

SET @post_id53 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id53); SET @day_id53_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id53); SET @day_id53_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id53); SET @day_id53_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id53); SET @day_id53_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id53); SET @day_id53_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id53_1, '활동 1', '설명 1', 'http://example.com/image53_1_1.jpg'),
    (@day_id53_1, '활동 2', '설명 2', 'http://example.com/image53_1_2.jpg'),
    (@day_id53_1, '활동 3', '설명 3', 'http://example.com/image53_1_3.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id53_1, ST_GeomFromText('POINT(35.15073369922724 128.67546617285086)', 4326), 1),
    (@day_id53_1, ST_GeomFromText('POINT(35.38641974316439 128.4653431593152)', 4326), 2);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id53_2, '활동 1', '설명 1', 'http://example.com/image53_2_1.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id53_2, ST_GeomFromText('POINT(35.40514278026844 128.37605642678568)', 4326), 1),
    (@day_id53_2, ST_GeomFromText('POINT(35.410366171490395 128.11706356026338)', 4326), 2),
    (@day_id53_2, ST_GeomFromText('POINT(35.62382386363193 128.2658852081903)', 4326), 3);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id53_3, '활동 1', '설명 1', 'http://example.com/image53_3_1.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id53_3, ST_GeomFromText('POINT(35.42283723738385 128.19678980081642)', 4326), 1),
    (@day_id53_3, ST_GeomFromText('POINT(35.41963862531093 128.7059177597888)', 4326), 2),
    (@day_id53_3, ST_GeomFromText('POINT(35.1149489157486 128.77119310715526)', 4326), 3);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id53_4, '활동 1', '설명 1', 'http://example.com/image53_4_1.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id53_4, ST_GeomFromText('POINT(35.529472475973904 128.50629186301487)', 4326), 1);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id53_5, '활동 1', '설명 1', 'http://example.com/image53_5_1.jpg'),
    (@day_id53_5, '활동 2', '설명 2', 'http://example.com/image53_5_2.jpg'),
    (@day_id53_5, '활동 3', '설명 3', 'http://example.com/image53_5_3.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id53_5, ST_GeomFromText('POINT(35.056707289374785 128.81454445598106)', 4326), 1),
    (@day_id53_5, ST_GeomFromText('POINT(35.33394868607431 128.98299065811236)', 4326), 2);


-- 게시물 54
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    54, 'INFJ', '여행 제목 54', '2024-12-01', '2024-12-10', 7,
    'KR', 'ASIA', '지역 54', 240992, 183965, 231743,
    40, 21, 'FEMALE', 'UNRELATED', '2024-11-01', 'RECRUITING'
);

SET @post_id54 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id54); SET @day_id54_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id54); SET @day_id54_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id54); SET @day_id54_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id54); SET @day_id54_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id54); SET @day_id54_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id54_1, '활동 1', '설명 1', 'http://example.com/image54_1_1.jpg'),
    (@day_id54_1, '활동 2', '설명 2', 'http://example.com/image54_1_2.jpg'),
    (@day_id54_1, '활동 3', '설명 3', 'http://example.com/image54_1_3.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id54_1, ST_GeomFromText('POINT(35.85781139479814 128.21422797559742)', 4326), 1);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id54_2, '활동 1', '설명 1', 'http://example.com/image54_2_1.jpg'),
    (@day_id54_2, '활동 2', '설명 2', 'http://example.com/image54_2_2.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id54_2, ST_GeomFromText('POINT(35.17052073332904 128.49725049641114)', 4326), 1),
    (@day_id54_2, ST_GeomFromText('POINT(35.816534831122226 128.9854160388948)', 4326), 2),
    (@day_id54_2, ST_GeomFromText('POINT(35.91818711859518 128.4153464680374)', 4326), 3);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id54_3, '활동 1', '설명 1', 'http://example.com/image54_3_1.jpg'),
    (@day_id54_3, '활동 2', '설명 2', 'http://example.com/image54_3_2.jpg'),
    (@day_id54_3, '활동 3', '설명 3', 'http://example.com/image54_3_3.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id54_3, ST_GeomFromText('POINT(35.58300072943256 128.45165341599778)', 4326), 1),
    (@day_id54_3, ST_GeomFromText('POINT(35.939989835416114 128.3903458527401)', 4326), 2);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id54_4, '활동 1', '설명 1', 'http://example.com/image54_4_1.jpg'),
    (@day_id54_4, '활동 2', '설명 2', 'http://example.com/image54_4_2.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id54_4, ST_GeomFromText('POINT(35.427943218326696 128.1423610552784)', 4326), 1),
    (@day_id54_4, ST_GeomFromText('POINT(35.19491733587888 128.7908933453886)', 4326), 2),
    (@day_id54_4, ST_GeomFromText('POINT(35.80836964516556 128.40583434578937)', 4326), 3);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id54_5, '활동 1', '설명 1', 'http://example.com/image54_5_1.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id54_5, ST_GeomFromText('POINT(35.04767660059189 128.1274892079875)', 4326), 1),
    (@day_id54_5, ST_GeomFromText('POINT(35.87607007610653 128.2150468142453)', 4326), 2),
    (@day_id54_5, ST_GeomFromText('POINT(35.81676870341812 128.80662792431207)', 4326), 3);


-- 게시물 55
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    55, 'ESFJ', '여행 제목 55', '2024-12-01', '2024-12-10', 5,
    'KR', 'ASIA', '지역 55', 288824, 69878, 144521,
    45, 29, 'UNRELATED', 'NON_SMOKER', '2024-11-01', 'RECRUITING'
);

SET @post_id55 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id55); SET @day_id55_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id55); SET @day_id55_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id55); SET @day_id55_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id55); SET @day_id55_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id55); SET @day_id55_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id55_1, '활동 1', '설명 1', 'http://example.com/image55_1_1.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id55_1, ST_GeomFromText('POINT(35.687461917978986 128.19049534850487)', 4326), 1),
    (@day_id55_1, ST_GeomFromText('POINT(35.674508484555275 128.90448200424163)', 4326), 2);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id55_2, '활동 1', '설명 1', 'http://example.com/image55_2_1.jpg'),
    (@day_id55_2, '활동 2', '설명 2', 'http://example.com/image55_2_2.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id55_2, ST_GeomFromText('POINT(35.52857906084053 128.57207631609398)', 4326), 1),
    (@day_id55_2, ST_GeomFromText('POINT(35.02103460143222 128.97112042678697)', 4326), 2);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id55_3, '활동 1', '설명 1', 'http://example.com/image55_3_1.jpg'),
    (@day_id55_3, '활동 2', '설명 2', 'http://example.com/image55_3_2.jpg'),
    (@day_id55_3, '활동 3', '설명 3', 'http://example.com/image55_3_3.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id55_3, ST_GeomFromText('POINT(35.540518576824894 128.15648319209362)', 4326), 1),
    (@day_id55_3, ST_GeomFromText('POINT(35.72535378978239 128.9924845489433)', 4326), 2),
    (@day_id55_3, ST_GeomFromText('POINT(35.5916535119648 128.44707348084475)', 4326), 3);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id55_4, '활동 1', '설명 1', 'http://example.com/image55_4_1.jpg'),
    (@day_id55_4, '활동 2', '설명 2', 'http://example.com/image55_4_2.jpg'),
    (@day_id55_4, '활동 3', '설명 3', 'http://example.com/image55_4_3.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id55_4, ST_GeomFromText('POINT(35.495030713390435 128.99943550528263)', 4326), 1),
    (@day_id55_4, ST_GeomFromText('POINT(35.63530761646675 128.77585103505606)', 4326), 2);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id55_5, '활동 1', '설명 1', 'http://example.com/image55_5_1.jpg'),
    (@day_id55_5, '활동 2', '설명 2', 'http://example.com/image55_5_2.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id55_5, ST_GeomFromText('POINT(35.86377396118385 128.58529389608054)', 4326), 1),
    (@day_id55_5, ST_GeomFromText('POINT(35.63896735886203 128.3536671046778)', 4326), 2);


-- 게시물 56
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    56, 'ENFP', '여행 제목 56', '2024-12-01', '2024-12-10', 3,
    'KR', 'ASIA', '지역 56', 101869, 105271, 220218,
    39, 28, 'MALE', 'UNRELATED', '2024-11-01', 'RECRUITING'
);

SET @post_id56 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id56); SET @day_id56_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id56); SET @day_id56_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id56); SET @day_id56_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id56); SET @day_id56_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id56); SET @day_id56_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id56_1, '활동 1', '설명 1', 'http://example.com/image56_1_1.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id56_1, ST_GeomFromText('POINT(35.70580211345932 128.44938736499827)', 4326), 1),
    (@day_id56_1, ST_GeomFromText('POINT(35.352575555507244 128.8067308617641)', 4326), 2);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id56_2, '활동 1', '설명 1', 'http://example.com/image56_2_1.jpg'),
    (@day_id56_2, '활동 2', '설명 2', 'http://example.com/image56_2_2.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id56_2, ST_GeomFromText('POINT(35.20331332028239 128.60065644156015)', 4326), 1),
    (@day_id56_2, ST_GeomFromText('POINT(35.44283351517343 128.38415867106696)', 4326), 2),
    (@day_id56_2, ST_GeomFromText('POINT(35.167132058186915 128.29176015487678)', 4326), 3);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id56_3, '활동 1', '설명 1', 'http://example.com/image56_3_1.jpg'),
    (@day_id56_3, '활동 2', '설명 2', 'http://example.com/image56_3_2.jpg'),
    (@day_id56_3, '활동 3', '설명 3', 'http://example.com/image56_3_3.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id56_3, ST_GeomFromText('POINT(35.512113170018424 128.79063999650654)', 4326), 1),
    (@day_id56_3, ST_GeomFromText('POINT(35.983033243948796 128.58799683967462)', 4326), 2),
    (@day_id56_3, ST_GeomFromText('POINT(35.21647592815408 128.13230732919857)', 4326), 3);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id56_4, '활동 1', '설명 1', 'http://example.com/image56_4_1.jpg'),
    (@day_id56_4, '활동 2', '설명 2', 'http://example.com/image56_4_2.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id56_4, ST_GeomFromText('POINT(35.5777335565171 128.51532460796443)', 4326), 1),
    (@day_id56_4, ST_GeomFromText('POINT(35.27027704876478 128.40577196849233)', 4326), 2);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id56_5, '활동 1', '설명 1', 'http://example.com/image56_5_1.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id56_5, ST_GeomFromText('POINT(35.64470458147827 128.8285761113491)', 4326), 1),
    (@day_id56_5, ST_GeomFromText('POINT(35.60209711777317 128.62657918352843)', 4326), 2),
    (@day_id56_5, ST_GeomFromText('POINT(35.34420497576193 128.7250150649179)', 4326), 3);


-- 게시물 57
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    57, 'ISFJ', '여행 제목 57', '2024-12-01', '2024-12-10', 7,
    'KR', 'ASIA', '지역 57', 218777, 73811, 112472,
    49, 22, 'MALE', 'SMOKER', '2024-11-01', 'RECRUITING'
);

SET @post_id57 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id57); SET @day_id57_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id57); SET @day_id57_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id57); SET @day_id57_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id57); SET @day_id57_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id57); SET @day_id57_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id57_1, '활동 1', '설명 1', 'http://example.com/image57_1_1.jpg'),
    (@day_id57_1, '활동 2', '설명 2', 'http://example.com/image57_1_2.jpg'),
    (@day_id57_1, '활동 3', '설명 3', 'http://example.com/image57_1_3.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id57_1, ST_GeomFromText('POINT(35.58882192631258 128.13287443949983)', 4326), 1),
    (@day_id57_1, ST_GeomFromText('POINT(35.89793762256317 128.11669216090482)', 4326), 2),
    (@day_id57_1, ST_GeomFromText('POINT(35.20808763951256 128.614237026281)', 4326), 3);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id57_2, '활동 1', '설명 1', 'http://example.com/image57_2_1.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id57_2, ST_GeomFromText('POINT(35.24360308988195 128.54263308006168)', 4326), 1);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id57_3, '활동 1', '설명 1', 'http://example.com/image57_3_1.jpg'),
    (@day_id57_3, '활동 2', '설명 2', 'http://example.com/image57_3_2.jpg'),
    (@day_id57_3, '활동 3', '설명 3', 'http://example.com/image57_3_3.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id57_3, ST_GeomFromText('POINT(35.50199210921105 128.9145353175073)', 4326), 1);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id57_4, '활동 1', '설명 1', 'http://example.com/image57_4_1.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id57_4, ST_GeomFromText('POINT(35.23508129238178 128.74039314542338)', 4326), 1),
    (@day_id57_4, ST_GeomFromText('POINT(35.1435997705307 128.80309372978863)', 4326), 2);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id57_5, '활동 1', '설명 1', 'http://example.com/image57_5_1.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id57_5, ST_GeomFromText('POINT(35.02280062783201 128.9632095538633)', 4326), 1);


-- 게시물 58
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    58, 'ENFJ', '여행 제목 58', '2024-12-01', '2024-12-10', 4,
    'KR', 'ASIA', '지역 58', 156343, 238231, 200603,
    50, 29, 'MALE', 'SMOKER', '2024-11-01', 'RECRUITING'
);

SET @post_id58 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id58); SET @day_id58_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id58); SET @day_id58_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id58); SET @day_id58_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id58); SET @day_id58_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id58); SET @day_id58_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id58_1, '활동 1', '설명 1', 'http://example.com/image58_1_1.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id58_1, ST_GeomFromText('POINT(35.46972076015907 128.00775497220144)', 4326), 1);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id58_2, '활동 1', '설명 1', 'http://example.com/image58_2_1.jpg'),
    (@day_id58_2, '활동 2', '설명 2', 'http://example.com/image58_2_2.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id58_2, ST_GeomFromText('POINT(35.80011452303958 128.2784289531669)', 4326), 1);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id58_3, '활동 1', '설명 1', 'http://example.com/image58_3_1.jpg'),
    (@day_id58_3, '활동 2', '설명 2', 'http://example.com/image58_3_2.jpg'),
    (@day_id58_3, '활동 3', '설명 3', 'http://example.com/image58_3_3.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id58_3, ST_GeomFromText('POINT(35.67233722192096 128.69627739502047)', 4326), 1),
    (@day_id58_3, ST_GeomFromText('POINT(35.63224039644343 128.7063510550295)', 4326), 2),
    (@day_id58_3, ST_GeomFromText('POINT(35.14063418081021 128.75404655954196)', 4326), 3);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id58_4, '활동 1', '설명 1', 'http://example.com/image58_4_1.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id58_4, ST_GeomFromText('POINT(35.73290603884944 128.10428268494715)', 4326), 1),
    (@day_id58_4, ST_GeomFromText('POINT(35.59554991871978 128.20711145624136)', 4326), 2),
    (@day_id58_4, ST_GeomFromText('POINT(35.294599444396184 128.16133176875135)', 4326), 3);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id58_5, '활동 1', '설명 1', 'http://example.com/image58_5_1.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id58_5, ST_GeomFromText('POINT(35.90646349400844 128.6068637924812)', 4326), 1);


-- 게시물 59
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    59, 'INTP', '여행 제목 59', '2024-12-01', '2024-12-10', 5,
    'KR', 'ASIA', '지역 59', 278487, 228082, 193977,
    48, 28, 'MALE', 'SMOKER', '2024-11-01', 'RECRUITING'
);

SET @post_id59 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id59); SET @day_id59_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id59); SET @day_id59_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id59); SET @day_id59_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id59); SET @day_id59_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id59); SET @day_id59_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id59_1, '활동 1', '설명 1', 'http://example.com/image59_1_1.jpg'),
    (@day_id59_1, '활동 2', '설명 2', 'http://example.com/image59_1_2.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id59_1, ST_GeomFromText('POINT(35.023104176968815 128.97061038276243)', 4326), 1);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id59_2, '활동 1', '설명 1', 'http://example.com/image59_2_1.jpg'),
    (@day_id59_2, '활동 2', '설명 2', 'http://example.com/image59_2_2.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id59_2, ST_GeomFromText('POINT(35.61194753573941 128.9932940025449)', 4326), 1),
    (@day_id59_2, ST_GeomFromText('POINT(35.37395434101873 128.60778839954446)', 4326), 2),
    (@day_id59_2, ST_GeomFromText('POINT(35.64350315989669 128.16596508815638)', 4326), 3);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id59_3, '활동 1', '설명 1', 'http://example.com/image59_3_1.jpg'),
    (@day_id59_3, '활동 2', '설명 2', 'http://example.com/image59_3_2.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id59_3, ST_GeomFromText('POINT(35.09465861029365 128.30023939715852)', 4326), 1),
    (@day_id59_3, ST_GeomFromText('POINT(35.43626717744044 128.98762221492558)', 4326), 2),
    (@day_id59_3, ST_GeomFromText('POINT(35.595304565039626 128.08530200119185)', 4326), 3);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id59_4, '활동 1', '설명 1', 'http://example.com/image59_4_1.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id59_4, ST_GeomFromText('POINT(35.055553786799436 128.14829272204733)', 4326), 1),
    (@day_id59_4, ST_GeomFromText('POINT(35.780133341641125 128.952264040972)', 4326), 2),
    (@day_id59_4, ST_GeomFromText('POINT(35.743193444042575 128.18522578723443)', 4326), 3);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id59_5, '활동 1', '설명 1', 'http://example.com/image59_5_1.jpg'),
    (@day_id59_5, '활동 2', '설명 2', 'http://example.com/image59_5_2.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id59_5, ST_GeomFromText('POINT(35.51860753063238 128.02858547359412)', 4326), 1);


-- 게시물 60
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    60, 'INTJ', '여행 제목 60', '2024-12-01', '2024-12-10', 4,
    'KR', 'ASIA', '지역 60', 188721, 89500, 235560,
    58, 26, 'UNRELATED', 'SMOKER', '2024-11-01', 'RECRUITING'
);

SET @post_id60 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id60); SET @day_id60_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id60); SET @day_id60_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id60); SET @day_id60_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id60); SET @day_id60_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id60); SET @day_id60_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id60_1, '활동 1', '설명 1', 'http://example.com/image60_1_1.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id60_1, ST_GeomFromText('POINT(35.46182265823395 128.80433304435883)', 4326), 1),
    (@day_id60_1, ST_GeomFromText('POINT(35.0276982997936 128.88016142495053)', 4326), 2),
    (@day_id60_1, ST_GeomFromText('POINT(35.51235594020455 128.8606063947349)', 4326), 3);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id60_2, '활동 1', '설명 1', 'http://example.com/image60_2_1.jpg'),
    (@day_id60_2, '활동 2', '설명 2', 'http://example.com/image60_2_2.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id60_2, ST_GeomFromText('POINT(35.86789140301589 128.04223690797343)', 4326), 1);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id60_3, '활동 1', '설명 1', 'http://example.com/image60_3_1.jpg'),
    (@day_id60_3, '활동 2', '설명 2', 'http://example.com/image60_3_2.jpg'),
    (@day_id60_3, '활동 3', '설명 3', 'http://example.com/image60_3_3.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id60_3, ST_GeomFromText('POINT(35.35941360847881 128.6059601187073)', 4326), 1);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id60_4, '활동 1', '설명 1', 'http://example.com/image60_4_1.jpg'),
    (@day_id60_4, '활동 2', '설명 2', 'http://example.com/image60_4_2.jpg'),
    (@day_id60_4, '활동 3', '설명 3', 'http://example.com/image60_4_3.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id60_4, ST_GeomFromText('POINT(35.49579015764045 128.860289187514)', 4326), 1),
    (@day_id60_4, ST_GeomFromText('POINT(35.93235544176361 128.28706440082152)', 4326), 2);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id60_5, '활동 1', '설명 1', 'http://example.com/image60_5_1.jpg'),
    (@day_id60_5, '활동 2', '설명 2', 'http://example.com/image60_5_2.jpg'),
    (@day_id60_5, '활동 3', '설명 3', 'http://example.com/image60_5_3.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id60_5, ST_GeomFromText('POINT(35.369656562552414 128.90260533626574)', 4326), 1);


-- 게시물 61
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    61, 'ENTJ', '여행 제목 61', '2024-12-01', '2024-12-10', 9,
    'KR', 'ASIA', '지역 61', 467953, 67573, 247168,
    48, 27, 'UNRELATED', 'UNRELATED', '2024-11-01', 'RECRUITING'
);

SET @post_id61 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id61); SET @day_id61_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id61); SET @day_id61_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id61); SET @day_id61_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id61); SET @day_id61_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id61); SET @day_id61_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id61_1, '활동 1', '설명 1', 'http://example.com/image61_1_1.jpg'),
    (@day_id61_1, '활동 2', '설명 2', 'http://example.com/image61_1_2.jpg'),
    (@day_id61_1, '활동 3', '설명 3', 'http://example.com/image61_1_3.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id61_1, ST_GeomFromText('POINT(35.71886166732978 128.17634436518503)', 4326), 1),
    (@day_id61_1, ST_GeomFromText('POINT(35.77403659939017 128.6351315570837)', 4326), 2);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id61_2, '활동 1', '설명 1', 'http://example.com/image61_2_1.jpg'),
    (@day_id61_2, '활동 2', '설명 2', 'http://example.com/image61_2_2.jpg'),
    (@day_id61_2, '활동 3', '설명 3', 'http://example.com/image61_2_3.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id61_2, ST_GeomFromText('POINT(35.895159613218325 128.21670200546467)', 4326), 1);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id61_3, '활동 1', '설명 1', 'http://example.com/image61_3_1.jpg'),
    (@day_id61_3, '활동 2', '설명 2', 'http://example.com/image61_3_2.jpg'),
    (@day_id61_3, '활동 3', '설명 3', 'http://example.com/image61_3_3.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id61_3, ST_GeomFromText('POINT(35.10901915930419 128.44731376466922)', 4326), 1);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id61_4, '활동 1', '설명 1', 'http://example.com/image61_4_1.jpg'),
    (@day_id61_4, '활동 2', '설명 2', 'http://example.com/image61_4_2.jpg'),
    (@day_id61_4, '활동 3', '설명 3', 'http://example.com/image61_4_3.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id61_4, ST_GeomFromText('POINT(35.12410641265296 128.44039654775685)', 4326), 1),
    (@day_id61_4, ST_GeomFromText('POINT(35.65677714047579 128.16548596529267)', 4326), 2);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id61_5, '활동 1', '설명 1', 'http://example.com/image61_5_1.jpg'),
    (@day_id61_5, '활동 2', '설명 2', 'http://example.com/image61_5_2.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id61_5, ST_GeomFromText('POINT(35.06275148529675 128.71454963824047)', 4326), 1),
    (@day_id61_5, ST_GeomFromText('POINT(35.06511841777054 128.7376474303848)', 4326), 2);


-- 게시물 62
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    62, 'INFP', '여행 제목 62', '2024-12-01', '2024-12-10', 10,
    'KR', 'ASIA', '지역 62', 588991, 170342, 229776,
    52, 24, 'UNRELATED', 'SMOKER', '2024-11-01', 'RECRUITING'
);

SET @post_id62 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id62); SET @day_id62_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id62); SET @day_id62_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id62); SET @day_id62_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id62); SET @day_id62_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id62); SET @day_id62_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id62_1, '활동 1', '설명 1', 'http://example.com/image62_1_1.jpg'),
    (@day_id62_1, '활동 2', '설명 2', 'http://example.com/image62_1_2.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id62_1, ST_GeomFromText('POINT(35.15033186784088 128.56133694813042)', 4326), 1),
    (@day_id62_1, ST_GeomFromText('POINT(35.200649326808346 128.12861131724122)', 4326), 2);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id62_2, '활동 1', '설명 1', 'http://example.com/image62_2_1.jpg'),
    (@day_id62_2, '활동 2', '설명 2', 'http://example.com/image62_2_2.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id62_2, ST_GeomFromText('POINT(35.53812189106767 128.23789034350733)', 4326), 1),
    (@day_id62_2, ST_GeomFromText('POINT(35.29783110976686 128.88700050654793)', 4326), 2);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id62_3, '활동 1', '설명 1', 'http://example.com/image62_3_1.jpg'),
    (@day_id62_3, '활동 2', '설명 2', 'http://example.com/image62_3_2.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id62_3, ST_GeomFromText('POINT(35.40543557282093 128.56045895609856)', 4326), 1),
    (@day_id62_3, ST_GeomFromText('POINT(35.91845914344466 128.5315011281563)', 4326), 2);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id62_4, '활동 1', '설명 1', 'http://example.com/image62_4_1.jpg'),
    (@day_id62_4, '활동 2', '설명 2', 'http://example.com/image62_4_2.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id62_4, ST_GeomFromText('POINT(35.51268246039301 128.73587390266405)', 4326), 1),
    (@day_id62_4, ST_GeomFromText('POINT(35.89933245557996 128.90061881932752)', 4326), 2),
    (@day_id62_4, ST_GeomFromText('POINT(35.28453483611628 128.70752538393936)', 4326), 3);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id62_5, '활동 1', '설명 1', 'http://example.com/image62_5_1.jpg'),
    (@day_id62_5, '활동 2', '설명 2', 'http://example.com/image62_5_2.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id62_5, ST_GeomFromText('POINT(35.39117641932151 128.66341266464937)', 4326), 1),
    (@day_id62_5, ST_GeomFromText('POINT(35.705976899489166 128.891052749871)', 4326), 2),
    (@day_id62_5, ST_GeomFromText('POINT(35.3472063398975 128.2070814008012)', 4326), 3);


-- 게시물 63
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    63, 'ESFP', '여행 제목 63', '2024-12-01', '2024-12-10', 3,
    'KR', 'ASIA', '지역 63', 247542, 233944, 373359,
    46, 24, 'MALE', 'SMOKER', '2024-11-01', 'RECRUITING'
);

SET @post_id63 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id63); SET @day_id63_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id63); SET @day_id63_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id63); SET @day_id63_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id63); SET @day_id63_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id63); SET @day_id63_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id63_1, '활동 1', '설명 1', 'http://example.com/image63_1_1.jpg'),
    (@day_id63_1, '활동 2', '설명 2', 'http://example.com/image63_1_2.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id63_1, ST_GeomFromText('POINT(35.029276332013815 128.6410757475943)', 4326), 1),
    (@day_id63_1, ST_GeomFromText('POINT(35.293170417187845 128.86227876825424)', 4326), 2),
    (@day_id63_1, ST_GeomFromText('POINT(35.472534687169464 128.07017183360358)', 4326), 3);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id63_2, '활동 1', '설명 1', 'http://example.com/image63_2_1.jpg'),
    (@day_id63_2, '활동 2', '설명 2', 'http://example.com/image63_2_2.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id63_2, ST_GeomFromText('POINT(35.96747391375367 128.585016357133)', 4326), 1);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id63_3, '활동 1', '설명 1', 'http://example.com/image63_3_1.jpg'),
    (@day_id63_3, '활동 2', '설명 2', 'http://example.com/image63_3_2.jpg'),
    (@day_id63_3, '활동 3', '설명 3', 'http://example.com/image63_3_3.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id63_3, ST_GeomFromText('POINT(35.92527688399804 128.65920354671638)', 4326), 1);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id63_4, '활동 1', '설명 1', 'http://example.com/image63_4_1.jpg'),
    (@day_id63_4, '활동 2', '설명 2', 'http://example.com/image63_4_2.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id63_4, ST_GeomFromText('POINT(35.67737220228913 128.05747991052635)', 4326), 1),
    (@day_id63_4, ST_GeomFromText('POINT(35.87441930110972 128.01010636245152)', 4326), 2),
    (@day_id63_4, ST_GeomFromText('POINT(35.52260194855463 128.76099652634633)', 4326), 3);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id63_5, '활동 1', '설명 1', 'http://example.com/image63_5_1.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id63_5, ST_GeomFromText('POINT(35.85442808640849 128.2498619762397)', 4326), 1),
    (@day_id63_5, ST_GeomFromText('POINT(35.83470287318918 128.63378099795474)', 4326), 2);


-- 게시물 64
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    64, 'ENFJ', '여행 제목 64', '2024-12-01', '2024-12-10', 7,
    'KR', 'ASIA', '지역 64', 543333, 93531, 207256,
    31, 26, 'UNRELATED', 'SMOKER', '2024-11-01', 'RECRUITING'
);

SET @post_id64 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id64); SET @day_id64_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id64); SET @day_id64_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id64); SET @day_id64_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id64); SET @day_id64_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id64); SET @day_id64_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id64_1, '활동 1', '설명 1', 'http://example.com/image64_1_1.jpg'),
    (@day_id64_1, '활동 2', '설명 2', 'http://example.com/image64_1_2.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id64_1, ST_GeomFromText('POINT(35.63746016218313 128.92731510908405)', 4326), 1),
    (@day_id64_1, ST_GeomFromText('POINT(35.42017820389066 128.02696607073867)', 4326), 2),
    (@day_id64_1, ST_GeomFromText('POINT(35.515483106449395 128.5056786717289)', 4326), 3);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id64_2, '활동 1', '설명 1', 'http://example.com/image64_2_1.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id64_2, ST_GeomFromText('POINT(35.16761456848916 128.20446721612595)', 4326), 1),
    (@day_id64_2, ST_GeomFromText('POINT(35.584158701049496 128.9945856803555)', 4326), 2),
    (@day_id64_2, ST_GeomFromText('POINT(35.8648332531553 128.44170516925112)', 4326), 3);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id64_3, '활동 1', '설명 1', 'http://example.com/image64_3_1.jpg'),
    (@day_id64_3, '활동 2', '설명 2', 'http://example.com/image64_3_2.jpg'),
    (@day_id64_3, '활동 3', '설명 3', 'http://example.com/image64_3_3.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id64_3, ST_GeomFromText('POINT(35.78273735242783 128.09228179102533)', 4326), 1),
    (@day_id64_3, ST_GeomFromText('POINT(35.3351650884804 128.78429987515722)', 4326), 2),
    (@day_id64_3, ST_GeomFromText('POINT(35.34186101789194 128.51958105515678)', 4326), 3);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id64_4, '활동 1', '설명 1', 'http://example.com/image64_4_1.jpg'),
    (@day_id64_4, '활동 2', '설명 2', 'http://example.com/image64_4_2.jpg'),
    (@day_id64_4, '활동 3', '설명 3', 'http://example.com/image64_4_3.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id64_4, ST_GeomFromText('POINT(35.837931409696 128.5303864798775)', 4326), 1),
    (@day_id64_4, ST_GeomFromText('POINT(35.140248917102795 128.88646597806485)', 4326), 2);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id64_5, '활동 1', '설명 1', 'http://example.com/image64_5_1.jpg'),
    (@day_id64_5, '활동 2', '설명 2', 'http://example.com/image64_5_2.jpg'),
    (@day_id64_5, '활동 3', '설명 3', 'http://example.com/image64_5_3.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id64_5, ST_GeomFromText('POINT(35.06900562157427 128.10063362046074)', 4326), 1),
    (@day_id64_5, ST_GeomFromText('POINT(35.692180465623686 128.5904374004194)', 4326), 2);


-- 게시물 65
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    65, 'ISFP', '여행 제목 65', '2024-12-01', '2024-12-10', 6,
    'KR', 'ASIA', '지역 65', 242315, 121529, 273156,
    45, 21, 'FEMALE', 'SMOKER', '2024-11-01', 'RECRUITING'
);

SET @post_id65 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id65); SET @day_id65_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id65); SET @day_id65_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id65); SET @day_id65_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id65); SET @day_id65_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id65); SET @day_id65_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id65_1, '활동 1', '설명 1', 'http://example.com/image65_1_1.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id65_1, ST_GeomFromText('POINT(35.928718373043644 128.44581017982745)', 4326), 1),
    (@day_id65_1, ST_GeomFromText('POINT(35.91124037105191 128.53282374284976)', 4326), 2),
    (@day_id65_1, ST_GeomFromText('POINT(35.11950435044872 128.89272299268222)', 4326), 3);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id65_2, '활동 1', '설명 1', 'http://example.com/image65_2_1.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id65_2, ST_GeomFromText('POINT(35.69695935477245 128.23252772674573)', 4326), 1);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id65_3, '활동 1', '설명 1', 'http://example.com/image65_3_1.jpg'),
    (@day_id65_3, '활동 2', '설명 2', 'http://example.com/image65_3_2.jpg'),
    (@day_id65_3, '활동 3', '설명 3', 'http://example.com/image65_3_3.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id65_3, ST_GeomFromText('POINT(35.26749193155512 128.11457779602404)', 4326), 1);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id65_4, '활동 1', '설명 1', 'http://example.com/image65_4_1.jpg'),
    (@day_id65_4, '활동 2', '설명 2', 'http://example.com/image65_4_2.jpg'),
    (@day_id65_4, '활동 3', '설명 3', 'http://example.com/image65_4_3.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id65_4, ST_GeomFromText('POINT(35.9419113609302 128.53752911472776)', 4326), 1),
    (@day_id65_4, ST_GeomFromText('POINT(35.293889766059195 128.88860984209123)', 4326), 2);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id65_5, '활동 1', '설명 1', 'http://example.com/image65_5_1.jpg'),
    (@day_id65_5, '활동 2', '설명 2', 'http://example.com/image65_5_2.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id65_5, ST_GeomFromText('POINT(35.88265460928403 128.23658542443079)', 4326), 1);


-- 게시물 66
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    66, 'ENTJ', '여행 제목 66', '2024-12-01', '2024-12-10', 6,
    'KR', 'ASIA', '지역 66', 487496, 53807, 211539,
    33, 22, 'UNRELATED', 'UNRELATED', '2024-11-01', 'RECRUITING'
);

SET @post_id66 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id66); SET @day_id66_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id66); SET @day_id66_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id66); SET @day_id66_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id66); SET @day_id66_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id66); SET @day_id66_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id66_1, '활동 1', '설명 1', 'http://example.com/image66_1_1.jpg'),
    (@day_id66_1, '활동 2', '설명 2', 'http://example.com/image66_1_2.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id66_1, ST_GeomFromText('POINT(35.82983563393184 128.51660356918336)', 4326), 1),
    (@day_id66_1, ST_GeomFromText('POINT(35.04723599372244 128.80637527072338)', 4326), 2),
    (@day_id66_1, ST_GeomFromText('POINT(35.342426450792544 128.92706729340702)', 4326), 3);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id66_2, '활동 1', '설명 1', 'http://example.com/image66_2_1.jpg'),
    (@day_id66_2, '활동 2', '설명 2', 'http://example.com/image66_2_2.jpg'),
    (@day_id66_2, '활동 3', '설명 3', 'http://example.com/image66_2_3.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id66_2, ST_GeomFromText('POINT(35.87854899431029 128.39720031518024)', 4326), 1);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id66_3, '활동 1', '설명 1', 'http://example.com/image66_3_1.jpg'),
    (@day_id66_3, '활동 2', '설명 2', 'http://example.com/image66_3_2.jpg'),
    (@day_id66_3, '활동 3', '설명 3', 'http://example.com/image66_3_3.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id66_3, ST_GeomFromText('POINT(35.27269519563753 128.2302969211114)', 4326), 1),
    (@day_id66_3, ST_GeomFromText('POINT(35.09775542134554 128.14729276884586)', 4326), 2);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id66_4, '활동 1', '설명 1', 'http://example.com/image66_4_1.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id66_4, ST_GeomFromText('POINT(35.3963400677067 128.29369395968394)', 4326), 1),
    (@day_id66_4, ST_GeomFromText('POINT(35.78726781217115 128.98231351774558)', 4326), 2);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id66_5, '활동 1', '설명 1', 'http://example.com/image66_5_1.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id66_5, ST_GeomFromText('POINT(35.64755170051919 128.51947000199002)', 4326), 1);


-- 게시물 67
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    67, 'ENFJ', '여행 제목 67', '2024-12-01', '2024-12-10', 10,
    'KR', 'ASIA', '지역 67', 507014, 115128, 161799,
    56, 24, 'FEMALE', 'UNRELATED', '2024-11-01', 'RECRUITING'
);

SET @post_id67 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id67); SET @day_id67_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id67); SET @day_id67_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id67); SET @day_id67_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id67); SET @day_id67_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id67); SET @day_id67_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id67_1, '활동 1', '설명 1', 'http://example.com/image67_1_1.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id67_1, ST_GeomFromText('POINT(35.95770025132053 128.29969902996513)', 4326), 1),
    (@day_id67_1, ST_GeomFromText('POINT(35.66414789948415 128.38708709691662)', 4326), 2),
    (@day_id67_1, ST_GeomFromText('POINT(35.53997582191545 128.6040068557916)', 4326), 3);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id67_2, '활동 1', '설명 1', 'http://example.com/image67_2_1.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id67_2, ST_GeomFromText('POINT(35.78786572144176 128.45221340057563)', 4326), 1);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id67_3, '활동 1', '설명 1', 'http://example.com/image67_3_1.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id67_3, ST_GeomFromText('POINT(35.98418232452171 128.58683028369893)', 4326), 1),
    (@day_id67_3, ST_GeomFromText('POINT(35.008039344721155 128.69910262972206)', 4326), 2),
    (@day_id67_3, ST_GeomFromText('POINT(35.73943309416312 128.35017332503787)', 4326), 3);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id67_4, '활동 1', '설명 1', 'http://example.com/image67_4_1.jpg'),
    (@day_id67_4, '활동 2', '설명 2', 'http://example.com/image67_4_2.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id67_4, ST_GeomFromText('POINT(35.9234896182962 128.75206810193015)', 4326), 1);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id67_5, '활동 1', '설명 1', 'http://example.com/image67_5_1.jpg'),
    (@day_id67_5, '활동 2', '설명 2', 'http://example.com/image67_5_2.jpg'),
    (@day_id67_5, '활동 3', '설명 3', 'http://example.com/image67_5_3.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id67_5, ST_GeomFromText('POINT(35.04139283000932 128.92366780479313)', 4326), 1),
    (@day_id67_5, ST_GeomFromText('POINT(35.059565451781246 128.41628774549636)', 4326), 2);


-- 게시물 68
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    68, 'ESFP', '여행 제목 68', '2024-12-01', '2024-12-10', 1,
    'KR', 'ASIA', '지역 68', 312088, 120083, 108116,
    30, 24, 'FEMALE', 'UNRELATED', '2024-11-01', 'RECRUITING'
);

SET @post_id68 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id68); SET @day_id68_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id68); SET @day_id68_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id68); SET @day_id68_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id68); SET @day_id68_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id68); SET @day_id68_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id68_1, '활동 1', '설명 1', 'http://example.com/image68_1_1.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id68_1, ST_GeomFromText('POINT(35.07211863871229 128.53103580560818)', 4326), 1),
    (@day_id68_1, ST_GeomFromText('POINT(35.20129407354387 128.85743825496635)', 4326), 2),
    (@day_id68_1, ST_GeomFromText('POINT(35.48934016558213 128.52729838625567)', 4326), 3);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id68_2, '활동 1', '설명 1', 'http://example.com/image68_2_1.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id68_2, ST_GeomFromText('POINT(35.95829934051426 128.40115515468824)', 4326), 1);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id68_3, '활동 1', '설명 1', 'http://example.com/image68_3_1.jpg'),
    (@day_id68_3, '활동 2', '설명 2', 'http://example.com/image68_3_2.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id68_3, ST_GeomFromText('POINT(35.316075645044336 128.5220965662709)', 4326), 1),
    (@day_id68_3, ST_GeomFromText('POINT(35.769634978025614 128.89363323188053)', 4326), 2);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id68_4, '활동 1', '설명 1', 'http://example.com/image68_4_1.jpg'),
    (@day_id68_4, '활동 2', '설명 2', 'http://example.com/image68_4_2.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id68_4, ST_GeomFromText('POINT(35.03334209381192 128.110007768788)', 4326), 1),
    (@day_id68_4, ST_GeomFromText('POINT(35.36395574022931 128.97010697256812)', 4326), 2),
    (@day_id68_4, ST_GeomFromText('POINT(35.28451458888641 128.15824395608692)', 4326), 3);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id68_5, '활동 1', '설명 1', 'http://example.com/image68_5_1.jpg'),
    (@day_id68_5, '활동 2', '설명 2', 'http://example.com/image68_5_2.jpg'),
    (@day_id68_5, '활동 3', '설명 3', 'http://example.com/image68_5_3.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id68_5, ST_GeomFromText('POINT(35.22929826311512 128.88441705910677)', 4326), 1);


-- 게시물 69
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    69, 'ISFP', '여행 제목 69', '2024-12-01', '2024-12-10', 3,
    'KR', 'ASIA', '지역 69', 158225, 217459, 129350,
    50, 26, 'MALE', 'NON_SMOKER', '2024-11-01', 'RECRUITING'
);

SET @post_id69 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id69); SET @day_id69_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id69); SET @day_id69_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id69); SET @day_id69_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id69); SET @day_id69_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id69); SET @day_id69_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id69_1, '활동 1', '설명 1', 'http://example.com/image69_1_1.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id69_1, ST_GeomFromText('POINT(35.107390872154134 128.7279497640337)', 4326), 1),
    (@day_id69_1, ST_GeomFromText('POINT(35.846690624789545 128.2012592831804)', 4326), 2),
    (@day_id69_1, ST_GeomFromText('POINT(35.21020776696984 128.31128637405806)', 4326), 3);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id69_2, '활동 1', '설명 1', 'http://example.com/image69_2_1.jpg'),
    (@day_id69_2, '활동 2', '설명 2', 'http://example.com/image69_2_2.jpg'),
    (@day_id69_2, '활동 3', '설명 3', 'http://example.com/image69_2_3.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id69_2, ST_GeomFromText('POINT(35.83350130859173 128.76167663262518)', 4326), 1),
    (@day_id69_2, ST_GeomFromText('POINT(35.13962274249247 128.59654268354)', 4326), 2),
    (@day_id69_2, ST_GeomFromText('POINT(35.00384641177742 128.28818325329541)', 4326), 3);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id69_3, '활동 1', '설명 1', 'http://example.com/image69_3_1.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id69_3, ST_GeomFromText('POINT(35.78322226413001 128.43306117072606)', 4326), 1),
    (@day_id69_3, ST_GeomFromText('POINT(35.164746619524195 128.70795836271807)', 4326), 2),
    (@day_id69_3, ST_GeomFromText('POINT(35.36348229550974 128.59101098946144)', 4326), 3);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id69_4, '활동 1', '설명 1', 'http://example.com/image69_4_1.jpg'),
    (@day_id69_4, '활동 2', '설명 2', 'http://example.com/image69_4_2.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id69_4, ST_GeomFromText('POINT(35.568826203070984 128.67294856472668)', 4326), 1),
    (@day_id69_4, ST_GeomFromText('POINT(35.505876411589895 128.18409104952377)', 4326), 2),
    (@day_id69_4, ST_GeomFromText('POINT(35.80565347722632 128.8138480229686)', 4326), 3);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id69_5, '활동 1', '설명 1', 'http://example.com/image69_5_1.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id69_5, ST_GeomFromText('POINT(35.73403578393214 128.0357392380214)', 4326), 1),
    (@day_id69_5, ST_GeomFromText('POINT(35.55416000684542 128.21997805361323)', 4326), 2);


-- 게시물 70
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    70, 'ESTJ', '여행 제목 70', '2024-12-01', '2024-12-10', 5,
    'KR', 'ASIA', '지역 70', 481980, 135748, 363805,
    33, 23, 'UNRELATED', 'UNRELATED', '2024-11-01', 'RECRUITING'
);

SET @post_id70 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id70); SET @day_id70_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id70); SET @day_id70_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id70); SET @day_id70_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id70); SET @day_id70_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id70); SET @day_id70_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id70_1, '활동 1', '설명 1', 'http://example.com/image70_1_1.jpg'),
    (@day_id70_1, '활동 2', '설명 2', 'http://example.com/image70_1_2.jpg'),
    (@day_id70_1, '활동 3', '설명 3', 'http://example.com/image70_1_3.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id70_1, ST_GeomFromText('POINT(35.48776915007844 128.7714480129948)', 4326), 1),
    (@day_id70_1, ST_GeomFromText('POINT(35.32615553875334 128.72838063715963)', 4326), 2);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id70_2, '활동 1', '설명 1', 'http://example.com/image70_2_1.jpg'),
    (@day_id70_2, '활동 2', '설명 2', 'http://example.com/image70_2_2.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id70_2, ST_GeomFromText('POINT(35.575849937588295 128.02055786808776)', 4326), 1);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id70_3, '활동 1', '설명 1', 'http://example.com/image70_3_1.jpg'),
    (@day_id70_3, '활동 2', '설명 2', 'http://example.com/image70_3_2.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id70_3, ST_GeomFromText('POINT(35.35502411224489 128.07990901459263)', 4326), 1),
    (@day_id70_3, ST_GeomFromText('POINT(35.823933715155356 128.67783679372184)', 4326), 2),
    (@day_id70_3, ST_GeomFromText('POINT(35.37198992225995 128.77014730364562)', 4326), 3);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id70_4, '활동 1', '설명 1', 'http://example.com/image70_4_1.jpg'),
    (@day_id70_4, '활동 2', '설명 2', 'http://example.com/image70_4_2.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id70_4, ST_GeomFromText('POINT(35.044135783980515 128.82983437699554)', 4326), 1),
    (@day_id70_4, ST_GeomFromText('POINT(35.36369825216029 128.2799630710327)', 4326), 2),
    (@day_id70_4, ST_GeomFromText('POINT(35.56979415884261 128.6505241981368)', 4326), 3);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id70_5, '활동 1', '설명 1', 'http://example.com/image70_5_1.jpg'),
    (@day_id70_5, '활동 2', '설명 2', 'http://example.com/image70_5_2.jpg'),
    (@day_id70_5, '활동 3', '설명 3', 'http://example.com/image70_5_3.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id70_5, ST_GeomFromText('POINT(35.5429615777085 128.56918787362193)', 4326), 1);


-- 게시물 71
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    71, 'ESTP', '여행 제목 71', '2024-12-01', '2024-12-10', 7,
    'KR', 'ASIA', '지역 71', 325207, 67752, 143913,
    37, 24, 'UNRELATED', 'NON_SMOKER', '2024-11-01', 'RECRUITING'
);

SET @post_id71 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id71); SET @day_id71_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id71); SET @day_id71_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id71); SET @day_id71_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id71); SET @day_id71_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id71); SET @day_id71_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id71_1, '활동 1', '설명 1', 'http://example.com/image71_1_1.jpg'),
    (@day_id71_1, '활동 2', '설명 2', 'http://example.com/image71_1_2.jpg'),
    (@day_id71_1, '활동 3', '설명 3', 'http://example.com/image71_1_3.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id71_1, ST_GeomFromText('POINT(35.254636826751586 128.89273501226984)', 4326), 1),
    (@day_id71_1, ST_GeomFromText('POINT(35.79347844443572 128.93658228503998)', 4326), 2),
    (@day_id71_1, ST_GeomFromText('POINT(35.37666602409171 128.87806886957185)', 4326), 3);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id71_2, '활동 1', '설명 1', 'http://example.com/image71_2_1.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id71_2, ST_GeomFromText('POINT(35.78547202888622 128.53871539152527)', 4326), 1),
    (@day_id71_2, ST_GeomFromText('POINT(35.352795484836406 128.33851195412788)', 4326), 2),
    (@day_id71_2, ST_GeomFromText('POINT(35.24083317338724 128.7971997669477)', 4326), 3);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id71_3, '활동 1', '설명 1', 'http://example.com/image71_3_1.jpg'),
    (@day_id71_3, '활동 2', '설명 2', 'http://example.com/image71_3_2.jpg'),
    (@day_id71_3, '활동 3', '설명 3', 'http://example.com/image71_3_3.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id71_3, ST_GeomFromText('POINT(35.2100079538133 128.38898097236296)', 4326), 1),
    (@day_id71_3, ST_GeomFromText('POINT(35.30086639787351 128.51686570397456)', 4326), 2),
    (@day_id71_3, ST_GeomFromText('POINT(35.12814230227192 128.5277543679922)', 4326), 3);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id71_4, '활동 1', '설명 1', 'http://example.com/image71_4_1.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id71_4, ST_GeomFromText('POINT(35.294516584657245 128.1693432257049)', 4326), 1),
    (@day_id71_4, ST_GeomFromText('POINT(35.33441548717319 128.75911323073532)', 4326), 2);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id71_5, '활동 1', '설명 1', 'http://example.com/image71_5_1.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id71_5, ST_GeomFromText('POINT(35.64468825801235 128.21531660202888)', 4326), 1),
    (@day_id71_5, ST_GeomFromText('POINT(35.21101231903785 128.91543535350675)', 4326), 2),
    (@day_id71_5, ST_GeomFromText('POINT(35.23335109738852 128.8792352739096)', 4326), 3);


-- 게시물 72
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    72, 'ISFJ', '여행 제목 72', '2024-12-01', '2024-12-10', 3,
    'KR', 'ASIA', '지역 72', 358241, 248790, 251721,
    39, 27, 'MALE', 'UNRELATED', '2024-11-01', 'RECRUITING'
);

SET @post_id72 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id72); SET @day_id72_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id72); SET @day_id72_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id72); SET @day_id72_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id72); SET @day_id72_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id72); SET @day_id72_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id72_1, '활동 1', '설명 1', 'http://example.com/image72_1_1.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id72_1, ST_GeomFromText('POINT(35.26448127893638 128.38365182919537)', 4326), 1),
    (@day_id72_1, ST_GeomFromText('POINT(35.49223090126966 128.85172278205405)', 4326), 2);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id72_2, '활동 1', '설명 1', 'http://example.com/image72_2_1.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id72_2, ST_GeomFromText('POINT(35.023425861574985 128.21556411307907)', 4326), 1);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id72_3, '활동 1', '설명 1', 'http://example.com/image72_3_1.jpg'),
    (@day_id72_3, '활동 2', '설명 2', 'http://example.com/image72_3_2.jpg'),
    (@day_id72_3, '활동 3', '설명 3', 'http://example.com/image72_3_3.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id72_3, ST_GeomFromText('POINT(35.1904043984874 128.41650083653286)', 4326), 1);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id72_4, '활동 1', '설명 1', 'http://example.com/image72_4_1.jpg'),
    (@day_id72_4, '활동 2', '설명 2', 'http://example.com/image72_4_2.jpg'),
    (@day_id72_4, '활동 3', '설명 3', 'http://example.com/image72_4_3.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id72_4, ST_GeomFromText('POINT(35.7852371842343 128.37932345915272)', 4326), 1);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id72_5, '활동 1', '설명 1', 'http://example.com/image72_5_1.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id72_5, ST_GeomFromText('POINT(35.298217885464624 128.06275147597688)', 4326), 1);


-- 게시물 73
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    73, 'ENTJ', '여행 제목 73', '2024-12-01', '2024-12-10', 9,
    'KR', 'ASIA', '지역 73', 367008, 85150, 207935,
    57, 25, 'UNRELATED', 'UNRELATED', '2024-11-01', 'RECRUITING'
);

SET @post_id73 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id73); SET @day_id73_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id73); SET @day_id73_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id73); SET @day_id73_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id73); SET @day_id73_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id73); SET @day_id73_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id73_1, '활동 1', '설명 1', 'http://example.com/image73_1_1.jpg'),
    (@day_id73_1, '활동 2', '설명 2', 'http://example.com/image73_1_2.jpg'),
    (@day_id73_1, '활동 3', '설명 3', 'http://example.com/image73_1_3.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id73_1, ST_GeomFromText('POINT(35.62810554835878 128.36508456991913)', 4326), 1),
    (@day_id73_1, ST_GeomFromText('POINT(35.183186658227555 128.58603144175058)', 4326), 2);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id73_2, '활동 1', '설명 1', 'http://example.com/image73_2_1.jpg'),
    (@day_id73_2, '활동 2', '설명 2', 'http://example.com/image73_2_2.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id73_2, ST_GeomFromText('POINT(35.85020126863871 128.0535948213905)', 4326), 1),
    (@day_id73_2, ST_GeomFromText('POINT(35.562709124933974 128.425270391035)', 4326), 2);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id73_3, '활동 1', '설명 1', 'http://example.com/image73_3_1.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id73_3, ST_GeomFromText('POINT(35.348182882430876 128.23626691412835)', 4326), 1),
    (@day_id73_3, ST_GeomFromText('POINT(35.41335398634935 128.88959720276688)', 4326), 2);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id73_4, '활동 1', '설명 1', 'http://example.com/image73_4_1.jpg'),
    (@day_id73_4, '활동 2', '설명 2', 'http://example.com/image73_4_2.jpg'),
    (@day_id73_4, '활동 3', '설명 3', 'http://example.com/image73_4_3.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id73_4, ST_GeomFromText('POINT(35.24582709740791 128.11084350812092)', 4326), 1),
    (@day_id73_4, ST_GeomFromText('POINT(35.57251123338838 128.69096833470047)', 4326), 2),
    (@day_id73_4, ST_GeomFromText('POINT(35.77121921937242 128.5134351732776)', 4326), 3);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id73_5, '활동 1', '설명 1', 'http://example.com/image73_5_1.jpg'),
    (@day_id73_5, '활동 2', '설명 2', 'http://example.com/image73_5_2.jpg'),
    (@day_id73_5, '활동 3', '설명 3', 'http://example.com/image73_5_3.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id73_5, ST_GeomFromText('POINT(35.866355814064455 128.87824278004663)', 4326), 1),
    (@day_id73_5, ST_GeomFromText('POINT(35.24041108518242 128.94135835292866)', 4326), 2);


-- 게시물 74
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    74, 'ISFP', '여행 제목 74', '2024-12-01', '2024-12-10', 4,
    'KR', 'ASIA', '지역 74', 548611, 76901, 366894,
    48, 29, 'MALE', 'SMOKER', '2024-11-01', 'RECRUITING'
);

SET @post_id74 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id74); SET @day_id74_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id74); SET @day_id74_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id74); SET @day_id74_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id74); SET @day_id74_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id74); SET @day_id74_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id74_1, '활동 1', '설명 1', 'http://example.com/image74_1_1.jpg'),
    (@day_id74_1, '활동 2', '설명 2', 'http://example.com/image74_1_2.jpg'),
    (@day_id74_1, '활동 3', '설명 3', 'http://example.com/image74_1_3.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id74_1, ST_GeomFromText('POINT(35.96981789331837 128.71711804881377)', 4326), 1),
    (@day_id74_1, ST_GeomFromText('POINT(35.792833119654475 128.52895935384)', 4326), 2);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id74_2, '활동 1', '설명 1', 'http://example.com/image74_2_1.jpg'),
    (@day_id74_2, '활동 2', '설명 2', 'http://example.com/image74_2_2.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id74_2, ST_GeomFromText('POINT(35.94118785189664 128.51821755170383)', 4326), 1),
    (@day_id74_2, ST_GeomFromText('POINT(35.77931269416991 128.7141002663713)', 4326), 2);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id74_3, '활동 1', '설명 1', 'http://example.com/image74_3_1.jpg'),
    (@day_id74_3, '활동 2', '설명 2', 'http://example.com/image74_3_2.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id74_3, ST_GeomFromText('POINT(35.283137957641166 128.70736533422445)', 4326), 1);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id74_4, '활동 1', '설명 1', 'http://example.com/image74_4_1.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id74_4, ST_GeomFromText('POINT(35.71615987013631 128.72165191711272)', 4326), 1),
    (@day_id74_4, ST_GeomFromText('POINT(35.63937661944305 128.5342866571559)', 4326), 2);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id74_5, '활동 1', '설명 1', 'http://example.com/image74_5_1.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id74_5, ST_GeomFromText('POINT(35.475334457764916 128.39435744828862)', 4326), 1),
    (@day_id74_5, ST_GeomFromText('POINT(35.74695306712042 128.37475435607774)', 4326), 2),
    (@day_id74_5, ST_GeomFromText('POINT(35.86758148003068 128.49056174238274)', 4326), 3);


-- 게시물 75
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    75, 'INTP', '여행 제목 75', '2024-12-01', '2024-12-10', 10,
    'KR', 'ASIA', '지역 75', 126766, 66397, 203405,
    57, 29, 'FEMALE', 'NON_SMOKER', '2024-11-01', 'RECRUITING'
);

SET @post_id75 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id75); SET @day_id75_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id75); SET @day_id75_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id75); SET @day_id75_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id75); SET @day_id75_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id75); SET @day_id75_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id75_1, '활동 1', '설명 1', 'http://example.com/image75_1_1.jpg'),
    (@day_id75_1, '활동 2', '설명 2', 'http://example.com/image75_1_2.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id75_1, ST_GeomFromText('POINT(35.3279273656557 128.4441594347915)', 4326), 1),
    (@day_id75_1, ST_GeomFromText('POINT(35.84356976049223 128.28611161744013)', 4326), 2);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id75_2, '활동 1', '설명 1', 'http://example.com/image75_2_1.jpg'),
    (@day_id75_2, '활동 2', '설명 2', 'http://example.com/image75_2_2.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id75_2, ST_GeomFromText('POINT(35.069077842408596 128.549275005867)', 4326), 1),
    (@day_id75_2, ST_GeomFromText('POINT(35.28823568958332 128.95289420970124)', 4326), 2);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id75_3, '활동 1', '설명 1', 'http://example.com/image75_3_1.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id75_3, ST_GeomFromText('POINT(35.621954427388594 128.03295133672418)', 4326), 1);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id75_4, '활동 1', '설명 1', 'http://example.com/image75_4_1.jpg'),
    (@day_id75_4, '활동 2', '설명 2', 'http://example.com/image75_4_2.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id75_4, ST_GeomFromText('POINT(35.997133490139056 128.9285855052554)', 4326), 1),
    (@day_id75_4, ST_GeomFromText('POINT(35.26895123400111 128.85905706607184)', 4326), 2),
    (@day_id75_4, ST_GeomFromText('POINT(35.64810816370888 128.6933100363126)', 4326), 3);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id75_5, '활동 1', '설명 1', 'http://example.com/image75_5_1.jpg'),
    (@day_id75_5, '활동 2', '설명 2', 'http://example.com/image75_5_2.jpg'),
    (@day_id75_5, '활동 3', '설명 3', 'http://example.com/image75_5_3.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id75_5, ST_GeomFromText('POINT(35.678553745485104 128.370329214712)', 4326), 1);


-- 게시물 76
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    76, 'ESTP', '여행 제목 76', '2024-12-01', '2024-12-10', 4,
    'KR', 'ASIA', '지역 76', 461574, 216871, 255153,
    45, 22, 'UNRELATED', 'NON_SMOKER', '2024-11-01', 'RECRUITING'
);

SET @post_id76 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id76); SET @day_id76_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id76); SET @day_id76_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id76); SET @day_id76_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id76); SET @day_id76_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id76); SET @day_id76_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id76_1, '활동 1', '설명 1', 'http://example.com/image76_1_1.jpg'),
    (@day_id76_1, '활동 2', '설명 2', 'http://example.com/image76_1_2.jpg'),
    (@day_id76_1, '활동 3', '설명 3', 'http://example.com/image76_1_3.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id76_1, ST_GeomFromText('POINT(35.64391720909597 128.96586279545448)', 4326), 1),
    (@day_id76_1, ST_GeomFromText('POINT(35.308543616959774 128.02091640742233)', 4326), 2),
    (@day_id76_1, ST_GeomFromText('POINT(35.079175507151625 128.32108382192152)', 4326), 3);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id76_2, '활동 1', '설명 1', 'http://example.com/image76_2_1.jpg'),
    (@day_id76_2, '활동 2', '설명 2', 'http://example.com/image76_2_2.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id76_2, ST_GeomFromText('POINT(35.878194654432335 128.9424293050161)', 4326), 1),
    (@day_id76_2, ST_GeomFromText('POINT(35.61389554954816 128.4537080249386)', 4326), 2),
    (@day_id76_2, ST_GeomFromText('POINT(35.530187049511824 128.40822988928855)', 4326), 3);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id76_3, '활동 1', '설명 1', 'http://example.com/image76_3_1.jpg'),
    (@day_id76_3, '활동 2', '설명 2', 'http://example.com/image76_3_2.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id76_3, ST_GeomFromText('POINT(35.74076017582236 128.2400094797377)', 4326), 1),
    (@day_id76_3, ST_GeomFromText('POINT(35.99998255643916 128.32387376862036)', 4326), 2);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id76_4, '활동 1', '설명 1', 'http://example.com/image76_4_1.jpg'),
    (@day_id76_4, '활동 2', '설명 2', 'http://example.com/image76_4_2.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id76_4, ST_GeomFromText('POINT(35.88636521736961 128.7090675119384)', 4326), 1);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id76_5, '활동 1', '설명 1', 'http://example.com/image76_5_1.jpg'),
    (@day_id76_5, '활동 2', '설명 2', 'http://example.com/image76_5_2.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id76_5, ST_GeomFromText('POINT(35.869433293057966 128.25060936581602)', 4326), 1),
    (@day_id76_5, ST_GeomFromText('POINT(35.20945123449765 128.27522755133083)', 4326), 2);


-- 게시물 77
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    77, 'INTP', '여행 제목 77', '2024-12-01', '2024-12-10', 8,
    'KR', 'ASIA', '지역 77', 472177, 99924, 245623,
    50, 21, 'FEMALE', 'UNRELATED', '2024-11-01', 'RECRUITING'
);

SET @post_id77 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id77); SET @day_id77_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id77); SET @day_id77_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id77); SET @day_id77_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id77); SET @day_id77_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id77); SET @day_id77_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id77_1, '활동 1', '설명 1', 'http://example.com/image77_1_1.jpg'),
    (@day_id77_1, '활동 2', '설명 2', 'http://example.com/image77_1_2.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id77_1, ST_GeomFromText('POINT(35.43141161161241 128.1836263326392)', 4326), 1),
    (@day_id77_1, ST_GeomFromText('POINT(35.307404461489604 128.63249006577925)', 4326), 2),
    (@day_id77_1, ST_GeomFromText('POINT(35.237945556369475 128.50571326039062)', 4326), 3);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id77_2, '활동 1', '설명 1', 'http://example.com/image77_2_1.jpg'),
    (@day_id77_2, '활동 2', '설명 2', 'http://example.com/image77_2_2.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id77_2, ST_GeomFromText('POINT(35.68221738455105 128.34081471054816)', 4326), 1),
    (@day_id77_2, ST_GeomFromText('POINT(35.90672016989759 128.15521052950314)', 4326), 2);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id77_3, '활동 1', '설명 1', 'http://example.com/image77_3_1.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id77_3, ST_GeomFromText('POINT(35.121117713695604 128.17200540207565)', 4326), 1);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id77_4, '활동 1', '설명 1', 'http://example.com/image77_4_1.jpg'),
    (@day_id77_4, '활동 2', '설명 2', 'http://example.com/image77_4_2.jpg'),
    (@day_id77_4, '활동 3', '설명 3', 'http://example.com/image77_4_3.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id77_4, ST_GeomFromText('POINT(35.474983899720776 128.05538780574025)', 4326), 1),
    (@day_id77_4, ST_GeomFromText('POINT(35.605910317659976 128.26948133459246)', 4326), 2),
    (@day_id77_4, ST_GeomFromText('POINT(35.11471443403939 128.5934800898108)', 4326), 3);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id77_5, '활동 1', '설명 1', 'http://example.com/image77_5_1.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id77_5, ST_GeomFromText('POINT(35.41500174991078 128.8872220236808)', 4326), 1),
    (@day_id77_5, ST_GeomFromText('POINT(35.02474466074853 128.61478051947464)', 4326), 2),
    (@day_id77_5, ST_GeomFromText('POINT(35.24410549979413 128.99970276116508)', 4326), 3);


-- 게시물 78
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    78, 'ESFJ', '여행 제목 78', '2024-12-01', '2024-12-10', 7,
    'KR', 'ASIA', '지역 78', 147528, 60837, 296259,
    49, 23, 'FEMALE', 'UNRELATED', '2024-11-01', 'RECRUITING'
);

SET @post_id78 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id78); SET @day_id78_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id78); SET @day_id78_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id78); SET @day_id78_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id78); SET @day_id78_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id78); SET @day_id78_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id78_1, '활동 1', '설명 1', 'http://example.com/image78_1_1.jpg'),
    (@day_id78_1, '활동 2', '설명 2', 'http://example.com/image78_1_2.jpg'),
    (@day_id78_1, '활동 3', '설명 3', 'http://example.com/image78_1_3.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id78_1, ST_GeomFromText('POINT(35.7154319655594 128.8716004908002)', 4326), 1);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id78_2, '활동 1', '설명 1', 'http://example.com/image78_2_1.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id78_2, ST_GeomFromText('POINT(35.92462152479644 128.01701699198898)', 4326), 1),
    (@day_id78_2, ST_GeomFromText('POINT(35.809219056578314 128.05187744663664)', 4326), 2),
    (@day_id78_2, ST_GeomFromText('POINT(35.55745564511507 128.43332976922687)', 4326), 3);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id78_3, '활동 1', '설명 1', 'http://example.com/image78_3_1.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id78_3, ST_GeomFromText('POINT(35.640978909215015 128.01253768469599)', 4326), 1);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id78_4, '활동 1', '설명 1', 'http://example.com/image78_4_1.jpg'),
    (@day_id78_4, '활동 2', '설명 2', 'http://example.com/image78_4_2.jpg'),
    (@day_id78_4, '활동 3', '설명 3', 'http://example.com/image78_4_3.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id78_4, ST_GeomFromText('POINT(35.10096224395603 128.4249568262977)', 4326), 1);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id78_5, '활동 1', '설명 1', 'http://example.com/image78_5_1.jpg'),
    (@day_id78_5, '활동 2', '설명 2', 'http://example.com/image78_5_2.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id78_5, ST_GeomFromText('POINT(35.913722842303905 128.45216749904708)', 4326), 1),
    (@day_id78_5, ST_GeomFromText('POINT(35.736148580416966 128.60920334010052)', 4326), 2);


-- 게시물 79
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    79, 'ENTJ', '여행 제목 79', '2024-12-01', '2024-12-10', 4,
    'KR', 'ASIA', '지역 79', 186620, 219454, 266139,
    58, 27, 'UNRELATED', 'SMOKER', '2024-11-01', 'RECRUITING'
);

SET @post_id79 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id79); SET @day_id79_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id79); SET @day_id79_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id79); SET @day_id79_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id79); SET @day_id79_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id79); SET @day_id79_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id79_1, '활동 1', '설명 1', 'http://example.com/image79_1_1.jpg'),
    (@day_id79_1, '활동 2', '설명 2', 'http://example.com/image79_1_2.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id79_1, ST_GeomFromText('POINT(35.473761866347495 128.90987040965726)', 4326), 1),
    (@day_id79_1, ST_GeomFromText('POINT(35.00639670820907 128.02913243663335)', 4326), 2);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id79_2, '활동 1', '설명 1', 'http://example.com/image79_2_1.jpg'),
    (@day_id79_2, '활동 2', '설명 2', 'http://example.com/image79_2_2.jpg'),
    (@day_id79_2, '활동 3', '설명 3', 'http://example.com/image79_2_3.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id79_2, ST_GeomFromText('POINT(35.71187047962233 128.593030120591)', 4326), 1),
    (@day_id79_2, ST_GeomFromText('POINT(35.47936347411924 128.4109106368362)', 4326), 2),
    (@day_id79_2, ST_GeomFromText('POINT(35.57888807787627 128.70661402411227)', 4326), 3);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id79_3, '활동 1', '설명 1', 'http://example.com/image79_3_1.jpg'),
    (@day_id79_3, '활동 2', '설명 2', 'http://example.com/image79_3_2.jpg'),
    (@day_id79_3, '활동 3', '설명 3', 'http://example.com/image79_3_3.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id79_3, ST_GeomFromText('POINT(35.65627534772234 128.52537834165685)', 4326), 1),
    (@day_id79_3, ST_GeomFromText('POINT(35.74838027841478 128.9098499245846)', 4326), 2);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id79_4, '활동 1', '설명 1', 'http://example.com/image79_4_1.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id79_4, ST_GeomFromText('POINT(35.348929492450985 128.9174227943445)', 4326), 1),
    (@day_id79_4, ST_GeomFromText('POINT(35.94564050600202 128.78261049974296)', 4326), 2),
    (@day_id79_4, ST_GeomFromText('POINT(35.309155458633164 128.89677144059195)', 4326), 3);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id79_5, '활동 1', '설명 1', 'http://example.com/image79_5_1.jpg'),
    (@day_id79_5, '활동 2', '설명 2', 'http://example.com/image79_5_2.jpg'),
    (@day_id79_5, '활동 3', '설명 3', 'http://example.com/image79_5_3.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id79_5, ST_GeomFromText('POINT(35.77808708074984 128.98686043387997)', 4326), 1),
    (@day_id79_5, ST_GeomFromText('POINT(35.96461880476429 128.63107627721925)', 4326), 2),
    (@day_id79_5, ST_GeomFromText('POINT(35.64789905891375 128.45033721836413)', 4326), 3);


-- 게시물 80
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    80, 'ISFJ', '여행 제목 80', '2024-12-01', '2024-12-10', 3,
    'KR', 'ASIA', '지역 80', 518813, 239189, 287114,
    57, 25, 'UNRELATED', 'UNRELATED', '2024-11-01', 'RECRUITING'
);

SET @post_id80 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id80); SET @day_id80_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id80); SET @day_id80_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id80); SET @day_id80_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id80); SET @day_id80_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id80); SET @day_id80_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id80_1, '활동 1', '설명 1', 'http://example.com/image80_1_1.jpg'),
    (@day_id80_1, '활동 2', '설명 2', 'http://example.com/image80_1_2.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id80_1, ST_GeomFromText('POINT(35.84270778365865 128.65779978798565)', 4326), 1),
    (@day_id80_1, ST_GeomFromText('POINT(35.353521452791 128.458407042572)', 4326), 2);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id80_2, '활동 1', '설명 1', 'http://example.com/image80_2_1.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id80_2, ST_GeomFromText('POINT(35.23291004832879 128.03886596578798)', 4326), 1),
    (@day_id80_2, ST_GeomFromText('POINT(35.046515949819884 128.39765771804107)', 4326), 2),
    (@day_id80_2, ST_GeomFromText('POINT(35.223971109319926 128.03655574008738)', 4326), 3);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id80_3, '활동 1', '설명 1', 'http://example.com/image80_3_1.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id80_3, ST_GeomFromText('POINT(35.526790647214916 128.5297416165261)', 4326), 1),
    (@day_id80_3, ST_GeomFromText('POINT(35.80513387752439 128.8183907547561)', 4326), 2);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id80_4, '활동 1', '설명 1', 'http://example.com/image80_4_1.jpg'),
    (@day_id80_4, '활동 2', '설명 2', 'http://example.com/image80_4_2.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id80_4, ST_GeomFromText('POINT(35.17667633008157 128.8534983911789)', 4326), 1),
    (@day_id80_4, ST_GeomFromText('POINT(35.91968011859196 128.92709570276128)', 4326), 2);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id80_5, '활동 1', '설명 1', 'http://example.com/image80_5_1.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id80_5, ST_GeomFromText('POINT(35.01288573095124 128.83374398000277)', 4326), 1);


-- 게시물 81
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    81, 'INFP', '여행 제목 81', '2024-12-01', '2024-12-10', 2,
    'KR', 'ASIA', '지역 81', 161556, 175573, 290975,
    46, 21, 'UNRELATED', 'NON_SMOKER', '2024-11-01', 'RECRUITING'
);

SET @post_id81 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id81); SET @day_id81_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id81); SET @day_id81_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id81); SET @day_id81_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id81); SET @day_id81_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id81); SET @day_id81_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id81_1, '활동 1', '설명 1', 'http://example.com/image81_1_1.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id81_1, ST_GeomFromText('POINT(35.56962159583232 128.29776198726833)', 4326), 1),
    (@day_id81_1, ST_GeomFromText('POINT(35.58629452221129 128.41203924817566)', 4326), 2);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id81_2, '활동 1', '설명 1', 'http://example.com/image81_2_1.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id81_2, ST_GeomFromText('POINT(35.31936088688269 128.608916164114)', 4326), 1),
    (@day_id81_2, ST_GeomFromText('POINT(35.379096920295986 128.67231232147162)', 4326), 2);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id81_3, '활동 1', '설명 1', 'http://example.com/image81_3_1.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id81_3, ST_GeomFromText('POINT(35.160476298893485 128.85229975793212)', 4326), 1),
    (@day_id81_3, ST_GeomFromText('POINT(35.261747666848535 128.08148579598165)', 4326), 2),
    (@day_id81_3, ST_GeomFromText('POINT(35.68242570923564 128.83938877782734)', 4326), 3);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id81_4, '활동 1', '설명 1', 'http://example.com/image81_4_1.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id81_4, ST_GeomFromText('POINT(35.55588239789166 128.12332734334495)', 4326), 1),
    (@day_id81_4, ST_GeomFromText('POINT(35.018940647778486 128.037892899808)', 4326), 2),
    (@day_id81_4, ST_GeomFromText('POINT(35.13011330582345 128.99734038165676)', 4326), 3);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id81_5, '활동 1', '설명 1', 'http://example.com/image81_5_1.jpg'),
    (@day_id81_5, '활동 2', '설명 2', 'http://example.com/image81_5_2.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id81_5, ST_GeomFromText('POINT(35.3139658243919 128.27524809630194)', 4326), 1),
    (@day_id81_5, ST_GeomFromText('POINT(35.19214439953376 128.06600180648442)', 4326), 2),
    (@day_id81_5, ST_GeomFromText('POINT(35.86423259987443 128.8125745970467)', 4326), 3);


-- 게시물 82
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    82, 'INFJ', '여행 제목 82', '2024-12-01', '2024-12-10', 5,
    'KR', 'ASIA', '지역 82', 271862, 246488, 394214,
    37, 20, 'FEMALE', 'NON_SMOKER', '2024-11-01', 'RECRUITING'
);

SET @post_id82 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id82); SET @day_id82_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id82); SET @day_id82_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id82); SET @day_id82_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id82); SET @day_id82_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id82); SET @day_id82_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id82_1, '활동 1', '설명 1', 'http://example.com/image82_1_1.jpg'),
    (@day_id82_1, '활동 2', '설명 2', 'http://example.com/image82_1_2.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id82_1, ST_GeomFromText('POINT(35.36701542841671 128.71467089897425)', 4326), 1),
    (@day_id82_1, ST_GeomFromText('POINT(35.34124767830393 128.4917527202883)', 4326), 2);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id82_2, '활동 1', '설명 1', 'http://example.com/image82_2_1.jpg'),
    (@day_id82_2, '활동 2', '설명 2', 'http://example.com/image82_2_2.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id82_2, ST_GeomFromText('POINT(35.280724101536784 128.90238819645924)', 4326), 1);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id82_3, '활동 1', '설명 1', 'http://example.com/image82_3_1.jpg'),
    (@day_id82_3, '활동 2', '설명 2', 'http://example.com/image82_3_2.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id82_3, ST_GeomFromText('POINT(35.694536020589304 128.79067270646763)', 4326), 1),
    (@day_id82_3, ST_GeomFromText('POINT(35.728439245579196 128.45322199510173)', 4326), 2);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id82_4, '활동 1', '설명 1', 'http://example.com/image82_4_1.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id82_4, ST_GeomFromText('POINT(35.478703690455454 128.00248029972488)', 4326), 1);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id82_5, '활동 1', '설명 1', 'http://example.com/image82_5_1.jpg'),
    (@day_id82_5, '활동 2', '설명 2', 'http://example.com/image82_5_2.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id82_5, ST_GeomFromText('POINT(35.536263829740655 128.01172553613176)', 4326), 1),
    (@day_id82_5, ST_GeomFromText('POINT(35.835266492809474 128.21732754280507)', 4326), 2),
    (@day_id82_5, ST_GeomFromText('POINT(35.66634459431008 128.75575186644667)', 4326), 3);


-- 게시물 83
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    83, 'ISFP', '여행 제목 83', '2024-12-01', '2024-12-10', 2,
    'KR', 'ASIA', '지역 83', 262708, 71114, 223369,
    54, 28, 'MALE', 'NON_SMOKER', '2024-11-01', 'RECRUITING'
);

SET @post_id83 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id83); SET @day_id83_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id83); SET @day_id83_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id83); SET @day_id83_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id83); SET @day_id83_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id83); SET @day_id83_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id83_1, '활동 1', '설명 1', 'http://example.com/image83_1_1.jpg'),
    (@day_id83_1, '활동 2', '설명 2', 'http://example.com/image83_1_2.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id83_1, ST_GeomFromText('POINT(35.27876698173556 128.64370202390836)', 4326), 1),
    (@day_id83_1, ST_GeomFromText('POINT(35.877584782814985 128.24550955477602)', 4326), 2),
    (@day_id83_1, ST_GeomFromText('POINT(35.092526619163024 128.14702547990083)', 4326), 3);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id83_2, '활동 1', '설명 1', 'http://example.com/image83_2_1.jpg'),
    (@day_id83_2, '활동 2', '설명 2', 'http://example.com/image83_2_2.jpg'),
    (@day_id83_2, '활동 3', '설명 3', 'http://example.com/image83_2_3.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id83_2, ST_GeomFromText('POINT(35.220075685401575 128.7821247379816)', 4326), 1),
    (@day_id83_2, ST_GeomFromText('POINT(35.39528357578549 128.03816753248523)', 4326), 2),
    (@day_id83_2, ST_GeomFromText('POINT(35.08191531205974 128.61935890529114)', 4326), 3);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id83_3, '활동 1', '설명 1', 'http://example.com/image83_3_1.jpg'),
    (@day_id83_3, '활동 2', '설명 2', 'http://example.com/image83_3_2.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id83_3, ST_GeomFromText('POINT(35.8996152746659 128.10978219475064)', 4326), 1),
    (@day_id83_3, ST_GeomFromText('POINT(35.993209048534325 128.9572896945691)', 4326), 2),
    (@day_id83_3, ST_GeomFromText('POINT(35.56721845128081 128.31699601445843)', 4326), 3);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id83_4, '활동 1', '설명 1', 'http://example.com/image83_4_1.jpg'),
    (@day_id83_4, '활동 2', '설명 2', 'http://example.com/image83_4_2.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id83_4, ST_GeomFromText('POINT(35.81855179347474 128.63201111809204)', 4326), 1);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id83_5, '활동 1', '설명 1', 'http://example.com/image83_5_1.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id83_5, ST_GeomFromText('POINT(35.8043423847486 128.61087339004402)', 4326), 1),
    (@day_id83_5, ST_GeomFromText('POINT(35.86601025823346 128.5763551882414)', 4326), 2),
    (@day_id83_5, ST_GeomFromText('POINT(35.158765693260754 128.94876728937749)', 4326), 3);


-- 게시물 84
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    84, 'ENFJ', '여행 제목 84', '2024-12-01', '2024-12-10', 7,
    'KR', 'ASIA', '지역 84', 236282, 141060, 380945,
    42, 21, 'MALE', 'SMOKER', '2024-11-01', 'RECRUITING'
);

SET @post_id84 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id84); SET @day_id84_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id84); SET @day_id84_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id84); SET @day_id84_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id84); SET @day_id84_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id84); SET @day_id84_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id84_1, '활동 1', '설명 1', 'http://example.com/image84_1_1.jpg'),
    (@day_id84_1, '활동 2', '설명 2', 'http://example.com/image84_1_2.jpg'),
    (@day_id84_1, '활동 3', '설명 3', 'http://example.com/image84_1_3.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id84_1, ST_GeomFromText('POINT(35.75344591307646 128.46576972604367)', 4326), 1),
    (@day_id84_1, ST_GeomFromText('POINT(35.70175742548673 128.16037697335597)', 4326), 2);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id84_2, '활동 1', '설명 1', 'http://example.com/image84_2_1.jpg'),
    (@day_id84_2, '활동 2', '설명 2', 'http://example.com/image84_2_2.jpg'),
    (@day_id84_2, '활동 3', '설명 3', 'http://example.com/image84_2_3.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id84_2, ST_GeomFromText('POINT(35.5591163301755 128.5842676151353)', 4326), 1);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id84_3, '활동 1', '설명 1', 'http://example.com/image84_3_1.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id84_3, ST_GeomFromText('POINT(35.0642689466013 128.8618381405769)', 4326), 1);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id84_4, '활동 1', '설명 1', 'http://example.com/image84_4_1.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id84_4, ST_GeomFromText('POINT(35.05997642125731 128.53341335536817)', 4326), 1),
    (@day_id84_4, ST_GeomFromText('POINT(35.638250357107154 128.14884497647887)', 4326), 2);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id84_5, '활동 1', '설명 1', 'http://example.com/image84_5_1.jpg'),
    (@day_id84_5, '활동 2', '설명 2', 'http://example.com/image84_5_2.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id84_5, ST_GeomFromText('POINT(35.88808308402269 128.2094270367548)', 4326), 1);


-- 게시물 85
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    85, 'ISTJ', '여행 제목 85', '2024-12-01', '2024-12-10', 7,
    'KR', 'ASIA', '지역 85', 381410, 111707, 320552,
    52, 23, 'UNRELATED', 'SMOKER', '2024-11-01', 'RECRUITING'
);

SET @post_id85 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id85); SET @day_id85_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id85); SET @day_id85_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id85); SET @day_id85_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id85); SET @day_id85_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id85); SET @day_id85_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id85_1, '활동 1', '설명 1', 'http://example.com/image85_1_1.jpg'),
    (@day_id85_1, '활동 2', '설명 2', 'http://example.com/image85_1_2.jpg'),
    (@day_id85_1, '활동 3', '설명 3', 'http://example.com/image85_1_3.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id85_1, ST_GeomFromText('POINT(35.774831334317 128.22995341779605)', 4326), 1);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id85_2, '활동 1', '설명 1', 'http://example.com/image85_2_1.jpg'),
    (@day_id85_2, '활동 2', '설명 2', 'http://example.com/image85_2_2.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id85_2, ST_GeomFromText('POINT(35.57569973444691 128.1957238832185)', 4326), 1),
    (@day_id85_2, ST_GeomFromText('POINT(35.55148123650336 128.83695864505938)', 4326), 2),
    (@day_id85_2, ST_GeomFromText('POINT(35.91306195717274 128.3364356801337)', 4326), 3);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id85_3, '활동 1', '설명 1', 'http://example.com/image85_3_1.jpg'),
    (@day_id85_3, '활동 2', '설명 2', 'http://example.com/image85_3_2.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id85_3, ST_GeomFromText('POINT(35.00704696792958 128.4606979273358)', 4326), 1);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id85_4, '활동 1', '설명 1', 'http://example.com/image85_4_1.jpg'),
    (@day_id85_4, '활동 2', '설명 2', 'http://example.com/image85_4_2.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id85_4, ST_GeomFromText('POINT(35.89906650007098 128.22575824222062)', 4326), 1),
    (@day_id85_4, ST_GeomFromText('POINT(35.801691172697545 128.66321430619885)', 4326), 2);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id85_5, '활동 1', '설명 1', 'http://example.com/image85_5_1.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id85_5, ST_GeomFromText('POINT(35.95909303435962 128.1121797867294)', 4326), 1),
    (@day_id85_5, ST_GeomFromText('POINT(35.52242819221623 128.3649840303709)', 4326), 2),
    (@day_id85_5, ST_GeomFromText('POINT(35.74786778519767 128.70061327170941)', 4326), 3);


-- 게시물 86
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    86, 'ISFP', '여행 제목 86', '2024-12-01', '2024-12-10', 8,
    'KR', 'ASIA', '지역 86', 576824, 224339, 263656,
    57, 29, 'MALE', 'SMOKER', '2024-11-01', 'RECRUITING'
);

SET @post_id86 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id86); SET @day_id86_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id86); SET @day_id86_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id86); SET @day_id86_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id86); SET @day_id86_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id86); SET @day_id86_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id86_1, '활동 1', '설명 1', 'http://example.com/image86_1_1.jpg'),
    (@day_id86_1, '활동 2', '설명 2', 'http://example.com/image86_1_2.jpg'),
    (@day_id86_1, '활동 3', '설명 3', 'http://example.com/image86_1_3.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id86_1, ST_GeomFromText('POINT(35.14103187607398 128.01045017013502)', 4326), 1),
    (@day_id86_1, ST_GeomFromText('POINT(35.508850578225314 128.48978062758223)', 4326), 2);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id86_2, '활동 1', '설명 1', 'http://example.com/image86_2_1.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id86_2, ST_GeomFromText('POINT(35.70211514378964 128.4913227706578)', 4326), 1),
    (@day_id86_2, ST_GeomFromText('POINT(35.01692545139796 128.44944432287903)', 4326), 2),
    (@day_id86_2, ST_GeomFromText('POINT(35.74703163632467 128.16101207502442)', 4326), 3);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id86_3, '활동 1', '설명 1', 'http://example.com/image86_3_1.jpg'),
    (@day_id86_3, '활동 2', '설명 2', 'http://example.com/image86_3_2.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id86_3, ST_GeomFromText('POINT(35.66311206896983 128.681282962326)', 4326), 1);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id86_4, '활동 1', '설명 1', 'http://example.com/image86_4_1.jpg'),
    (@day_id86_4, '활동 2', '설명 2', 'http://example.com/image86_4_2.jpg'),
    (@day_id86_4, '활동 3', '설명 3', 'http://example.com/image86_4_3.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id86_4, ST_GeomFromText('POINT(35.24787665581765 128.25099235428542)', 4326), 1),
    (@day_id86_4, ST_GeomFromText('POINT(35.74007449937817 128.8865650921793)', 4326), 2);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id86_5, '활동 1', '설명 1', 'http://example.com/image86_5_1.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id86_5, ST_GeomFromText('POINT(35.75359901004289 128.1381523338785)', 4326), 1),
    (@day_id86_5, ST_GeomFromText('POINT(35.164109192133125 128.1409410112476)', 4326), 2),
    (@day_id86_5, ST_GeomFromText('POINT(35.388178492711084 128.13531820631945)', 4326), 3);


-- 게시물 87
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    87, 'ISFP', '여행 제목 87', '2024-12-01', '2024-12-10', 8,
    'KR', 'ASIA', '지역 87', 390695, 136166, 285177,
    42, 29, 'FEMALE', 'UNRELATED', '2024-11-01', 'RECRUITING'
);

SET @post_id87 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id87); SET @day_id87_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id87); SET @day_id87_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id87); SET @day_id87_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id87); SET @day_id87_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id87); SET @day_id87_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id87_1, '활동 1', '설명 1', 'http://example.com/image87_1_1.jpg'),
    (@day_id87_1, '활동 2', '설명 2', 'http://example.com/image87_1_2.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id87_1, ST_GeomFromText('POINT(35.21231183357917 128.16794479616996)', 4326), 1);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id87_2, '활동 1', '설명 1', 'http://example.com/image87_2_1.jpg'),
    (@day_id87_2, '활동 2', '설명 2', 'http://example.com/image87_2_2.jpg'),
    (@day_id87_2, '활동 3', '설명 3', 'http://example.com/image87_2_3.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id87_2, ST_GeomFromText('POINT(35.70452736015819 128.8213977884951)', 4326), 1),
    (@day_id87_2, ST_GeomFromText('POINT(35.179674366770094 128.9649610904063)', 4326), 2);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id87_3, '활동 1', '설명 1', 'http://example.com/image87_3_1.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id87_3, ST_GeomFromText('POINT(35.258214187036245 128.49809831322077)', 4326), 1),
    (@day_id87_3, ST_GeomFromText('POINT(35.457926014978234 128.80622372397633)', 4326), 2);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id87_4, '활동 1', '설명 1', 'http://example.com/image87_4_1.jpg'),
    (@day_id87_4, '활동 2', '설명 2', 'http://example.com/image87_4_2.jpg'),
    (@day_id87_4, '활동 3', '설명 3', 'http://example.com/image87_4_3.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id87_4, ST_GeomFromText('POINT(35.02683220413459 128.51665463485702)', 4326), 1),
    (@day_id87_4, ST_GeomFromText('POINT(35.35393437494601 128.6992411683098)', 4326), 2);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id87_5, '활동 1', '설명 1', 'http://example.com/image87_5_1.jpg'),
    (@day_id87_5, '활동 2', '설명 2', 'http://example.com/image87_5_2.jpg'),
    (@day_id87_5, '활동 3', '설명 3', 'http://example.com/image87_5_3.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id87_5, ST_GeomFromText('POINT(35.166571503752216 128.6916501326025)', 4326), 1),
    (@day_id87_5, ST_GeomFromText('POINT(35.512915560123695 128.3641182107999)', 4326), 2);


-- 게시물 88
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    88, 'ESFJ', '여행 제목 88', '2024-12-01', '2024-12-10', 1,
    'KR', 'ASIA', '지역 88', 409565, 154904, 119733,
    43, 27, 'UNRELATED', 'NON_SMOKER', '2024-11-01', 'RECRUITING'
);

SET @post_id88 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id88); SET @day_id88_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id88); SET @day_id88_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id88); SET @day_id88_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id88); SET @day_id88_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id88); SET @day_id88_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id88_1, '활동 1', '설명 1', 'http://example.com/image88_1_1.jpg'),
    (@day_id88_1, '활동 2', '설명 2', 'http://example.com/image88_1_2.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id88_1, ST_GeomFromText('POINT(35.2807473841588 128.7487590156124)', 4326), 1),
    (@day_id88_1, ST_GeomFromText('POINT(35.877045066339434 128.4719594879524)', 4326), 2);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id88_2, '활동 1', '설명 1', 'http://example.com/image88_2_1.jpg'),
    (@day_id88_2, '활동 2', '설명 2', 'http://example.com/image88_2_2.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id88_2, ST_GeomFromText('POINT(35.473380439757136 128.80696650886404)', 4326), 1),
    (@day_id88_2, ST_GeomFromText('POINT(35.60706624501918 128.7498895676075)', 4326), 2),
    (@day_id88_2, ST_GeomFromText('POINT(35.78668269372838 128.84660855695145)', 4326), 3);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id88_3, '활동 1', '설명 1', 'http://example.com/image88_3_1.jpg'),
    (@day_id88_3, '활동 2', '설명 2', 'http://example.com/image88_3_2.jpg'),
    (@day_id88_3, '활동 3', '설명 3', 'http://example.com/image88_3_3.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id88_3, ST_GeomFromText('POINT(35.622252919946696 128.3076808005639)', 4326), 1),
    (@day_id88_3, ST_GeomFromText('POINT(35.22692084405994 128.33483590671602)', 4326), 2),
    (@day_id88_3, ST_GeomFromText('POINT(35.8240626868448 128.5123910778466)', 4326), 3);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id88_4, '활동 1', '설명 1', 'http://example.com/image88_4_1.jpg'),
    (@day_id88_4, '활동 2', '설명 2', 'http://example.com/image88_4_2.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id88_4, ST_GeomFromText('POINT(35.230510747253135 128.1071036249181)', 4326), 1);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id88_5, '활동 1', '설명 1', 'http://example.com/image88_5_1.jpg'),
    (@day_id88_5, '활동 2', '설명 2', 'http://example.com/image88_5_2.jpg'),
    (@day_id88_5, '활동 3', '설명 3', 'http://example.com/image88_5_3.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id88_5, ST_GeomFromText('POINT(35.17647910379389 128.91631729334665)', 4326), 1);


-- 게시물 89
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    89, 'ESFP', '여행 제목 89', '2024-12-01', '2024-12-10', 1,
    'KR', 'ASIA', '지역 89', 423507, 91331, 210013,
    35, 21, 'MALE', 'NON_SMOKER', '2024-11-01', 'RECRUITING'
);

SET @post_id89 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id89); SET @day_id89_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id89); SET @day_id89_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id89); SET @day_id89_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id89); SET @day_id89_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id89); SET @day_id89_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id89_1, '활동 1', '설명 1', 'http://example.com/image89_1_1.jpg'),
    (@day_id89_1, '활동 2', '설명 2', 'http://example.com/image89_1_2.jpg'),
    (@day_id89_1, '활동 3', '설명 3', 'http://example.com/image89_1_3.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id89_1, ST_GeomFromText('POINT(35.759203355659494 128.76828715922176)', 4326), 1),
    (@day_id89_1, ST_GeomFromText('POINT(35.796036495272915 128.1873481546323)', 4326), 2);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id89_2, '활동 1', '설명 1', 'http://example.com/image89_2_1.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id89_2, ST_GeomFromText('POINT(35.22097650213758 128.42539565397672)', 4326), 1),
    (@day_id89_2, ST_GeomFromText('POINT(35.669499704412935 128.94977176743535)', 4326), 2),
    (@day_id89_2, ST_GeomFromText('POINT(35.28303502059628 128.86647613909082)', 4326), 3);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id89_3, '활동 1', '설명 1', 'http://example.com/image89_3_1.jpg'),
    (@day_id89_3, '활동 2', '설명 2', 'http://example.com/image89_3_2.jpg'),
    (@day_id89_3, '활동 3', '설명 3', 'http://example.com/image89_3_3.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id89_3, ST_GeomFromText('POINT(35.93273166044042 128.09194456652094)', 4326), 1),
    (@day_id89_3, ST_GeomFromText('POINT(35.61874030020608 128.39339311930536)', 4326), 2),
    (@day_id89_3, ST_GeomFromText('POINT(35.52593772222518 128.60869513344912)', 4326), 3);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id89_4, '활동 1', '설명 1', 'http://example.com/image89_4_1.jpg'),
    (@day_id89_4, '활동 2', '설명 2', 'http://example.com/image89_4_2.jpg'),
    (@day_id89_4, '활동 3', '설명 3', 'http://example.com/image89_4_3.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id89_4, ST_GeomFromText('POINT(35.655929638090505 128.81029505071427)', 4326), 1);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id89_5, '활동 1', '설명 1', 'http://example.com/image89_5_1.jpg'),
    (@day_id89_5, '활동 2', '설명 2', 'http://example.com/image89_5_2.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id89_5, ST_GeomFromText('POINT(35.51407635630778 128.4984643533347)', 4326), 1),
    (@day_id89_5, ST_GeomFromText('POINT(35.562999095482766 128.85503623625553)', 4326), 2),
    (@day_id89_5, ST_GeomFromText('POINT(35.654023601377396 128.55636067469226)', 4326), 3);


-- 게시물 90
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    90, 'INTJ', '여행 제목 90', '2024-12-01', '2024-12-10', 1,
    'KR', 'ASIA', '지역 90', 478171, 222531, 173381,
    59, 21, 'UNRELATED', 'NON_SMOKER', '2024-11-01', 'RECRUITING'
);

SET @post_id90 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id90); SET @day_id90_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id90); SET @day_id90_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id90); SET @day_id90_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id90); SET @day_id90_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id90); SET @day_id90_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id90_1, '활동 1', '설명 1', 'http://example.com/image90_1_1.jpg'),
    (@day_id90_1, '활동 2', '설명 2', 'http://example.com/image90_1_2.jpg'),
    (@day_id90_1, '활동 3', '설명 3', 'http://example.com/image90_1_3.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id90_1, ST_GeomFromText('POINT(35.10443751074184 128.87818084580744)', 4326), 1);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id90_2, '활동 1', '설명 1', 'http://example.com/image90_2_1.jpg'),
    (@day_id90_2, '활동 2', '설명 2', 'http://example.com/image90_2_2.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id90_2, ST_GeomFromText('POINT(35.81002008136733 128.3769710557979)', 4326), 1),
    (@day_id90_2, ST_GeomFromText('POINT(35.095846830500804 128.46009134174116)', 4326), 2);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id90_3, '활동 1', '설명 1', 'http://example.com/image90_3_1.jpg'),
    (@day_id90_3, '활동 2', '설명 2', 'http://example.com/image90_3_2.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id90_3, ST_GeomFromText('POINT(35.277855734760024 128.74491338934374)', 4326), 1),
    (@day_id90_3, ST_GeomFromText('POINT(35.388214959274755 128.99260529990735)', 4326), 2);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id90_4, '활동 1', '설명 1', 'http://example.com/image90_4_1.jpg'),
    (@day_id90_4, '활동 2', '설명 2', 'http://example.com/image90_4_2.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id90_4, ST_GeomFromText('POINT(35.82801150589092 128.68348030640706)', 4326), 1);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id90_5, '활동 1', '설명 1', 'http://example.com/image90_5_1.jpg'),
    (@day_id90_5, '활동 2', '설명 2', 'http://example.com/image90_5_2.jpg'),
    (@day_id90_5, '활동 3', '설명 3', 'http://example.com/image90_5_3.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id90_5, ST_GeomFromText('POINT(35.214137389185005 128.6907391732954)', 4326), 1);


-- 게시물 91
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    91, 'ESFP', '여행 제목 91', '2024-12-01', '2024-12-10', 1,
    'KR', 'ASIA', '지역 91', 309014, 192453, 347327,
    34, 26, 'UNRELATED', 'UNRELATED', '2024-11-01', 'RECRUITING'
);

SET @post_id91 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id91); SET @day_id91_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id91); SET @day_id91_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id91); SET @day_id91_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id91); SET @day_id91_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id91); SET @day_id91_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id91_1, '활동 1', '설명 1', 'http://example.com/image91_1_1.jpg'),
    (@day_id91_1, '활동 2', '설명 2', 'http://example.com/image91_1_2.jpg'),
    (@day_id91_1, '활동 3', '설명 3', 'http://example.com/image91_1_3.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id91_1, ST_GeomFromText('POINT(35.0383242612755 128.85850134993896)', 4326), 1),
    (@day_id91_1, ST_GeomFromText('POINT(35.62503059441091 128.01610182682617)', 4326), 2);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id91_2, '활동 1', '설명 1', 'http://example.com/image91_2_1.jpg'),
    (@day_id91_2, '활동 2', '설명 2', 'http://example.com/image91_2_2.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id91_2, ST_GeomFromText('POINT(35.7888594301621 128.15761603084061)', 4326), 1),
    (@day_id91_2, ST_GeomFromText('POINT(35.852821350762476 128.3807609742076)', 4326), 2),
    (@day_id91_2, ST_GeomFromText('POINT(35.10414193511297 128.53391057689302)', 4326), 3);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id91_3, '활동 1', '설명 1', 'http://example.com/image91_3_1.jpg'),
    (@day_id91_3, '활동 2', '설명 2', 'http://example.com/image91_3_2.jpg'),
    (@day_id91_3, '활동 3', '설명 3', 'http://example.com/image91_3_3.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id91_3, ST_GeomFromText('POINT(35.57457632039078 128.961926015381)', 4326), 1),
    (@day_id91_3, ST_GeomFromText('POINT(35.96913652588785 128.6973837458783)', 4326), 2);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id91_4, '활동 1', '설명 1', 'http://example.com/image91_4_1.jpg'),
    (@day_id91_4, '활동 2', '설명 2', 'http://example.com/image91_4_2.jpg'),
    (@day_id91_4, '활동 3', '설명 3', 'http://example.com/image91_4_3.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id91_4, ST_GeomFromText('POINT(35.22622298298692 128.91696589755028)', 4326), 1);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id91_5, '활동 1', '설명 1', 'http://example.com/image91_5_1.jpg'),
    (@day_id91_5, '활동 2', '설명 2', 'http://example.com/image91_5_2.jpg'),
    (@day_id91_5, '활동 3', '설명 3', 'http://example.com/image91_5_3.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id91_5, ST_GeomFromText('POINT(35.65901470441104 128.99360008090142)', 4326), 1);


-- 게시물 92
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    92, 'ISFJ', '여행 제목 92', '2024-12-01', '2024-12-10', 5,
    'KR', 'ASIA', '지역 92', 561281, 224522, 121553,
    53, 23, 'FEMALE', 'SMOKER', '2024-11-01', 'RECRUITING'
);

SET @post_id92 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id92); SET @day_id92_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id92); SET @day_id92_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id92); SET @day_id92_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id92); SET @day_id92_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id92); SET @day_id92_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id92_1, '활동 1', '설명 1', 'http://example.com/image92_1_1.jpg'),
    (@day_id92_1, '활동 2', '설명 2', 'http://example.com/image92_1_2.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id92_1, ST_GeomFromText('POINT(35.348447883995696 128.2708800322296)', 4326), 1),
    (@day_id92_1, ST_GeomFromText('POINT(35.739042589296375 128.45008293071962)', 4326), 2),
    (@day_id92_1, ST_GeomFromText('POINT(35.982441103948354 128.56129557107255)', 4326), 3);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id92_2, '활동 1', '설명 1', 'http://example.com/image92_2_1.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id92_2, ST_GeomFromText('POINT(35.23437637673295 128.2263015840848)', 4326), 1);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id92_3, '활동 1', '설명 1', 'http://example.com/image92_3_1.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id92_3, ST_GeomFromText('POINT(35.14129160895907 128.27433019212248)', 4326), 1),
    (@day_id92_3, ST_GeomFromText('POINT(35.360089728928294 128.22044486394114)', 4326), 2);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id92_4, '활동 1', '설명 1', 'http://example.com/image92_4_1.jpg'),
    (@day_id92_4, '활동 2', '설명 2', 'http://example.com/image92_4_2.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id92_4, ST_GeomFromText('POINT(35.25750581049008 128.132758742059)', 4326), 1),
    (@day_id92_4, ST_GeomFromText('POINT(35.12860391654449 128.67338056297083)', 4326), 2),
    (@day_id92_4, ST_GeomFromText('POINT(35.007620691659966 128.5765803710978)', 4326), 3);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id92_5, '활동 1', '설명 1', 'http://example.com/image92_5_1.jpg'),
    (@day_id92_5, '활동 2', '설명 2', 'http://example.com/image92_5_2.jpg'),
    (@day_id92_5, '활동 3', '설명 3', 'http://example.com/image92_5_3.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id92_5, ST_GeomFromText('POINT(35.064473745245564 128.07635361918548)', 4326), 1),
    (@day_id92_5, ST_GeomFromText('POINT(35.356270064906006 128.3965764541893)', 4326), 2);


-- 게시물 93
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    93, 'ISTJ', '여행 제목 93', '2024-12-01', '2024-12-10', 10,
    'KR', 'ASIA', '지역 93', 101337, 106588, 141901,
    48, 21, 'UNRELATED', 'NON_SMOKER', '2024-11-01', 'RECRUITING'
);

SET @post_id93 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id93); SET @day_id93_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id93); SET @day_id93_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id93); SET @day_id93_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id93); SET @day_id93_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id93); SET @day_id93_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id93_1, '활동 1', '설명 1', 'http://example.com/image93_1_1.jpg'),
    (@day_id93_1, '활동 2', '설명 2', 'http://example.com/image93_1_2.jpg'),
    (@day_id93_1, '활동 3', '설명 3', 'http://example.com/image93_1_3.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id93_1, ST_GeomFromText('POINT(35.93413623857776 128.61405600945594)', 4326), 1);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id93_2, '활동 1', '설명 1', 'http://example.com/image93_2_1.jpg'),
    (@day_id93_2, '활동 2', '설명 2', 'http://example.com/image93_2_2.jpg'),
    (@day_id93_2, '활동 3', '설명 3', 'http://example.com/image93_2_3.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id93_2, ST_GeomFromText('POINT(35.45696353814449 128.06840432406517)', 4326), 1),
    (@day_id93_2, ST_GeomFromText('POINT(35.81374468873839 128.96137220811562)', 4326), 2);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id93_3, '활동 1', '설명 1', 'http://example.com/image93_3_1.jpg'),
    (@day_id93_3, '활동 2', '설명 2', 'http://example.com/image93_3_2.jpg'),
    (@day_id93_3, '활동 3', '설명 3', 'http://example.com/image93_3_3.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id93_3, ST_GeomFromText('POINT(35.79295687478683 128.690181861743)', 4326), 1),
    (@day_id93_3, ST_GeomFromText('POINT(35.712626085456016 128.42202519651627)', 4326), 2);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id93_4, '활동 1', '설명 1', 'http://example.com/image93_4_1.jpg'),
    (@day_id93_4, '활동 2', '설명 2', 'http://example.com/image93_4_2.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id93_4, ST_GeomFromText('POINT(35.86440777926721 128.26355853798518)', 4326), 1);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id93_5, '활동 1', '설명 1', 'http://example.com/image93_5_1.jpg'),
    (@day_id93_5, '활동 2', '설명 2', 'http://example.com/image93_5_2.jpg'),
    (@day_id93_5, '활동 3', '설명 3', 'http://example.com/image93_5_3.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id93_5, ST_GeomFromText('POINT(35.89505164354769 128.7450564671334)', 4326), 1);


-- 게시물 94
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    94, 'ENFJ', '여행 제목 94', '2024-12-01', '2024-12-10', 1,
    'KR', 'ASIA', '지역 94', 213900, 150726, 359665,
    53, 29, 'MALE', 'UNRELATED', '2024-11-01', 'RECRUITING'
);

SET @post_id94 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id94); SET @day_id94_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id94); SET @day_id94_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id94); SET @day_id94_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id94); SET @day_id94_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id94); SET @day_id94_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id94_1, '활동 1', '설명 1', 'http://example.com/image94_1_1.jpg'),
    (@day_id94_1, '활동 2', '설명 2', 'http://example.com/image94_1_2.jpg'),
    (@day_id94_1, '활동 3', '설명 3', 'http://example.com/image94_1_3.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id94_1, ST_GeomFromText('POINT(35.174130163107336 128.05659695893982)', 4326), 1),
    (@day_id94_1, ST_GeomFromText('POINT(35.03144609699468 128.2369000455234)', 4326), 2);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id94_2, '활동 1', '설명 1', 'http://example.com/image94_2_1.jpg'),
    (@day_id94_2, '활동 2', '설명 2', 'http://example.com/image94_2_2.jpg'),
    (@day_id94_2, '활동 3', '설명 3', 'http://example.com/image94_2_3.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id94_2, ST_GeomFromText('POINT(35.32044361927285 128.91426527133447)', 4326), 1),
    (@day_id94_2, ST_GeomFromText('POINT(35.375942814867614 128.36398703826833)', 4326), 2);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id94_3, '활동 1', '설명 1', 'http://example.com/image94_3_1.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id94_3, ST_GeomFromText('POINT(35.7666068984906 128.46587257127914)', 4326), 1),
    (@day_id94_3, ST_GeomFromText('POINT(35.7579862469883 128.94007692434957)', 4326), 2);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id94_4, '활동 1', '설명 1', 'http://example.com/image94_4_1.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id94_4, ST_GeomFromText('POINT(35.277150873853564 128.75175011244886)', 4326), 1),
    (@day_id94_4, ST_GeomFromText('POINT(35.80234636216086 128.57290394104038)', 4326), 2);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id94_5, '활동 1', '설명 1', 'http://example.com/image94_5_1.jpg'),
    (@day_id94_5, '활동 2', '설명 2', 'http://example.com/image94_5_2.jpg'),
    (@day_id94_5, '활동 3', '설명 3', 'http://example.com/image94_5_3.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id94_5, ST_GeomFromText('POINT(35.13469164591863 128.06860559605974)', 4326), 1),
    (@day_id94_5, ST_GeomFromText('POINT(35.87972676688934 128.17594117432284)', 4326), 2);


-- 게시물 95
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    95, 'INTP', '여행 제목 95', '2024-12-01', '2024-12-10', 9,
    'KR', 'ASIA', '지역 95', 225926, 77277, 206699,
    53, 23, 'FEMALE', 'NON_SMOKER', '2024-11-01', 'RECRUITING'
);

SET @post_id95 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id95); SET @day_id95_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id95); SET @day_id95_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id95); SET @day_id95_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id95); SET @day_id95_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id95); SET @day_id95_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id95_1, '활동 1', '설명 1', 'http://example.com/image95_1_1.jpg'),
    (@day_id95_1, '활동 2', '설명 2', 'http://example.com/image95_1_2.jpg'),
    (@day_id95_1, '활동 3', '설명 3', 'http://example.com/image95_1_3.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id95_1, ST_GeomFromText('POINT(35.088769538081216 128.53081548434292)', 4326), 1),
    (@day_id95_1, ST_GeomFromText('POINT(35.893470500625284 128.01190250489918)', 4326), 2);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id95_2, '활동 1', '설명 1', 'http://example.com/image95_2_1.jpg'),
    (@day_id95_2, '활동 2', '설명 2', 'http://example.com/image95_2_2.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id95_2, ST_GeomFromText('POINT(35.78739696206642 128.69864941145588)', 4326), 1),
    (@day_id95_2, ST_GeomFromText('POINT(35.89998279730968 128.3559997751128)', 4326), 2),
    (@day_id95_2, ST_GeomFromText('POINT(35.40760039847831 128.13930421063415)', 4326), 3);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id95_3, '활동 1', '설명 1', 'http://example.com/image95_3_1.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id95_3, ST_GeomFromText('POINT(35.65630876436975 128.87395390319827)', 4326), 1),
    (@day_id95_3, ST_GeomFromText('POINT(35.37007823131787 128.9768632266703)', 4326), 2);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id95_4, '활동 1', '설명 1', 'http://example.com/image95_4_1.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id95_4, ST_GeomFromText('POINT(35.69161547155582 128.69599316866106)', 4326), 1);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id95_5, '활동 1', '설명 1', 'http://example.com/image95_5_1.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id95_5, ST_GeomFromText('POINT(35.50671260372536 128.27314893490563)', 4326), 1),
    (@day_id95_5, ST_GeomFromText('POINT(35.93805838267376 128.65451052092658)', 4326), 2),
    (@day_id95_5, ST_GeomFromText('POINT(35.114070327175675 128.78019876780306)', 4326), 3);


-- 게시물 96
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    96, 'ESTP', '여행 제목 96', '2024-12-01', '2024-12-10', 10,
    'KR', 'ASIA', '지역 96', 344878, 227736, 253085,
    43, 23, 'FEMALE', 'NON_SMOKER', '2024-11-01', 'RECRUITING'
);

SET @post_id96 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id96); SET @day_id96_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id96); SET @day_id96_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id96); SET @day_id96_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id96); SET @day_id96_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id96); SET @day_id96_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id96_1, '활동 1', '설명 1', 'http://example.com/image96_1_1.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id96_1, ST_GeomFromText('POINT(35.16066639724006 128.60746770888343)', 4326), 1),
    (@day_id96_1, ST_GeomFromText('POINT(35.67262975581923 128.78647156324126)', 4326), 2);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id96_2, '활동 1', '설명 1', 'http://example.com/image96_2_1.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id96_2, ST_GeomFromText('POINT(35.844195307338204 128.92970848548575)', 4326), 1),
    (@day_id96_2, ST_GeomFromText('POINT(35.31361371399044 128.174457394848)', 4326), 2),
    (@day_id96_2, ST_GeomFromText('POINT(35.78359953730189 128.009848244008)', 4326), 3);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id96_3, '활동 1', '설명 1', 'http://example.com/image96_3_1.jpg'),
    (@day_id96_3, '활동 2', '설명 2', 'http://example.com/image96_3_2.jpg'),
    (@day_id96_3, '활동 3', '설명 3', 'http://example.com/image96_3_3.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id96_3, ST_GeomFromText('POINT(35.176319708266114 128.3435291057275)', 4326), 1);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id96_4, '활동 1', '설명 1', 'http://example.com/image96_4_1.jpg'),
    (@day_id96_4, '활동 2', '설명 2', 'http://example.com/image96_4_2.jpg'),
    (@day_id96_4, '활동 3', '설명 3', 'http://example.com/image96_4_3.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id96_4, ST_GeomFromText('POINT(35.9319183225786 128.29160885028864)', 4326), 1);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id96_5, '활동 1', '설명 1', 'http://example.com/image96_5_1.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id96_5, ST_GeomFromText('POINT(35.74007445890911 128.96448270443017)', 4326), 1);


-- 게시물 97
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    97, 'ENTP', '여행 제목 97', '2024-12-01', '2024-12-10', 8,
    'KR', 'ASIA', '지역 97', 381969, 128237, 111127,
    30, 23, 'MALE', 'NON_SMOKER', '2024-11-01', 'RECRUITING'
);

SET @post_id97 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id97); SET @day_id97_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id97); SET @day_id97_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id97); SET @day_id97_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id97); SET @day_id97_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id97); SET @day_id97_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id97_1, '활동 1', '설명 1', 'http://example.com/image97_1_1.jpg'),
    (@day_id97_1, '활동 2', '설명 2', 'http://example.com/image97_1_2.jpg'),
    (@day_id97_1, '활동 3', '설명 3', 'http://example.com/image97_1_3.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id97_1, ST_GeomFromText('POINT(35.871430313884275 128.4810176183973)', 4326), 1);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id97_2, '활동 1', '설명 1', 'http://example.com/image97_2_1.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id97_2, ST_GeomFromText('POINT(35.08661872138697 128.59541646280576)', 4326), 1),
    (@day_id97_2, ST_GeomFromText('POINT(35.199842905300244 128.72513982525658)', 4326), 2),
    (@day_id97_2, ST_GeomFromText('POINT(35.28231370004452 128.6933521712615)', 4326), 3);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id97_3, '활동 1', '설명 1', 'http://example.com/image97_3_1.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id97_3, ST_GeomFromText('POINT(35.646115728115625 128.9506726829974)', 4326), 1);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id97_4, '활동 1', '설명 1', 'http://example.com/image97_4_1.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id97_4, ST_GeomFromText('POINT(35.261775506731254 128.3915761413996)', 4326), 1),
    (@day_id97_4, ST_GeomFromText('POINT(35.42100629619787 128.3458649017642)', 4326), 2);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id97_5, '활동 1', '설명 1', 'http://example.com/image97_5_1.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id97_5, ST_GeomFromText('POINT(35.036798120745104 128.98574119966167)', 4326), 1),
    (@day_id97_5, ST_GeomFromText('POINT(35.40444995559568 128.6533658582484)', 4326), 2);


-- 게시물 98
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    98, 'ISTJ', '여행 제목 98', '2024-12-01', '2024-12-10', 4,
    'KR', 'ASIA', '지역 98', 504880, 93385, 357275,
    59, 25, 'MALE', 'NON_SMOKER', '2024-11-01', 'RECRUITING'
);

SET @post_id98 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id98); SET @day_id98_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id98); SET @day_id98_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id98); SET @day_id98_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id98); SET @day_id98_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id98); SET @day_id98_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id98_1, '활동 1', '설명 1', 'http://example.com/image98_1_1.jpg'),
    (@day_id98_1, '활동 2', '설명 2', 'http://example.com/image98_1_2.jpg'),
    (@day_id98_1, '활동 3', '설명 3', 'http://example.com/image98_1_3.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id98_1, ST_GeomFromText('POINT(35.506569899660505 128.91806614034422)', 4326), 1),
    (@day_id98_1, ST_GeomFromText('POINT(35.462481711694146 128.7331604682403)', 4326), 2),
    (@day_id98_1, ST_GeomFromText('POINT(35.201993453609276 128.56759082877167)', 4326), 3);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id98_2, '활동 1', '설명 1', 'http://example.com/image98_2_1.jpg'),
    (@day_id98_2, '활동 2', '설명 2', 'http://example.com/image98_2_2.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id98_2, ST_GeomFromText('POINT(35.2265922046911 128.17767172863918)', 4326), 1);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id98_3, '활동 1', '설명 1', 'http://example.com/image98_3_1.jpg'),
    (@day_id98_3, '활동 2', '설명 2', 'http://example.com/image98_3_2.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id98_3, ST_GeomFromText('POINT(35.33338909421033 128.8409440100942)', 4326), 1),
    (@day_id98_3, ST_GeomFromText('POINT(35.80327476176808 128.6356018990017)', 4326), 2);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id98_4, '활동 1', '설명 1', 'http://example.com/image98_4_1.jpg'),
    (@day_id98_4, '활동 2', '설명 2', 'http://example.com/image98_4_2.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id98_4, ST_GeomFromText('POINT(35.92486321536364 128.19137730202632)', 4326), 1),
    (@day_id98_4, ST_GeomFromText('POINT(35.38337031548324 128.79809536463725)', 4326), 2);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id98_5, '활동 1', '설명 1', 'http://example.com/image98_5_1.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id98_5, ST_GeomFromText('POINT(35.32859525681077 128.32592303282487)', 4326), 1),
    (@day_id98_5, ST_GeomFromText('POINT(35.966457386340466 128.14410037376646)', 4326), 2);


-- 게시물 99
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    99, 'ISTJ', '여행 제목 99', '2024-12-01', '2024-12-10', 1,
    'KR', 'ASIA', '지역 99', 213644, 234259, 193011,
    33, 21, 'UNRELATED', 'NON_SMOKER', '2024-11-01', 'RECRUITING'
);

SET @post_id99 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id99); SET @day_id99_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id99); SET @day_id99_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id99); SET @day_id99_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id99); SET @day_id99_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id99); SET @day_id99_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id99_1, '활동 1', '설명 1', 'http://example.com/image99_1_1.jpg'),
    (@day_id99_1, '활동 2', '설명 2', 'http://example.com/image99_1_2.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id99_1, ST_GeomFromText('POINT(35.61454466705613 128.69910761875693)', 4326), 1),
    (@day_id99_1, ST_GeomFromText('POINT(35.367620194061644 128.97461190547537)', 4326), 2),
    (@day_id99_1, ST_GeomFromText('POINT(35.30470525218401 128.07398931149737)', 4326), 3);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id99_2, '활동 1', '설명 1', 'http://example.com/image99_2_1.jpg'),
    (@day_id99_2, '활동 2', '설명 2', 'http://example.com/image99_2_2.jpg'),
    (@day_id99_2, '활동 3', '설명 3', 'http://example.com/image99_2_3.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id99_2, ST_GeomFromText('POINT(35.86803239567548 128.3725226093389)', 4326), 1);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id99_3, '활동 1', '설명 1', 'http://example.com/image99_3_1.jpg'),
    (@day_id99_3, '활동 2', '설명 2', 'http://example.com/image99_3_2.jpg'),
    (@day_id99_3, '활동 3', '설명 3', 'http://example.com/image99_3_3.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id99_3, ST_GeomFromText('POINT(35.856570245665374 128.29939857598677)', 4326), 1),
    (@day_id99_3, ST_GeomFromText('POINT(35.34265895932957 128.51442612314185)', 4326), 2),
    (@day_id99_3, ST_GeomFromText('POINT(35.363693357209655 128.4564947977535)', 4326), 3);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id99_4, '활동 1', '설명 1', 'http://example.com/image99_4_1.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id99_4, ST_GeomFromText('POINT(35.856962055474504 128.95432140840356)', 4326), 1);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id99_5, '활동 1', '설명 1', 'http://example.com/image99_5_1.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id99_5, ST_GeomFromText('POINT(35.0654821475192 128.46348209821863)', 4326), 1);


-- 게시물 100
INSERT INTO post (
    user_id, mbti, title, travel_start_date, travel_end_date, max_participants,
    travel_country, continent, region, accommodation_fee, other_expenses, airplane_fee,
    limit_max_age, limit_min_age, limit_sex, limit_smoke, deadline, status
) VALUES (
    100, 'ENTP', '여행 제목 100', '2024-12-01', '2024-12-10', 2,
    'KR', 'ASIA', '지역 100', 229916, 81756, 375809,
    34, 28, 'MALE', 'UNRELATED', '2024-11-01', 'RECRUITING'
);

SET @post_id100 = LAST_INSERT_ID();

INSERT INTO day (post_id) VALUES (@post_id100); SET @day_id100_1 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id100); SET @day_id100_2 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id100); SET @day_id100_3 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id100); SET @day_id100_4 = LAST_INSERT_ID();
INSERT INTO day (post_id) VALUES (@post_id100); SET @day_id100_5 = LAST_INSERT_ID();

-- Day 1 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id100_1, '활동 1', '설명 1', 'http://example.com/image100_1_1.jpg'),
    (@day_id100_1, '활동 2', '설명 2', 'http://example.com/image100_1_2.jpg'),
    (@day_id100_1, '활동 3', '설명 3', 'http://example.com/image100_1_3.jpg');

-- Day 1 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id100_1, ST_GeomFromText('POINT(35.33817652505592 128.79844643742726)', 4326), 1),
    (@day_id100_1, ST_GeomFromText('POINT(35.639614934966026 128.26815170197608)', 4326), 2),
    (@day_id100_1, ST_GeomFromText('POINT(35.41377128206581 128.05660438823563)', 4326), 3);

-- Day 2 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id100_2, '활동 1', '설명 1', 'http://example.com/image100_2_1.jpg'),
    (@day_id100_2, '활동 2', '설명 2', 'http://example.com/image100_2_2.jpg'),
    (@day_id100_2, '활동 3', '설명 3', 'http://example.com/image100_2_3.jpg');

-- Day 2 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id100_2, ST_GeomFromText('POINT(35.602668781105926 128.22563149489827)', 4326), 1),
    (@day_id100_2, ST_GeomFromText('POINT(35.89978606421639 128.20319997207343)', 4326), 2),
    (@day_id100_2, ST_GeomFromText('POINT(35.61452635453568 128.02077702163373)', 4326), 3);

-- Day 3 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id100_3, '활동 1', '설명 1', 'http://example.com/image100_3_1.jpg'),
    (@day_id100_3, '활동 2', '설명 2', 'http://example.com/image100_3_2.jpg'),
    (@day_id100_3, '활동 3', '설명 3', 'http://example.com/image100_3_3.jpg');

-- Day 3 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id100_3, ST_GeomFromText('POINT(35.33135881393283 128.6326469963536)', 4326), 1);

-- Day 4 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id100_4, '활동 1', '설명 1', 'http://example.com/image100_4_1.jpg');

-- Day 4 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id100_4, ST_GeomFromText('POINT(35.70306830918199 128.91098161024937)', 4326), 1);

-- Day 5 상세 정보
INSERT INTO day_detail (day_id, title, description, file_address) VALUES
    (@day_id100_5, '활동 1', '설명 1', 'http://example.com/image100_5_1.jpg'),
    (@day_id100_5, '활동 2', '설명 2', 'http://example.com/image100_5_2.jpg'),
    (@day_id100_5, '활동 3', '설명 3', 'http://example.com/image100_5_3.jpg');

-- Day 5 방문 장소
INSERT INTO itinerary_visit (day_id, point, order_number) VALUES
    (@day_id100_5, ST_GeomFromText('POINT(35.02364400505419 128.22730964969813)', 4326), 1),
    (@day_id100_5, ST_GeomFromText('POINT(35.88324401480934 128.4092329218736)', 4326), 2);


