package com.senai.prova.infrastructure.handlers;

import com.senai.prova.infrastructure.exceptions.BadRequestException;
import com.senai.prova.infrastructure.exceptions.NotFoundException;
import com.senai.prova.presentation.dtos.ExceptionOnRequestDTO;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author MauricioMucci
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {
            ConstraintViolationException.class,
            BadRequestException.class
    })
    public ResponseEntity<ExceptionOnRequestDTO> handleBadRequest(Exception ex) {
        log.warn("Request com problemas nos parametros: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionOnRequestDTO(ex));
    }

    @ExceptionHandler(value = {
            ServiceException.class
    })
    public ResponseEntity<ExceptionOnRequestDTO> handleInternalServerError(Exception ex) {
        log.error("Erro interno no servidor: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ExceptionOnRequestDTO(ex));
    }

    @ExceptionHandler(value = {
            NotFoundException.class
    })
    public ResponseEntity<ExceptionOnRequestDTO> handleNotFound(Exception ex) {
        log.info(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionOnRequestDTO(ex));
    }
}
