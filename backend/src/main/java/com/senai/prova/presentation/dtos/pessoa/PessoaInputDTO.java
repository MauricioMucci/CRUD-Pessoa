package com.senai.prova.presentation.dtos.pessoa;

import com.senai.prova.infrastructure.constraints.EmailConstraint;
import com.senai.prova.infrastructure.constraints.NomeConstraint;
import com.senai.prova.presentation.dtos.endereco.EnderecoDTO;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.br.CPF;

import java.time.Instant;

  /**
 * @author MauricioMucci
 */
public record PessoaInputDTO(
        @NomeConstraint String nome,
        Instant nascimento,
        @CPF String cpf,
        @EmailConstraint String email,
        @Valid EnderecoDTO endereco
) {
}
