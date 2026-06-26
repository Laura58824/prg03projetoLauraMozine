package br.com.ifba.gestaoanimal.pessoa.repository;

import br.com.ifba.gestaoanimal.pessoa.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PessoaIRepository extends JpaRepository<Pessoa, Long> {
    List<Pessoa> findByAtivoTrue();
    
    List<Pessoa> findByNomeContainingIgnoreCaseAndAtivoTrue(String nome);
    
    List<Pessoa> findByNomeContainingIgnoreCase(String nome);
    
    boolean existsByCpf(String cpf);
}