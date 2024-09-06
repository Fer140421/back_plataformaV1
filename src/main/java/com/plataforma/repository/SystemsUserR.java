package com.plataforma.repository;

import com.plataforma.model.plataforma.Permission;
import com.plataforma.model.plataforma.Rol;
import com.plataforma.model.plataforma.SystemsUser;

import java.util.List;

public interface SystemsUserR {

    SystemsUser findSystemUserByUsername(String username);

    List<Rol> findRolListByUser(Integer systemUserId);

    List<Permission> findPermissionListByRol(Integer roleId);

    List<SystemsUser> findAll();

    SystemsUser getById(Integer id);

    Integer save(SystemsUser user);

    boolean update(SystemsUser user);

    boolean deleteById(Integer id);
}
