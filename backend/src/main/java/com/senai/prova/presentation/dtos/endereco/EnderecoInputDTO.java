package com.senai.prova.presentation.dtos.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * @author MauricioMucci
 */
public record EnderecoInputDTO(
        @NotNull Integer cep,
        @NotBlank String rua,
        @NotNull Integer numero,
        @NotBlank String cidade,
        @NotBlank String estado
) {

    public EnderecoInputDTO(ViaCepDTO viaCepDTO, Integer numero) {
        this(
                cleanAndParseCep(viaCepDTO.cep()),
                viaCepDTO.rua(),
                numero,
                viaCepDTO.cidade(),
                viaCepDTO.estado()
        );
    }

    private static @NotNull Integer cleanAndParseCep(String cep) {
        try {
            return Integer.valueOf(cep.replaceAll("[^0-9]", ""));
        } catch (NumberFormatException e) {
            return Integer.valueOf("0");
        }
    }
}
