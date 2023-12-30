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

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookingGuest {
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
}
