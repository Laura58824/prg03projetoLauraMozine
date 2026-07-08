package br.com.ifba.gestaoanimal.usuario.controller;

import br.com.ifba.gestaoanimal.usuario.entity.Usuario;
import java.util.List;

public interface UsuarioIController {
    Usuario save(Usuario usuario);
    Usuario update(Usuario usuario);
    void delete(Long id);
    List<Usuario> findAll();
    List<Usuario> findByAtivoTrue();
    Usuario findById(Long id);
    Usuario autenticar(String login, String senha);
}