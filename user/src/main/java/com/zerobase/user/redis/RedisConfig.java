package com.zerobase.user.redis;

import static com.fasterxml.jackson.databind.SerializationFeature.*;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.zerobase.user.dto.response.UserInfoResponseDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.text.SimpleDateFormat;

@Configuration
public class RedisConfig {

    @Bean
    public ObjectMapper redisObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        // JavaTimeModule 등록하여 LocalDate 지원
        mapper.registerModule(new JavaTimeModule());
        // 날짜를 타임스탬프로 쓰지 않고, 문자열로 직렬화
        mapper.disable(WRITE_DATES_AS_TIMESTAMPS);
        // 날짜 형식 지정 (필요 시)
        mapper.setDateFormat(new java.text.SimpleDateFormat("yyyy-MM-dd"));

        // PolymorphicTypeValidator 설정 (신뢰할 수 있는 패키지 허용)
        PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder()
            .allowIfSubType(UserInfoResponseDTO.class)
            .build();

        // Default Typing 활성화 (타입 정보를 포함하도록 설정)
        mapper.activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);

        return mapper;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory, ObjectMapper redisObjectMapper) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        // 키 직렬화 방식 설정
        StringRedisSerializer stringSerializer = new StringRedisSerializer();
        template.setKeySerializer(stringSerializer);
        template.setHashKeySerializer(stringSerializer);

        // 값 직렬화 방식 설정 (Jackson2JsonRedisSerializer 사용, Object.class 지정)
        Jackson2JsonRedisSerializer<Object> jsonSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        jsonSerializer.setObjectMapper(redisObjectMapper);
        template.setValueSerializer(jsonSerializer);
        template.setHashValueSerializer(jsonSerializer);

        template.afterPropertiesSet();
        return template;
    }

    public RedisTemplate<String, UserInfoResponseDTO> userInfoRedisTemplate(RedisConnectionFactory connectionFactory, ObjectMapper redisObjectMapper) {
        RedisTemplate<String, UserInfoResponseDTO> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        // 키 직렬화 방식 설정
        StringRedisSerializer stringSerializer = new StringRedisSerializer();
        template.setKeySerializer(stringSerializer);
        template.setHashKeySerializer(stringSerializer);

        // 값 직렬화 방식 설정 (Jackson2JsonRedisSerializer 사용, UserInfoResponseDTO.class 지정)
        Jackson2JsonRedisSerializer<UserInfoResponseDTO> serializer = new Jackson2JsonRedisSerializer<>(UserInfoResponseDTO.class);
        serializer.setObjectMapper(redisObjectMapper);
        template.setValueSerializer(serializer);
        template.setHashValueSerializer(serializer);

        template.afterPropertiesSet();
        return template;
    }
}
