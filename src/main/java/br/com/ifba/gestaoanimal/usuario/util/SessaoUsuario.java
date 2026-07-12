package br.com.ifba.gestaoanimal.usuario.util;

import br.com.ifba.gestaoanimal.usuario.entity.Usuario;
import java.util.Arrays;

public class SessaoUsuario {

    private static Usuario usuarioLogado;

    private SessaoUsuario() {
    }

    public static void login(Usuario usuario) {
        usuarioLogado = usuario;
    }

    public static Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public static boolean temPermissao(String permissao) {
        if (usuarioLogado == null || usuarioLogado.getPerfil() == null) {
            return false;
        }

        String permissoes = usuarioLogado.getPerfil().getPermissoes();

        if (permissoes == null || permissoes.isBlank()) {
            return false;
        }

        return Arrays.stream(permissoes.split(","))
                .map(String::trim)
                .anyMatch(p -> p.equalsIgnoreCase(permissao));
    }

    public static void logout() {
        usuarioLogado = null;
    }
}