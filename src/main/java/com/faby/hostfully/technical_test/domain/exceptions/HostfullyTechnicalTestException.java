package com.faby.hostfully.technical_test.domain.exceptions;

public class HostfullyTechnicalTestException extends RuntimeException {

    public HostfullyTechnicalTestException(HostfullyExceptionCode code) {
        super(code.name());
    }
}
