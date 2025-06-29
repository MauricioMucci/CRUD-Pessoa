package com.senai.prova.application.services.endereco;

import com.senai.prova.domain.entities.Endereco;
import com.senai.prova.domain.entities.Pessoa;
import com.senai.prova.presentation.dtos.endereco.EnderecoDTO;
import com.senai.prova.presentation.dtos.endereco.ViaCepDTO;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

/**
 * @author MauricioMucci
 */
@Validated
public interface IEnderecoService {

    Optional<Endereco> lookupEnderecoByPessoa(@Valid Pessoa pessoa);

    Optional<ViaCepDTO> getEnderecoByCep(String cep);

    void create(@Valid Pessoa pessoa, @Valid EnderecoDTO enderecoDTO);
}
