package com.senai.prova.application.services.endereco;

import com.senai.prova.domain.entities.Endereco;
import com.senai.prova.domain.entities.Pessoa;
import com.senai.prova.domain.repositories.EnderecoRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

/**
 * @author MauricioMucci
 */
@Slf4j
@Validated
@RequiredArgsConstructor
@Service
public class EnderecoService implements IEnderecoService {

    private final EnderecoRepository enderecoRepository;

    @Override
    public Optional<Endereco> lookupEnderecoByPessoa(@Valid Pessoa pessoa) {
        return enderecoRepository.findByPessoa(pessoa);
    }
}
