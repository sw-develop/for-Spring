package com.study.jwttutorial.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/*
Login 시 사용할 DTO
 */

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

    @NotNull
    @Size(min = 3, max = 50) //validation
    private String name;

    @NotNull
    @Size(min = 3, max = 100)
    private String password;
}
