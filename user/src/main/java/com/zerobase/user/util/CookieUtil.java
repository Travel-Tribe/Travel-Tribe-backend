package com.zerobase.user.util;

import jakarta.servlet.http.Cookie;
import org.springframework.stereotype.Component;

@Component
public class CookieUtil {

    public Cookie createCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(24 * 60 * 60); // 1 day
        cookie.setHttpOnly(true); // Set HttpOnly flag
        // cookie.setSecure(true); // Uncomment to use only with HTTPS
        // cookie.setPath("/"); // Uncomment to set path for the cookie
        return cookie;
    }
}
