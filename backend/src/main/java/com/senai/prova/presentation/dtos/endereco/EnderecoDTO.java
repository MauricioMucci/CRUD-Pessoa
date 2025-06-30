package com.senai.prova.presentation.dtos.endereco;

import com.senai.prova.domain.entities.Endereco;
import com.senai.prova.infrastructure.utils.CepFormatter;
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

    public EnderecoDTO(Endereco endereco) {
        this(
                endereco.getCep(),
                endereco.getRua(),
                endereco.getNumero(),
                endereco.getCidade(),
                endereco.getEstado()
        );
    }

    public EnderecoDTO(ViaCepDTO viaCepDTO) {
        this(
                new CepFormatter(viaCepDTO.cep()).parse(),
                viaCepDTO.rua(),
                0,
                viaCepDTO.cidade(),
                viaCepDTO.estado()
        );
    }
}
