package com.senai.prova.presentation.dtos.pessoa;

import com.senai.prova.presentation.dtos.endereco.EnderecoInputDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.time.Instant;

/**
 * @author MauricioMucci
 */
public record PessoaOutputDTO(
        @NotBlank String nome,
        @NotNull Instant nascimento,
        @CPF String cpf,
        @NotNull @Email String email,
        @Valid EnderecoInputDTO endereco,
        @NotNull Instant dataHoraInclusaoRegistro,
        @NotNull Instant dataHoraUltimaAlteracaoRegistro
) {
}
