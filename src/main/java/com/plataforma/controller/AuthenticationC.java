package com.plataforma.controller;

import com.plataforma.controller.request.AuthLoginRequest;
import com.plataforma.controller.response.AuthResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthenticationC {
    @PostMapping("log-in")
    ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthLoginRequest userRequest);
}
