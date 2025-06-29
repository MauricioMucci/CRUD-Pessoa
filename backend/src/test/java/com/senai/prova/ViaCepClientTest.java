package com.senai.prova;

import com.senai.prova.infrastructure.components.ViaCepClient;
import com.senai.prova.presentation.dtos.endereco.ViaCepDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author MauricioMucci
 */
@SpringBootTest
public class ViaCepClientTest {

    @Autowired
    private ViaCepClient viaCepClient;

    @Test
    void testViaCepDTOPorCepValido() throws Exception {
        String cepValido = "96085470"; // Exemplo de CEP para Pelotas/RS - https://www.4devs.com.br/gerador_de_cep

        Optional<ViaCepDTO> maybeDTO = viaCepClient.getViaCepDTO(cepValido);
        ViaCepDTO viaCepDTO = maybeDTO.orElse(null);

        assertNotNull(viaCepDTO, "O dto não deve ser nulo para um CEP válido.");
        assertEquals("96085-470", viaCepDTO.cep());
        assertEquals("Avenida Domingos José de Almeida", viaCepDTO.rua());
        assertEquals("Pelotas", viaCepDTO.cidade());
        assertEquals("Rio Grande do Sul", viaCepDTO.estado());
    }

    @Test
    void testViaCepDTOPorCepInvalido() throws Exception {
        String cepInvalido = "00000000";

        Optional<ViaCepDTO> maybeDTO = viaCepClient.getViaCepDTO(cepInvalido);
        ViaCepDTO viaCepDTO = maybeDTO.orElse(null);

        assertNull(viaCepDTO, "O dto não deve existir.");
    }

    @Test
    void testViaCepDTOPorRequestInvalido() throws Exception {
        String cepInvalido = "0";

        Optional<ViaCepDTO> maybeDTO = viaCepClient.getViaCepDTO(cepInvalido);
        ViaCepDTO viaCepDTO = maybeDTO.orElse(null);

        assertNull(viaCepDTO, "O dto não deve existir.");
    }

}
