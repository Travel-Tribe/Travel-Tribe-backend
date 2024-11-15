package com.zerobase.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class BaseEntity {

    @CreatedDate  // 엔티티가 생성될 때 자동으로 날짜 설정
    @Column(name = "create_date", updatable = false)
    private LocalDateTime createDate;

    @LastModifiedDate  // 엔티티가 수정될 때 자동으로 날짜 설정
    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate;
}

