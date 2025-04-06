package br.com.fiap.coletalixo.repositories;

import br.com.fiap.coletalixo.models.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {
    Optional<Agendamento> findByIdAndEmail(Integer id, String email);

    List<Agendamento> findAllByEmail(String email);
}
