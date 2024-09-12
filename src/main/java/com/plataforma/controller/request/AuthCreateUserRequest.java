package com.plataforma.controller.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record AuthCreateUserRequest(@NotBlank String username, @NotBlank String password,
                                    @NotBlank String email,
                                    @NotBlank String alias,
                                    @NotBlank String cell,
                                    String codeCell,
                                    LocalDateTime dateStartVerification,
                                    LocalDateTime dateEndVerification,
                                    @Valid AuthCreateRoleRequest roleRequest,
                                    @NotBlank String ci,
                                    @NotBlank String firstLastName,
                                    @NotBlank String secondLastName,
                                    @NotBlank String name,
                                    @NotBlank String gender
                                    ) {
}
