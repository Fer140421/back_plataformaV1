package com.plataforma.service;

import com.plataforma.controller.request.AuthCreateUserRequest;
import com.plataforma.controller.request.AuthLoginRequest;
import com.plataforma.controller.response.AuthResponse;
import com.plataforma.model.plataforma.SystemsUser;
import com.plataforma.util.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

public interface SystemsUserS {
    ResponseEntity<ApiResponse> findAll();

    SystemsUser findById(Long id);

    ResponseEntity<ApiResponse> save(SystemsUser user);

    ResponseEntity<ApiResponse> update(SystemsUser user);

    ResponseEntity<ApiResponse> deleteById(Long id);

    AuthResponse loginUser(AuthLoginRequest authLoginRequest);

    Authentication authenticate(String username, String password);

    AuthResponse createUser(AuthCreateUserRequest authCreateUserRequest);

    SystemsUser findSystemUserByEmail(String email);
}
