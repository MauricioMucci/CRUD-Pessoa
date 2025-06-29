package com.senai.prova.infrastructure.handlers;

import com.senai.prova.presentation.dtos.BadRequestDTO;
import jakarta.validation.ConstraintViolationException;
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
    public ResponseEntity<BadRequestDTO> handleBadRequest(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BadRequestDTO(ex));
    }
}
