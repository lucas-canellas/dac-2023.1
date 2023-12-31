package com.dac.cmseventos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dac.cmseventos.exception.DefaultException;
import com.dac.cmseventos.model.Evento;
import com.dac.cmseventos.repository.EventoRepository;

import jakarta.persistence.EntityManager;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private EntityManager manager;

    @Transactional
    public Evento salvar(Evento evento) {

        manager.detach(evento);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        for (GrantedAuthority authority : authentication.getAuthorities()) {
            String role = authority.getAuthority();
            if (role.equals("ROLE_USER")) {
                throw new DefaultException("Usuário não tem permissão para cadastrar/editar eventos");
            }
        }

        Evento eventoExistente = eventoRepository.findByCaminho(evento.getCaminho());

        if (eventoExistente != null && !eventoExistente.getId().equals(evento.getId())) {
            throw new DefaultException("Já existe um evento cadastrado com este caminho");
        }

        return eventoRepository.save(evento);
    }

    public void excluir(Long eventoId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        for (GrantedAuthority authority : authentication.getAuthorities()) {
            String role = authority.getAuthority();
            if (role.equals("ROLE_USER")) {
                throw new DefaultException("Usuário não tem permissão para excluir eventos");
            }
        }
        Evento evento = buscarOuFalhar(eventoId);
        eventoRepository.delete(evento);
    }

    public Evento buscarOuFalhar(Long eventoId) {
        return eventoRepository.findById(eventoId).orElseThrow(() -> new DefaultException("Evento não encontrado"));
    }

}
