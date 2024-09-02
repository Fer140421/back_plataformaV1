package com.plataforma.service;

import com.plataforma.model.plataforma.Person;
import com.plataforma.util.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface PersonS {
    ResponseEntity<ApiResponse> findAll();

    ResponseEntity<ApiResponse> findById(Long id);

    ResponseEntity<ApiResponse> save(Person obj);

    ResponseEntity<ApiResponse> update(Person obj, Long id);

    ResponseEntity<ApiResponse> deleteById(Long id);
}
