package br.com.ifba.gestaoanimal.doacao.service;

import br.com.ifba.gestaoanimal.doacao.entity.Doacao;
import br.com.ifba.gestaoanimal.doacao.repository.DoacaoRepository;
import br.com.ifba.gestaoanimal.enums.TipoDoacaoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class DoacaoService implements DoacaoIService {

    @Autowired
    private DoacaoRepository doacaoRepository;

    @Override
    public Doacao save(Doacao doacao) {
        validar(doacao);
        return doacaoRepository.save(doacao);
    }

    @Override
    public Doacao update(Doacao doacao) {
        if (doacao.getId() == null) {
            throw new IllegalArgumentException("Doação sem ID não pode ser atualizada.");
        }
        if (!doacaoRepository.existsById(doacao.getId())) {
            throw new IllegalArgumentException("Doação não encontrada para atualização.");
        }
        validar(doacao);
        return doacaoRepository.save(doacao);
    }

    @Override
    public void delete(Long id) {
        if (id == null || !doacaoRepository.existsById(id)) {
            throw new IllegalArgumentException("Doação não encontrada para exclusão.");
        }
        doacaoRepository.deleteById(id);
    }

    @Override
    public Doacao findById(Long id) {
        return doacaoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Doação não encontrada."));
    }

    @Override
    public List<Doacao> findAll() {
        return doacaoRepository.findAllByOrderByDataDesc();
    }

    @Override
    public List<Doacao> findByDoador(Long doadorId) {
        return doacaoRepository.findByDoadorId(doadorId);
    }

    @Override
    public List<Doacao> findByTipo(TipoDoacaoEnum tipo) {
        return doacaoRepository.findByTipo(tipo);
    }

    @Override
    public List<Doacao> findByPeriodo(LocalDate inicio, LocalDate fim) {
        return doacaoRepository.findByDataBetween(inicio, fim);
    }

    private void validar(Doacao doacao) {
        if (doacao.getDoador() == null || doacao.getDoador().getId() == null) {
            throw new IllegalArgumentException("Selecione o doador.");
        }
        if (doacao.getTipo() == null) {
            throw new IllegalArgumentException("Selecione o tipo de doação.");
        }
        if (doacao.getData() == null) {
            throw new IllegalArgumentException("Informe a data da doação.");
        }
        if (doacao.getData().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("A data da doação não pode ser futura.");
        }

        if (doacao.getTipo() == TipoDoacaoEnum.FINANCEIRA) {
            if (doacao.getValor() == null || doacao.getValor().compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("Informe um valor válido para doação financeira.");
            }
            doacao.setQuantidade(null);
            doacao.setUnidade(null);
        } else {
            if (doacao.getQuantidade() == null || doacao.getQuantidade() <= 0) {
                throw new IllegalArgumentException("Informe uma quantidade válida para a doação.");
            }
            if (doacao.getUnidade() == null || doacao.getUnidade().isBlank()) {
                throw new IllegalArgumentException("Informe a unidade de medida (ex: kg, unidades, litros).");
            }
            doacao.setValor(null);
        }
    }
}