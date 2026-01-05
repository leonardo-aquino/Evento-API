package com.aquino.eventos.demo.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "evento")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tit ulo", length = 100)
    private String titulo;

    @Column(name = "descricao", length = 1000)
    private String descricao;

    @Column(name = "data_hora")
    private LocalDateTime dataHora;

    @Column(name = "local", length = 200)
    private String local;

    @Column(name = "deletado")
    private Boolean deletado = false;
}
