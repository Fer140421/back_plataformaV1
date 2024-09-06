package com.plataforma.service.impl;

import com.plataforma.model.plataforma.Permission;
import com.plataforma.model.plataforma.Rol;
import com.plataforma.model.plataforma.SystemsUser;
import com.plataforma.repository.SystemsUserR;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImplS implements UserDetailsService {
    private final SystemsUserR systemsUserR;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SystemsUser user = systemsUserR.findSystemUserByUsername(username);
        List<Rol> rolList = systemsUserR.findRolListByUser(user.getId());
        List<Permission> permissionList = new ArrayList<>();
        for (Rol rol : rolList) {
            permissionList.addAll(systemsUserR.findPermissionListByRol(rol.getId()));
        }

        Set<GrantedAuthority> authorities = new HashSet<>();

        authorities.addAll(rolList
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                .collect(Collectors.toSet()));

        authorities.addAll(permissionList
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getName()))
                .collect(Collectors.toSet()));

        Set<GrantedAuthority> authoritiesSet = new HashSet<>(authorities);

        // Verificar si el usuario tiene al menos un rol o permiso
        if (authorities.isEmpty()) {
            throw new IllegalStateException("El usuario no tiene roles ni permisos asignados");
        }

        // Manejar valores nulos de las propiedades booleanas
        boolean isEnabled = user.getIsEnabled() != null ? user.getIsEnabled() : true;
        boolean accountNonExpired = user.getAccountNoExpired() != null ? user.getAccountNoExpired() : true;
        boolean accountNonLocked = user.getAccountNoLocked() != null ? user.getAccountNoLocked() : true;
        boolean credentialNonExpired = user.getCredentialNoExipred() != null ? user.getCredentialNoExipred() : true;

        return new User(
                user.getUsername(),
                user.getPassword(),
                isEnabled,
                accountNonExpired,
                credentialNonExpired,
                accountNonLocked,
                authoritiesSet
        );
    }
}
