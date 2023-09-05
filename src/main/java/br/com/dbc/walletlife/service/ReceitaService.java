package br.com.dbc.walletlife.service;

import br.com.dbc.walletlife.exceptions.BancoDeDadosException;
import br.com.dbc.walletlife.modelos.Receita;
import br.com.dbc.walletlife.modelos.Usuario;
import br.com.dbc.walletlife.dao.ReceitaDAO;

import java.util.List;

public class ReceitaService {

    private ReceitaDAO receitaDAO;

    public ReceitaService() {
        receitaDAO = new ReceitaDAO();
    }

    // Criação de um objeto
    public Receita adicionarReceita(Receita receita) {
        Receita receitaAdicionado = null;
        try {
            receitaAdicionado = receitaDAO.adicionar(receita);
            System.out.println();
            System.out.println("Receita adicinada com sucesso!");
            return receitaAdicionado;
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }
        return receitaAdicionado;
    }

    // Remoção
    public void removerReceita(Integer id) {
        try {
            receitaDAO.remover(id);
            System.out.println();
            System.out.println("Removido com sucesso!");
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    // Atualização de um objeto
    public Receita editarReceita(Receita receita) {
        Receita ReceitaEditada = null;
        try {
            ReceitaEditada = receitaDAO.editar(receita);
            System.out.println();
            System.out.println("Alteração realizada com sucesso!");
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return ReceitaEditada;
    }

    // Leitura
    public List<Receita> listar(Integer idReceita) {
        try {
            return receitaDAO.listar(idReceita);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Receita buscarPeloId(Integer idReceita) throws BancoDeDadosException {
        return receitaDAO.listar(idReceita).get(0);
    }

    public List<Receita> listarReceitas() {
        try {
            return receitaDAO.listar(null);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Receita> buscarReceitasPeloUsuario(Usuario usuario) throws BancoDeDadosException {
        return receitaDAO.listarReceitaPeloUsuario(usuario);
    }
}
