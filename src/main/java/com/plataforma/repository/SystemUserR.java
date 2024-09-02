package com.plataforma.repository;

import com.plataforma.model.plataforma.SystemsUser;

import java.util.List;

public interface SystemUserR {
    List<SystemsUser> findAll();

    SystemsUser getById(Integer id);

    Integer save(SystemsUser user);

    boolean update(SystemsUser user);

    boolean deleteById(Integer id);
}
