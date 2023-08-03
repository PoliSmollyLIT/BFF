package com.tinqin.academy.rest.controllers;

import com.tinqin.academy.rest.security.AuthenticationService;
import com.tinqin.academy.rest.securitymodels.AuthenticationRequest;
import com.tinqin.academy.rest.securitymodels.AuthenticationResponse;
import com.tinqin.academy.rest.securitymodels.LoginRequest;
import com.tinqin.academy.rest.securitymodels.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authenticationService.login(request));
    }
}
