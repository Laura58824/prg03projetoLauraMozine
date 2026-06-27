package br.com.ifba.gestaoanimal.pessoa.controller;

import br.com.ifba.gestaoanimal.pessoa.entity.Pessoa;
import br.com.ifba.gestaoanimal.pessoa.service.PessoaIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
public class PessoaController implements PessoaIController {

    @Autowired
    private PessoaIService pessoaService;

    @Override
    public Pessoa save(Pessoa pessoa) {
        return pessoaService.save(pessoa);
    }

    @Override
    public Pessoa update(Pessoa pessoa) {
        return pessoaService.update(pessoa);
    }

    @Override
    public void delete(Long id) {
        pessoaService.delete(id);
    }

    @Override
    public List<Pessoa> findAll() {
        return pessoaService.findAll();
    }

    @Override
    public Pessoa findById(Long id) {
        return pessoaService.findById(id);
    }

    @Override
    public List<Pessoa> findByNome(String nome) {
        return pessoaService.findByNome(nome);
    }
}