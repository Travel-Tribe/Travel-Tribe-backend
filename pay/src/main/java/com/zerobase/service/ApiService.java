package com.zerobase.service;

import com.zerobase.model.ResponseApi.PayReadyApiDto;

public interface ApiService {

    public PayReadyApiDto sendPayReadySign(Long orderId, String userId);


}
