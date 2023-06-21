package com.dac.cmseventos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dac.cmseventos.models.Espaco;

public interface EspacoRepository extends JpaRepository<Espaco, Long> {
    
}
