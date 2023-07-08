package com.dac.cmseventos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dac.cmseventos.model.Edicao;
import com.dac.cmseventos.model.dto.EdicaoInput;
import com.dac.cmseventos.repository.EdicaoRepository;
import com.dac.cmseventos.service.EdicaoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Edição", description = "Endpoints edição")
@RestController
@RequestMapping("/edicoes")
public class EdicaoController {
    
    @Autowired
    private EdicaoService edicaoService;

    @Autowired
    private EdicaoRepository edicaoRepository;

    @Operation(summary = "Lista todas as edições")
    @GetMapping
    public ResponseEntity<List<Edicao>> listar() {
        List<Edicao> edicoes = edicaoRepository.findAll();
        return ResponseEntity.ok(edicoes);
    }

    @Operation(summary = "Cadastra uma edição")
    @PostMapping
    public ResponseEntity<Edicao> salvar(@RequestBody @Valid EdicaoInput edicaoInput) {
        Edicao edicaoSalvo = edicaoService.salvar(toDomain(edicaoInput));
        return ResponseEntity.ok(edicaoSalvo);
    }

    @Operation(summary = "Adicionar organizador a uma edição")
    @PostMapping("/{edicaoId}/organizador/{organizadorId}")
    public ResponseEntity<Edicao> adicionarOrganizador(Long edicaoId, Long organizadorId) {
        Edicao edicaoSalvo = edicaoService.adicionarOrganizador(edicaoId, organizadorId);
        return ResponseEntity.ok(edicaoSalvo);
    }

    @Operation(summary = "Atualiza uma edição")
    @PutMapping("/{edicaoId}")
    public ResponseEntity<Edicao> atualizar(@RequestBody @Valid EdicaoInput edicaoInput) {
        Edicao edicaoSalvo = edicaoService.salvar(toDomain(edicaoInput));
        return ResponseEntity.ok(edicaoSalvo);
    }

    @Operation(summary = "Exclui uma edição")
    @DeleteMapping("/{edicaoId}")
    public ResponseEntity<Void> excluir(Long edicaoId) {
        edicaoService.excluir(edicaoId);
        return ResponseEntity.noContent().build();
    }

    public Edicao toDomain(EdicaoInput edicaoInput) {
        Edicao edicao = new Edicao();
        return edicao;
    }










}
