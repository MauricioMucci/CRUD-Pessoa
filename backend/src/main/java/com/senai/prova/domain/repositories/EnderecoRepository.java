package com.senai.prova.domain.repositories;

import com.senai.prova.domain.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
}
