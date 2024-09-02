package com.plataforma.service;

import com.plataforma.model.plataforma.person;
import com.plataforma.util.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface personS {
    ResponseEntity<ApiResponse> findAll();

    ResponseEntity<ApiResponse> findById(Long id);

    ResponseEntity<ApiResponse> save(person obj);

    ResponseEntity<ApiResponse> update(person obj, Long id);

    ResponseEntity<ApiResponse> deleteById(Long id);
}
