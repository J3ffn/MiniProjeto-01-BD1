package br.com.dbc.walletlife.service;

import br.com.dbc.walletlife.exceptions.BancoDeDadosException;
import br.com.dbc.walletlife.modelos.Investimento;
import br.com.dbc.walletlife.repository.InvestimentoDAO;

import java.util.List;

public class InvestimentoService {
    private InvestimentoDAO investimentoDao;

    public InvestimentoService() {
        investimentoDao = new InvestimentoDAO();
    }

    // criação de um objeto
    public void adicionarInvestimento(Investimento investimento) {
        try {
            Investimento investimentoAdicionado = investimentoDao.adicionar(investimento);
            System.out.println();
            System.out.println("INVESTIMENTO adicionado com sucesso!");
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    // remoção
    public void removerInvestimento(Integer id) {
        try {
            investimentoDao.remover(id);
            System.out.println();

            System.out.println("INVESTIMENTO removido com sucesso!");
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    // atualização de um objeto
    public void editarInvestimento(Integer id, Investimento investimento) {
        try {
            investimentoDao.editar(investimento);
            System.out.println();
            System.out.println("INVESTIMENTO alterado com sucesso!");
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    // leitura
    public List<Investimento> listar(Integer idUsuario) {
        try {
            return investimentoDao.listar(idUsuario);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return null;
    }

}
