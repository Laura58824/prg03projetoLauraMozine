package br.com.ifba.gestaoanimal.adocao.controller;

import br.com.ifba.gestaoanimal.adocao.entity.Adocao;
import br.com.ifba.gestaoanimal.adocao.service.AdocaoIService;
import br.com.ifba.gestaoanimal.enums.StatusAdocaoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
public class AdocaoController implements AdocaoIController {

    @Autowired
    private AdocaoIService adocaoService;

    @Override
    public Adocao save(Adocao adocao) {
        return adocaoService.save(adocao);
    }

    @Override
    public Adocao update(Adocao adocao) {
        return adocaoService.update(adocao);
    }

    @Override
    public void delete(Long id) {
        adocaoService.delete(id);
    }

    @Override
    public List<Adocao> findAll() {
        return adocaoService.findAll();
    }

    @Override
    public Adocao findById(Long id) {
        return adocaoService.findById(id);
    }

    @Override
    public List<Adocao> findByStatus(StatusAdocaoEnum status) {
        return adocaoService.findByStatus(status);
    }
}