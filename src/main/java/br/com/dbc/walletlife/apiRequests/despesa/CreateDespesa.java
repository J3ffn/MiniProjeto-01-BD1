package br.com.dbc.walletlife.apiRequests.despesa;

import br.com.dbc.walletlife.enumerators.TipoDespesaEReceita;
import br.com.dbc.walletlife.exceptions.BancoDeDadosException;
import br.com.dbc.walletlife.modelos.Despesa;
import br.com.dbc.walletlife.modelos.Receita;
import br.com.dbc.walletlife.modelos.Usuario;
import br.com.dbc.walletlife.service.DespesaService;
import br.com.dbc.walletlife.service.ReceitaService;
import br.com.dbc.walletlife.service.UsuarioService;

import java.time.LocalDate;
import java.util.List;

public class CreateDespesa {

    public static void main(String[] args) throws BancoDeDadosException {

        DespesaService despesaService = new DespesaService();

        UsuarioService usuarioService = new UsuarioService();

        Usuario usuarioJeff = usuarioService.buscarUsuarioPeloId(1);

        Usuario usuarioEduardo = usuarioService.buscarUsuarioPeloId(2);

        Usuario usuarioBeatriz = usuarioService.buscarUsuarioPeloId(3);

        Despesa despesaJefferson = new Despesa(
                TipoDespesaEReceita.FIXA,
                400,
                "Aluguel",
                LocalDate.of(2023, 10, 20),
                usuarioJeff
        );

        Despesa despesaEduardo = new Despesa(
                TipoDespesaEReceita.VARIAVEL,
                3000,
                "Pirâmide",
                LocalDate.of(2023, 9, 15),
                usuarioEduardo
        );

        Despesa despesaBeatriz = new Despesa(
                TipoDespesaEReceita.FIXA,
                500,
                "Trabalho",
                LocalDate.of(2023, 10, 23),
                usuarioBeatriz
        );

        List<Despesa> receitas = List.of(despesaJefferson, despesaEduardo, despesaBeatriz);

        receitas.forEach(despesaService::adicionarDespesa);

    }

}
