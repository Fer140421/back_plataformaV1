package com.plataforma.service.impl;

import com.plataforma.model.plataforma.person;
import com.plataforma.repository.personR;
import com.plataforma.service.personS;
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
public class personImplS implements personS {
    private final personR personR;
    private final CustomResponseBuilder customResponseBuilder;

    @Override
    public ResponseEntity<ApiResponse> findAll(){
        List<person> personas = personR.findAll();
        return customResponseBuilder.buildResponse(HttpStatus.OK.value(),"Consulta exitosamente", personas);
    }

    @Override
    public ResponseEntity<ApiResponse> findById(Long id){
        person personas= personR.getById(id);
        return customResponseBuilder.buildResponse(HttpStatus.OK.value(),"Consulta Exitosa",personas);
    }

    @Override
    public ResponseEntity<ApiResponse> save(person obj) {
        personR.save(obj);
        return customResponseBuilder.buildResponse(HttpStatus.CREATED.value(), "Registro exitosa.", obj);
    }

    @Override
    public ResponseEntity<ApiResponse> update(person obj, Long id){
        personR.updateById(obj,id);
        return customResponseBuilder.buildResponse(HttpStatus.OK.value(), "Modificacion exitosa.", id);
    }

    @Override
    public ResponseEntity<ApiResponse> deleteById(Long id) {
        personR.deleteById(id);
        return customResponseBuilder.buildResponse(HttpStatus.OK.value(), "Eliminacion exitosa.", id);
    }
}
