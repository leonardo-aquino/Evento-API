package com.aquino.eventos.demo.model.entity.dtos;

import com.aquino.eventos.demo.model.entity.Evento;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


public record DTOEntrada(
        @NotBlank(message = "Campo não pode ser nulo ou vazio")
        @Size(max = 100, message = "Campo com maximo de caracteres até 100")
        String titulo,

        @NotBlank(message = "Campo não pode ser nulo ou vazio")
        @Size(max = 1000, message = "Campo com limite de caracteres até 1000")
        String descricao,

        @NotNull(message = "Campo não pode ser nulo ou vazio")
        @FutureOrPresent(message = "Campo não pode ser uma data no passado")
        LocalDateTime dataHora,

        @NotBlank(message = "Campo não pode ser nulo ou vazio")
        String local) {

    public Evento toEntity(DTOEntrada dto){
        Evento evento = new Evento();
        evento.setTitulo(dto.titulo);
        evento.setLocal(dto.local);
        evento.setDataHora(dto.dataHora);
        evento.setDescricao(dto.descricao);
        return evento;
    }
}
