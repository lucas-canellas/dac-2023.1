package com.dac.cmseventos.model.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EdicaoInput {

    @NotNull
    private Integer numero;

    @NotNull
    private Integer ano;

    @NotNull
    private LocalDateTime dataInicio;

    @NotNull
    private LocalDateTime dataFim;

    @NotBlank
    private String cidade;

    @NotNull
    private EventoInputId evento;
    
}
