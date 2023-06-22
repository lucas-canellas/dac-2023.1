package com.dac.cmseventos.model.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventoInputId {
    
    @NotBlank
    private Long id;

}
