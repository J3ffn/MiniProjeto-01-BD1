package br.com.dbc.walletlife.apiRequests.receita;

import br.com.dbc.walletlife.service.UsuarioService;

public class ListReceita {

    public static void main(String[] args) {

        UsuarioService usuarioService = new UsuarioService();

        usuarioService.listarPessoas()
                .forEach(
                        usuario -> System.out.println(usuario.toString() + "\n")
                );
    }
}
