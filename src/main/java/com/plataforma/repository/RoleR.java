package com.plataforma.repository;

import com.plataforma.model.plataforma.Permission;
import com.plataforma.model.plataforma.Rol;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RoleR {
    List<Rol> findAll();

    Rol getById(Integer id);

    Integer save(Rol obj);

    boolean deleteById(Integer id);

    List<Rol> findByEntitiesByRole(List<String> roleNames);

    @Transactional
    boolean saveAll(Long userId, List<Rol> rolList);

    boolean saveRolePermissions(Integer roleId, List<Permission> permissions);
}
