package com.senai.prova.infrastructure.exceptions;

/**
 * @author MauricioMucci
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}
