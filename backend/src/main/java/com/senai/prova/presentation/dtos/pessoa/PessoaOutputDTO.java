package com.senai.prova.presentation.dtos.pessoa;

import com.senai.prova.domain.entities.Pessoa;
import com.senai.prova.infrastructure.constraints.EmailConstraint;
import com.senai.prova.infrastructure.constraints.NomeConstraint;
import com.senai.prova.infrastructure.utils.CpfFormatter;
import com.senai.prova.presentation.dtos.endereco.EnderecoDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.time.Instant;
import java.time.LocalDate;

/**
 * @author MauricioMucci
 */
public record PessoaOutputDTO(
        @NomeConstraint String nome,
        LocalDate nascimento,
        @CPF String cpf,
        @EmailConstraint String email,
        @Valid EnderecoDTO endereco,
        @NotNull Instant dataHoraInclusaoRegistro,
        @NotNull Instant dataHoraUltimaAlteracaoRegistro
) {

    public PessoaOutputDTO(Pessoa pessoa, EnderecoDTO endereco) {
        this(
                pessoa.getNome(),
                pessoa.getNascimento(),
                new CpfFormatter(pessoa.getCpf()).format(),
                pessoa.getEmail(),
                endereco,
                pessoa.getCriacaoRegistro(),
                pessoa.getAlteracaoRegistro()
        );
    }
}
