package com.dac.cmseventos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dac.cmseventos.model.Evento;
import com.dac.cmseventos.model.input.EventoInput;
import com.dac.cmseventos.repository.EventoRepository;
import com.dac.cmseventos.service.EventoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/eventos")
public class EventoController {
    
    @Autowired
    private EventoService eventoService;

    @Autowired
    private EventoRepository eventoRepository;

    @GetMapping
    public ResponseEntity<List<Evento>> listar() {
        List<Evento> eventos = eventoRepository.findAll();
        return ResponseEntity.ok(eventos);
    }

    @PostMapping
    public ResponseEntity<Evento> salvar(@RequestBody @Valid EventoInput eventoInput) {
        Evento eventoSalvo = eventoService.salvar(toDomain(eventoInput));
        return ResponseEntity.ok(eventoSalvo);
    }

    @GetMapping("/{idEvento}")
    public ResponseEntity<Evento> buscar(@PathVariable Long idEvento) {
        Evento evento = eventoService.buscarOuFalhar(idEvento);
        return ResponseEntity.ok(evento);
    }

    @PutMapping("/{idEvento}")
    public ResponseEntity<Evento> atualizar(@PathVariable Long idEvento, @RequestBody EventoInput eventoInput) {
        Evento eventoAtual = eventoService.buscarOuFalhar(idEvento);

        copyToDomainObject(eventoInput, eventoAtual);
        
        eventoAtual = eventoService.salvar(eventoAtual);
        
        return ResponseEntity.ok(eventoAtual);
    }

    @DeleteMapping("/{idEvento}")
    public ResponseEntity<Void> excluir(@PathVariable Long idEvento) {
        eventoService.excluir(idEvento);
        return ResponseEntity.noContent().build();
    }

    /**
     * Converte um EventoInput para um Evento
     * @param eventoInput
     * @return Evento
     */
    private Evento toDomain(EventoInput eventoInput) {
        Evento evento = new Evento();
        evento.setNome(eventoInput.getNome());
        evento.setSigla(eventoInput.getSigla());
        evento.setDescricao(eventoInput.getDescricao());
        return evento;
    }

    /**
     * Copia os dados de um EventoInput para um Evento
     * @param eventoInput
     * @param evento
     * @return
     */
    private Evento copyToDomainObject(EventoInput eventoInput, Evento evento) {

        if (eventoInput.getNome() != null) {
            evento.setNome(eventoInput.getNome());
        }

        if (eventoInput.getSigla() != null) {
            evento.setSigla(eventoInput.getSigla());
        }

        if (eventoInput.getDescricao() != null) {
            evento.setDescricao(eventoInput.getDescricao());
        }

        return evento;
    }

}
