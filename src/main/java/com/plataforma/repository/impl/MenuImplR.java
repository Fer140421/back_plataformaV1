package com.plataforma.repository.impl;

import com.plataforma.model.plataforma.Menu;
import com.plataforma.repository.MenuR;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MenuImplR implements MenuR {
    private final JdbcTemplate db;
    private String sql;

    @Override
    public List<Menu> findAll() {
        sql = "SELECT * FROM menu WHERE status = true;";
        return db.query(sql, BeanPropertyRowMapper.newInstance(Menu.class));
    }

    @Override
    public Menu findById(Integer id) {
        sql = "SELECT * FROM menu WHERE id = ?";
        return db.queryForObject(sql, BeanPropertyRowMapper.newInstance(Menu.class), id);
    }

    @Override
    public boolean save(Menu menu) {
        sql = "INSERT INTO menu (name, description) VALUES (?, ?)";
        return db.update(sql, menu.getName(), menu.getDescription()) > 0;
    }

    @Override
    public boolean update(Menu menu, Integer id) {
        sql = "UPDATE menu SET name = ?, description = ? WHERE id = ?";
        return db.update(sql, menu.getName(), menu.getDescription(), id) > 0;
    }

    @Override
    public boolean deleteById(Integer id) {
        sql = "UPDATE menu SET status = false WHERE id = ?";
        return db.update(sql, id) > 0;
    }

}
