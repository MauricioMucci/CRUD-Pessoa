package com.senai.prova.infrastructure.utils;

import lombok.RequiredArgsConstructor;

/**
 * @author MauricioMucci
 */
@RequiredArgsConstructor
public class CepFormatter {

    private final String cep;

    public String format() {
        if (this.cep == null) {
            throw new IllegalStateException("CEP não pode ser nulo");
        }
        return this.cep.replaceAll("(\\d{5})(\\d{3})", "$1-$2");
    }

    public String unformat() {
        if (this.cep == null) {
            throw new IllegalStateException("CEP não pode ser nulo");
        }
        return this.cep.replaceAll("[^0-9]", "");
    }

    public Integer parse() {
        if (this.cep == null) {
            throw new IllegalStateException("CEP não pode ser nulo");
        }
        return Integer.valueOf(this.cep.replaceAll("[^0-9]", ""));
    }
}
