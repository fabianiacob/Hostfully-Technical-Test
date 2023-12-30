package com.faby.hostfully.technical_test.domain.dtos.requests;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticateRequest {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;

}
