package com.plataforma.service.impl;

import com.plataforma.config.jwt.JwtUtils;
import com.plataforma.controller.request.AuthCreateUserRequest;
import com.plataforma.controller.request.AuthLoginRequest;
import com.plataforma.controller.response.AuthResponse;
import com.plataforma.model.plataforma.Permission;
import com.plataforma.model.plataforma.Person;
import com.plataforma.model.plataforma.Rol;
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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    public SystemsUser findById(Long id) {
        return systemsUserR.getById(id);
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
    public ResponseEntity<ApiResponse> deleteById(Long id){
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

    @Override
    public AuthResponse createUser(AuthCreateUserRequest authCreateUserRequest) {
        List<String> roleRequest = authCreateUserRequest.roleRequest().roleListName();
        Set<Rol> rolSet = roleR.findByEntitiesByRole(roleRequest).stream().collect(Collectors.toSet());
        if (rolSet.isEmpty()) {
            throw new IllegalArgumentException("Invalid role");
        }
        Person person = Person.builder()
                .ci(authCreateUserRequest.ci())
                .firstLastName(authCreateUserRequest.firstLastName())
                .secondLastName(authCreateUserRequest.secondLastName())
                .name(authCreateUserRequest.name())
                .gender(authCreateUserRequest.gender())
                .status(true)
                .build();
        Long idPersona = personR.save(person);

        SystemsUser user = SystemsUser.builder()
                .id(idPersona)
                .username(authCreateUserRequest.username())
                .password(passwordEncoder.encode(authCreateUserRequest.password()))
                .email(authCreateUserRequest.email())
                .alias(authCreateUserRequest.alias())
                .cell(authCreateUserRequest.cell())
                .codeCell(authCreateUserRequest.codeCell())
                .dateStartVerification(authCreateUserRequest.dateStartVerification())
                .dateEndVerification(authCreateUserRequest.dateEndVerification())
                .status(true)
                .isEnabled(true)
                .accountNoLocked(true)
                .accountNoExpired(true)
                .credentialNoExipred(true)
                .build();
        SystemsUser savedUser = systemsUserR.save(user);
        rolSet.forEach(role -> { // guardamos en la tabla intermedia el usuario y los roles
            List<Permission> permissions = systemsUserR.findPermissionListByRol(role.getId());
            roleR.saveRolePermissions(role.getId(), permissions);
            roleR.saveAll(savedUser.getId(), new ArrayList<>(rolSet));
        });

        List<Permission> permissionList = new ArrayList<>();
        for (Rol rol : rolSet) {
            permissionList.addAll(systemsUserR.findPermissionListByRol(rol.getId()));
        }
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.addAll(rolSet
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                .collect(Collectors.toSet()));

        authorities.addAll(permissionList
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getName()))
                .collect(Collectors.toSet()));
        
        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getUsername(), savedUser.getPassword(), authorities);
        String acccessToken = jwtUtils.createToken(authentication);
        return new AuthResponse(savedUser.getUsername(), "Usuario creado", acccessToken, true);
    }

    @Override
    public SystemsUser findSystemUserByEmail(String email) {
        SystemsUser isUpdated = systemsUserR.findSystemUserByEmail(email);
        return isUpdated;
    }
}
