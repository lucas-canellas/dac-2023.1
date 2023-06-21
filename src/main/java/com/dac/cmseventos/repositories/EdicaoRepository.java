package com.dac.cmseventos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dac.cmseventos.models.Edicao;

public interface EdicaoRepository extends JpaRepository<Edicao, Long> {
    
}
