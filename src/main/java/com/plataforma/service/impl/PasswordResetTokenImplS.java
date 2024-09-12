package com.plataforma.service.impl;

import com.plataforma.model.plataforma.SystemsUser;
import com.plataforma.repository.PasswordResetTokenR;
import com.plataforma.service.PasswordResetTokenS;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class PasswordResetTokenImplS implements PasswordResetTokenS {
    private final PasswordResetTokenR passwordResetTokenR;

    @Override
    public String createToken(SystemsUser user) {
        Random random = new Random();
        String token = String.valueOf(1000 + random.nextInt(9000));
        LocalDateTime now = LocalDateTime.now();
        passwordResetTokenR.updateCodeCell(token, now, now.plusMinutes(5), user.getId());
        return token;
    }

    @Override
    public SystemsUser verifyToken(String token) {
        SystemsUser user = passwordResetTokenR.findByCodeCell(token);
        if (user == null || user.getDateEndVerification().isBefore(LocalDateTime.now())) {
            return null;
        }
        return user;
    }

}
