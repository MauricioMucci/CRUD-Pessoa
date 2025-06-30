package com.senai.prova.application.services.endereco;

import com.senai.prova.domain.entities.Endereco;
import com.senai.prova.domain.entities.Pessoa;
import com.senai.prova.domain.repositories.EnderecoRepository;
import com.senai.prova.infrastructure.components.ViaCepClient;
import com.senai.prova.infrastructure.exceptions.BadRequestException;
import com.senai.prova.presentation.dtos.endereco.EnderecoDTO;
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
    public Optional<EnderecoDTO> getEnderecoByCep(String cep) {
        try {
            return viaCepClient.getViaCepDTO(cep).map(EnderecoDTO::new);
        } catch (IOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void create(Pessoa pessoa, EnderecoDTO enderecoDTO) {
        Optional<EnderecoDTO> maybeEndereco = this.getEnderecoByCep(String.valueOf(enderecoDTO.cep()));
        if (maybeEndereco.isEmpty()) {
            throw new BadRequestException("O CEP informado é inválido");
        }
        enderecoRepository.save(new Endereco(pessoa, enderecoDTO));
    }

    @Override
    public void update(Pessoa pessoa, EnderecoDTO enderecoDTO) {
        Optional<Endereco> maybeEndereco = enderecoRepository.findByPessoa(pessoa);
        if (maybeEndereco.isEmpty()) {
            throw new ServiceException("Pessoa salva sem endereço associado.");
        }
        Optional<EnderecoDTO> maybeEnderecoDTO = this.getEnderecoByCep(String.valueOf(enderecoDTO.cep()));
        if (maybeEnderecoDTO.isEmpty()) {
            throw new BadRequestException("O CEP informado é inválido");
        }

        Endereco endereco = maybeEndereco.get();
        endereco.setCep(enderecoDTO.cep());
        endereco.setNumero(enderecoDTO.numero());
        endereco.setRua(enderecoDTO.rua());
        endereco.setCidade(enderecoDTO.cidade());
        endereco.setEstado(enderecoDTO.estado());

        enderecoRepository.save(endereco);
    }
}
