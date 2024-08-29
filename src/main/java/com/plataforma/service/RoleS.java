package com.plataforma.service;

import com.plataforma.model.plataforma.Rol;
import com.plataforma.util.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface RoleS {
    ResponseEntity<ApiResponse> findAll();

    ResponseEntity<ApiResponse> findById(Integer id);

    ResponseEntity<ApiResponse> save(Rol obj);

    ResponseEntity<ApiResponse> deleteById(Integer id);
}
