package br.com.ifba.gestaoanimal.doacao.service;

import br.com.ifba.gestaoanimal.doacao.entity.Doacao;
import br.com.ifba.gestaoanimal.enums.TipoDoacaoEnum;

import java.time.LocalDate;
import java.util.List;

public interface DoacaoIService {

    Doacao save(Doacao doacao);

    Doacao update(Doacao doacao);

    void delete(Long id);

    Doacao findById(Long id);

    List<Doacao> findAll();

    List<Doacao> findByDoador(Long doadorId);

    List<Doacao> findByTipo(TipoDoacaoEnum tipo);

    List<Doacao> findByPeriodo(LocalDate inicio, LocalDate fim);
}