package br.com.ifba.gestaoanimal.logauditoria.service;

import br.com.ifba.gestaoanimal.logauditoria.entity.LogAuditoria;
import java.util.List;

public interface LogAuditoriaIService {
    LogAuditoria save(LogAuditoria log);
    List<LogAuditoria> findAll();
}