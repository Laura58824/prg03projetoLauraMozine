package br.com.ifba.gestaoanimal.logauditoria.service;

import br.com.ifba.gestaoanimal.logauditoria.entity.LogAuditoria;
import br.com.ifba.gestaoanimal.logauditoria.repository.LogAuditoriaRepository;
import br.com.ifba.gestaoanimal.usuario.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LogAuditoriaService implements LogAuditoriaIService {

    @Autowired
    private LogAuditoriaRepository logAuditoriaRepository;

    @Override
    public LogAuditoria registrar(Usuario usuario, String acao) {
        return registrar(usuario, acao, null);
    }

    @Override
    public LogAuditoria registrar(Usuario usuario, String acao, String ip) {
        if (usuario == null)
            throw new IllegalArgumentException("Usuario e obrigatorio para registrar log de auditoria.");
        if (acao == null || acao.isBlank())
            throw new IllegalArgumentException("Acao e obrigatoria para registrar log de auditoria.");

        LogAuditoria log = new LogAuditoria();
        log.setUsuario(usuario);
        log.setAcao(acao);
        log.setIp(ip);
        log.setDataHora(LocalDateTime.now());

        return logAuditoriaRepository.save(log);
    }

    @Override
    public List<LogAuditoria> findAll() {
        return logAuditoriaRepository.findAllByOrderByDataHoraDesc();
    }

    @Override
    public LogAuditoria findById(Long id) {
        if (id == null)
            throw new IllegalArgumentException("ID nao pode ser nulo.");
        return logAuditoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Log de auditoria nao encontrado com id: " + id));
    }

    @Override
    public List<LogAuditoria> findByUsuario(Long usuarioId) {
        if (usuarioId == null)
            throw new IllegalArgumentException("ID do usuario nao pode ser nulo.");
        return logAuditoriaRepository.findByUsuarioId(usuarioId);
    }

    @Override
    public List<LogAuditoria> findByAcao(String acao) {
        if (acao == null || acao.isBlank())
            throw new IllegalArgumentException("Acao nao pode ser vazia.");
        return logAuditoriaRepository.findByAcaoContainingIgnoreCase(acao);
    }

    @Override
    public List<LogAuditoria> findByPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        if (inicio == null || fim == null)
            throw new IllegalArgumentException("Periodo (inicio e fim) e obrigatorio.");
        if (fim.isBefore(inicio))
            throw new IllegalArgumentException("Data final nao pode ser anterior a data inicial.");
        return logAuditoriaRepository.findByDataHoraBetween(inicio, fim);
    }
}