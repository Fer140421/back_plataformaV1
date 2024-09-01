package com.plataforma.repository;

import com.plataforma.model.plataforma.Permission;

import java.util.List;

public interface PermissionR {
    List<Permission> findAll();

    Permission getById(Integer id);

    Integer save(Permission obj);

    boolean deleteById(Integer id);
}
