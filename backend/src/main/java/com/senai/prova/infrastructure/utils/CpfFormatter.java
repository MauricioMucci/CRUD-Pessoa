package com.senai.prova.infrastructure.utils;

import lombok.RequiredArgsConstructor;

/**
 * @author MauricioMucci
 */
@RequiredArgsConstructor
public class CpfFormatter {

    private final String cpf;

    public String format() {
        return this.cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    }

    public String unformat() {
        return this.cpf.replaceAll("[^0-9]", "");
    }
}
