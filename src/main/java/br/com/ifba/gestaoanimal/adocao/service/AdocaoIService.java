package br.com.ifba.gestaoanimal.adocao.service;

import br.com.ifba.gestaoanimal.adocao.entity.Adocao;
import br.com.ifba.gestaoanimal.enums.StatusAdocaoEnum;
import java.util.List;


public interface AdocaoIService {
    Adocao save(Adocao adocao);
    
    Adocao update(Adocao adocao);
    
    void delete(Long id);
    
    List<Adocao> findAll();
    
    Adocao findById(Long id);
    
    List<Adocao> findByStatus(StatusAdocaoEnum status);
}