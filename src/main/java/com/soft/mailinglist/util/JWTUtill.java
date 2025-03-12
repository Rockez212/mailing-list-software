package com.soft.mailinglist.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Slf4j
@Component
@RequiredArgsConstructor
public class JWTUtill {
    @Value("${jwt_secret}")
    private String secret;

    public String generateAccessToken(String username) {
        Date expirationDate = Date.from(ZonedDateTime.now().plusSeconds(10).toInstant());

        log.info("Generating access token for user: " + username);
        return JWT.create()
                .withSubject("USER TOKEN")
                .withClaim("username", username)
                .withIssuedAt(new Date())
                .withIssuer("ROCKEZ")
                .withExpiresAt(expirationDate)
                .sign(Algorithm.HMAC256(secret));
    }

    public String generateRefreshToken(String username) {
        Date expirationDate = Date.from(ZonedDateTime.now().plusDays(7).toInstant());

        log.info("Generating refresh token for user: " + username);
        return JWT.create()
                .withSubject("REFRESH TOKEN")
                .withClaim("username", username)
                .withIssuedAt(new Date())
                .withIssuer("ROCKEZ")
                .withExpiresAt(expirationDate)
                .sign(Algorithm.HMAC256(secret));
    }

    public Map<String, String> validateToken(String token) throws JWTVerificationException {
        log.info("Validating token: " + token);
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withIssuer("ROCKEZ")
                .build();

        try {
            DecodedJWT jwt = verifier.verify(token);
            Map<String, String> claims = new HashMap<>();
            jwt.getClaims().forEach((key, value) -> claims.put(key, value.asString()));

            log.info("Token successfully validated: .");
            return claims;
        } catch (JWTVerificationException e) {
            log.error("Error validating: {}", e.getMessage());
            throw new JWTVerificationException("Invalid or expired JWT token");
        }
    }
}
