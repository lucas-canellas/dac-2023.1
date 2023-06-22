package com.dac.cmseventos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dac.cmseventos.model.Edicao;

public interface EdicaoRepository extends JpaRepository<Edicao, Long> {
    
}
