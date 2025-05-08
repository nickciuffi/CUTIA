package br.edu.usf.cutia.repository;

import br.edu.usf.cutia.dto.AmbienteDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface AmbienteRepository extends MongoRepository<AmbienteDTO, String> {
    List<AmbienteDTO> findByDia(LocalDate dia);
}
