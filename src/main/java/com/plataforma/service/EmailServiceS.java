package com.plataforma.service;

public interface EmailServiceS {
    void sendPasswordResetToken(String email, String token);
}
