package br.com.ifba.gestaoanimal.logauditoria.repository;

import br.com.ifba.gestaoanimal.logauditoria.entity.LogAuditoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LogAuditoriaRepository extends JpaRepository<LogAuditoria, Long> {

    List<LogAuditoria> findByUsuarioId(Long usuarioId);

    List<LogAuditoria> findByAcaoContainingIgnoreCase(String acao);

    List<LogAuditoria> findByDataHoraBetween(LocalDateTime inicio, LocalDateTime fim);

    List<LogAuditoria> findAllByOrderByDataHoraDesc();
}