package com.plataforma.repository.impl;

import com.plataforma.model.plataforma.Permission;
import com.plataforma.repository.PermissionR;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PermissionImplR implements PermissionR {
    private final JdbcTemplate db;
    private String sql;


    @Override
    public List<Permission> findAll() {
        sql = "SELECT * FROM \"permission\" WHERE status = true;";
        return db.query(sql, BeanPropertyRowMapper.newInstance(Permission.class));
    }

    @Override
    public Permission getById(Integer id) {
        sql = "SELECT * FROM \"permission\" WHERE id = ?;";
        return (Permission) db.query(sql, BeanPropertyRowMapper.newInstance(Permission.class)) ;
    }

    @Override
    public Integer save(Permission obj) {
        sql = "INSERT INTO \"permission\"(description,\"name\") VALUES (?,?) returning id;";
        return db.queryForObject(sql, new Object[]{obj.getDescription(),obj.getName()},Integer.class);
    }

    @Override
    public boolean deleteById(Integer id) {
        sql = "UPDATE \"permission\" SET status = false  WHERE id = ?;";
        return db.update(sql, id)>0;
    }
}
