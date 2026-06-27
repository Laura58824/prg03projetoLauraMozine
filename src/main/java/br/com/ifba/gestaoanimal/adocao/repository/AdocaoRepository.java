package br.com.ifba.gestaoanimal.adocao.repository;

import br.com.ifba.gestaoanimal.adocao.entity.Adocao;
import br.com.ifba.gestaoanimal.enums.StatusAdocaoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AdocaoRepository extends JpaRepository<Adocao, Long> {
    
    List<Adocao> findByStatus(StatusAdocaoEnum status);
    
}