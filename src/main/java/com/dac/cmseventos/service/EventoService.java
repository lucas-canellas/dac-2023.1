package com.dac.cmseventos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dac.cmseventos.exception.DefaultException;
import com.dac.cmseventos.model.Evento;
import com.dac.cmseventos.repository.EventoRepository;

@Service
public class EventoService {
    
    @Autowired
    private EventoRepository eventoRepository;

    public Evento salvar(Evento evento) {
        return eventoRepository.save(evento);
    }

    public void excluir(Long eventoId) {
        Evento evento = buscarOuFalhar(eventoId);
        eventoRepository.delete(evento);
    }

    public Evento buscarOuFalhar(Long eventoId) {
        return eventoRepository.findById(eventoId).orElseThrow(() -> new DefaultException("Evento não encontrado"));
    }

}
