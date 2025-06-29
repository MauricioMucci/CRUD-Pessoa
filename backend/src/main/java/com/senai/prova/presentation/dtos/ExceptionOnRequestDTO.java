package com.senai.prova.presentation.dtos;

/**
 * @author MauricioMucci
 */
public record ExceptionOnRequestDTO(String message, String exception) {

    public ExceptionOnRequestDTO(Exception ex) {
        this(ex.getMessage(), ex.getClass().getSimpleName());
    }
}
