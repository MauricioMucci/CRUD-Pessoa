package com.senai.prova.application.services.pessoa;

import com.senai.prova.presentation.dtos.acao.SavePessoaDTO;
import com.senai.prova.presentation.dtos.acao.DeletePessoaDTO;
import com.senai.prova.presentation.dtos.pessoa.PessoaInputDTO;
import com.senai.prova.presentation.dtos.pessoa.PessoaOutputDTO;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * @author MauricioMucci
 */
@Validated
public interface IPessoaService {

    PessoaOutputDTO getPessoaByCpf(@CPF String cpf);

    DeletePessoaDTO delete(@CPF String cpf);

    SavePessoaDTO create(@Valid PessoaInputDTO pessoaDTO);

    List<PessoaOutputDTO> getAll();

    SavePessoaDTO update(@CPF String cpf,@Valid PessoaInputDTO pessoaDTO);
}
