package com.plataforma.model.plataforma;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Person {
    private Long id;
    private String ci;
    private String firstLastName;
    private String gender;
    private String name;
    private String secondLastName;
    private Boolean status;
}
