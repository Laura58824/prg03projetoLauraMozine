package br.com.ifba.gestaoanimal.perfilusuario.service;

import br.com.ifba.gestaoanimal.perfilusuario.entity.PerfilUsuario;
import br.com.ifba.gestaoanimal.perfilusuario.repository.PerfilUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PerfilUsuarioService implements PerfilUsuarioIService {

    @Autowired
    private PerfilUsuarioRepository perfilUsuarioRepository;

    @Override
    public PerfilUsuario save(PerfilUsuario perfil) {
        return perfilUsuarioRepository.save(perfil);
    }

    @Override
    public PerfilUsuario update(PerfilUsuario perfil) {
        return perfilUsuarioRepository.save(perfil);
    }

    @Override
    public void delete(Long id) {
        perfilUsuarioRepository.deleteById(id);
    }

    @Override
    public List<PerfilUsuario> findAll() {
        return perfilUsuarioRepository.findAll();
    }

    @Override
    public PerfilUsuario findById(Long id) {
        return perfilUsuarioRepository.findById(id).orElse(null);
    }
}