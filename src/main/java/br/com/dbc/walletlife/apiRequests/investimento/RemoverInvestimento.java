package br.com.dbc.walletlife.apiRequests.investimento;

import br.com.dbc.walletlife.service.InvestimentoService;

public class RemoverInvestimento {

    public static void main(String[] args) {

        InvestimentoService investimentoService = new InvestimentoService();

        investimentoService.removerInvestimento(1);
        investimentoService.removerInvestimento(2);
        investimentoService.removerInvestimento(3);

    }

}
