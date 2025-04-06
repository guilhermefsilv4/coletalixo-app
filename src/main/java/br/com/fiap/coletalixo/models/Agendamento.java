package br.com.fiap.coletalixo.models;

import br.com.fiap.coletalixo.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_agendamento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Agendamento {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_AGENDAMENTOS"
    )
    @SequenceGenerator(
            name = "SEQ_AGENDAMENTOS",
            sequenceName = "SEQ_AGENDAMENTOS",
            allocationSize = 1
    )
    private Integer id;
    @Column(name = "data_agendamento")
    private LocalDate dataAgendamento;
    @Column(name = "data_coleta")
    private LocalDateTime dataColeta;
    private String email;
    private String localizacao;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "data_criacao")
    @CreationTimestamp
    private Instant dataCriacao;
    @Column(name = "data_atualizacao")
    @UpdateTimestamp
    private Instant dataAtualizacao;

    @PrePersist
    private void prePersist() {
        status = Status.PENDENTE;
    }
}