package com.soft.mailinglist.service;

import com.soft.mailinglist.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Attempting to load user with username={}", username);

        return userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    log.warn("User with username={} not found", username);
                    return new UsernameNotFoundException("User not found with username: " + username);
                });
    }
}
