package com.soft.mailinglist.service;

import com.soft.mailinglist.command.LoginCommand;
import com.soft.mailinglist.command.RegisterCommand;
import com.soft.mailinglist.entity.User;
import com.soft.mailinglist.exception.InvalidPasswordException;
import com.soft.mailinglist.exception.UsernameOrEmailExistException;
import com.soft.mailinglist.repository.UserRepository;
import com.soft.mailinglist.util.JWTUtill;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        String refreshToken = jwtUtill.generateRefreshToken(email);
        User newUser = new User(username, email, password, refreshToken);
        userRepository.save(newUser);
    }

    @Transactional(readOnly = true)
    public String login(LoginCommand loginCommand) {
        User user = userRepository.findByUsername(loginCommand.getUsername()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        UsernamePasswordAuthenticationToken authInPutToken = new UsernamePasswordAuthenticationToken
                (loginCommand.getUsername(), loginCommand.getPassword());
        try {
            authenticationManager.authenticate(authInPutToken);
        } catch (BadCredentialsException e) {
            throw new InvalidPasswordException("Invalid credentials");
        }
        return jwtUtill.generateToken(user.getEmail());


    }
    private void checkIfUsernameOrEmailExists(String username, String email) {
        if (userRepository.findByUsername(username).isPresent() | userRepository.findByEmail(email).isPresent()) {
            throw new UsernameOrEmailExistException("Username or email already exists");
        }
    }

}
