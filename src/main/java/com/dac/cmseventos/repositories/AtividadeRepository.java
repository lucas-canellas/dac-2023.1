package com.dac.cmseventos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dac.cmseventos.models.Atividade;

public interface AtividadeRepository extends JpaRepository<Atividade, Long> {
    
}
