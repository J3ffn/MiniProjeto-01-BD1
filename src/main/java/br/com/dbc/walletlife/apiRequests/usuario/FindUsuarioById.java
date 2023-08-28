package br.com.dbc.walletlife.apiRequests.usuario;

import br.com.dbc.walletlife.exceptions.BancoDeDadosException;
import br.com.dbc.walletlife.modelos.Usuario;
import br.com.dbc.walletlife.service.UsuarioService;

public class FindUsuarioById {

    public static void main(String[] args) throws BancoDeDadosException {

        UsuarioService usuarioService = new UsuarioService();

        Integer idUsuario = 1;

        Usuario usuarioPuxadoPeloId = usuarioService.buscarUsuarioPeloId(idUsuario);

        System.out.println(usuarioPuxadoPeloId.toString());

    }

}
