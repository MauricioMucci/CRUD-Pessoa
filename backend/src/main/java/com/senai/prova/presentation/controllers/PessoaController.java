package com.senai.prova.presentation.controllers;

import com.senai.prova.application.services.pessoa.IPessoaService;
import com.senai.prova.presentation.dtos.acao.CreatePessoaDTO;
import com.senai.prova.presentation.dtos.acao.DeletePessoaDTO;
import com.senai.prova.presentation.dtos.pessoa.PessoaInputDTO;
import com.senai.prova.presentation.dtos.pessoa.PessoaOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author MauricioMucci
 */
@RequiredArgsConstructor
@Controller
@RequestMapping("/pessoa")
public class PessoaController {

    private final IPessoaService pessoaService;

    @GetMapping(value = "/cpf/{cpf}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PessoaOutputDTO> getPessoaByCpf(@PathVariable String cpf) {
        return ResponseEntity.of(pessoaService.getPessoaByCpf(cpf));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreatePessoaDTO> create(@RequestBody PessoaInputDTO pessoaDTO) {
        return ResponseEntity.ok(pessoaService.create(pessoaDTO));
    }

    @DeleteMapping(value = "/cpf/{cpf}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeletePessoaDTO> delete(@PathVariable String cpf) {
        return ResponseEntity.ok(pessoaService.delete(cpf));
    }

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PessoaOutputDTO>> getAll() {
        return ResponseEntity.ok(pessoaService.getAll());
    }
}
