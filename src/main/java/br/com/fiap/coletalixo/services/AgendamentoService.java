package br.com.fiap.coletalixo.services;

import br.com.fiap.coletalixo.config.security.AuthenticationData;
import br.com.fiap.coletalixo.dto.AgendamentoExibicaoDTO;
import br.com.fiap.coletalixo.dto.CadastroAgendamentoDTO;
import br.com.fiap.coletalixo.enums.Status;
import br.com.fiap.coletalixo.exception.AgendamentoConcluidoException;
import br.com.fiap.coletalixo.exception.AgendamentoNotFound;
import br.com.fiap.coletalixo.models.Agendamento;
import br.com.fiap.coletalixo.repositories.AgendamentoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private AuthenticationData authenticationData;

    public List<AgendamentoExibicaoDTO> findAllByEmail() {
        String email = authenticationData.getEmail();
        return agendamentoRepository.findAllByEmail(email).stream().map(AgendamentoExibicaoDTO::new).toList();
    }

    public List<AgendamentoExibicaoDTO> findAll() {
        return agendamentoRepository.findAll().stream().map(AgendamentoExibicaoDTO::new).toList();
    }

    public AgendamentoExibicaoDTO findById(Integer id) {
        Agendamento agendamento = findAgendamentoById(id);
        return new AgendamentoExibicaoDTO(agendamento);
    }

    public AgendamentoExibicaoDTO save(CadastroAgendamentoDTO agendamentoDTO) {
        Agendamento agendamento = new Agendamento();

        BeanUtils.copyProperties(agendamentoDTO, agendamento);
        agendamento.setEmail(authenticationData.getEmail());
        return new AgendamentoExibicaoDTO(agendamentoRepository.save(agendamento));
    }

    public AgendamentoExibicaoDTO update(@Valid CadastroAgendamentoDTO agendamentoDTO) {
        Agendamento agendamento = findAgendamentoById(agendamentoDTO.id());

        if (agendamento.getDataColeta() != null || agendamento.getStatus() == Status.CONCLUIDO) {
            throw new AgendamentoConcluidoException("Não é possível alterar um agendamento já concluido");
        }
        agendamento.setDataAgendamento(agendamentoDTO.dataAgendamento());
        agendamento.setLocalizacao(agendamentoDTO.localizacao());
        return new AgendamentoExibicaoDTO(agendamentoRepository.save(agendamento));
    }

    public AgendamentoExibicaoDTO cancelarAgendamento(Integer id) {
        Agendamento agendamento = findAgendamentoById(id);
        agendamento.setStatus(Status.CANCELADO);
        return new AgendamentoExibicaoDTO(agendamentoRepository.save(agendamento));
    }

    public void deleteAgendamento(Integer id) {
        Agendamento agendamento = findAgendamentoById(id);
        agendamentoRepository.delete(agendamento);
    }

    private Agendamento findAgendamentoById(Integer id) {
        String email = authenticationData.getEmail();
        Optional<Agendamento> agendamento;

        if (authenticationData.isAdmin()) {
            agendamento = agendamentoRepository.findById(id);
        } else {
            agendamento = agendamentoRepository.findByIdAndEmail(id, email);
        }

        return agendamento.orElseThrow(() -> new AgendamentoNotFound("Esse agendamento não existe"));
    }
}
