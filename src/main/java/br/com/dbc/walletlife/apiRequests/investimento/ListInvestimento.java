package br.com.dbc.walletlife.apiRequests.investimento;

import br.com.dbc.walletlife.service.InvestimentoService;

public class ListInvestimento {

    public static void main(String[] args) {

        InvestimentoService investimentoService = new InvestimentoService();

        investimentoService.listar()
                .forEach(
                        investimento -> System.out.println(investimento.toString() + "\n")
                );
    }
}
