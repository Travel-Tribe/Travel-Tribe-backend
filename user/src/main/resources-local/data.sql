    -- Clear existing data
    DELETE FROM visited_country_entity;
    DELETE FROM lang_ability_entity;
    DELETE FROM profile_entity;
    DELETE FROM user_entity;

    -- Reset auto-increment counters (optional)
    ALTER TABLE lang_ability_entity AUTO_INCREMENT = 1;
    ALTER TABLE visited_country_entity AUTO_INCREMENT = 1;
    ALTER TABLE profile_entity AUTO_INCREMENT = 1;
    ALTER TABLE user_entity AUTO_INCREMENT = 1;

    -- Insert data into user_entity
    INSERT INTO user_entity ( username, nickname, phone, password, email, role, status)
    VALUES
        ( 'user1', 'User One', '111-111-1111', '$2a$10$XL8lSNnaAkAemYUjgGfGLOAp9apDNOUQE04GxUkeW7F..KFjZ/x7G', 'user1@example.com', 'ROLE_USER', 'ACTIVE'),
        ( 'user2', 'User Two', '222-222-2222', '$2a$10$XL8lSNnaAkAemYUjgGfGLOAp9apDNOUQE04GxUkeW7F..KFjZ/x7G', 'user2@example.com', 'ROLE_USER', 'ACTIVE'),
        ( 'user3', 'User Three', '333-333-3333', '$2a$10$XL8lSNnaAkAemYUjgGfGLOAp9apDNOUQE04GxUkeW7F..KFjZ/x7G', 'user3@example.com', 'ROLE_USER', 'INACTIVE'),
        ( 'user4', 'User Four', '444-444-4444', '$2a$10$XL8lSNnaAkAemYUjgGfGLOAp9apDNOUQE04GxUkeW7F..KFjZ/x7G', 'user4@example.com', 'ROLE_USER', 'ACTIVE'),
        ( 'user5', 'User Five', '555-555-5555', '$2a$10$XL8lSNnaAkAemYUjgGfGLOAp9apDNOUQE04GxUkeW7F..KFjZ/x7G', 'user5@example.com', 'ROLE_ADMIN', 'ACTIVE'),
        ( 'user6', 'User Six', '666-666-6666', '$2a$10$XL8lSNnaAkAemYUjgGfGLOAp9apDNOUQE04GxUkeW7F..KFjZ/x7G', 'user6@example.com', 'ROLE_USER', 'INACTIVE'),
        ( 'user7', 'User Seven', '777-777-7777', '$2a$10$XL8lSNnaAkAemYUjgGfGLOAp9apDNOUQE04GxUkeW7F..KFjZ/x7G', 'user7@example.com', 'ROLE_USER', 'ACTIVE'),
        ( 'user8', 'User Eight', '888-888-8888', '$2a$10$XL8lSNnaAkAemYUjgGfGLOAp9apDNOUQE04GxUkeW7F..KFjZ/x7G', 'user8@example.com', 'ROLE_USER', 'ACTIVE'),
        ( 'user9', 'User Nine', '999-999-9999', '$2a$10$XL8lSNnaAkAemYUjgGfGLOAp9apDNOUQE04GxUkeW7F..KFjZ/x7G', 'user9@example.com', 'ROLE_USER', 'ACTIVE'),
        ( 'user10', 'User Ten', '101-010-1010', '$2a$10$XL8lSNnaAkAemYUjgGfGLOAp9apDNOUQE04GxUkeW7F..KFjZ/x7G', 'user10@example.com', 'ROLE_USER', 'INACTIVE');

    -- Insert data into profile_entity
    INSERT INTO profile_entity (user_id, introduction, mbti, smoking, gender, birth, rating_avg, file_address)
    VALUES
        (1, '안녕하세요! 저는 사용자 일입니다.', 'INTJ', 'NO', 'MALE', '1990-01-01', 4.5, '/path/to/user1_profile.jpg'),
        (2, '안녕하세요! 저는 사용자 이입니다.', 'ENFP', 'YES', 'FEMALE', '1985-05-05', 4.8, '/path/to/user2_profile.jpg'),
        (3, '안녕하세요! 저는 사용자 삼입니다.', 'ISTJ', 'NO', 'MALE', '1988-03-03', 4.2, '/path/to/user3_profile.jpg'),
        (4, '안녕하세요! 저는 사용자 사입니다.', 'ENTP', 'YES', 'FEMALE', '1992-04-04', 4.7, '/path/to/user4_profile.jpg'),
        (5, '안녕하세요! 저는 사용자 오입니다.', 'ISFJ', 'NO', 'MALE', '1995-05-05', 4.6, '/path/to/user5_profile.jpg'),
        (6, '안녕하세요! 저는 사용자 육입니다.', 'INFJ', 'YES', 'FEMALE', '1993-06-06', 4.3, '/path/to/user6_profile.jpg'),
        (7, '안녕하세요! 저는 사용자 칠입니다.', 'ESTP', 'NO', 'MALE', '1989-07-07', 4.9, '/path/to/user7_profile.jpg'),
        (8, '안녕하세요! 저는 사용자 팔입니다.', 'ENTJ', 'YES', 'FEMALE', '1991-08-08', 4.4, '/path/to/user8_profile.jpg'),
        (9, '안녕하세요! 저는 사용자 구입니다.', 'INTP', 'NO', 'MALE', '1994-09-09', 4.1, '/path/to/user9_profile.jpg'),
        (10, '안녕하세요! 저는 사용자 십입니다.', 'ESFJ', 'YES', 'FEMALE', '1987-10-10', 4.0, '/path/to/user10_profile.jpg');

    -- Insert data into lang_ability_entity
    INSERT INTO lang_ability_entity (profile_id, lang)
    VALUES
        -- Profile 1 Languages
        (1, '영어'),
        (1, '스페인어'),
        (1, '프랑스어'),
        (1, '독일어'),
        (1, '중국어'),
        -- Profile 2 Languages
        (2, '일본어'),
        (2, '러시아어'),
        (2, '아랍어'),
        (2, '포르투갈어'),
        (2, '힌디어'),
        -- Profile 3 Languages
        (3, '한국어'),
        (3, '이탈리아어'),
        (3, '네덜란드어'),
        (3, '스웨덴어'),
        (3, '노르웨이어'),
        -- Profile 4 Languages
        (4, '덴마크어'),
        (4, '핀란드어'),
        (4, '그리스어'),
        (4, '터키어'),
        (4, '히브리어'),
        -- Profile 5 Languages
        (5, '영어'),
        (5, '일본어'),
        (5, '한국어'),
        (5, '스페인어'),
        (5, '아랍어'),
        -- Profile 6 Languages
        (6, '프랑스어'),
        (6, '독일어'),
        (6, '중국어'),
        (6, '포르투갈어'),
        (6, '힌디어'),
        -- Profile 7 Languages
        (7, '러시아어'),
        (7, '이탈리아어'),
        (7, '네덜란드어'),
        (7, '스웨덴어'),
        (7, '노르웨이어'),
        -- Profile 8 Languages
        (8, '덴마크어'),
        (8, '핀란드어'),
        (8, '그리스어'),
        (8, '터키어'),
        (8, '히브리어'),
        -- Profile 9 Languages
        (9, '영어'),
        (9, '중국어'),
        (9, '일본어'),
        (9, '한국어'),
        (9, '힌디어'),
        -- Profile 10 Languages
        (10, '스페인어'),
        (10, '프랑스어'),
        (10, '독일어'),
        (10, '러시아어'),
        (10, '아랍어');
