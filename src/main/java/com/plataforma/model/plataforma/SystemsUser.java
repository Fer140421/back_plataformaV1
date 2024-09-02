package com.plataforma.model.plataforma;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SystemsUser {
    private Integer id;
    private String alias;
    private String email;
    private String username;
    private String password;
    private String cell;
    private String codeCell;
    private Boolean status;
    private LocalDateTime dateStarVerification;
    private LocalDateTime dateEndVerification;
    private Boolean isEnabled;
    private Boolean accountNoExpired;
    private Boolean accountNoLocked;
    private Boolean credentialNoExipred;
}
