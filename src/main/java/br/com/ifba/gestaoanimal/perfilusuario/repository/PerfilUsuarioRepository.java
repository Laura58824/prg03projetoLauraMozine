package br.com.ifba.gestaoanimal.perfilusuario.repository;

import br.com.ifba.gestaoanimal.perfilusuario.entity.PerfilUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilUsuarioRepository extends JpaRepository<PerfilUsuario, Long> {
}