package com.senai.prova.presentation.controllers;

import com.senai.prova.application.services.endereco.IEnderecoService;
import com.senai.prova.presentation.dtos.endereco.EnderecoDTO;
import com.senai.prova.presentation.dtos.endereco.ViaCepDTO;
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
@RequestMapping("/endereco")
public class EnderecoController {

    private final IEnderecoService enderecoService;

    @GetMapping(value = "/cep/{cep}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EnderecoDTO> getEnderecoByCep(@PathVariable String cep) {
        return ResponseEntity.of(enderecoService.getEnderecoByCep(cep));
    }
}
