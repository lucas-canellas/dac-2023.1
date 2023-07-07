package com.dac.cmseventos.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventoInput {    
    
    @NotBlank
    private String nome;    

    @NotBlank
    private String sigla;

    @NotBlank
    private String descricao;

    @NotBlank
    private String caminho;

}
