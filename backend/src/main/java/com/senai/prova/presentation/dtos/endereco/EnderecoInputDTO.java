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
}
