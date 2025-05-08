package br.edu.usf.cutia.service;

import br.edu.usf.cutia.dto.AmbienteDTO;
import br.edu.usf.cutia.dto.AmbienteResponseDTO;
import br.edu.usf.cutia.repository.AmbienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AmbienteService {
    private AmbienteRepository repository;

    public void adicionarAmbiente(AmbienteDTO ambiente){

        repository.save(ambiente);
    }

    public List<AmbienteResponseDTO> buscarAmbientes(LocalDate dia){
        List<AmbienteDTO> ambientes = repository.findByDia(dia);
        List<AmbienteResponseDTO> res = new ArrayList<>();
        for(AmbienteDTO ambiente : ambientes){
            res.add(AmbienteResponseDTO
                    .builder()
                    .temperatura(ambiente.getTemperatura())
                    .umidade(ambiente.getUmidade())
                    .hora(ambiente.getHora().truncatedTo(java.time.temporal.ChronoUnit.SECONDS))
                    .build());
        }
        return res;
    }
}
