package br.com.ifba.gestaoanimal.pessoa.controller;

import br.com.ifba.gestaoanimal.pessoa.entity.Pessoa;
import br.com.ifba.gestaoanimal.pessoa.service.PessoaIService;
import br.com.ifba.gestaoanimal.logauditoria.controller.LogAuditoriaIController;
import br.com.ifba.gestaoanimal.usuario.util.SessaoUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
public class PessoaController implements PessoaIController {

    @Autowired
    private PessoaIService pessoaService;

    @Autowired
    private LogAuditoriaIController logAuditoriaController;

    @Override
    public Pessoa save(Pessoa pessoa) {
        Pessoa salva = pessoaService.save(pessoa);
        logAuditoriaController.registrar(SessaoUsuario.getUsuarioLogado(),
                "Cadastrou pessoa: " + salva.getNome() + " (ID " + salva.getId() + ")");
        return salva;
    }

    @Override
    public Pessoa update(Pessoa pessoa) {
        Pessoa atualizada = pessoaService.update(pessoa);
        logAuditoriaController.registrar(SessaoUsuario.getUsuarioLogado(),
                "Editou pessoa: " + atualizada.getNome() + " (ID " + atualizada.getId() + ")");
        return atualizada;
    }

    @Override
    public void delete(Long id) {
        Pessoa pessoa = pessoaService.findById(id);
        pessoaService.delete(id);
        logAuditoriaController.registrar(SessaoUsuario.getUsuarioLogado(),
                "Desativou pessoa: " + (pessoa != null ? pessoa.getNome() : "") + " (ID " + id + ")");
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