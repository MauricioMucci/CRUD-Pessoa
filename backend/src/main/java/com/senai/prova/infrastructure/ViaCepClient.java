package com.senai.prova.infrastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.senai.prova.presentation.dtos.endereco.ViaCepDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author MauricioMucci
 */
@Slf4j
@Component("viaCepClient")
@RequiredArgsConstructor
public class ViaCepClient {

    private static final String BASE_URL = "https://viacep.com.br/ws/%s/json/";
    private final ObjectMapper objectMapper;

    public Optional<ViaCepDTO> getViaCepDTO(String cep) throws Exception {
        cep = cep.replaceAll("[^0-9]", "");

        HttpURLConnection connection = getHttpURLConnection(cep);

        int responseCode = connection.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            log.warn("Erro ao buscar CEP [{}] - Código de resposta HTTP [{}]", cep, responseCode);
            return Optional.empty();
        }

        return tryToGetViaCepDTO(connection);
    }

    private HttpURLConnection getHttpURLConnection(String cep) throws IOException {
        URL url = new URL(String.format(BASE_URL, cep));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        return connection;
    }

    private Optional<ViaCepDTO> tryToGetViaCepDTO(HttpURLConnection connection) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            String jsonString = response.toString();
            if (jsonString.contains("erro")) {
                log.warn("Erro ao buscar CEP: {}", jsonString);
                return Optional.empty();
            }

            return Optional.of(objectMapper.readValue(jsonString, ViaCepDTO.class));
        } catch (Exception e) {
            log.error("Erro ao transformar JSON para objeto ViaCepDTO", e);
            return Optional.empty();
        } finally {
            connection.disconnect();
        }
    }
}
