package com.plataforma.controller;

import com.plataforma.model.plataforma.SystemsUser;
import com.plataforma.util.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface SystemsUserC {
    @GetMapping("findAll")
    ResponseEntity<ApiResponse> findAll();

    @GetMapping("findById/{id}")
    ResponseEntity<ApiResponse> findById(@PathVariable int id);

    @PostMapping("save")
    ResponseEntity<ApiResponse> save(@RequestBody SystemsUser user);

    @PutMapping("update")
    ResponseEntity<ApiResponse> update(@RequestBody SystemsUser user);

    @PutMapping("deleteById/{id}")
    ResponseEntity<ApiResponse> deleteById(@PathVariable int id);
}
