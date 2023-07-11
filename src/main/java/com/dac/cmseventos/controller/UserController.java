package com.dac.cmseventos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dac.cmseventos.model.User;
import com.dac.cmseventos.model.dto.UserInput;
import com.dac.cmseventos.repository.UserRepository;
import com.dac.cmseventos.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Usuário", description = "Endpoints usuário")
@RestController
@RequestMapping("/usuarios")
public class UserController {
    
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Operation(summary = "Lista todos os usuários")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de usuários" ),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping
    public ResponseEntity<List<User>> listar() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    @Operation(summary = "Cadastra um usuário")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Usuário cadastrado" ),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping
    public ResponseEntity<User> salvar(@RequestBody @Valid UserInput userInput) {
        User userSalvo = userService.salvar(toDomain(userInput));
        return ResponseEntity.ok(userSalvo);
    }

    @Operation(summary = "Busca um usuário pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário encontrado" ),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping("/{idUsuario}")
    public ResponseEntity<User> buscar(@PathVariable Long idUsuario) {
        User user = userService.buscarOuFalhar(idUsuario);
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "Atualiza um usuário")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário atualizado" ),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @PostMapping("/{idUsuario}")
    public ResponseEntity<User> atualizar(@PathVariable Long idUsuario, @RequestBody UserInput userInput) {
        User userAtual = userService.buscarOuFalhar(idUsuario);
        return ResponseEntity.ok(userAtual);
    }

    @Operation(summary = "Deleta um usuário")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Usuário deletado" ),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @DeleteMapping("/{idUsuario}/deletar")
    public ResponseEntity<Void> deletar(@PathVariable Long idUsuario) {
        userService.excluir(idUsuario);
        return ResponseEntity.noContent().build();
    }

    private User toDomain(UserInput userInput) {
        User user = new User();
        user.setNome(userInput.getNome());
        user.setEmail(userInput.getEmail());
        user.setSenha(userInput.getSenha());
        return user;
    }

    


}
