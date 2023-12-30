package com.faby.hostfully.technical_test.controllers.advice;

import com.faby.hostfully.technical_test.domain.dtos.HostfullyExceptionResponse;
import com.faby.hostfully.technical_test.domain.exceptions.HostfullyTechnicalTestException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.RollbackException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class ControllerErrorHandlerAdvice {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public HostfullyExceptionResponse onConstraintViolationException(ConstraintViolationException e) {
        log.error("", e);
        HostfullyExceptionResponse response = new HostfullyExceptionResponse();
        for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
            response.getMessages()
                    .add(String.format("%s: %s", violation.getPropertyPath().toString(), violation.getMessage()));
        }
        return response;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public HostfullyExceptionResponse onHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("", e);
        HostfullyExceptionResponse response = new HostfullyExceptionResponse();
        if (e.getCause() instanceof InvalidFormatException ee) {
            response.getMessages().add(String.format("%s: %s", ee.getPathReference(), ee.getMessage()));
        } else {
            response.getMessages().add(e.getMessage());
        }

        return response;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public HostfullyExceptionResponse onConstraintViolationException(MethodArgumentNotValidException e) {
        log.error("", e);
        HostfullyExceptionResponse response = new HostfullyExceptionResponse();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            response.getMessages().add(String.format("%s: %s", fieldError.getField(), fieldError.getDefaultMessage()));
        }
        return response;
    }

    @ExceptionHandler(TransactionSystemException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public HostfullyExceptionResponse onTransactionSystemException(TransactionSystemException e) {
        if (e.getCause() instanceof RollbackException
                && e.getCause().getCause() instanceof ConstraintViolationException cve) {
            return onConstraintViolationException(cve);
        } else {
            return onRuntimeException(e);
        }
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ResponseBody
    public HostfullyExceptionResponse onEntityNotFoundException(EntityNotFoundException e) {
        log.warn("", e);
        HostfullyExceptionResponse response = new HostfullyExceptionResponse();
        response.getMessages().add(e.getMessage());
        return response;
    }

    @ExceptionHandler(HostfullyTechnicalTestException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public HostfullyExceptionResponse onHostfullyException(HostfullyTechnicalTestException e) {
        log.warn("", e);
        HostfullyExceptionResponse response = new HostfullyExceptionResponse();
        response.getMessages().add(e.getMessage());
        return response;
    }

    @ExceptionHandler({RuntimeException.class, Exception.class})
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public HostfullyExceptionResponse onRuntimeException(RuntimeException e) {
        log.error("", e);
        HostfullyExceptionResponse response = new HostfullyExceptionResponse();
        response.getMessages().add(e.getMessage());
        return response;
    }

}
