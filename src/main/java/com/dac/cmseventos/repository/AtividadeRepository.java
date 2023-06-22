package com.dac.cmseventos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dac.cmseventos.model.Atividade;

public interface AtividadeRepository extends JpaRepository<Atividade, Long> {
    
}
