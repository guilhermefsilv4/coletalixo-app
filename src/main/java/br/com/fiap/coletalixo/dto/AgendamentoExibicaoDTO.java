package br.com.fiap.coletalixo.dto;

import br.com.fiap.coletalixo.enums.Status;
import br.com.fiap.coletalixo.models.Agendamento;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record AgendamentoExibicaoDTO(Integer id,
                                     String email,
                                     LocalDate dataAgendamento,
                                     LocalDateTime dataColeta,
                                     Instant dataCriacao,
                                     Instant dataAtualizacao,
                                     String localizacao,
                                     Status status) {

    public AgendamentoExibicaoDTO(Agendamento agendamento) {
        this(
                agendamento.getId(),
                agendamento.getEmail(),
                agendamento.getDataAgendamento(),
                agendamento.getDataColeta(),
                agendamento.getDataCriacao(),
                agendamento.getDataAtualizacao(),
                agendamento.getLocalizacao(),
                agendamento.getStatus());
    }
}
