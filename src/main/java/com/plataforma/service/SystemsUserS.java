package com.plataforma.service;

import com.plataforma.model.plataforma.SystemsUser;
import com.plataforma.util.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface SystemsUserS {
    ResponseEntity<ApiResponse> findAll();

    ResponseEntity<ApiResponse> findById(Integer id);

    ResponseEntity<ApiResponse> save(SystemsUser user);

    ResponseEntity<ApiResponse> update(SystemsUser user);

    ResponseEntity<ApiResponse> deleteById(Integer id);
}
