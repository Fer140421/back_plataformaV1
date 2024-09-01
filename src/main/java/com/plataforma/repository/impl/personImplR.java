package com.plataforma.repository.impl;

import com.plataforma.model.plataforma.person;
import com.plataforma.repository.personR;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class personImplR implements personR {
    private final JdbcTemplate db;
    private String sql;

    public List<person> findAll(){
        sql = "SELECT * FROM person;";
        return db.query(sql, BeanPropertyRowMapper.newInstance(person.class));
    }

    @Override
    public person getById(Long id) {
        sql = "SELECT * FROM person WHERE id = ?;";
        return db.queryForObject(sql,BeanPropertyRowMapper.newInstance(person.class),id);
    }

    @Override
    public Integer save(person obj) {
        sql = "INSERT INTO person(ci,first_lastename,gender,name,second_lastname) VALUES(?,?,?,?,?) RETURNING id; ";
        return db.queryForObject(sql, new Object[]{obj.getCi(), obj.getFirst_lastename(), obj.getGender(), obj.getName(), obj.getSecond_lastname()}, Integer.class);
    }

    @Override
    public boolean updateById(person persona, Long id){
        sql = "UPDATE person set ci = ?, first_lastename = ?, gender = ?,name = ?, second_lastname = ? where id = ?;";
        return db.update(sql,persona.getCi(), persona.getFirst_lastename(),
                persona.getGender(), persona.getName(),
                persona.getSecond_lastname(), id)>0;
    }

    @Override
    public boolean deleteById(Long id){
        sql = "UPDATE person SET status = false WHERE id=?;";
        return db.update(sql, id)>0;
    }
}
