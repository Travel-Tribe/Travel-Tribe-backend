package com.zerobase.api;

import com.zerobase.model.ResponseApi.PayReadyApiDto;

public interface ApiInterface {

    public PayReadyApiDto sendPayReadySign(long depositId, long id, String userId);


}
