package br.com.ifba.gestaoanimal.doacao.controller;

import br.com.ifba.gestaoanimal.doacao.entity.Doacao;
import br.com.ifba.gestaoanimal.doacao.service.DoacaoIService;

import br.com.ifba.gestaoanimal.enums.TipoDoacaoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DoacaoController implements DoacaoIController {

    @Autowired
    private DoacaoIService doacaoService;

    @Override
    public Doacao salvar(Doacao doacao) {
        return doacaoService.save(doacao);
    }

    @Override
    public Doacao atualizar(Doacao doacao) {
        return doacaoService.update(doacao);
    }

    @Override
    public void excluir(Long id) {
        doacaoService.delete(id);
    }

    @Override
    public Doacao findById(Long id) {
        return doacaoService.findById(id);
    }

    @Override
    public List<Doacao> findAll() {
        return doacaoService.findAll();
    }

    @Override
    public List<Doacao> findByDoador(Long doadorId) {
        return doacaoService.findByDoador(doadorId);
    }

    @Override
    public List<Doacao> findByTipo(TipoDoacaoEnum tipo) {
        return doacaoService.findByTipo(tipo);
    }

    @Override
    public List<Doacao> findByPeriodo(LocalDate inicio, LocalDate fim) {
        return doacaoService.findByPeriodo(inicio, fim);
    }
}