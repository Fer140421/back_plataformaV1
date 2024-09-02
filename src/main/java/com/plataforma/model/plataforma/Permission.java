package com.plataforma.model.plataforma;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Permission {
    private Integer id;
    private String description;
    private String name;
    private Boolean status;
}
