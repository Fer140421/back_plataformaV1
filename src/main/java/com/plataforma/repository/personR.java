package com.plataforma.repository;

import com.plataforma.model.plataforma.person;

import java.util.List;

public interface personR {
    List<person> findAll();
    person getById(Long id);
    Integer save(person obj);
    boolean updateById(person obj, Long id);
    boolean deleteById(Long id);
}
