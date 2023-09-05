package br.com.dbc.walletlife.apiRequests.receita;

import br.com.dbc.walletlife.enumerators.TipoDespesaEReceita;
import br.com.dbc.walletlife.exceptions.BancoDeDadosException;
import br.com.dbc.walletlife.modelos.Receita;
import br.com.dbc.walletlife.modelos.Usuario;
import br.com.dbc.walletlife.service.ReceitaService;
import br.com.dbc.walletlife.service.UsuarioService;

import java.util.List;
import java.util.Optional;

public class EditarReceita {
    public static void main(String[] args) throws BancoDeDadosException {

        ReceitaService receitaService = new ReceitaService();

        Receita receita = receitaService.buscarPeloId(1);

        receita.setDecricao("Bônus");
        receita.setBanco("Itaú");
        receita.setEmpresa("Uol");
        receita.setValor(400D);
        receita.setTipo(TipoDespesaEReceita.VARIAVEL);

        receitaService.editarReceita(receita);

    }
}
