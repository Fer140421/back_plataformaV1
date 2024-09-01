package com.plataforma.repository;

import com.plataforma.model.plataforma.Rol;

import java.util.List;

public interface RoleR {
    List<Rol> findAll();

    Rol getById(Integer id);

    Integer save(Rol obj);

    boolean deleteById(Integer id);
}
