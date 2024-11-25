package com.zerobase.user.util;

import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

@Component
public class CookieUtil {

    public ResponseCookie createCookie(String key, String value) {

        return ResponseCookie.from(key, value)
            .path("/")
            .sameSite("None")
            .httpOnly(true)
            .secure(true)
            .maxAge(24 * 60 * 60)
            .build();
    }

    public static ResponseCookie createCookie1(String key, String value) {

        return ResponseCookie.from(key, value)
            .path("/")
            .sameSite("None")
            .httpOnly(true)
            .secure(true)
            .maxAge(24 * 60 * 60)
            .build();
    }
}
