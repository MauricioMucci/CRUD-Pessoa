package com.senai.prova.presentation.dtos.acao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * @author MauricioMucci
 */
public record CreatePessoaDTO(@NotNull Integer idPessoa, @NotBlank String mensagem) {}
