package br.com.dbc.walletlife.apiRequests.despesa;

import br.com.dbc.walletlife.enumerators.TipoDespesaEReceita;
import br.com.dbc.walletlife.exceptions.BancoDeDadosException;
import br.com.dbc.walletlife.modelos.Despesa;
import br.com.dbc.walletlife.modelos.Receita;
import br.com.dbc.walletlife.modelos.Usuario;
import br.com.dbc.walletlife.service.DespesaService;
import br.com.dbc.walletlife.service.ReceitaService;
import br.com.dbc.walletlife.service.UsuarioService;

public class EditarDespesa {
    public static void main(String[] args) throws BancoDeDadosException {

        DespesaService despesaService = new DespesaService();

        Despesa despesa = despesaService.buscarPeloId(1);

        despesa.setDecricao("Bônus");
        despesa.setValor(400D);
        despesa.setTipo(TipoDespesaEReceita.VARIAVEL);

        despesaService.editarDespesa(despesa);

    }
}
