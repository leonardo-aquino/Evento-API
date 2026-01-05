package com.aquino.eventos.demo.model.entity.dtos;

import java.util.List;

public record ErroResposta(int status, String mensagem, List<ErroCampo> erros) {
}
