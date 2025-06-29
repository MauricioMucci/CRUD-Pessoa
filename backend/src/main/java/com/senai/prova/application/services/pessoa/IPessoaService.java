package com.senai.prova.application.services.pessoa;

import com.senai.prova.presentation.dtos.pessoa.PessoaOutputDTO;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

/**
 * @author MauricioMucci
 */
@Validated
public interface IPessoaService {

    Optional<PessoaOutputDTO> getPessoaByCpf(@CPF String cpf);
}
