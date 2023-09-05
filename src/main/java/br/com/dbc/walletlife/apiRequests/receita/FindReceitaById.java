package br.com.dbc.walletlife.apiRequests.receita;

import br.com.dbc.walletlife.exceptions.BancoDeDadosException;
import br.com.dbc.walletlife.modelos.Receita;
import br.com.dbc.walletlife.modelos.Usuario;
import br.com.dbc.walletlife.service.ReceitaService;
import br.com.dbc.walletlife.service.UsuarioService;

import java.util.List;

public class FindReceitaById {

    public static void main(String[] args) throws BancoDeDadosException {

        UsuarioService usuarioService = new UsuarioService();
        Usuario usuario = usuarioService.buscarUsuarioPeloId(1);


        ReceitaService receitaService = new ReceitaService();
        Receita receita = receitaService.buscarPeloId(1);
        System.out.println(receita);


        List<Receita> receitasDeUsuario = receitaService.buscarReceitasPeloUsuario(usuario);
        receitasDeUsuario.forEach(System.out::println);
    }

}
