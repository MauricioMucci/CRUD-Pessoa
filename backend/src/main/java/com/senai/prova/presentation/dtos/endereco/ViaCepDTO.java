package com.senai.prova.presentation.dtos.endereco;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author MauricioMucci
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record ViaCepDTO(
        @JsonProperty("cep") String cep,
        @JsonProperty("logradouro") String rua,
        @JsonProperty("localidade") String cidade,
        @JsonProperty("estado") String estado
) {
}
