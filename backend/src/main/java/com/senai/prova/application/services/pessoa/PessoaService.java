package com.senai.prova.application.services.pessoa;

import com.senai.prova.application.services.endereco.IEnderecoService;
import com.senai.prova.domain.entities.Pessoa;
import com.senai.prova.domain.repositories.PessoaRepository;
import com.senai.prova.infrastructure.exceptions.BadRequestException;
import com.senai.prova.infrastructure.exceptions.NotFoundException;
import com.senai.prova.infrastructure.utils.CpfFormatter;
import com.senai.prova.presentation.dtos.acao.SavePessoaDTO;
import com.senai.prova.presentation.dtos.acao.DeletePessoaDTO;
import com.senai.prova.presentation.dtos.endereco.EnderecoDTO;
import com.senai.prova.presentation.dtos.pessoa.PessoaInputDTO;
import com.senai.prova.presentation.dtos.pessoa.PessoaOutputDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
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
    public PessoaOutputDTO getPessoaByCpf(@CPF String cpf) {
        return this.lookupPessoaByCpf(cpf)
                .map(this::buildPessoaOutputDTO)
                .orElseThrow(() -> new NotFoundException(
                        String.format("CPF (%s) não está vinculado a nenhuma pessoa.", cpf)
                ));
    }

    private Optional<Pessoa> lookupPessoaByCpf(String cpf) {
        cpf = new CpfFormatter(cpf).unformat();
        log.info("Tentando buscar pessoa com [CPF {}]", cpf);
        return pessoaRepository.findByCpf(cpf);
    }

    private PessoaOutputDTO buildPessoaOutputDTO(Pessoa pessoa) {
        log.info("CPF pertecente à [{}]", pessoa);
        EnderecoDTO enderecoDTO = enderecoService.lookupEnderecoByPessoa(pessoa)
                .map(EnderecoDTO::new)
                .orElse(null);
        return new PessoaOutputDTO(pessoa, enderecoDTO);
    }

    @Transactional
    @Override
    public SavePessoaDTO create(@Valid PessoaInputDTO pessoaDTO) {
        log.info("Tentando criar pessoa com [{}]", pessoaDTO);

        String invalid = getInvalidationMessage(pessoaDTO);
        if (!invalid.isBlank()) {
            throw new BadRequestException(invalid);
        }

        Pessoa pessoa = pessoaRepository.save(new Pessoa(pessoaDTO));
        enderecoService.create(pessoa, pessoaDTO.endereco());
        return new SavePessoaDTO(
                pessoa.getId(),
                String.format("Sucesso: %s cadastrado(a)", pessoa)
        );
    }

    @Override
    public List<PessoaOutputDTO> getAll() {
        log.info("Tentando buscar todas as pessoas cadastradas");
        List<PessoaOutputDTO> pessoaList = pessoaRepository.findAllDTO();
        log.info("Total de pessoas encontradas: {}", pessoaList.size());
        return pessoaList;
    }

    @Override
    public SavePessoaDTO update(String cpf, PessoaInputDTO pessoaDTO) {
        Pessoa pessoa = this.lookupPessoaByCpf(cpf)
                .orElseThrow(() -> new NotFoundException(
                        String.format("CPF (%s) não está vinculado a nenhuma pessoa.", cpf)
                ));

        log.info("Tentando atualizar pessoa [{}]", pessoa);

        pessoa.setNome(pessoaDTO.nome());
        pessoa.setEmail(pessoaDTO.email());
        pessoa.setAlteracaoRegistro(Instant.now());

        pessoa = pessoaRepository.save(pessoa);
        enderecoService.update(pessoa, pessoaDTO.endereco());
        return new SavePessoaDTO(
                pessoa.getId(),
                String.format("Sucesso: %s atualizado(a)", pessoa)
        );

    }

    private String getInvalidationMessage(PessoaInputDTO pessoaDTO) {
        LocalDate nascimento = pessoaDTO.nascimento();
        if (nascimento != null && nascimento.isAfter(LocalDate.now())) {
            return "A data de nascimento não pode ser maior que o dia atual.";
        }
        if (isCpfAlreadyRegistered(pessoaDTO)) {
            return "CPF já cadastrado.";
        }
        return "";
    }

    private boolean isCpfAlreadyRegistered(PessoaInputDTO pessoaDTO) {
        return pessoaRepository.existsPessoaByCpf(new CpfFormatter(pessoaDTO.cpf()).unformat());
    }

    @Transactional
    @Override
    public DeletePessoaDTO delete(@CPF String cpf) {
        log.info("Tentando excluir pessoa com [CPF {}]", cpf);
        Optional<Pessoa> maybePessoa = lookupPessoaByCpf(cpf);
        if (maybePessoa.isEmpty()) {
            log.info("Pessoa não encontrada com CPF informado");
            throw new NotFoundException(String.format("Pessoa não existe com CPF informado [%s]", cpf));
        }

        Pessoa pessoa = maybePessoa.get();
        try {
            pessoaRepository.delete(pessoa);
            log.info("[{}] foi excluído(a) com sucesso", pessoa);
            return new DeletePessoaDTO(String.format("Sucesso: %s excluído(a)", pessoa.getNome()));
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
