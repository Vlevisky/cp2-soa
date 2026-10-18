package br.com.fiap.checkpoint2.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Instant;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record ErroResposta(
        Instant instante,
        int status,
        String erro,
        String mensagem,
        String caminho,
        Map<String, String> campos) {
}
