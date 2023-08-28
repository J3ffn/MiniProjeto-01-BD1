package br.com.dbc.walletlife.repository;

import br.com.dbc.walletlife.exceptions.BancoDeDadosException;

import java.util.List;

public interface Repositorio<CHAVE, OBJETO> {

    OBJETO adicionar(OBJETO object) throws BancoDeDadosException;

    void remover(CHAVE id) throws BancoDeDadosException;

    OBJETO editar(OBJETO objeto) throws BancoDeDadosException;

    List<OBJETO> listar(Integer idUsuario) throws BancoDeDadosException;

}
