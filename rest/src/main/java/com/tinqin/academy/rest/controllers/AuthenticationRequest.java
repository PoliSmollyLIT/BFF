package com.tinqin.academy.rest.controllers;

import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest {
    private String email;
    private String firstname;
    private String lastname;
    private String password;

}
