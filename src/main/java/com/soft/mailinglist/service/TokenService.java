package com.soft.mailinglist.service;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.soft.mailinglist.command.TokenResponse;
import com.soft.mailinglist.entity.User;
import com.soft.mailinglist.exception.UserNotFoundException;
import com.soft.mailinglist.repository.UserRepository;
import com.soft.mailinglist.util.JWTUtill;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final JWTUtill jwtUtill;
    private final UserRepository userRepository;

    @Transactional
    public TokenResponse updatedAccessToken(String refreshToken) {
        Map<String, String> claims;
        try {
            claims = jwtUtill.validateToken(refreshToken);
        } catch (JWTVerificationException e) {
            throw new RuntimeException("Invalid token");
        }

        String username = claims.get("username");
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User not found"));
        if (!refreshToken.equals(user.getRefreshToken())) {
            throw new RuntimeException("Invalid refresh token");
        }

        String newAccessToken = jwtUtill.generateAccessToken(username);
        String newRefreshToken = jwtUtill.generateRefreshToken(username);
        user.setRefreshToken(newRefreshToken);
        userRepository.save(user);
        return new TokenResponse(newAccessToken, newRefreshToken);
    }


}
