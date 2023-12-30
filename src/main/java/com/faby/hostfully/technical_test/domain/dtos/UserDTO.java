package com.faby.hostfully.technical_test.domain.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @NotNull
    private Long id;
    @NotEmpty
    private String username;
    @NotNull
    private LocalDate creationDate;
}
