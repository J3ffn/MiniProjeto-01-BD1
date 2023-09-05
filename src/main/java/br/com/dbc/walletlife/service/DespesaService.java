package br.com.dbc.walletlife.service;


import br.com.dbc.walletlife.exceptions.BancoDeDadosException;
import br.com.dbc.walletlife.modelos.Despesa;
import br.com.dbc.walletlife.repository.DespesaDAO;

import java.util.List;

public class DespesaService {

    private DespesaDAO despesaDao;

    public DespesaService() {
        despesaDao = new DespesaDAO();
    }

    // criação de um objeto
    public void adicionarDespesa(Despesa despesa) {
        try {

            Despesa despesaAdicionado = despesaDao.adicionar(despesa);
            System.out.println();
            System.out.println("DESPESA adicionada com sucesso!");
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
//            System.out.println("TRACE: ");
//            e.printStackTrace();
        }
    }

    // remoção
    public void removerDespesa(Integer id) {
        try {
            despesaDao.remover(id);
            System.out.println();
            System.out.println("DESPESA removida com sucesso!");
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    // atualização de um objeto
    public void editarDespesa(Integer id, Despesa despesa) {
        try {
            despesaDao.editar(despesa);
            System.out.println();
            System.out.println("DESPESA alterada com sucesso!");
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    // leitura
    public List<Despesa> listarDespesa(Integer idUsuario) {
        try {
            return despesaDao.listar(idUsuario);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return null;
    }


}
