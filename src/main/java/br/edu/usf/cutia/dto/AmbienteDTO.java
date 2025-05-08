package br.edu.usf.cutia.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Builder
public class AmbienteDTO {
    @Id
    private String id;
    private float temperatura;
    private float umidade;
    private LocalTime hora;
    private LocalDate dia;
}
