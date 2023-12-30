package com.faby.hostfully.technical_test.domain.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlockDTO {
    @NotNull
    private Long id;
    @NotNull
    private LocalDate creationDate;
    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;
}
