package com.faby.hostfully.technical_test.domain.dtos.requests;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBlockRequest {
    @NotNull
    private Long propertyId;
    @NotNull
    @FutureOrPresent
    private LocalDate startDate;
    @NotNull
    @FutureOrPresent
    private LocalDate endDate;
}
