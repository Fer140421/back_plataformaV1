package com.plataforma.repository;

import com.plataforma.model.plataforma.Menu;

import java.util.List;

public interface MenuR {
    List<Menu> findAll();

    Menu findById(Integer id);

    boolean save(Menu menu);

    boolean update(Menu menu, Integer id);

    boolean deleteById(Integer id);
}
