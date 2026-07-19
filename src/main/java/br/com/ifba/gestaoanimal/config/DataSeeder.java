package br.com.ifba.gestaoanimal.config;

import br.com.ifba.gestaoanimal.perfilusuario.entity.PerfilUsuario;
import br.com.ifba.gestaoanimal.perfilusuario.repository.PerfilUsuarioRepository;
import br.com.ifba.gestaoanimal.pessoa.entity.Pessoa;
import br.com.ifba.gestaoanimal.pessoa.repository.PessoaIRepository;
import br.com.ifba.gestaoanimal.usuario.entity.Usuario;
import br.com.ifba.gestaoanimal.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private PerfilUsuarioRepository perfilUsuarioRepository;

    @Autowired
    private PessoaIRepository pessoaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final String ADMIN_LOGIN = "admin";
    private static final String ADMIN_SENHA_PADRAO = "admin123";
    private static final String ADMIN_CPF = "00000000000";

    @Override
    public void run(String... args) {
        PerfilUsuario perfilAdmin = seedPerfilAdministrador();
        seedPerfilUsuarioComum();
        seedPerfilVoluntario();
        Pessoa pessoaAdmin = seedPessoaAdmin();
        seedUsuarioAdmin(perfilAdmin, pessoaAdmin);
    }

    private static final String PERMISSOES_ADMIN = String.join(",",
            "CADASTRAR_ANIMAL", "EDITAR_ANIMAL", "DESATIVAR_ANIMAL",
            "CADASTRAR_PESSOA", "EDITAR_PESSOA", "DESATIVAR_PESSOA",
            "CADASTRAR_ADOCAO", "EDITAR_ADOCAO", "DESATIVAR_ADOCAO",
            "CADASTRAR_OCORRENCIA", "EDITAR_OCORRENCIA", "DESATIVAR_OCORRENCIA",
            "CADASTRAR_REGISTRO_SAUDE", "EDITAR_REGISTRO_SAUDE", "DESATIVAR_REGISTRO_SAUDE",
            "CADASTRAR_DOACAO",
            "CADASTRAR_SOLICITACAO_VOLUNTARIO", "ANALISAR_SOLICITACAO_VOLUNTARIO"
    );

    
    private static final String PERMISSOES_USUARIO_COMUM = String.join(",",
            "CADASTRAR_OCORRENCIA",
            "CADASTRAR_DOACAO",
            "CADASTRAR_ADOCAO",
            "CADASTRAR_SOLICITACAO_VOLUNTARIO"
    );

   
    private static final String PERMISSOES_VOLUNTARIO = String.join(",",
            "CADASTRAR_OCORRENCIA", "EDITAR_OCORRENCIA",
            "CADASTRAR_DOACAO",
            "CADASTRAR_ADOCAO", "EDITAR_ADOCAO",
            "CADASTRAR_SOLICITACAO_VOLUNTARIO"
    );

    private PerfilUsuario seedPerfil(String descricao, String permissoes) {
        PerfilUsuario perfil = perfilUsuarioRepository.findByDescricao(descricao)
                .orElseGet(() -> {
                    PerfilUsuario novo = new PerfilUsuario();
                    novo.setDescricao(descricao);
                    System.out.println("[DataSeeder] Perfil '" + descricao + "' criado.");
                    return novo;
                });

        if (!permissoes.equals(perfil.getPermissoes())) {
            perfil.setPermissoes(permissoes);
            perfil = perfilUsuarioRepository.save(perfil);
            System.out.println("[DataSeeder] Permissões do perfil '" + descricao + "' atualizadas.");
        }

        return perfil;
    }

    private PerfilUsuario seedPerfilAdministrador() {
        return seedPerfil("Administrador", PERMISSOES_ADMIN);
    }

    private PerfilUsuario seedPerfilUsuarioComum() {
        return seedPerfil("Usuário Comum", PERMISSOES_USUARIO_COMUM);
    }

    private PerfilUsuario seedPerfilVoluntario() {
        return seedPerfil("Voluntário", PERMISSOES_VOLUNTARIO);
    }

    private Pessoa seedPessoaAdmin() {
        if (pessoaRepository.existsByCpf(ADMIN_CPF)) {
            return pessoaRepository.findByNomeContainingIgnoreCase("Administrador do Sistema")
                    .stream()
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException(
                            "CPF admin ja existe, mas a Pessoa nao foi encontrada."));
        }

        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Administrador do Sistema");
        pessoa.setCpf(ADMIN_CPF);
        pessoa.setAtivo(true);
        System.out.println("[DataSeeder] Pessoa 'Administrador do Sistema' criada.");
        return pessoaRepository.save(pessoa);
    }

    private void seedUsuarioAdmin(PerfilUsuario perfilAdmin, Pessoa pessoaAdmin) {
        if (usuarioRepository.findByLogin(ADMIN_LOGIN).isPresent()) {
            return;
        }

        Usuario usuario = new Usuario();
        usuario.setLogin(ADMIN_LOGIN);
        usuario.setSenha(passwordEncoder.encode(ADMIN_SENHA_PADRAO));
        usuario.setAtivo(true);
        usuario.setPessoa(pessoaAdmin);
        usuario.setPerfil(perfilAdmin);
        usuarioRepository.save(usuario);

        System.out.println("=================================================");
        System.out.println("[DataSeeder] Usuario admin criado.");
        System.out.println("[DataSeeder] Login: " + ADMIN_LOGIN);
        System.out.println("[DataSeeder] Senha: " + ADMIN_SENHA_PADRAO);
        System.out.println("=================================================");
    }
}