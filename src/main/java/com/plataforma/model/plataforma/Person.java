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
    private String first_lastename;
    private String gender;
    private String name;
    private String second_lastname;
    private Boolean status;
}
