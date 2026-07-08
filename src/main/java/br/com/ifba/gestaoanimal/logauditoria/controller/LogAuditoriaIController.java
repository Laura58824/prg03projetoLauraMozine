package br.com.ifba.gestaoanimal.logauditoria.controller;

import br.com.ifba.gestaoanimal.logauditoria.entity.LogAuditoria;
import java.util.List;

public interface LogAuditoriaIController {
    LogAuditoria save(LogAuditoria log);
    List<LogAuditoria> findAll();
}