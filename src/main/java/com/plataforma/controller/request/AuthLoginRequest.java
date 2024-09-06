package com.plataforma.controller.request;

import jakarta.validation.constraints.NotBlank;

public record AuthLoginRequest(@NotBlank String username, String password) {
}
