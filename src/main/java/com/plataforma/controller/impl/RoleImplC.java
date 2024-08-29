package com.plataforma.controller.impl;

import com.plataforma.model.plataforma.Rol;
import com.plataforma.service.RoleS;
import com.plataforma.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rol/")
public class RoleImplC implements com.plataforma.controller.RoleC {
    private final RoleS rolS;

    @Override
    @GetMapping("findAll")
    public ResponseEntity<ApiResponse> findAll() {
        return rolS.findAll();
    }

    @Override
    @GetMapping("findById/{id}")
    public ResponseEntity<ApiResponse> findById(@PathVariable int id) {
        return rolS.findById(id);
    }

    @Override
    @PostMapping("save")
    public ResponseEntity<ApiResponse> save(@RequestBody Rol rol) {
        return rolS.save(rol);
    }

    @Override
    @PutMapping("deleteById")
    public ResponseEntity<ApiResponse> deleteById(@RequestParam int id) {
        return rolS.deleteById(id);
    }
}