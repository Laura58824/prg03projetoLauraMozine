package br.com.ifba.gestaoanimal.logauditoria.controller;

import br.com.ifba.gestaoanimal.logauditoria.entity.LogAuditoria;
import br.com.ifba.gestaoanimal.logauditoria.service.LogAuditoriaIService;
import br.com.ifba.gestaoanimal.usuario.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class LogAuditoriaController implements LogAuditoriaIController {

    @Autowired
    private LogAuditoriaIService logAuditoriaService;

    @Override
    public LogAuditoria registrar(Usuario usuario, String acao) {
        return logAuditoriaService.registrar(usuario, acao);
    }

    @Override
    public LogAuditoria registrar(Usuario usuario, String acao, String ip) {
        return logAuditoriaService.registrar(usuario, acao, ip);
    }

    @Override
    public List<LogAuditoria> findAll() {
        return logAuditoriaService.findAll();
    }

    @Override
    public LogAuditoria findById(Long id) {
        return logAuditoriaService.findById(id);
    }

    @Override
    public List<LogAuditoria> findByUsuario(Long usuarioId) {
        return logAuditoriaService.findByUsuario(usuarioId);
    }

    @Override
    public List<LogAuditoria> findByAcao(String acao) {
        return logAuditoriaService.findByAcao(acao);
    }

    @Override
    public List<LogAuditoria> findByPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        return logAuditoriaService.findByPeriodo(inicio, fim);
    }
}