package br.com.ifba.gestaoanimal.solicitacaovoluntario.service;

import br.com.ifba.gestaoanimal.solicitacaovoluntario.entity.SolicitacaoVoluntario;
import br.com.ifba.gestaoanimal.solicitacaovoluntario.repository.SolicitacaoVoluntarioRepository;
import br.com.ifba.gestaoanimal.enums.StatusSolicitacaoEnum;
import br.com.ifba.gestaoanimal.enums.StatusVoluntarioEnum;
import br.com.ifba.gestaoanimal.pessoa.entity.Pessoa;
import br.com.ifba.gestaoanimal.pessoa.repository.PessoaIRepository;
import br.com.ifba.gestaoanimal.perfilusuario.entity.PerfilUsuario;
import br.com.ifba.gestaoanimal.perfilusuario.repository.PerfilUsuarioRepository;
import br.com.ifba.gestaoanimal.usuario.entity.Usuario;
import br.com.ifba.gestaoanimal.usuario.service.UsuarioIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SolicitacaoVoluntarioService implements SolicitacaoVoluntarioIService {

  
    private static final String SENHA_PADRAO_VOLUNTARIO = "voluntario123";

    @Autowired
    private SolicitacaoVoluntarioRepository solicitacaoVoluntarioRepository;

    @Autowired
    private PessoaIRepository pessoaRepository;

    @Autowired
    private PerfilUsuarioRepository perfilUsuarioRepository;

    @Autowired
    private UsuarioIService usuarioService;

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

    @Override
    public SolicitacaoVoluntario aprovar(Long id, Pessoa analisadoPor, String observacaoAdmin) {
        SolicitacaoVoluntario solicitacao = findById(id);
        if (solicitacao == null) {
            throw new IllegalArgumentException("Solicitação não encontrada.");
        }

        solicitacao.setStatus(StatusSolicitacaoEnum.APROVADA);
        solicitacao.setDataResposta(LocalDateTime.now());
        solicitacao.setObservacaoAdmin(observacaoAdmin);
        solicitacao.setAnalisadoPor(analisadoPor);
        solicitacao = solicitacaoVoluntarioRepository.save(solicitacao);

        Pessoa solicitante = solicitacao.getSolicitante();
        solicitante.setStatusVoluntario(StatusVoluntarioEnum.ATIVO);
        solicitante = pessoaRepository.save(solicitante);

        garantirLoginDeVoluntario(solicitante);

        return solicitacao;
    }

    @Override
    public SolicitacaoVoluntario recusar(Long id, Pessoa analisadoPor, String observacaoAdmin) {
        SolicitacaoVoluntario solicitacao = findById(id);
        if (solicitacao == null) {
            throw new IllegalArgumentException("Solicitação não encontrada.");
        }

        solicitacao.setStatus(StatusSolicitacaoEnum.RECUSADA);
        solicitacao.setDataResposta(LocalDateTime.now());
        solicitacao.setObservacaoAdmin(observacaoAdmin);
        solicitacao.setAnalisadoPor(analisadoPor);
        return solicitacaoVoluntarioRepository.save(solicitacao);
    }

    private void garantirLoginDeVoluntario(Pessoa pessoa) {
        PerfilUsuario perfilVoluntario = perfilUsuarioRepository.findByDescricao("Voluntário")
                .orElseThrow(() -> new IllegalStateException("Perfil 'Voluntário' não encontrado. Reinicie a aplicação."));

        Usuario usuario = usuarioService.findByPessoaId(pessoa.getId());

        if (usuario == null) {
            Usuario novo = new Usuario();
            novo.setLogin(pessoa.getCpf());
            novo.setSenha(SENHA_PADRAO_VOLUNTARIO);
            novo.setPessoa(pessoa);
            novo.setPerfil(perfilVoluntario);
            usuarioService.save(novo);
            System.out.println("[SolicitacaoVoluntario] Login criado para " + pessoa.getNome()
                    + " — usuário: " + pessoa.getCpf() + " / senha: " + SENHA_PADRAO_VOLUNTARIO);
        } else {
            usuario.setPerfil(perfilVoluntario);
            usuarioService.update(usuario);
            System.out.println("[SolicitacaoVoluntario] Perfil de " + pessoa.getNome() + " atualizado para Voluntário.");
        }
    }
}