package com.dac.cmseventos.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Edicao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer numero;

    private Integer ano;

    @Column(name = "data_inicial")
    private LocalDateTime dataInicio;

    @Column(name = "data_final")
    private LocalDateTime dataFim;

    private String cidade;

    @OneToMany(mappedBy = "edicao")
    private List<Atividade> atividades = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "evento_id")
    private Evento evento;

    @OneToOne
    @JoinColumn(name = "usuario_organizador_id")
    private User organizador;


 
}
