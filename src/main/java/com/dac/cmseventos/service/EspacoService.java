package com.dac.cmseventos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dac.cmseventos.exception.DefaultException;
import com.dac.cmseventos.model.Espaco;
import com.dac.cmseventos.repository.EspacoRepository;

@Service
public class EspacoService {
    
    @Autowired
    private EspacoRepository espacoRepository;

    public Espaco salvar(Espaco espaco) {
        return espacoRepository.save(espaco);
    }

    public void excluir(Long espacoId) {
        Espaco espaco = buscarOuFalhar(espacoId);
        espacoRepository.delete(espaco);
    }

    public Espaco buscarOuFalhar(Long espacoId) {
        return espacoRepository.findById(espacoId).orElseThrow(() -> new DefaultException("Espaco não encontrado"));
    }

}
