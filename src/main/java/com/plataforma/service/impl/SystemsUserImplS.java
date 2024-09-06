package com.plataforma.service.impl;

import com.plataforma.config.jwt.JwtUtils;
import com.plataforma.controller.request.AuthLoginRequest;
import com.plataforma.controller.response.AuthResponse;
import com.plataforma.model.plataforma.SystemsUser;
import com.plataforma.repository.PersonR;
import com.plataforma.repository.RoleR;
import com.plataforma.repository.SystemsUserR;
import com.plataforma.service.SystemsUserS;
import com.plataforma.util.ApiResponse;
import com.plataforma.util.CustomResponseBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class SystemsUserImplS implements SystemsUserS {
    private final SystemsUserR systemsUserR;
    private final RoleR roleR;
    private final PersonR personR;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final CustomResponseBuilder customResponseBuilder;
    private final UserDetailServiceImplS userDetailServiceImplS;

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

    @Override
    public AuthResponse loginUser(AuthLoginRequest authLoginRequest) {
        String username = authLoginRequest.username();
        String password = authLoginRequest.password();
        Authentication authentication = this.authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accessToken = jwtUtils.createToken(authentication);
        return new AuthResponse(username, "User loged success", accessToken, true);
    }

    @Override
    public Authentication authenticate(String username, String password) {
        UserDetails userDetails = userDetailServiceImplS.loadUserByUsername(username);
        if (userDetails == null) {
            throw new BadCredentialsException("Invalid username or password");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }
        return new UsernamePasswordAuthenticationToken(username, userDetails.getPassword(), userDetails.getAuthorities());
    }

}
