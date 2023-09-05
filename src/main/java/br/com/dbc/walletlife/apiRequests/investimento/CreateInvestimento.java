package br.com.dbc.walletlife.apiRequests.investimento;

import br.com.dbc.walletlife.exceptions.BancoDeDadosException;
import br.com.dbc.walletlife.modelos.Investimento;
import br.com.dbc.walletlife.modelos.Usuario;
import br.com.dbc.walletlife.service.InvestimentoService;
import br.com.dbc.walletlife.service.UsuarioService;

import java.time.LocalDate;
import java.util.List;

public class CreateInvestimento {

    public static void main(String[] args) throws BancoDeDadosException {

        InvestimentoService investimentoService = new InvestimentoService();

        UsuarioService usuarioService = new UsuarioService();

        Usuario usuarioJeff = usuarioService.buscarUsuarioPeloId(1);
        Investimento investimento = new Investimento(
                "XP",
                "Invetimento",
                200D,
                LocalDate.of(2023, 9, 1),
                "Investimento Microsoft",
                usuarioJeff
        );

        Usuario usuarioEduardo = usuarioService.buscarUsuarioPeloId(2);
        Investimento investimento2 = new Investimento(
                "NuInvest",
                "Invetimento",
                200D,
                LocalDate.of(2023, 9, 1),
                "Investimento Microsoft",
                usuarioEduardo
        );

        Usuario usuarioBeatriz = usuarioService.buscarUsuarioPeloId(3);
        Investimento investimento3 = new Investimento(
                "Real",
                "Ação",
                350D,
                LocalDate.of(2023, 9, 1),
                "Investimento Microsoft",
                usuarioBeatriz
        );

        List<Investimento> investimentos = List.of(investimento, investimento2, investimento3);

        investimentos.forEach(investimentoService::adicionarInvestimento);


    }

}
