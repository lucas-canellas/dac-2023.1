package com.dac.cmseventos.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;

@RestController
@Data
@RequestMapping("/auth")
public class AuthController {
    
    public String login() {
        return "login";
    }

}
