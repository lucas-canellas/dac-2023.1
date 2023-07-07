package com.dac.cmseventos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dac.cmseventos.exception.DefaultException;
import com.dac.cmseventos.model.User;
import com.dac.cmseventos.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public User salvar(User user) {
        return userRepository.save(user);
    }

    public void excluir(Long userId) {
        User user = buscarOuFalhar(userId);
        userRepository.delete(user);
    }

    public User buscarOuFalhar(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new DefaultException("User não encontrado"));
    }

    

}
