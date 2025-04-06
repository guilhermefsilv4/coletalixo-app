package br.com.fiap.coletalixo.controllers;

import br.com.fiap.coletalixo.dto.AgendamentoExibicaoDTO;
import br.com.fiap.coletalixo.dto.CadastroAgendamentoDTO;
import br.com.fiap.coletalixo.services.AgendamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AgendamentoExibicaoDTO> getAllSchedulesForAdmin() {
        return agendamentoService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AgendamentoExibicaoDTO getScheduleById(@PathVariable("id") Integer id) {
        return agendamentoService.findById(id);
    }

    @GetMapping("/meus-agendamentos")
    @ResponseStatus(HttpStatus.OK)
    public List<AgendamentoExibicaoDTO> getUserSchedules() {
        return agendamentoService.findAllByEmail();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AgendamentoExibicaoDTO createSchedule(@RequestBody @Valid CadastroAgendamentoDTO schedule) {
        return agendamentoService.save(schedule);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public AgendamentoExibicaoDTO updateSchedule(@RequestBody @Valid CadastroAgendamentoDTO schedule) {
        return agendamentoService.update(schedule);
    }

    @PutMapping("/cancelar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AgendamentoExibicaoDTO cancelSchedule(@PathVariable("id") Integer id) {
        return agendamentoService.cancelarAgendamento(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSchedule(@PathVariable("id") Integer id) {
        agendamentoService.deleteAgendamento(id);
    }
}