package com.senai.prova.infrastructure.exceptions;

/**
 * @author MauricioMucci
 */
public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }
}
