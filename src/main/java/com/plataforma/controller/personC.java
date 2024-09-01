package com.plataforma.controller;

import com.plataforma.model.plataforma.person;
import com.plataforma.util.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface personC {
    @GetMapping("findAll")
    ResponseEntity<ApiResponse> findAll();

    @GetMapping("findById/{id}")
    ResponseEntity<ApiResponse> findById(@PathVariable long id);

    @PostMapping("save")
    ResponseEntity<ApiResponse> save(@RequestBody person person);

    @PutMapping("update")
    ResponseEntity<ApiResponse> update(@RequestBody person person, @PathVariable long id);

    @PutMapping("deleteById")
    ResponseEntity<ApiResponse> deleteById(@PathVariable long id);
}
