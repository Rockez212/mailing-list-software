package com.soft.mailinglist.service;

import com.soft.mailinglist.command.LoginCommand;
import com.soft.mailinglist.command.RegisterCommand;
import com.soft.mailinglist.command.TokenResponse;
import com.soft.mailinglist.entity.User;
import com.soft.mailinglist.exception.InvalidPasswordException;
import com.soft.mailinglist.exception.UsernameOrEmailExistException;
import com.soft.mailinglist.repository.UserRepository;
import com.soft.mailinglist.util.JWTUtill;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JWTUtill jwtUtill;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public void register(RegisterCommand registerCommand) {
        String username = registerCommand.getUsername();
        String email = registerCommand.getEmail();
        checkIfUsernameOrEmailExists(username, email);
        String password = passwordEncoder.encode(registerCommand.getPassword());
        User newUser = new User(username, email, password, null);
        userRepository.save(newUser);
    }

    @Transactional
    public TokenResponse login(LoginCommand loginCommand) {
        log.info("login attempt: username={}",loginCommand.getUsername());
        UsernamePasswordAuthenticationToken authInPutToken = new UsernamePasswordAuthenticationToken
                (loginCommand.getUsername(), loginCommand.getPassword());
        try {
            authenticationManager.authenticate(authInPutToken);
        } catch (BadCredentialsException e) {
            log.warn("Failed login attempt: username={}", loginCommand.getUsername());

            throw new InvalidPasswordException("Invalid credentials");
        }
        User currentUser = userRepository.findByUsername(loginCommand.getUsername()).orElseThrow(() -> new UsernameNotFoundException(loginCommand.getUsername()));
        String username = currentUser.getUsername();
        String refreshToken = jwtUtill.generateRefreshToken(username);
        currentUser.setRefreshToken(refreshToken);
        userRepository.save(currentUser);
        String accessToken = jwtUtill.generateAccessToken(loginCommand.getUsername());

        log.info("User successfully logged in: username={}", username);

        return new TokenResponse(accessToken, refreshToken);
    }


    private void checkIfUsernameOrEmailExists(String username, String email) {
        if (userRepository.findByUsername(username).isPresent() || userRepository.findByEmail(email).isPresent()) {
            log.warn("Attempt to register with an existing one: username={} or email={}", username, email);
            throw new UsernameOrEmailExistException("Username or email already exists");
        }
    }

}
