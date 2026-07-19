package br.com.ifba.gestaoanimal.usuario.repository;

import br.com.ifba.gestaoanimal.usuario.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    Optional<Usuario> findByLogin(String login);
    List<Usuario> findByAtivoTrue();
 Optional<Usuario> findByPessoaId(Long pessoaId);  
}