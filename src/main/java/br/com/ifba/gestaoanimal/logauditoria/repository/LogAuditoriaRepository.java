package br.com.ifba.gestaoanimal.logauditoria.repository;

import br.com.ifba.gestaoanimal.logauditoria.entity.LogAuditoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogAuditoriaRepository extends JpaRepository<LogAuditoria, Long> {
}