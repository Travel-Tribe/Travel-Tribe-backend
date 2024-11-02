package com.zerobase.config;

public final class Constants {

    // Prevent instantiation
    private Constants() {}

    // KakaoPay constants
    public static final String KAKAO_CID = "TC0ONETIME";
    public static final String KAKAO_APPROVAL_URL = "http://localhost:8080";
    public static final String KAKAO_FAIL_URL = "http://localhost:8080";
    public static final String KAKAO_CANCEL_URL = "http://localhost:8080";

    // DepositService constants
    public static final String ITEM_NAME = "여행참여 보증금";
    public static final int QUANTITY = 1;
    public static final int DEPOSIT_AMOUNT = 100_000;
    public static final int TAX_FREE_AMOUNT = DEPOSIT_AMOUNT / 11;

}
