package com.dac.cmseventos.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Atividade {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private TipoAtividade tipo;

    private String descricao;

    private LocalDateTime data;

    @Column(name = "horario_inicial")
    private LocalDateTime horarioInicial;

    @Column(name = "horario_final")
    private LocalDateTime horarioFinal;  
    
    @OneToOne
    @JoinColumn(name = "espaco_id")
    private Espaco espaco;

    @ManyToOne
    @JoinColumn(name = "edicao_id", nullable = false)
    private Edicao edicao;

}
