package com.dac.cmseventos.model.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.dac.cmseventos.model.Atividade;
import com.dac.cmseventos.model.Evento;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EdicaoInput {

    private Integer numero;

    private Integer ano;

    private LocalDateTime dataInicio;

    private LocalDateTime dataFim;

    private String cidade;

    private List<Atividade> atividades = new ArrayList<>();

    private Evento evento;
    
}
