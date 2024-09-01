package com.plataforma.controller.impl;

import com.plataforma.controller.personC;
import com.plataforma.model.plataforma.person;
import com.plataforma.service.personS;
import com.plataforma.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/person/")
public class personImplC implements personC {
    private final personS persons;

    @GetMapping("findAll")
    @Override
    public ResponseEntity<ApiResponse> findAll(){
        return persons.findAll();
    }
    @Override
    @GetMapping("findById/{id}")
    public ResponseEntity<ApiResponse> findById(@PathVariable long id) {
        return persons.findById(id);
    }

    @Override
    @PostMapping("save")
    public ResponseEntity<ApiResponse> save(@RequestBody person person) {
        return persons.save(person);
    }

    @Override
    @PutMapping("updateById/{id}")
    public ResponseEntity<ApiResponse> update(@RequestBody person person, @PathVariable long id) {
        return persons.update(person,id);
    }

    @Override
    @PutMapping("deleteById/{id}")
    public ResponseEntity<ApiResponse> deleteById(@PathVariable long id) {
        return persons.deleteById(id);
    }
}

