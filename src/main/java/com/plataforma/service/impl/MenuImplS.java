package com.plataforma.service.impl;

import com.plataforma.model.plataforma.Menu;
import com.plataforma.repository.MenuR;
import com.plataforma.service.MenuS;
import com.plataforma.util.ApiResponse;
import com.plataforma.util.CustomResponseBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MenuImplS implements MenuS {
    private final MenuR menuR;
    private final CustomResponseBuilder customResponseBuilder;

    @Override
    public ResponseEntity<ApiResponse> findAll() {
        List<Menu> menus = menuR.findAll();
        return customResponseBuilder.buildResponse(HttpStatus.OK.value(),"Consulta exitosa", menus);
    }

    @Override
    public ResponseEntity<ApiResponse> findById(Integer id) {
        Menu menu = menuR.findById(id);
        return customResponseBuilder.buildResponse(HttpStatus.OK.value(),"Consulta exitosa" ,menu);
    }

    @Override
    public ResponseEntity<ApiResponse> save(Menu obj) {
        menuR.save(obj);
        return customResponseBuilder.buildResponse(HttpStatus.CREATED.value(), "Registro exitoso", obj);
    }

    @Override
    public ResponseEntity<ApiResponse> deleteById(Integer id) {
        menuR.deleteById(id);
        return customResponseBuilder.buildResponse(HttpStatus.OK.value(),"Se ha eliminado con exito", id);
    }
}
