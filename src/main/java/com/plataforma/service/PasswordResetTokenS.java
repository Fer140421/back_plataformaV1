package com.plataforma.service;

import com.plataforma.model.plataforma.SystemsUser;

public interface PasswordResetTokenS {
    String createToken(SystemsUser user);

    SystemsUser verifyToken(String token);
}
