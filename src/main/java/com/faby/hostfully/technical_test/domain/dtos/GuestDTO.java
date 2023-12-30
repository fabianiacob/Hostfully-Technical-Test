package com.faby.hostfully.technical_test.domain.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuestDTO {
    @NotNull
    private Long id;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
}
