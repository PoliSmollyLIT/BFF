package com.tinqin.academy.rest.security;

import com.tinqin.academy.persistence.models.Role;
import com.tinqin.academy.persistence.models.User;
import com.tinqin.academy.persistence.models.UserLevel;
import com.tinqin.academy.persistence.repositories.UserRepository;
import com.tinqin.academy.rest.securitymodels.AuthenticationRequest;
import com.tinqin.academy.rest.securitymodels.AuthenticationResponse;
import com.tinqin.academy.rest.securitymodels.LoginRequest;
import com.tinqin.academy.rest.securitymodels.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(AuthenticationRequest request) {
            User user = User.builder()
                    .firstName(request.getFirstname())
                    .lastName(request.getLastname())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.USER)
                    .currentPoints(500)
                    .userLevel(UserLevel.BRONZE)
                    .build();
            userRepository.save(user);
            String token = jwtService.generateToken(user);
            AuthenticationResponse response = AuthenticationResponse.builder()
                    .token(token)
                    .build();
            return response;
    }

    public LoginResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(()-> new UsernameNotFoundException("Username not found!"));
        String token = jwtService.generateToken(user);
        LoginResponse response = LoginResponse.builder()
                .token(token)
                .build();
        return response;
    }
}
