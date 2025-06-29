package com.senai.prova.infrastructure.utils;

import lombok.RequiredArgsConstructor;

/**
 * @author MauricioMucci
 */
@RequiredArgsConstructor
public class CepFormatter {

    private final String cep;

    public String format() {
        return this.cep.replaceAll("(\\d{5})(\\d{3})", "$1-$2");
    }

    public String unformat() {
        return this.cep.replaceAll("[^0-9]", "");
    }
}
