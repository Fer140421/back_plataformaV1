package com.plataforma.repository.impl;

import com.plataforma.model.plataforma.SystemsUser;
import com.plataforma.repository.SystemUserR;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SystemUsereImplR implements SystemUserR {
    private final JdbcTemplate db;
    private String sql;

    @Override
    public List<SystemsUser> findAll() {
        sql = "SELECT * FROM systems_user WHERE status = true;";
        return db.query(sql, BeanPropertyRowMapper.newInstance(SystemsUser.class));
    }

    @Override
    public SystemsUser getById(Integer id) {
        sql = "SELECT * FROM systems_user WHERE id = ?;";
        return db.queryForObject(sql, BeanPropertyRowMapper.newInstance(SystemsUser.class), id);
    }

    @Override
    public Integer save(SystemsUser user) {
        sql = "INSERT INTO systems_user (id,alias, email, username, password, cell, code_cell, status,date_start_verification,date_end_verification, is_enabled, account_no_expired, account_no_locked, credential_no_exipred) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id;";
        return db.queryForObject(sql,
                new Object[]{user.getId(),user.getAlias(), user.getEmail(), user.getUsername(), user.getPassword(), user.getCell(), user.getCodeCell(), user.getStatus(), user.getDateStarVerification(),user.getDateEndVerification(),user.getIsEnabled(), user.getAccountNoExpired(), user.getAccountNoLocked(), user.getCredentialNoExipred()},
                Integer.class);
    }

    @Override
    public boolean update(SystemsUser user) {
        sql = "UPDATE systems_user SET alias = ?, email = ?, username = ?, password = ?, cell = ?, code_cell = ?, status = ?,date_start_verification = ? , date_end_verification = ?, is_enabled = ?, account_no_expired = ?, account_no_locked = ?, credential_no_exipred = ? " +
                "WHERE id = ?;";
        return db.update(sql,
                user.getAlias(), user.getEmail(), user.getUsername(), user.getPassword(), user.getCell(), user.getCodeCell(), user.getStatus(), user.getDateStarVerification(), user.getDateEndVerification(),user.getIsEnabled(), user.getAccountNoExpired(), user.getAccountNoLocked(), user.getCredentialNoExipred(), user.getId()) > 0;
    }

    @Override
    public boolean deleteById(Integer id) {
        sql = "UPDATE systems_user SET status = false WHERE id = ?;";
        return db.update(sql, id) > 0;
    }
}
