package com.faby.hostfully.technical_test.domain.dtos.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBookingGuest {
    //Can be null on adding guests
    private Long id;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
}
