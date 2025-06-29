package com.senai.prova.infrastructure.utils;

import lombok.RequiredArgsConstructor;

/**
 * @author MauricioMucci
 */
@RequiredArgsConstructor
public class CpfFormatter {

    private final String cpf;

    public String format() {
        if (this.cpf == null) {
            throw new IllegalStateException("CPF não pode ser nulo");
        }
        return this.cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    }

    public String unformat() {
        if (this.cpf == null) {
            throw new IllegalStateException("CPF não pode ser nulo");
        }
        return this.cpf.replaceAll("[^0-9]", "");
    }
}
