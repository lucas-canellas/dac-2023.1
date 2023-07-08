package com.dac.cmseventos.model.dto;

import java.time.LocalDateTime;

import com.dac.cmseventos.model.Edicao;
import com.dac.cmseventos.model.Espaco;
import com.dac.cmseventos.model.TipoAtividade;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtividadeInput {
    
    private String nome;

    private TipoAtividade tipo;

    private String descricao;

    private LocalDateTime data;

    private LocalDateTime horarioInicial;

    private LocalDateTime horarioFinal;  

    private Espaco espaco;

    private Edicao edicao;

}
