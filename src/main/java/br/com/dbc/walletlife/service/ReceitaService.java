package br.com.dbc.walletlife.service;

import br.com.dbc.walletlife.exceptions.BancoDeDadosException;
import br.com.dbc.walletlife.modelos.Receita;
import br.com.dbc.walletlife.repository.ReceitaRepository;

import java.util.List;

public class ReceitaService {

    private ReceitaRepository receitaRepository;

    public ReceitaService() {
        receitaRepository = new ReceitaRepository();
    }

    // Criação de um objeto
    public Receita adicionarReceita(Receita receita) {
        Receita receitaAdicionado = null;
        try {
            receitaAdicionado = receitaRepository.adicionar(receita);
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
            receitaRepository.remover(id);
            System.out.println();
            System.out.println("Removido com sucesso!");
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    // Atualização de um objeto
    public Receita editarReceita(Integer id, Receita receita) {
        Receita ReceitaEditada = null;
        try {
            ReceitaEditada = receitaRepository.editar(receita);
            System.out.println();
            System.out.println("Alteração realizada com sucesso!");
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return ReceitaEditada;
    }

    // Leitura
    public List<Receita> listar(Integer idUsuario) {
        try {
            return receitaRepository.listar(idUsuario);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return null;
    }
}
