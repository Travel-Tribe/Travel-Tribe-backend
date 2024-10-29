package com.zerobase.repository;

import com.zerobase.entity.KakaoPayPaymentHistoryEntity;
import com.zerobase.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KakaoPayHistoryRepository extends JpaRepository<KakaoPayPaymentHistoryEntity,Long> {

}
