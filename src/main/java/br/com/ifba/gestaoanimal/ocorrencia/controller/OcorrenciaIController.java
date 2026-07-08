package br.com.ifba.gestaoanimal.ocorrencia.controller;

import br.com.ifba.gestaoanimal.ocorrencia.entity.Ocorrencia;
import br.com.ifba.gestaoanimal.enums.TipoOcorrenciaEnum;
import br.com.ifba.gestaoanimal.enums.StatusOcorrenciaEnum;

import java.util.List;

public interface OcorrenciaIController {
    
    Ocorrencia save(Ocorrencia ocorrencia);
    Ocorrencia update(Ocorrencia ocorrencia);
    void delete(Long id);
    List<Ocorrencia> findAll();
    Ocorrencia findById(Long id);
    List<Ocorrencia> findByStatus(StatusOcorrenciaEnum status);
    List<Ocorrencia> findByTipo(TipoOcorrenciaEnum tipo);
    
}