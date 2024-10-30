package com.fipura.lol_mh_viewer.service;

import com.fipura.lol_mh_viewer.auth.AuthenticationRequest;
import com.fipura.lol_mh_viewer.auth.AuthenticationResponse;
import com.fipura.lol_mh_viewer.auth.RegisterRequest;

public interface AuthenticationService {

    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
