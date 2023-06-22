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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dac.cmseventos.model.Evento;
import com.dac.cmseventos.model.input.EventoInput;
import com.dac.cmseventos.model.input.EventoInputId;
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
    public ResponseEntity<Evento> buscar(@RequestParam EventoInputId eventoInputId) {
        Evento evento = eventoService.buscarOuFalhar(eventoInputId.getId());
        return ResponseEntity.ok(evento);
    }

    @PutMapping("/{idEvento}")
    public ResponseEntity<Evento> atualizar(@RequestParam EventoInputId eventoInputId, @RequestBody EventoInput eventoInput) {
        Evento evento = eventoService.buscarOuFalhar(eventoInputId.getId());
        
        evento.setNome(eventoInput.getNome());
        evento.setSigla(eventoInput.getSigla());
        evento.setDescricao(eventoInput.getDescricao()); 
        
        return ResponseEntity.ok(eventoService.salvar(evento));
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

}
