package com.dac.cmseventos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dac.cmseventos.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
}
