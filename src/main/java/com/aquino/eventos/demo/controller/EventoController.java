package com.aquino.eventos.demo.controller;

import com.aquino.eventos.demo.model.entity.Evento;
import com.aquino.eventos.demo.model.entity.dtos.DTOEntrada;
import com.aquino.eventos.demo.model.entity.dtos.DTOSaida;
import com.aquino.eventos.demo.service.EventoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("api/events")
public class EventoController {

    @Autowired
    private EventoService eventoService;


    @PostMapping
    public ResponseEntity<DTOSaida> criarEvento( @Valid @RequestBody DTOEntrada dto){
        Evento evento = dto.toEntity(dto);
        DTOSaida resposta = DTOSaida.fromDTO(eventoService.salvarEvento(evento));

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(evento.getId())
                .toUri();

        return ResponseEntity.created(location).body(resposta);
    }

    @GetMapping("{id}")
    public ResponseEntity<DTOSaida> detalheEvento (@PathVariable("id") Long id){
        Optional<Evento> evento = eventoService.buscarEvento(id);
        if (evento.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        DTOSaida resultado = DTOSaida.fromDTO(evento.get());
        return ResponseEntity.ok(resultado);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> atualizarEvento(@Valid @PathVariable("id")Long id, @RequestBody Evento evento){
        Evento findyEvento = eventoService.atualizarEvento(id,evento);
        return ResponseEntity.ok("Evento Atualizado");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarEvento(@PathVariable Long id){
        eventoService.deletarEvento(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<Page<DTOSaida>> paginacao(@RequestParam (value = "pagina",defaultValue = "0") Integer pagina,
                                                    @RequestParam (value = "tamanho-pagina", defaultValue = "10") Integer tamanoPagina){
        Page<DTOSaida> resultadoPaginado = eventoService.buscarOsEventos(pagina,tamanoPagina);
        return ResponseEntity.ok(resultadoPaginado);
    }
}
