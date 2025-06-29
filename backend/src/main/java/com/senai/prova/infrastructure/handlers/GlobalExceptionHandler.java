package com.senai.prova.infrastructure.handlers;

import com.senai.prova.presentation.dtos.ExceptionOnRequestDTO;
import jakarta.validation.ConstraintViolationException;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author MauricioMucci
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {
            ConstraintViolationException.class
    })
    public ResponseEntity<ExceptionOnRequestDTO> handleBadRequest(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionOnRequestDTO(ex));
    }

    @ExceptionHandler(value = {
            ServiceException.class
    })
    public ResponseEntity<ExceptionOnRequestDTO> handleInternalServerError(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ExceptionOnRequestDTO(ex));
    }
}
