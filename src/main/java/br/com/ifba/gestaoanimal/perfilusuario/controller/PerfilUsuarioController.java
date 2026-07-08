package br.com.ifba.gestaoanimal.perfilusuario.controller;

import br.com.ifba.gestaoanimal.perfilusuario.entity.PerfilUsuario;
import br.com.ifba.gestaoanimal.perfilusuario.service.PerfilUsuarioIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
public class PerfilUsuarioController implements PerfilUsuarioIController {

    @Autowired
    private PerfilUsuarioIService perfilUsuarioService;

    public PerfilUsuario save(PerfilUsuario perfil) {
        return perfilUsuarioService.save(perfil);
    }

    public PerfilUsuario update(PerfilUsuario perfil) {
        return perfilUsuarioService.update(perfil);
    }

    public void delete(Long id) {
        perfilUsuarioService.delete(id);
    }

    public List<PerfilUsuario> findAll() {
        return perfilUsuarioService.findAll();
    }

    public PerfilUsuario findById(Long id) {
        return perfilUsuarioService.findById(id);
    }
}