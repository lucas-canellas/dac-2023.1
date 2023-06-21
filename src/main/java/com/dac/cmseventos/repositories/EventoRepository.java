package com.dac.cmseventos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dac.cmseventos.models.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long> {
    
}
