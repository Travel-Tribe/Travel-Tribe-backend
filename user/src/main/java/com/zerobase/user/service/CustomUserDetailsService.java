package com.zerobase.user.service;

import com.zerobase.user.entity.UserEntity;
import com.zerobase.user.jwt.CustomUserDetails;
import com.zerobase.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        // DB에서 조회하고, 없으면 예외를 던짐
        UserEntity userEntity = userRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        // UserDetails에 담아서 return하면 AuthenticationManager가 검증함
        return new CustomUserDetails(userEntity);
    }

}
