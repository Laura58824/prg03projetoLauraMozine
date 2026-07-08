package br.com.ifba.gestaoanimal.solicitacaovoluntario.service;

import br.com.ifba.gestaoanimal.solicitacaovoluntario.entity.SolicitacaoVoluntario;
import br.com.ifba.gestaoanimal.solicitacaovoluntario.repository.SolicitacaoVoluntarioRepository;
import br.com.ifba.gestaoanimal.enums.StatusSolicitacaoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SolicitacaoVoluntarioService implements SolicitacaoVoluntarioIService {

    @Autowired
    private SolicitacaoVoluntarioRepository solicitacaoVoluntarioRepository;

    @Override
    public SolicitacaoVoluntario save(SolicitacaoVoluntario solicitacao) {
        return solicitacaoVoluntarioRepository.save(solicitacao);
    }

    @Override
    public SolicitacaoVoluntario update(SolicitacaoVoluntario solicitacao) {
        return solicitacaoVoluntarioRepository.save(solicitacao);
    }

    @Override
    public List<SolicitacaoVoluntario> findAll() {
        return solicitacaoVoluntarioRepository.findAll();
    }

    @Override
    public List<SolicitacaoVoluntario> findByStatus(StatusSolicitacaoEnum status) {
        return solicitacaoVoluntarioRepository.findByStatus(status);
    }

    @Override
    public SolicitacaoVoluntario findById(Long id) {
        return solicitacaoVoluntarioRepository.findById(id).orElse(null);
    }
}