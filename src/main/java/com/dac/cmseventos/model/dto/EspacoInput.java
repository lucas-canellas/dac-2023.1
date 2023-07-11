package com.dac.cmseventos.model.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EspacoInput {
    
    private String nome;

    private String localizacao;

    private Integer capacidade;

    private List<String> recursos;

}
