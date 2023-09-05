package br.com.dbc.walletlife.apiRequests.receita;

import br.com.dbc.walletlife.service.ReceitaService;

public class ListReceita {

    public static void main(String[] args) {

        ReceitaService receitaService = new ReceitaService();

        receitaService.listarReceitas()
                .forEach(
                        usuario -> System.out.println(usuario.toString() + "\n")
                );
    }
}
