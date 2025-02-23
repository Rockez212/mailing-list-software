package com.soft.mailinglist.security;

import com.soft.mailinglist.util.JWTUtill;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JWTFilter  extends OncePerRequestFilter {
    private final JWTUtill jwtUtill;
    private final UserDea

}
