package com.dac.cmseventos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dac.cmseventos.model.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long> {

    Evento findByNome(String nome);

    Evento findByCaminho(String caminho);
    
}
