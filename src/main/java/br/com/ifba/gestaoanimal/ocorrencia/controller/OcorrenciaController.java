package br.com.ifba.gestaoanimal.ocorrencia.controller;

import br.com.ifba.gestaoanimal.ocorrencia.entity.Ocorrencia;
import br.com.ifba.gestaoanimal.ocorrencia.service.OcorrenciaIService;
import br.com.ifba.gestaoanimal.enums.TipoOcorrenciaEnum;
import br.com.ifba.gestaoanimal.enums.StatusOcorrenciaEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class OcorrenciaController implements OcorrenciaIController {

    @Autowired
    private OcorrenciaIService ocorrenciaService;

    public Ocorrencia save(Ocorrencia ocorrencia) {
        return ocorrenciaService.save(ocorrencia);
    }

    public Ocorrencia update(Ocorrencia ocorrencia) {
        return ocorrenciaService.update(ocorrencia);
    }

    public void delete(Long id) {
        ocorrenciaService.delete(id);
    }

    public List<Ocorrencia> findAll() {
        return ocorrenciaService.findAll();
    }

    public Ocorrencia findById(Long id) {
        return ocorrenciaService.findById(id);
    }

    public List<Ocorrencia> findByStatus(StatusOcorrenciaEnum status) {
        return ocorrenciaService.findByStatus(status);
    }

    public List<Ocorrencia> findByTipo(TipoOcorrenciaEnum tipo) {
        return ocorrenciaService.findByTipo(tipo);
    }
}