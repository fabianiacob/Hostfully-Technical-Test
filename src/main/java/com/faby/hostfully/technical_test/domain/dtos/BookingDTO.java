package com.faby.hostfully.technical_test.domain.dtos;

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
public class BookingDTO {
    @NotNull
    private Long id;
    @NotNull
    private LocalDate creationDate;
    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;
    @NotEmpty
    private Set<GuestDTO> guests = new HashSet<>();
    @NotNull
    private Boolean cancelled;
}
