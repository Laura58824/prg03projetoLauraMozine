package br.com.ifba.gestaoanimal.logauditoria.service;

import br.com.ifba.gestaoanimal.logauditoria.entity.LogAuditoria;
import br.com.ifba.gestaoanimal.logauditoria.repository.LogAuditoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LogAuditoriaService implements LogAuditoriaIService {

    @Autowired
    private LogAuditoriaRepository logAuditoriaRepository;

    @Override
    public LogAuditoria save(LogAuditoria log) {
        return logAuditoriaRepository.save(log);
    }

    @Override
    public List<LogAuditoria> findAll() {
        return logAuditoriaRepository.findAll();
    }
}