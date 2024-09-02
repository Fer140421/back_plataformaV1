package com.plataforma.service.impl;

import com.plataforma.model.plataforma.SystemsUser;
import com.plataforma.repository.SystemUserR;
import com.plataforma.service.SystemsUserS;
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
public class SystemsUserIS implements SystemsUserS {
    private final SystemUserR systemsUserR;
    private final CustomResponseBuilder customResponseBuilder;

    @Override
    public ResponseEntity<ApiResponse> findAll() {
        List<SystemsUser> systemUser = systemsUserR.findAll();
        return customResponseBuilder.buildResponse(HttpStatus.OK.value(), "Consulta exitosa.", systemUser);
    }
    @Override
    public ResponseEntity<ApiResponse> findById(Integer id) {
        SystemsUser systemUser = systemsUserR.getById(id);
        return customResponseBuilder.buildResponse(HttpStatus.OK.value(), "Consulta exitosa.", systemUser);
    }
    @Override
    public ResponseEntity<ApiResponse> save(SystemsUser user) {
        systemsUserR.save(user);
        return customResponseBuilder.buildResponse(HttpStatus.CREATED.value(), "Registro exitosa.", user);
    }
    @Override
    public ResponseEntity<ApiResponse> update(SystemsUser user){
        systemsUserR.update(user);
        return customResponseBuilder.buildResponse(HttpStatus.OK.value(),"modificacion exitosa",user);
    }
    @Override
    public ResponseEntity<ApiResponse> deleteById(Integer id){
        systemsUserR.deleteById(id);
        return customResponseBuilder.buildResponse(HttpStatus.OK.value(), "Eliminacion exitosa.", id);
    }
}
