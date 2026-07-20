package br.com.ifba.gestaoanimal.doacao.controller;

import br.com.ifba.gestaoanimal.doacao.entity.Doacao;
import br.com.ifba.gestaoanimal.enums.TipoDoacaoEnum;

import java.time.LocalDate;
import java.util.List;

public interface DoacaoIController {

    Doacao salvar(Doacao doacao);

    Doacao atualizar(Doacao doacao);

    void excluir(Long id);

    Doacao findById(Long id);

    List<Doacao> findAll();

    List<Doacao> findByDoador(Long doadorId);

    List<Doacao> findByTipo(TipoDoacaoEnum tipo);

    List<Doacao> findByPeriodo(LocalDate inicio, LocalDate fim);
}