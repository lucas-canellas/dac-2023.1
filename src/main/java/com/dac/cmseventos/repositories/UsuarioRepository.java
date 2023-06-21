package com.dac.cmseventos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dac.cmseventos.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
}
