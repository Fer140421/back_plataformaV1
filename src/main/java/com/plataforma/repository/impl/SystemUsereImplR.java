package com.plataforma.repository.impl;

import com.plataforma.model.plataforma.Permission;
import com.plataforma.model.plataforma.Rol;
import com.plataforma.model.plataforma.SystemsUser;
import com.plataforma.repository.SystemsUserR;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SystemUsereImplR implements SystemsUserR {
    private final JdbcTemplate db;
    private String sql;

    @Override
    public SystemsUser findSystemUserByUsername(String username) {
        sql = "SELECT su.* FROM systems_user su WHERE su.username = ? AND status = true;";
        return db.queryForObject(sql, BeanPropertyRowMapper.newInstance(SystemsUser.class), username);
    }

    @Override
    public List<Rol> findRolListByUser(Integer systemUserId) {
        sql = "SELECT r.* FROM rol r INNER JOIN user_rol ur ON ur.rol_id = r.id AND ur.system_user_id = ? WHERE r.status = true;";
        return db.query(sql, BeanPropertyRowMapper.newInstance(Rol.class), systemUserId);
    }
    @Override
    public List<Permission> findPermissionListByRol(Integer roleId) {
        sql = "SELECT p.* FROM permission p INNER JOIN rol_permission rp ON rp.permission_id =p.id  AND rp.rol_id  = ? WHERE p.status = true;";
        return db.query(sql, BeanPropertyRowMapper.newInstance(Permission.class), roleId);
    }
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
