package com.plataforma.controller;

import com.plataforma.model.plataforma.Permission;
import com.plataforma.model.plataforma.Rol;
import com.plataforma.util.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface PermissionC {
    @GetMapping("findAll")
    ResponseEntity<ApiResponse> findAll();

    @GetMapping("findById/{id}")
    ResponseEntity<ApiResponse> findById(@PathVariable int id);

    @PostMapping("save")
    ResponseEntity<ApiResponse> save(@RequestBody Permission permission);

    @PutMapping("deleteById")
    ResponseEntity<ApiResponse> deleteById(@RequestParam int id);
}
