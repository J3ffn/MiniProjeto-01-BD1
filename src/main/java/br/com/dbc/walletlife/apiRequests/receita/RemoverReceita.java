package br.com.dbc.walletlife.apiRequests.receita;

import br.com.dbc.walletlife.service.ReceitaService;

public class RemoverReceita {

    public static void main(String[] args) {

        ReceitaService receitaService = new ReceitaService();

        receitaService.removerReceita(1);
        receitaService.removerReceita(2);
        receitaService.removerReceita(3);

    }

}
