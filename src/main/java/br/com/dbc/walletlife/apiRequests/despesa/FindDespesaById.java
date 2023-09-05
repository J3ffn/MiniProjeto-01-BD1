package br.com.dbc.walletlife.apiRequests.despesa;

import br.com.dbc.walletlife.exceptions.BancoDeDadosException;
import br.com.dbc.walletlife.modelos.Despesa;
import br.com.dbc.walletlife.modelos.Usuario;
import br.com.dbc.walletlife.service.DespesaService;
import br.com.dbc.walletlife.service.UsuarioService;

import java.util.List;

public class FindDespesaById {

    public static void main(String[] args) throws BancoDeDadosException {

        DespesaService despesaService = new DespesaService();

        Despesa despesa = despesaService.buscarPeloId(1);

        System.out.println(despesa);

        UsuarioService usuarioService = new UsuarioService();
        Usuario usuario = usuarioService.buscarUsuarioPeloId(1);

        List<Despesa> despesas = despesaService.buscarReceitasPeloUsuario(usuario);
        despesas.forEach(System.out::println);
    }

}
