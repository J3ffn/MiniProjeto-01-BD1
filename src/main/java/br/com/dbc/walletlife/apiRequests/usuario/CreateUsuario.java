package br.com.dbc.walletlife.apiRequests.usuario;

import br.com.dbc.walletlife.modelos.Usuario;
import br.com.dbc.walletlife.service.UsuarioService;

import java.time.LocalDate;

public class CreateUsuario {

    public static void main(String[] args) {

        UsuarioService usuarioService = new UsuarioService();

        Usuario usuarioCobaia = new Usuario(
                "Jeff",
                LocalDate.of(2000, 12, 12),
                "11122233312",
                "jeff@gmail.com",
                "123");

        usuarioService.adicionarUsuario(usuarioCobaia);
    }

}
