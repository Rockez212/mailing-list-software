package com.soft.mailinglist.util;

import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;
@Slf4j
@Component
@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {
    private final JWTUtill jwtUtill;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);

            if (token.isBlank()) {
                log.warn("Token is empty. Access denied.");

                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            } else {
                try {
                    log.info("Trying to verify token");
                    Map<String, String> claims = jwtUtill.validateToken(token);
                    String username = claims.get("username");

                    log.info("Token is valid for username {}", username);

                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    if (SecurityContextHolder.getContext().getAuthentication() == null) {
                        log.info("Set up authentication");
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                } catch (JWTVerificationException e) {
                    log.error("JWT verification failed", e);
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid or expired JWT token");
                }
            }
        }
        chain.doFilter(request, response);
    }
}
