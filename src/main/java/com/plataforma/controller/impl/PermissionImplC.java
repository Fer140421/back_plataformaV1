package com.plataforma.controller.impl;

import com.plataforma.controller.PermissionC;
import com.plataforma.service.PermissionS;
import com.plataforma.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/permission/")
public class PermissionImplC implements PermissionC {
    private final PermissionS permissionS;

    @Override
    @GetMapping("findAll")
    public ResponseEntity<ApiResponse> findAll() {
        return permissionS.findAll();
    }

    @Override
    @GetMapping("findById/{id}")
    public ResponseEntity<ApiResponse> findById(@PathVariable int id) {
        return permissionS.findById(id);
    }

    @Override
    @PostMapping("save")
    public ResponseEntity<ApiResponse> save(@RequestBody com.plataforma.model.plataforma.Permission permission) {
        return permissionS.save(permission);
    }

    @Override
    @PutMapping("deleteById")
    public ResponseEntity<ApiResponse> deleteById(@RequestParam int id) {
        return permissionS.deleteById(id);
    }
}
