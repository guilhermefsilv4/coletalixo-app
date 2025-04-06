package br.com.fiap.coletalixo.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record CadastroAgendamentoDTO(
        Integer id,
        @NotNull
        @Future
        LocalDate dataAgendamento,
        @NotBlank
        @Size(min = 5, message = "tamanho deve ser de 5 caracteres")
        String localizacao
) {
}
