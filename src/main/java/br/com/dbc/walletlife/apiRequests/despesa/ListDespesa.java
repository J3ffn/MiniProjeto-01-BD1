package br.com.dbc.walletlife.apiRequests.despesa;

import br.com.dbc.walletlife.service.DespesaService;

public class ListDespesa {

    public static void main(String[] args) {

        DespesaService despesaService = new DespesaService();

        despesaService.listarDespesa()
                .forEach(
                        despesa -> System.out.println(despesa.toString() + "\n")
                );
    }
}
