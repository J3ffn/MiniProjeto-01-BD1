package br.com.dbc.walletlife.apiRequests.receita;

import br.com.dbc.walletlife.enumerators.TipoDespesaEReceita;
import br.com.dbc.walletlife.exceptions.BancoDeDadosException;
import br.com.dbc.walletlife.modelos.Receita;
import br.com.dbc.walletlife.modelos.Usuario;
import br.com.dbc.walletlife.service.ReceitaService;
import br.com.dbc.walletlife.service.UsuarioService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CreateReceita {

    public static void main(String[] args) throws BancoDeDadosException {

        ReceitaService receitaService = new ReceitaService();

        UsuarioService usuarioService = new UsuarioService();

        Usuario usuarioJeff = usuarioService.buscarUsuarioPeloId(1);

        Usuario usuarioEduardo = usuarioService.buscarUsuarioPeloId(2);

        Usuario usuarioBeatriz = usuarioService.buscarUsuarioPeloId(3);

        Receita receitaValeRefeicaoJefferson = new Receita(
                TipoDespesaEReceita.FIXA,
                200,
                "vale-refeição",
                "Nubank",
                "DBC",
                usuarioJeff
        );

        Receita receitaTrabalhoOnlineEduardo = new Receita(
                TipoDespesaEReceita.VARIAVEL,
                30000,
                "Pirâmide",
                "Anônimo",
                "fantasma",
                usuarioEduardo
        );

        Receita receitaTrabalhoBeatriz = new Receita(
                TipoDespesaEReceita.FIXA,
                500,
                "Trabalho",
                "Itaú",
                "DBC",
                usuarioBeatriz
        );

        List<Receita> receitas = List.of(receitaValeRefeicaoJefferson, receitaTrabalhoOnlineEduardo, receitaTrabalhoBeatriz);

        receitas.forEach(receitaService::adicionarReceita);

    }

}
