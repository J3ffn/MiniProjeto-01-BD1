package br.com.dbc.walletlife.apiRequests.investimento;

import br.com.dbc.walletlife.exceptions.BancoDeDadosException;
import br.com.dbc.walletlife.modelos.Investimento;
import br.com.dbc.walletlife.modelos.Usuario;
import br.com.dbc.walletlife.service.InvestimentoService;
import br.com.dbc.walletlife.service.UsuarioService;

public class EditarInvestimento {
    public static void main(String[] args) throws BancoDeDadosException {

        InvestimentoService investimentoService = new InvestimentoService();

        Investimento investimento = investimentoService.buscarPeloId(1);

        investimento.setCorretora("Real");
        investimento.setDescricao("Ação Apple");
        investimento.setTipo("Ação");
        investimento.setValor(500D);

        investimentoService.editarInvestimento(investimento);

        System.out.println(investimento);
    }
}
