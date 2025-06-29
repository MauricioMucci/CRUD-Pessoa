package com.senai.prova.application.services.pessoa;

import com.senai.prova.application.services.endereco.IEnderecoService;
import com.senai.prova.domain.entities.Pessoa;
import com.senai.prova.domain.repositories.PessoaRepository;
import com.senai.prova.presentation.dtos.endereco.EnderecoDTO;
import com.senai.prova.presentation.dtos.pessoa.PessoaOutputDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.br.CPF;
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
public class PessoaService implements IPessoaService {

    private final PessoaRepository pessoaRepository;
    private final IEnderecoService enderecoService;

    @Override
    public Optional<PessoaOutputDTO> getPessoaByCpf(@CPF String cpf) {
        return this.lookupPessoaByCpf(cpf)
                .map(this::buildPessoaOutputDTO);
    }

    private Optional<Pessoa> lookupPessoaByCpf(String cpf) {
        return pessoaRepository.findByCpf(cpf);
    }

    private PessoaOutputDTO buildPessoaOutputDTO(Pessoa pessoa) {
        EnderecoDTO enderecoDTO = enderecoService.lookupEnderecoByPessoa(pessoa)
                .map(EnderecoDTO::new)
                .orElse(null);
        return new PessoaOutputDTO(pessoa, enderecoDTO);
    }
}
