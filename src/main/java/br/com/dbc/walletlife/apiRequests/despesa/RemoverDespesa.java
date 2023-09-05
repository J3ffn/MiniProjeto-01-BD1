package br.com.dbc.walletlife.apiRequests.despesa;

import br.com.dbc.walletlife.service.DespesaService;

public class RemoverDespesa {

    public static void main(String[] args) {

        DespesaService despesaService = new DespesaService();

        despesaService.removerDespesa(1);
        despesaService.removerDespesa(2);
        despesaService.removerDespesa(3);

    }

}
