package com.plataforma.repository;

import com.plataforma.model.plataforma.Person;

import java.util.List;

public interface PersonR {
    List<Person> findAll();
    Person getById(Long id);
    Long save(Person obj);
    boolean updateById(Person obj, Long id);
    boolean deleteById(Long id);
}
