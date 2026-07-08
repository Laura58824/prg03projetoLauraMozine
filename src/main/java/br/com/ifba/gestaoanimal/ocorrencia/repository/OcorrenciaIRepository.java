package br.com.ifba.gestaoanimal.ocorrencia.repository;

import br.com.ifba.gestaoanimal.ocorrencia.entity.Ocorrencia;
import br.com.ifba.gestaoanimal.enums.TipoOcorrenciaEnum;
import br.com.ifba.gestaoanimal.enums.StatusOcorrenciaEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OcorrenciaIRepository extends JpaRepository<Ocorrencia, Long> {
    
    List<Ocorrencia> findByStatus(StatusOcorrenciaEnum status);
    List<Ocorrencia> findByTipo(TipoOcorrenciaEnum tipo);
    List<Ocorrencia> findByRegistradaPorId(Long pessoaId);
    List<Ocorrencia> findByVoluntarioId(Long pessoaId);
}