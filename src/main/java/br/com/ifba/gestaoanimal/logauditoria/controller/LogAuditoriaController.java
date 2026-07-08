package br.com.ifba.gestaoanimal.logauditoria.controller;

import br.com.ifba.gestaoanimal.logauditoria.entity.LogAuditoria;
import br.com.ifba.gestaoanimal.logauditoria.service.LogAuditoriaIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
public class LogAuditoriaController {

    @Autowired
    private LogAuditoriaIService logAuditoriaService;

    public LogAuditoria save(LogAuditoria log) {
        return logAuditoriaService.save(log);
    }

    public List<LogAuditoria> findAll() {
        return logAuditoriaService.findAll();
    }
}