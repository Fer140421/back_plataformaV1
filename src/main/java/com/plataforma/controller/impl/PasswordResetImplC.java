package com.plataforma.controller.impl;

import com.plataforma.model.plataforma.SystemsUser;
import com.plataforma.service.EmailServiceS;
import com.plataforma.service.PasswordResetTokenS;
import com.plataforma.service.SystemsUserS;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reset/")
@RequiredArgsConstructor
public class PasswordResetImplC {
    private final SystemsUserS systemsUserS;
    private final EmailServiceS emailService;
    private final PasswordResetTokenS tokenService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("password-reset/request")
    public ResponseEntity<String> requestPasswordReset(@RequestParam String email) {
        SystemsUser user = systemsUserS.findSystemUserByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
        String token = tokenService.createToken(user);
        emailService.sendPasswordResetToken(email, token);
        return ResponseEntity.ok("Código de recuperación enviado al correo electrónico");
    }

    @PostMapping("password-reset/verify")
    public ResponseEntity<String> verifyPasswordResetToken(@RequestParam String token) {
        SystemsUser user = tokenService.verifyToken(token);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token inválido o expirado");
        }
        return ResponseEntity.ok("Código verificado. Proceda a cambiar su contraseña.");
    }

    @PostMapping("password-reset/change")
    public ResponseEntity<String> changePassword(
            @RequestParam String token,
            @RequestParam String newPassword) {
        SystemsUser user = tokenService.verifyToken(token);
        SystemsUser userRecovery = systemsUserS.findById(user.getId());
        user.setId(userRecovery.getId());
        user.setAlias(userRecovery.getUsername());
        user.setEmail(userRecovery.getEmail());
        user.setUsername(userRecovery.getUsername());
        user.setCell(userRecovery.getCell());
        user.setStatus(true);
        user.setIsEnabled(true);
        user.setAccountNoExpired(true);
        user.setAccountNoLocked(true);
        user.setCredentialNoExipred(true);
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setCodeCell(null);
        user.setDateStartVerification(null);
        user.setDateEndVerification(null);
        systemsUserS.update(user);
        return ResponseEntity.ok("Contraseña actualizada exitosamente");
    }

}
