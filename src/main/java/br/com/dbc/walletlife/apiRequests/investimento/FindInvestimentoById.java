package br.com.dbc.walletlife.apiRequests.investimento;

import br.com.dbc.walletlife.exceptions.BancoDeDadosException;
import br.com.dbc.walletlife.modelos.Investimento;
import br.com.dbc.walletlife.modelos.Usuario;
import br.com.dbc.walletlife.service.InvestimentoService;
import br.com.dbc.walletlife.service.UsuarioService;

public class FindInvestimentoById {

    public static void main(String[] args) throws BancoDeDadosException {

        InvestimentoService investimentoService = new InvestimentoService();
        Investimento investimento = investimentoService.buscarPeloId(2);

        System.out.println(investimento != null ? investimento : "");

        UsuarioService usuarioService = new UsuarioService();

        Usuario usuario = usuarioService.buscarUsuarioPeloId(2);

        investimentoService.buscarinvestimentosPeloUsuario(usuario)
                .forEach(System.out::println);



    }

}
