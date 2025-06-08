package br.edu.usf.cutia.controller;

import br.edu.usf.cutia.dto.AmbienteDTO;
import br.edu.usf.cutia.dto.AmbienteRequestDTO;
import br.edu.usf.cutia.dto.AmbienteResponseDTO;
import br.edu.usf.cutia.service.AmbienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController()
@RequestMapping("/ambiente")
@AllArgsConstructor
public class AmbienteController {

    private AmbienteService service;

    @PostMapping("/")
    public ResponseEntity<String> adicionaAmbiente(
            @RequestBody AmbienteRequestDTO apiBody
    ){
        if(apiBody.getTemperatura() == null || apiBody.getUmidade() == null){
            ResponseEntity.badRequest().body("Você precisa enviar a temperatura e a umidade no corpo da requisição");
        }
        try {
            AmbienteDTO ambiente = AmbienteDTO
                    .builder()
                    .temperatura(Float.parseFloat(apiBody.getTemperatura()))
                    .umidade(Float.parseFloat(apiBody.getUmidade()))
                    .coolerLigado(apiBody.isCoolerLigado())
                    .umidificadorLigado(apiBody.isUmidificadorLigado())
                    .dia(LocalDate.now(ZoneId.of("America/Sao_Paulo")))
                    .hora(LocalTime.now(ZoneId.of("America/Sao_Paulo")))
                    .build();

            service.adicionarAmbiente(ambiente);
            return ResponseEntity.ok().body("Ambiente cadastrado com sucesso!!");
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        }
        }

    @GetMapping("/")
    public ResponseEntity<?> buscarAmbientesPorDia(
            @RequestParam String dia
    ){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try{
            List<AmbienteResponseDTO> res = service.buscarAmbientes(LocalDate.parse(dia, formatter));
            return ResponseEntity.ok().body(res);
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        }
    }
}
