package com.senai.prova.application.services.endereco;

import com.senai.prova.domain.entities.Endereco;
import com.senai.prova.domain.entities.Pessoa;
import com.senai.prova.domain.repositories.EnderecoRepository;
import com.senai.prova.infrastructure.components.ViaCepClient;
import com.senai.prova.presentation.dtos.acao.CreatePessoaDTO;
import com.senai.prova.presentation.dtos.endereco.EnderecoDTO;
import com.senai.prova.presentation.dtos.endereco.ViaCepDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.io.IOException;
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
    private final ViaCepClient viaCepClient;

    @Override
    public Optional<Endereco> lookupEnderecoByPessoa(@Valid Pessoa pessoa) {
        return enderecoRepository.findByPessoa(pessoa);
    }

    @Override
    public Optional<ViaCepDTO> getEnderecoByCep(String cep) {
        try {
            return viaCepClient.getViaCepDTO(cep);
        } catch (IOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void create(Pessoa pessoa, EnderecoDTO enderecoDTO) {
        enderecoRepository.save(new Endereco(pessoa, enderecoDTO));
    }
}
