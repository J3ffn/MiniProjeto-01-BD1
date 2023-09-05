package br.com.dbc.walletlife.service;


import br.com.dbc.walletlife.exceptions.BancoDeDadosException;
import br.com.dbc.walletlife.modelos.Despesa;
import br.com.dbc.walletlife.modelos.Usuario;
import br.com.dbc.walletlife.dao.DespesaDAO;

import java.util.List;

public class DespesaService {

    private DespesaDAO despesaDAO;

    public DespesaService() {
        despesaDAO = new DespesaDAO();
    }

    // criação de um objeto
    public Despesa adicionarDespesa(Despesa despesa) {
        try {

            Despesa despesaAdicionado = despesaDAO.adicionar(despesa);
            System.out.println();
            System.out.println("DESPESA adicionada com sucesso!");
            return despesaAdicionado;
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
//            System.out.println("TRACE: ");
//            e.printStackTrace();
        }
        return null;
    }

    // remoção
    public void removerDespesa(Integer id) {
        try {
            despesaDAO.remover(id);
            System.out.println();
            System.out.println("DESPESA removida com sucesso!");
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    // atualização de um objeto
    public void editarDespesa(Despesa despesa) {
        try {
            despesaDAO.editar(despesa);
            System.out.println();
            System.out.println("DESPESA alterada com sucesso!");
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    // leitura
    public List<Despesa> listarDespesa() {
        try {
            return despesaDAO.listar(null);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Despesa buscarPeloId(Integer idDespesa) throws BancoDeDadosException {
        return despesaDAO.listar(idDespesa).get(0);
    }

    public List<Despesa> buscarReceitasPeloUsuario(Usuario usuario) throws BancoDeDadosException {
        return despesaDAO.listarReceitaPeloUsuario(usuario);
    }

}
