package com.dac.cmseventos.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventoInputId {
    
    @NotBlank
    private Long id;

}
