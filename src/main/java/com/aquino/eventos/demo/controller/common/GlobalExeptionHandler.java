package com.aquino.eventos.demo.controller.common;

import com.aquino.eventos.demo.exception.EventoInexistenteException;
import com.aquino.eventos.demo.model.entity.dtos.ErroCampo;
import com.aquino.eventos.demo.model.entity.dtos.ErroResposta;
import jakarta.validation.UnexpectedTypeException;
import org.springframework.beans.MethodInvocationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExeptionHandler {

    @ExceptionHandler(EventoInexistenteException.class)
    public ResponseEntity<String> eventoInexistenteException(EventoInexistenteException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Evento não Encontrado");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErroResposta MethodArgumentNotValidException (MethodArgumentNotValidException e){
        List<FieldError> fieldErrors = e.getFieldErrors();
        List<ErroCampo> erroCampoList = fieldErrors.stream().map(erro -> new ErroCampo(erro.getField(), erro.getDefaultMessage())).collect(Collectors.toList());
        return new ErroResposta(HttpStatus.BAD_REQUEST.value(),"Erro de Validação",erroCampoList);
    }
}
