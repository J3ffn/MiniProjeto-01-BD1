package br.com.dbc.walletlife.apiRequests.usuario;

import br.com.dbc.walletlife.modelos.Usuario;
import br.com.dbc.walletlife.service.UsuarioService;

import java.time.LocalDate;
import java.util.List;

public class CreateUsuario {

    public static void main(String[] args) {

        UsuarioService usuarioService = new UsuarioService();

        Usuario usuarioJeff = new Usuario(
                "Jeff",
                LocalDate.of(2000, 12, 12),
                "04673072682",
                "jeff@teste.com",
                "9215221#");

        Usuario usuarioEduardo = new Usuario(
                "Jeff",
                LocalDate.of(2000, 12, 12),
                "18412957301",
                "eduardo@teste.com",
                "32@1423");

        Usuario usuarioBeatriz = new Usuario(
                "Jeff",
                LocalDate.of(2000, 12, 12),
                "16342938271",
                "beatriz@teste.com",
                "15112!476");

        List<Usuario> listaDeusuarios = List.of(usuarioJeff, usuarioEduardo, usuarioBeatriz);

        listaDeusuarios.forEach(usuarioService::adicionarUsuario);



    }

}
