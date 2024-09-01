package com.plataforma.repository.impl;

import com.plataforma.model.plataforma.Rol;
import com.plataforma.repository.RoleR;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RoleImplR implements RoleR {
    private final JdbcTemplate db;
    private String sql;

    public  List<Rol> findAll() {
        sql = "SELECT * FROM rol WHERE status = true;";
        return db.query(sql, BeanPropertyRowMapper.newInstance(Rol.class));
    }

    @Override
    public Rol getById(Integer id) {
        sql = "SELECT * FROM rol WHERE id = ?;";
        return (Rol) db.query(sql, BeanPropertyRowMapper.newInstance(Rol.class));
    }

    @Override
    public Integer save(Rol obj) {
        sql = "INSERT INTO rol(description,name) VALUES(?,?) RETURNING id; ";
        return db.queryForObject(sql, new Object[]{obj.getDescription(), obj.getName()}, Integer.class);
    }

    @Override
    public boolean deleteById(Integer id) {
        sql = "UPDATE rol SET status = false  WHERE id = ?;";
        return db.update(sql, id) > 0;
    }
}
