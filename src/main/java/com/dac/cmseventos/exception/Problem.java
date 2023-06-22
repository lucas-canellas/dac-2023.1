package com.dac.cmseventos.exception;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Problem {
    private Integer status;
    private LocalDateTime timestamp;
    private String userMessage;
}
