package br.edu.usf.cutia.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Builder
public class AmbienteResponseDTO {
    private float temperatura;
    private float umidade;
    private LocalTime hora;
}
