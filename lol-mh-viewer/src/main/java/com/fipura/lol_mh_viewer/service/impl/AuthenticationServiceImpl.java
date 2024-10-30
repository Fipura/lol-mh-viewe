package com.fipura.lol_mh_viewer.service.impl;

import com.fipura.lol_mh_viewer.auth.AuthenticationRequest;
import com.fipura.lol_mh_viewer.auth.AuthenticationResponse;
import com.fipura.lol_mh_viewer.auth.RegisterRequest;
import com.fipura.lol_mh_viewer.model.Role;
import com.fipura.lol_mh_viewer.model.User;
import com.fipura.lol_mh_viewer.repository.UserRepository;
import com.fipura.lol_mh_viewer.service.AuthenticationService;
import com.fipura.lol_mh_viewer.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        User user =  User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalStateException("This broke here"));
        String jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
        } catch (AuthenticationException e) {
            System.out.println("Authentication failed: " + e.getMessage());
            throw new IllegalStateException("Authentication failed", e);
        }
    }
}
