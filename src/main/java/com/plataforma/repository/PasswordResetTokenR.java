package com.plataforma.repository;

import com.plataforma.model.plataforma.SystemsUser;

import java.time.LocalDateTime;

public interface PasswordResetTokenR {
    boolean updateCodeCell(String token, LocalDateTime nowStart, LocalDateTime nowEnd, Long id);

    SystemsUser findByCodeCell(String token);
}
