package com.plataforma.service;

import com.plataforma.model.plataforma.Permission;
import com.plataforma.util.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface PermissionS {
    ResponseEntity<ApiResponse> findAll();

    ResponseEntity<ApiResponse> findById(Integer id);

    ResponseEntity<ApiResponse> save(Permission obj);

    ResponseEntity<ApiResponse> deleteById(Integer id);
}
