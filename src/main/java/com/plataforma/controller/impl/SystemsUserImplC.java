package com.plataforma.controller.impl;

import com.plataforma.controller.SystemsUserC;
import com.plataforma.model.plataforma.SystemsUser;
import com.plataforma.service.SystemsUserS;
import com.plataforma.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/SystemUser/")
@PreAuthorize("hasAnyRole('ADMINISTRADOR')")
public class SystemsUserImplC implements SystemsUserC {
    private final SystemsUserS systemsUserS;

    @GetMapping("findAll")
    @PreAuthorize("hasAuthority('GET')")
    @Override
    public ResponseEntity<ApiResponse> findAll() {return systemsUserS.findAll();}

    @GetMapping("findById/{id}")
    @PreAuthorize("hasAuthority('GET')")
    @Override
    public SystemsUser findById(@PathVariable Long id) {return systemsUserS.findById(id);}

    @PostMapping("save")
    @PreAuthorize("hasAuthority('CREATE')")
    @Override
    public ResponseEntity<ApiResponse> save(@RequestBody SystemsUser user) {return systemsUserS.save(user);}

    @PutMapping("update")
    @PreAuthorize("hasAuthority('UPDATE')")
    @Override
    public ResponseEntity<ApiResponse> update(@RequestBody SystemsUser user) {return systemsUserS.update(user);}

    @PutMapping("deleteById/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    @Override
    public ResponseEntity<ApiResponse> deleteById(@PathVariable Long id) {
        return systemsUserS.deleteById(id);
    }

}
