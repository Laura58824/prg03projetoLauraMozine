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

    @Override
    public Ocorrencia save(Ocorrencia ocorrencia) {
        return ocorrenciaService.save(ocorrencia);
    }

    @Override
    public Ocorrencia update(Ocorrencia ocorrencia) {
        return ocorrenciaService.update(ocorrencia);
    }

    @Override
    public void delete(Long id) {
        ocorrenciaService.delete(id);
    }

    @Override
    public List<Ocorrencia> findAll() {
        return ocorrenciaService.findAll();
    }

    @Override
    public Ocorrencia findById(Long id) {
        return ocorrenciaService.findById(id);
    }

    @Override
    public List<Ocorrencia> findByStatus(StatusOcorrenciaEnum status) {
        return ocorrenciaService.findByStatus(status);
    }

    @Override
    public List<Ocorrencia> findByTipo(TipoOcorrenciaEnum tipo) {
        return ocorrenciaService.findByTipo(tipo);
    }
}