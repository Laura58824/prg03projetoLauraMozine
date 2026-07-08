package br.com.ifba.gestaoanimal.ocorrencia.service;

import br.com.ifba.gestaoanimal.ocorrencia.entity.Ocorrencia;
import br.com.ifba.gestaoanimal.enums.TipoOcorrenciaEnum;
import br.com.ifba.gestaoanimal.enums.StatusOcorrenciaEnum;
import br.com.ifba.gestaoanimal.ocorrencia.repository.OcorrenciaIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OcorrenciaService implements OcorrenciaIService {

    @Autowired
    private OcorrenciaIRepository ocorrenciaRepository;

    @Override
    public Ocorrencia save(Ocorrencia ocorrencia) {
        if (ocorrencia == null)
            throw new IllegalArgumentException("Ocorrencia nao pode ser nula.");
        if (ocorrencia.getId() != null)
            throw new IllegalArgumentException("Ocorrencia nova nao deve ter ID.");
        if (ocorrencia.getTipo() == null)
            throw new IllegalArgumentException("Tipo de ocorrencia e obrigatorio.");
        if (ocorrencia.getUrgencia() == null)
            throw new IllegalArgumentException("Urgencia e obrigatoria.");
        if (ocorrencia.getRegistradaPor() == null)
            throw new IllegalArgumentException("Pessoa que registrou a ocorrencia e obrigatoria.");
        if (ocorrencia.getStatus() == null)
            ocorrencia.setStatus(StatusOcorrenciaEnum.ABERTA);
        if (ocorrencia.getDataRegistro() == null)
            ocorrencia.setDataRegistro(LocalDateTime.now());
        if (ocorrencia.getDataAtendimento() != null
                && ocorrencia.getDataAtendimento().isBefore(ocorrencia.getDataRegistro()))
            throw new IllegalArgumentException("Data de atendimento nao pode ser anterior a data de registro.");
        return ocorrenciaRepository.save(ocorrencia);
    }

    @Override
    public Ocorrencia update(Ocorrencia ocorrencia) {
        if (ocorrencia == null)
            throw new IllegalArgumentException("Ocorrencia nao pode ser nula.");
        if (ocorrencia.getId() == null)
            throw new IllegalArgumentException("ID e obrigatorio para atualizacao.");
        if (!ocorrenciaRepository.existsById(ocorrencia.getId()))
            throw new RuntimeException("Ocorrencia nao encontrada com id: " + ocorrencia.getId());
        if (ocorrencia.getTipo() == null)
            throw new IllegalArgumentException("Tipo de ocorrencia e obrigatorio.");
        if (ocorrencia.getUrgencia() == null)
            throw new IllegalArgumentException("Urgencia e obrigatoria.");
        if (ocorrencia.getStatus() == null)
            throw new IllegalArgumentException("Status e obrigatorio.");
        if (ocorrencia.getRegistradaPor() == null)
            throw new IllegalArgumentException("Pessoa que registrou a ocorrencia e obrigatoria.");
        if (ocorrencia.getDataRegistro() == null)
            throw new IllegalArgumentException("Data de registro e obrigatoria.");
        if (ocorrencia.getDataAtendimento() != null
                && ocorrencia.getDataAtendimento().isBefore(ocorrencia.getDataRegistro()))
            throw new IllegalArgumentException("Data de atendimento nao pode ser anterior a data de registro.");
        return ocorrenciaRepository.save(ocorrencia);
    }

    @Override
    public void delete(Long id) {
        if (id == null)
            throw new IllegalArgumentException("ID nao pode ser nulo.");
        if (!ocorrenciaRepository.existsById(id))
            throw new RuntimeException("Ocorrencia nao encontrada com id: " + id);
        ocorrenciaRepository.deleteById(id);
    }

    @Override
    public List<Ocorrencia> findAll() {
        return ocorrenciaRepository.findAll();
    }

    @Override
    public Ocorrencia findById(Long id) {
        if (id == null)
            throw new IllegalArgumentException("ID nao pode ser nulo.");
        return ocorrenciaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ocorrencia nao encontrada com id: " + id));
    }

    @Override
    public List<Ocorrencia> findByStatus(StatusOcorrenciaEnum status) {
        if (status == null)
            throw new IllegalArgumentException("Status nao pode ser nulo.");
        return ocorrenciaRepository.findByStatus(status);
    }

    @Override
    public List<Ocorrencia> findByTipo(TipoOcorrenciaEnum tipo) {
        if (tipo == null)
            throw new IllegalArgumentException("Tipo nao pode ser nulo.");
        return ocorrenciaRepository.findByTipo(tipo);
    }
}