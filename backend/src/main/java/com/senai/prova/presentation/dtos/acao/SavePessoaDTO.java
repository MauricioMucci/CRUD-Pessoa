package com.senai.prova.presentation.dtos.acao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * @author MauricioMucci
 */
public record SavePessoaDTO(@NotNull Long idPessoa, @NotBlank String mensagem) {}
