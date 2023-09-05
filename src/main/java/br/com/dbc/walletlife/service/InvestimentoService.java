package br.com.dbc.walletlife.service;

import br.com.dbc.walletlife.dao.InvestimentoDAO;
import br.com.dbc.walletlife.exceptions.BancoDeDadosException;
import br.com.dbc.walletlife.modelos.Investimento;
import br.com.dbc.walletlife.modelos.Usuario;

import java.util.List;

public class InvestimentoService {
    private InvestimentoDAO investimentoDAO;

    public InvestimentoService() {
        investimentoDAO = new InvestimentoDAO();
    }

    // criação de um objeto
    public void adicionarInvestimento(Investimento investimento) {
        try {
            Investimento investimentoAdicionado = investimentoDAO.adicionar(investimento);
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
            investimentoDAO.remover(id);
            System.out.println();

            System.out.println("INVESTIMENTO removido com sucesso!");
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    // atualização de um objeto
    public void editarInvestimento(Investimento investimento) {
        try {
            investimentoDAO.editar(investimento);
            System.out.println();
            System.out.println("INVESTIMENTO alterado com sucesso!");
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    // leitura
    public List<Investimento> listar() {
        try {
            return investimentoDAO.listar(null);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Investimento buscarPeloId(Integer idInvestimento) throws BancoDeDadosException {
        Investimento investimento = investimentoDAO.buscarPeloId(idInvestimento);
        if (investimento == null) {
            System.out.println("Investimento não existe.");
        }
        return investimento;
    }

    public List<Investimento> listarReceitas() {
        try {
            return investimentoDAO.listar(null);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Investimento> buscarinvestimentosPeloUsuario(Usuario usuario) {
        try {
            List<Investimento> investimentos = investimentoDAO.listarInvestimentoPeloUsuario(usuario);
            if (investimentos.isEmpty()) {
                System.out.println("Não existe investimentos vinculado a esse usuario.");
            }
            return investimentos;
        } catch (BancoDeDadosException e) {
            System.out.println("Investimento não encontrado.");
            return null;
        }
    }

}
