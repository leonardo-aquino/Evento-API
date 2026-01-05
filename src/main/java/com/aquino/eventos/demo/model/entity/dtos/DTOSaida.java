package com.aquino.eventos.demo.model.entity.dtos;

import com.aquino.eventos.demo.model.entity.Evento;


import java.time.LocalDateTime;


public record DTOSaida(String titulo, String descricao, LocalDateTime dataHora, String local) {


    public static  DTOSaida fromDTO (Evento evento) {

        return new DTOSaida(
                evento.getTitulo(),
                evento.getDescricao(),
                evento.getDataHora(),
                evento.getLocal()
        );
    }
}
