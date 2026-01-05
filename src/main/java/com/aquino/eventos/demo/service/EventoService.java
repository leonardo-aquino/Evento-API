package com.aquino.eventos.demo.service;

import com.aquino.eventos.demo.exception.EventoInexistenteException;
import com.aquino.eventos.demo.model.entity.Evento;
import com.aquino.eventos.demo.model.entity.dtos.DTOSaida;
import com.aquino.eventos.demo.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    public Evento salvarEvento(Evento evento){
        return eventoRepository.save(evento);
    }

    public Optional<Evento> buscarEvento(Long id){
       return eventoRepository.findById(id);
    }

    public Evento atualizarEvento (Long id, Evento evento) {
        Optional<Evento> findyEvento = eventoRepository.findById(id);
        if (findyEvento.isPresent()) {
            Evento eventoAtualizado = findyEvento.get();
            eventoAtualizado.setDescricao(evento.getDescricao());
            eventoAtualizado.setLocal(evento.getLocal());
            eventoAtualizado.setDataHora(evento.getDataHora());
            salvarEvento(eventoAtualizado);
            return eventoAtualizado;
        }
        throw new EventoInexistenteException("Não há Evento para atualizar");
    }

    public void deletarEvento(Long id) {
       Optional<Evento> existEvento = eventoRepository.findById(id);
       if (existEvento.isPresent()){
           Evento evento = existEvento.get();
           evento.setDeletado(true);
           eventoRepository.save(evento);
       }else{
           throw new EventoInexistenteException("Evento não Existe");
       }
    }

    public Page<DTOSaida> buscarOsEventos(Integer pagina, Integer tamanoPagina) {
        Pageable pageRequest = PageRequest.of(pagina,tamanoPagina);
        Page<Evento> eventos = eventoRepository.findByDeletadoIsNullOrDeletadoFalse(pageRequest);
        return eventos.map(evento -> DTOSaida.fromDTO(evento));
    }
}
