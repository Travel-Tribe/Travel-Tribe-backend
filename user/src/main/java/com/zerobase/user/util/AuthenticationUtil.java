package com.zerobase.user.util;

import com.zerobase.user.jwt.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationUtil {

    public String getLoginedUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        // 토큰에서 로그인한 사용자의 Email 가져옴.
        return customUserDetails.getUsername();
    }
}
