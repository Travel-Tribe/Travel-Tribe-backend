package com.zerobase.service;

import com.zerobase.model.PayApiDto;

public interface ApiService {

    public PayApiDto getReady(Long orderId, String userId);


}
