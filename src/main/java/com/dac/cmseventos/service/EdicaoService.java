package com.dac.cmseventos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dac.cmseventos.exception.DefaultException;
import com.dac.cmseventos.model.Edicao;
import com.dac.cmseventos.model.User;
import com.dac.cmseventos.repository.EdicaoRepository;

@Service
public class EdicaoService {
    
    @Autowired
    private EdicaoRepository edicaoRepository;

    @Autowired
    private UserService userService;

    public Edicao salvar(Edicao edicao) {

        Edicao edicaoExistente = edicaoRepository.findByEventoId(edicao.getEvento().getId()).orElse(null);

        if (edicaoExistente != null && !edicaoExistente.equals(edicao)) {
            throw new DefaultException("Já existe uma edição associada a este evento");
        }

        return edicaoRepository.save(edicao);
    }

    public void excluir(Long edicaoId) {
        Edicao edicao = buscarOuFalhar(edicaoId);
        edicaoRepository.delete(edicao);
    }   

    public Edicao buscarOuFalhar(Long edicaoId) {
        return edicaoRepository.findById(edicaoId).orElseThrow(() -> new DefaultException("Edição não encontrada"));
    }

    public Edicao adicionarOrganizador(Long edicaoId, Long organizadorId) {
        Edicao edicao = buscarOuFalhar(edicaoId);
        User organizador = userService.buscarOuFalhar(organizadorId);
        
        if(edicao.getOrganizador() != null) {
            throw new DefaultException("Já existe um organizador associado a esta edição");
        }

        Edicao edicaoExistente = edicaoRepository.findByOrganizadorId(organizadorId).orElse(null);

        if (edicaoExistente != null && !edicaoExistente.equals(edicao)) {
            throw new DefaultException("Este organizador já está associado a uma edição");
        }

        edicao.setOrganizador(organizador);
        return edicaoRepository.save(edicao);
    }

}
