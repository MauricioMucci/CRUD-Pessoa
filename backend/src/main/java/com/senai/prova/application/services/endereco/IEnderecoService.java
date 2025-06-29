package com.senai.prova.application.services.endereco;

import com.senai.prova.domain.entities.Endereco;
import com.senai.prova.domain.entities.Pessoa;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

/**
 * @author MauricioMucci
 */
@Validated
public interface IEnderecoService {

    Optional<Endereco> lookupEnderecoByPessoa(@Valid Pessoa pessoa);
}
