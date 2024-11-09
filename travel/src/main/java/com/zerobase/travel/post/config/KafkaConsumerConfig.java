package com.zerobase.travel.post.config;

import com.zerobase.travel.post.kafka.UserMbtiChangedEvent;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.mapping.DefaultJackson2JavaTypeMapper;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;


    @Bean
    public ConsumerFactory<String, UserMbtiChangedEvent> consumerFactory() {
        Map<String, Object> props = new HashMap<>();

        // Kafka 브로커 주소
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);

        // 그룹 ID 설정
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "travel-service-group");

        // Key와 Value Deserializer 인스턴스 생성
        StringDeserializer keyDeserializer = new StringDeserializer();

        // 실제 역직렬화를 수행할 Deserializer 지정
        props.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, StringDeserializer.class);
        props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class);

        JsonDeserializer<UserMbtiChangedEvent> valueDeserializer = new JsonDeserializer<>(
            UserMbtiChangedEvent.class);
        valueDeserializer.setTypeMapper(customTypeMapper());
        valueDeserializer.addTrustedPackages("*");

        return new DefaultKafkaConsumerFactory<>(props, keyDeserializer, valueDeserializer);
    }

    private DefaultJackson2JavaTypeMapper customTypeMapper() {
        DefaultJackson2JavaTypeMapper typeMapper = new DefaultJackson2JavaTypeMapper();

        // 클래스 아이디에 대한 매핑 설정
        Map<String, Class<?>> idClassMapping = new HashMap<>();
        idClassMapping.put("com.zerobase.user.kafka.UserMbtiChangedEvent", UserMbtiChangedEvent.class);

        typeMapper.setIdClassMapping(idClassMapping);
        typeMapper.setTypePrecedence(DefaultJackson2JavaTypeMapper.TypePrecedence.TYPE_ID);

        return typeMapper;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, UserMbtiChangedEvent> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, UserMbtiChangedEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
