package com.zerobase.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constants {

    // Prevent instantiation
    private Constants() {}

    // KakaoPay constants
    @Value("${kakaopay.cid}")
    public String KAKAO_CID;

    @Value("${kakaopay.approval-url}")
    public String KAKAO_APPROVAL_URL;

    @Value("${kakaopay.fail-url}")
    public String KAKAO_FAIL_URL;

    @Value("${kakaopay.cancel-url}")
    public String KAKAO_CANCEL_URL;

    // DepositService constants
    @Value("${kakaopay.item-name}")
    public String ITEM_NAME;

    @Value("${kakaopay.quantity}")
    public long QUANTITY;

    @Value("${kakaopay.deposit-amount}")
    public long DEPOSIT_AMOUNT;

    @Value("${kakaopay.tax-free-amount}")
    public long TAX_FREE_AMOUNT;

    @Value("${kakaopay.vat-amount}")
    public long VAT_AMOUNT;


    @Value("${kakaopay.secret-key}")
    public String SECRET_KEY;



}
