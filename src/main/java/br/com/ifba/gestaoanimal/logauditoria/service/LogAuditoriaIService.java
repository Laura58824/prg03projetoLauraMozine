package br.com.ifba.gestaoanimal.logauditoria.service;

import br.com.ifba.gestaoanimal.logauditoria.entity.LogAuditoria;
import br.com.ifba.gestaoanimal.usuario.entity.Usuario;

import java.time.LocalDateTime;
import java.util.List;

public interface LogAuditoriaIService {

    LogAuditoria registrar(Usuario usuario, String acao);
    LogAuditoria registrar(Usuario usuario, String acao, String ip);

    List<LogAuditoria> findAll();
    LogAuditoria findById(Long id);
    List<LogAuditoria> findByUsuario(Long usuarioId);
    List<LogAuditoria> findByAcao(String acao);
    List<LogAuditoria> findByPeriodo(LocalDateTime inicio, LocalDateTime fim);
}