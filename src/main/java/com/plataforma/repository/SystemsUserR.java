package com.plataforma.repository;

import com.plataforma.model.plataforma.Permission;
import com.plataforma.model.plataforma.Rol;
import com.plataforma.model.plataforma.SystemsUser;

import java.util.List;

public interface SystemsUserR {

    SystemsUser findSystemUserByUsername(String username);

    List<Rol> findRolListByUser(Long systemUserId);

    List<Permission> findPermissionListByRol(Integer roleId);

    List<SystemsUser> findAll();

    SystemsUser getById(Long id);

   SystemsUser save(SystemsUser user);

    boolean update(SystemsUser user);

    boolean deleteById(Long id);

    SystemsUser findSystemUserByEmail(String email);
}
