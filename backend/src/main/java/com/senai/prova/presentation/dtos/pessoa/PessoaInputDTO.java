package com.senai.prova.presentation.dtos.pessoa;

import com.senai.prova.infrastructure.constraints.EmailConstraint;
import com.senai.prova.infrastructure.constraints.NomeConstraint;
import com.senai.prova.presentation.dtos.endereco.EnderecoDTO;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.br.CPF;

import java.time.Instant;
import java.time.LocalDate;

/**
 * @author MauricioMucci
 */
public record PessoaInputDTO(
        @NomeConstraint String nome,
        LocalDate nascimento,
        @CPF String cpf,
        @EmailConstraint String email,
        @Valid EnderecoDTO endereco
) {
}
