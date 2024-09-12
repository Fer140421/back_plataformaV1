package com.plataforma.controller.impl;

import com.plataforma.controller.AuthenticationC;
import com.plataforma.controller.request.AuthCreateUserRequest;
import com.plataforma.controller.request.AuthLoginRequest;
import com.plataforma.controller.response.AuthResponse;
import com.plataforma.service.SystemsUserS;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/")
public class AuthenticationImplC implements AuthenticationC {
private final SystemsUserS systemsUserS;

    @Override
    @PostMapping("log-in")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthLoginRequest userRequest) {
        return new ResponseEntity<AuthResponse>(systemsUserS.loginUser(userRequest), HttpStatus.OK);
    }


    @Override
    @PostMapping("sign-up")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid AuthCreateUserRequest authCreateUser) {
        return new ResponseEntity<>(systemsUserS.createUser(authCreateUser), HttpStatus.CREATED);
    }
}
