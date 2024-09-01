package com.plataforma.model.plataforma;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rol {
    private Integer id;
    private String name;
    private String description;
    private Boolean status;
    private String roleSecurity;
}
