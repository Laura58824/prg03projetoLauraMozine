package br.com.ifba.gestaoanimal.perfilusuario.controller;

import br.com.ifba.gestaoanimal.perfilusuario.entity.PerfilUsuario;
import java.util.List;

public interface PerfilUsuarioIController {
    PerfilUsuario save(PerfilUsuario perfil);
    PerfilUsuario update(PerfilUsuario perfil);
    void delete(Long id);
    List<PerfilUsuario> findAll();
    PerfilUsuario findById(Long id);
}