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

import com.dac.cmseventos.exception.DefaultException;
import com.dac.cmseventos.model.Edicao;
import com.dac.cmseventos.model.Evento;
import com.dac.cmseventos.model.dto.EdicaoInput;
import com.dac.cmseventos.repository.EdicaoRepository;
import com.dac.cmseventos.repository.EventoRepository;
import com.dac.cmseventos.service.EdicaoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Autowired
    private EventoRepository eventoRepository;

    @Operation(summary = "Lista todas as edições")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de edições" ),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<Edicao>> listar() {
        List<Edicao> edicoes = edicaoRepository.findAll();
        return ResponseEntity.ok(edicoes);
    }

    @Operation(summary = "Cadastra uma edição")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Edição cadastrada" ),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Edicao> salvar(@RequestBody @Valid EdicaoInput edicaoInput) {
        Edicao edicaoSalvo = edicaoService.salvar(toDomain(edicaoInput));
        return ResponseEntity.ok(edicaoSalvo);
    }

    @Operation(summary = "Adicionar organizador a uma edição")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Organizador adicionado" ),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content),
        @ApiResponse(responseCode = "404", description = "Edição ou organizador não encontrado", content = @Content)
    })
    @PostMapping("/{edicaoId}/organizador/{organizadorId}")
    public ResponseEntity<Edicao> adicionarOrganizador(@PathVariable Long edicaoId, @PathVariable Long organizadorId) {
        Edicao edicaoSalvo = edicaoService.adicionarOrganizador(edicaoId, organizadorId);
        return ResponseEntity.ok(edicaoSalvo);
    }

    @Operation(summary = "Atualiza uma edição")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Edição atualizada" ),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content),
        @ApiResponse(responseCode = "404", description = "Edição não encontrada", content = @Content)
    })  
    @PutMapping("/{edicaoId}")
    public ResponseEntity<Edicao> atualizar(@RequestBody @Valid EdicaoInput edicaoInput) {
        Edicao edicaoSalvo = edicaoService.salvar(toDomain(edicaoInput));
        return ResponseEntity.ok(edicaoSalvo);
    }

    @Operation(summary = "Exclui uma edição")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Edição excluída" ),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content),
        @ApiResponse(responseCode = "404", description = "Edição não encontrada", content = @Content)
    })

    @DeleteMapping("/{edicaoId}")
    public ResponseEntity<Void> excluir(Long edicaoId) {
        edicaoService.excluir(edicaoId);
        return ResponseEntity.noContent().build();
    }

    public Edicao toDomain(EdicaoInput edicaoInput) {
        Edicao edicao = new Edicao();
        edicao.setAno(edicaoInput.getAno());
        edicao.setNumero(edicaoInput.getNumero());
        edicao.setCidade(edicaoInput.getCidade());
        edicao.setDataInicio(edicaoInput.getDataInicio());
        edicao.setDataFim(edicaoInput.getDataFim());

        Evento evento = eventoRepository.findById(edicaoInput.getEvento().getId())
            .orElseThrow(() -> new DefaultException("Evento não encontrado"));

        edicao.setEvento(evento);

        return edicao;
    }










}
