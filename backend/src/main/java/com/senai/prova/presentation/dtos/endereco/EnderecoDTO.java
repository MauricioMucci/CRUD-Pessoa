package com.senai.prova.presentation.dtos.endereco;

import com.senai.prova.domain.entities.Endereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * @author MauricioMucci
 */
public record EnderecoDTO(
        @NotNull Integer cep,
        @NotBlank String rua,
        @NotNull Integer numero,
        @NotBlank String cidade,
        @NotBlank String estado
) {

    public EnderecoDTO(ViaCepDTO viaCepDTO, Integer numero) {
        this(
                cleanAndParseCep(viaCepDTO.cep()),
                viaCepDTO.rua(),
                numero,
                viaCepDTO.cidade(),
                viaCepDTO.estado()
        );
    }

    public EnderecoDTO(Endereco endereco) {
        this(
                endereco.getCep(),
                endereco.getRua(),
                endereco.getNumero(),
                endereco.getCidade(),
                endereco.getEstado()
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
