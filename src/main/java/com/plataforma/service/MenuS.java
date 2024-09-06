package com.plataforma.service;

import com.plataforma.model.plataforma.Menu;
import com.plataforma.util.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface MenuS {
    ResponseEntity<ApiResponse> findAll();

    ResponseEntity<ApiResponse> findById(Integer id);

    ResponseEntity<ApiResponse> save(Menu obj);

    ResponseEntity<ApiResponse> deleteById(Integer id);
}
