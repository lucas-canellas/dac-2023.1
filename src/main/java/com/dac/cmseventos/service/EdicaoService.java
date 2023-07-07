package com.dac.cmseventos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dac.cmseventos.exception.DefaultException;
import com.dac.cmseventos.model.Edicao;
import com.dac.cmseventos.repository.EdicaoRepository;

@Service
public class EdicaoService {
    
    @Autowired
    private EdicaoRepository edicaoRepository;

    public Edicao salvar(Edicao edicao) {
        return edicaoRepository.save(edicao);
    }

    public void excluir(Long edicaoId) {
        Edicao edicao = buscarOuFalhar(edicaoId);
        edicaoRepository.delete(edicao);
    }

    public Edicao buscarOuFalhar(Long edicaoId) {
        return edicaoRepository.findById(edicaoId).orElseThrow(() -> new DefaultException("Edição não encontrada"));
    }

}
