package br.com.ifba.gestaoanimal.pessoa.service;

import br.com.ifba.gestaoanimal.pessoa.entity.Pessoa;
import java.util.List;


public interface PessoaIService {
    
    Pessoa save(Pessoa pessoa);
    
    Pessoa update(Pessoa pessoa);
    
    void delete(Long id);
    
    List<Pessoa> findAll();
    
    Pessoa findById(Long id);
    
    List<Pessoa> findByNome(String nome);
}