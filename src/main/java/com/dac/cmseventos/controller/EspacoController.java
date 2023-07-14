package com.dac.cmseventos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dac.cmseventos.model.Espaco;
import com.dac.cmseventos.repository.EspacoRepository;
import com.dac.cmseventos.service.EspacoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Espacos", description = "Endpoints espacos")
@RestController
@RequestMapping("/espacos")
public class EspacoController {
    
    @Autowired
    private EspacoService espacoService;

    @Autowired
    private EspacoRepository espacoRepository;

    @Operation(summary = "Lista todos os espacos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de espacos" ),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<Espaco>> listar() {
        List<Espaco> espacos = espacoRepository.findAll();
        return ResponseEntity.ok(espacos);
    }

    @Operation(summary = "Cadastra um espaco")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Espaco cadastrado" ),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Espaco> salvar(@RequestBody @Valid Espaco espaco) {
        Espaco espacoSalvo = espacoService.salvar(espaco);
        return ResponseEntity.ok(espacoSalvo);
    }

    @Operation(summary = "Busca um espaco pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Espaco encontrado" ),
        @ApiResponse(responseCode = "404", description = "Espaco não encontrado", content = @Content),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    })
    @GetMapping("/{idEspaco}")
    public ResponseEntity<Espaco> buscar(@PathVariable Long idEspaco) {
        Espaco espaco = espacoService.buscarOuFalhar(idEspaco);
        return ResponseEntity.ok(espaco);
    }

    @Operation(summary = "Atualiza um espaco")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Espaco atualizado" ),
        @ApiResponse(responseCode = "404", description = "Espaco não encontrado", content = @Content),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    })
    @PutMapping("/{idEspaco}")
    public ResponseEntity<Espaco> atualizar(@PathVariable Long idEspaco, @RequestBody Espaco espaco) {
        Espaco espacoAtual = espacoService.buscarOuFalhar(idEspaco);
        return ResponseEntity.ok(espacoService.salvar(espacoAtual));
    }

    @Operation(summary = "Exclui um espaco")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Espaco excluído" ),
        @ApiResponse(responseCode = "404", description = "Espaco não encontrado", content = @Content),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    })
    @DeleteMapping("/{idEspaco}/excluir")
    public ResponseEntity<Void> excluir(@PathVariable Long idEspaco) {
        espacoService.excluir(idEspaco);
        return ResponseEntity.noContent().build();
    }



}
