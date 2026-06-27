package br.com.ifba.gestaoanimal.adocao.controller;

import br.com.ifba.gestaoanimal.adocao.entity.Adocao;
import br.com.ifba.gestaoanimal.enums.StatusAdocaoEnum;
import java.util.List;

public interface AdocaoIController {
    Adocao save(Adocao adocao);
    
    Adocao update(Adocao adocao);
    
    void delete(Long id);
    
    List<Adocao> findAll();
    
    Adocao findById(Long id);
    
    List<Adocao> findByStatus(StatusAdocaoEnum status);
}