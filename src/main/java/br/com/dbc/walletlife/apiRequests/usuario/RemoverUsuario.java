package br.com.dbc.walletlife.apiRequests.usuario;

import br.com.dbc.walletlife.service.UsuarioService;

public class RemoverUsuario {

    public static void main(String[] args) {

        UsuarioService usuarioService = new UsuarioService();

        usuarioService.removerPessoa(1);
        usuarioService.removerPessoa(2);
        usuarioService.removerPessoa(3);

    }

}
