package com.senai.prova.presentation.dtos;

/**
 * @author MauricioMucci
 */
public record BadRequestDTO(String message, String exception) {

    public BadRequestDTO(Exception ex) {
        this(ex.getMessage(), ex.getClass().getSimpleName());
    }
}
