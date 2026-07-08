package br.com.ifba.gestaoanimal.usuario.controller;

import br.com.ifba.gestaoanimal.usuario.entity.Usuario;
import br.com.ifba.gestaoanimal.usuario.service.UsuarioIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioIService usuarioService;

    public Usuario save(Usuario usuario) {
        return usuarioService.save(usuario);
    }

    public Usuario update(Usuario usuario) {
        return usuarioService.update(usuario);
    }

    public void delete(Long id) {
        usuarioService.delete(id);
    }

    public List<Usuario> findAll() {
        return usuarioService.findAll();
    }

    public List<Usuario> findByAtivoTrue() {
        return usuarioService.findByAtivoTrue();
    }

    public Usuario findById(Long id) {
        return usuarioService.findById(id);
    }

    public Usuario autenticar(String login, String senha) {
        return usuarioService.autenticar(login, senha);
    }
}