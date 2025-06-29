package com.senai.prova.presentation.controllers;

import com.senai.prova.application.services.pessoa.IPessoaService;
import com.senai.prova.presentation.dtos.pessoa.PessoaOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
