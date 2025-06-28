package com.senai.prova.domain.repositories;

import com.senai.prova.domain.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    Optional<Pessoa> findByCpf(String cpf);
}
