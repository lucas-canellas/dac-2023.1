package com.dac.cmseventos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dac.cmseventos.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
