package com.faby.hostfully.technical_test.domain.dtos.requests;

import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBookingRequest {
    @NotNull
    private Long id;
    @NotNull
    @FutureOrPresent
    private LocalDate startDate;
    @NotNull
    @FutureOrPresent
    private LocalDate endDate;
    @NotEmpty
    private Set<@Valid UpdateBookingGuest> guests = new HashSet<>();
}
