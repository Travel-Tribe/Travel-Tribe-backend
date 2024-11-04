package com.zerobase.config;

public final class Constants {

    // Prevent instantiation
    private Constants() {}

    // KakaoPay constants
    public static final String KAKAO_CID = "TC0ONETIME";
    public static final String KAKAO_APPROVAL_URL = "http://localhost:8080/api/v1/pay/deposit/success";
    public static final String KAKAO_FAIL_URL = "http://localhost:8080/api/v1/pay/deposit/fail";
    public static final String KAKAO_CANCEL_URL = "http://localhost:8080/api/v1/pay/deposit/refund";

    // DepositService constants
    public static final String ITEM_NAME = "여행참여 보증금";
    public static final long QUANTITY = 1;
    public static final long DEPOSIT_AMOUNT = 100_000;
    public static final long TAX_FREE_AMOUNT = DEPOSIT_AMOUNT / 11;

    public static final String SECRET_KEY = "DEVBB2EC55AEA5209485B283C6EAC15502C4B307";


}
