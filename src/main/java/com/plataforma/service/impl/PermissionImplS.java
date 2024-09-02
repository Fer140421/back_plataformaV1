package com.plataforma.service.impl;

import com.plataforma.model.plataforma.Permission;
import com.plataforma.repository.PermissionR;
import com.plataforma.service.PermissionS;
import com.plataforma.util.ApiResponse;
import com.plataforma.util.CustomResponseBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class PermissionImplS implements PermissionS {
    private final PermissionR permissionR;
    private final CustomResponseBuilder customResponseBuilder;

    @Override
    public ResponseEntity<ApiResponse> findAll() {
        List<Permission> permissions = permissionR.findAll();
        return customResponseBuilder.buildResponse(HttpStatus.OK.value(),"SE HA EJECUTADO LA CONSULTA", permissions);
    }

    @Override
    public ResponseEntity<ApiResponse> findById(Integer id) {
        Permission permission = permissionR.getById(id);
        return customResponseBuilder.buildResponse(HttpStatus.OK.value(),"SE HA EJECUTADO LA CONSULTA" ,permission);
    }

    @Override
    public ResponseEntity<ApiResponse> save(Permission obj) {
        permissionR.save(obj);
        return customResponseBuilder.buildResponse(HttpStatus.CREATED.value(), "REGISTRO EXITOSO", obj);
    }

    @Override
    public ResponseEntity<ApiResponse> deleteById(Integer id) {
        permissionR.deleteById(id);
        return customResponseBuilder.buildResponse(HttpStatus.OK.value(),"SE HA ELIMINADO EXITOSAMENTE", id);
    }
}
