package com.dac.cmseventos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dac.cmseventos.exception.DefaultException;
import com.dac.cmseventos.model.Atividade;
import com.dac.cmseventos.repository.AtividadeRepository;

@Service
public class AtividadeService {
    
    @Autowired
    private AtividadeRepository atividadeRepository;

    public Atividade salvar(Atividade atividade) {
        return atividadeRepository.save(atividade);
    }

    public void excluir(Long atividadeId) {
        Atividade atividade = buscarOuFalhar(atividadeId);
        atividadeRepository.delete(atividade);
    }

    public Atividade buscarOuFalhar(Long atividadeId) {
        return atividadeRepository.findById(atividadeId).orElseThrow(() -> new DefaultException("Atividade não encontrada"));
    }

}
