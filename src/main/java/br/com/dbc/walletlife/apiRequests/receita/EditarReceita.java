package br.com.dbc.walletlife.apiRequests.receita;

import br.com.dbc.walletlife.exceptions.BancoDeDadosException;
import br.com.dbc.walletlife.modelos.Usuario;
import br.com.dbc.walletlife.service.UsuarioService;

public class EditarReceita {
    public static void main(String[] args) throws BancoDeDadosException {

        UsuarioService usuarioService = new UsuarioService();

        Usuario atualizacaUsuario = usuarioService.buscarUsuarioPeloId(2);

        atualizacaUsuario.setEmail("eduardoEditado@teste.com");

        usuarioService.editarPessoa(atualizacaUsuario);

    }
}
