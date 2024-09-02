package com.plataforma.controller.impl;

import com.plataforma.controller.SystemsUserC;
import com.plataforma.model.plataforma.SystemsUser;
import com.plataforma.service.SystemsUserS;
import com.plataforma.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/SystemUser/")
public class SystemsUserImplC implements SystemsUserC {
    private final SystemsUserS systemsUserS;
    @GetMapping("findAll")
    @Override
    public ResponseEntity<ApiResponse> findAll() {return systemsUserS.findAll();}

    @GetMapping("findById/{id}")
    @Override
    public ResponseEntity<ApiResponse> findById(@PathVariable int id) {return systemsUserS.findById(id);}

    @PostMapping("save")
    @Override
    public ResponseEntity<ApiResponse> save(@RequestBody SystemsUser user) {return systemsUserS.save(user);}

    @PutMapping("update")
    @Override
    public ResponseEntity<ApiResponse> update(@RequestBody SystemsUser user) {return systemsUserS.update(user);}

    @PutMapping("deleteById/{id}")
    @Override
    public ResponseEntity<ApiResponse> deleteById(@PathVariable int id) {
        return systemsUserS.deleteById(id);
    }

}
