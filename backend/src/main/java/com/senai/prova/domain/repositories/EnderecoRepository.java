package com.senai.prova.domain.repositories;

import com.senai.prova.domain.entities.Endereco;
import com.senai.prova.domain.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

    Optional<Endereco> findByPessoa(Pessoa pessoa);
}
