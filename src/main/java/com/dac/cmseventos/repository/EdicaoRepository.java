package com.dac.cmseventos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dac.cmseventos.model.Edicao;

public interface EdicaoRepository extends JpaRepository<Edicao, Long> {

    Optional<Edicao> findByEventoId(Long eventoId);

    Optional<Edicao> findByOrganizadorId(Long organizadorId);
    
}
