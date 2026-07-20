package br.com.ifba.gestaoanimal.doacao.repository;

import br.com.ifba.gestaoanimal.doacao.entity.Doacao;
import br.com.ifba.gestaoanimal.enums.TipoDoacaoEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DoacaoRepository extends JpaRepository<Doacao, Long> {

    List<Doacao> findByDoadorId(Long doadorId);

    List<Doacao> findByTipo(TipoDoacaoEnum tipo);

    List<Doacao> findByDataBetween(LocalDate inicio, LocalDate fim);

    List<Doacao> findAllByOrderByDataDesc();
}