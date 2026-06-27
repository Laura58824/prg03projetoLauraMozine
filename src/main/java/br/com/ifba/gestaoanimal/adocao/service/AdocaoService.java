package br.com.ifba.gestaoanimal.adocao.service;

import br.com.ifba.gestaoanimal.adocao.entity.Adocao;
import br.com.ifba.gestaoanimal.adocao.repository.AdocaoRepository;
import br.com.ifba.gestaoanimal.enums.StatusAdocaoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdocaoService implements AdocaoIService {

    @Autowired
    private AdocaoRepository adocaoRepository;

    @Override
    public Adocao save(Adocao adocao) {
        return adocaoRepository.save(adocao);
    }

    @Override
    public Adocao update(Adocao adocao) {
        return adocaoRepository.save(adocao);
    }

    @Override
    public void delete(Long id) {
        adocaoRepository.deleteById(id);
    }

    @Override
    public List<Adocao> findAll() {
        return adocaoRepository.findAll();
    }

    @Override
    public Adocao findById(Long id) {
        return adocaoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Adocao> findByStatus(StatusAdocaoEnum status) {
        return adocaoRepository.findByStatus(status);
    }
}