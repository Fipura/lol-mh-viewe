package com.fipura.lol_mh_viewer.controller;

import com.fipura.lol_mh_viewer.auth.AuthenticationRequest;
import com.fipura.lol_mh_viewer.auth.AuthenticationResponse;
import com.fipura.lol_mh_viewer.auth.RegisterRequest;
import com.fipura.lol_mh_viewer.service.impl.AuthenticationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationServiceImpl authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {

        return ResponseEntity.ok(authenticationService.register(request));

    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest request) {

        return ResponseEntity.ok(authenticationService.authenticate(request));

    }


}
