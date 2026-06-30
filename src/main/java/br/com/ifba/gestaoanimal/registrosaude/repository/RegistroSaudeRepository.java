package br.com.ifba.gestaoanimal.registrosaude.repository;
 
import br.com.ifba.gestaoanimal.registrosaude.entity.RegistroSaude;
import br.com.ifba.gestaoanimal.enums.TipoProcedimentoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
import java.util.List;
 
@Repository
public interface RegistroSaudeRepository extends JpaRepository<RegistroSaude, Long> {
    List<RegistroSaude> findByAnimalId(Long animalId);
    List<RegistroSaude> findByTipo(TipoProcedimentoEnum tipo);
}
 