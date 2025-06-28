package com.senai.prova.presentation.dtos.acao;

import jakarta.validation.constraints.NotBlank;

/**
 * @author MauricioMucci
 */
public record DeletePessoaDTO(@NotBlank String mensagem) {}
