package com.faby.hostfully.technical_test.domain.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class HostfullyExceptionResponse {
    private List<String> messages = new ArrayList<>();
}
