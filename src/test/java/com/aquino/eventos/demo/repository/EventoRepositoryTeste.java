package com.aquino.eventos.demo.repository;

import com.aquino.eventos.demo.model.entity.Evento;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
public class EventoRepositoryTeste {

    @Autowired
   private EventoRepository eventoRepository;


    @Test
    public void salvarEventoTeste(){
        Evento evento = new Evento();
        evento.setDeletado(false);
        evento.setLocal("Rua Francisco Manoel da Silva");
        evento.setTitulo("Mr olimpia");
        evento.setDataHora(LocalDateTime.now());
        evento.setDescricao("Evento de BodyBiulder");
        eventoRepository.save(evento);
    }

    @Test
    public void deletarEvento(){
       Optional<Evento> evento =  eventoRepository.findById(8L);
        evento.ifPresent(value -> eventoRepository.delete(value));
    }
}
