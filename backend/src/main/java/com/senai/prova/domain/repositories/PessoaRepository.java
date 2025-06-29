package com.senai.prova.domain.repositories;

import com.senai.prova.domain.entities.Pessoa;
import com.senai.prova.presentation.dtos.pessoa.PessoaOutputDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    Optional<Pessoa> findByCpf(String cpf);

    @Query("""
           SELECT new com.senai.prova.presentation.dtos.pessoa.PessoaOutputDTO(
           p,
           new com.senai.prova.presentation.dtos.endereco.EnderecoDTO(e)
           )
           FROM Endereco e JOIN e.pessoa p
           """)
    List<PessoaOutputDTO> findAllDTO();

    boolean existsPessoaByCpf(String cpf);
}
