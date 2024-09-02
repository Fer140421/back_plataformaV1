package com.plataforma.service.impl;

import com.plataforma.model.plataforma.Person;
import com.plataforma.repository.PersonR;
import com.plataforma.service.PersonS;
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
public class PersonImplS implements PersonS {
    private final PersonR personR;
    private final CustomResponseBuilder customResponseBuilder;

    @Override
    public ResponseEntity<ApiResponse> findAll(){
        List<Person> personas = personR.findAll();
        return customResponseBuilder.buildResponse(HttpStatus.OK.value(),"Consulta exitosamente", personas);
    }

    @Override
    public ResponseEntity<ApiResponse> findById(Long id){
        Person personas= personR.getById(id);
        return customResponseBuilder.buildResponse(HttpStatus.OK.value(),"Consulta Exitosa",personas);
    }

    @Override
    public ResponseEntity<ApiResponse> save(Person obj) {
        personR.save(obj);
        return customResponseBuilder.buildResponse(HttpStatus.CREATED.value(), "Registro exitosa.", obj);
    }

    @Override
    public ResponseEntity<ApiResponse> update(Person obj, Long id){
        personR.updateById(obj,id);
        return customResponseBuilder.buildResponse(HttpStatus.OK.value(), "Modificacion exitosa.", id);
    }

    @Override
    public ResponseEntity<ApiResponse> deleteById(Long id) {
        personR.deleteById(id);
        return customResponseBuilder.buildResponse(HttpStatus.OK.value(), "Eliminacion exitosa.", id);
    }
}
