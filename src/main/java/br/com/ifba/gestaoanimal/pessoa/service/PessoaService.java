package br.com.ifba.gestaoanimal.pessoa.service;

import br.com.ifba.gestaoanimal.pessoa.entity.Pessoa;
import br.com.ifba.gestaoanimal.pessoa.repository.PessoaIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class PessoaService implements PessoaIService {

    @Autowired
    private PessoaIRepository pessoaRepository;

    @Override
    public Pessoa save(Pessoa pessoa) {
        if (pessoa == null)
            throw new IllegalArgumentException("Pessoa não pode ser nula.");
        if (pessoa.getId() != null)
            throw new IllegalArgumentException("Pessoa nova não deve ter ID.");
        if (pessoaRepository.existsByCpf(pessoa.getCpf()))
            throw new RuntimeException("Já existe uma pessoa cadastrada com esse CPF.");
        pessoa.setAtivo(true);
        if (pessoa.getDataCadastro() == null)
            pessoa.setDataCadastro(LocalDate.now());
        return pessoaRepository.save(pessoa);
    }

    @Override
    public Pessoa update(Pessoa pessoa) {
        if (pessoa == null)
            throw new IllegalArgumentException("Pessoa não pode ser nula.");
        if (pessoa.getId() == null)
            throw new IllegalArgumentException("ID é obrigatório para atualização.");
        if (!pessoaRepository.existsById(pessoa.getId()))
            throw new RuntimeException("Pessoa não encontrada com id: " + pessoa.getId());
        return pessoaRepository.save(pessoa);
    }

    @Override
    public void delete(Long id) {
        if (id == null)
            throw new IllegalArgumentException("ID não pode ser nulo.");
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada com id: " + id));
        if (Boolean.FALSE.equals(pessoa.getAtivo()))
            throw new RuntimeException("Pessoa já está inativa.");
        pessoa.setAtivo(false);
        pessoaRepository.save(pessoa);
    }

    @Override
    public List<Pessoa> findAll() {
        return pessoaRepository.findByAtivoTrue();
    }

    @Override
    public Pessoa findById(Long id) {
        if (id == null)
            throw new IllegalArgumentException("ID não pode ser nulo.");
        return pessoaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada com id: " + id));
    }

    @Override
    public List<Pessoa> findByNome(String nome) {
        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("Nome para busca não pode ser vazio.");
        return pessoaRepository.findByNomeContainingIgnoreCase(nome);
    }
}