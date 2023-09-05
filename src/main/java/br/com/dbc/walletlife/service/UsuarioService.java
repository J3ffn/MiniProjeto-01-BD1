package br.com.dbc.walletlife.service;

import br.com.dbc.walletlife.exceptions.BancoDeDadosException;
import br.com.dbc.walletlife.modelos.Usuario;
import br.com.dbc.walletlife.dao.UsuarioDAO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class UsuarioService {

    private static final UsuarioDAO USUARIO_DAO;

    static {
        USUARIO_DAO = new UsuarioDAO();
    }

    public UsuarioService() {
    }

    public boolean validarEmail(String email) {
        try {
            return USUARIO_DAO.validarEmail(email);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Usuario login(String email, String senha) {
        try {
            return USUARIO_DAO.loginUsuario(email, senha);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // criação de um objeto
    public void adicionarUsuario(Usuario usuario) {
        try {
            if (usuario.getCpf().length() != 11) {
                throw new Exception("CPF Invalido!");
            }

            boolean maiorDeIdade = Period.between(usuario.getDataNascimento(), LocalDate.now()).getYears() >= 18;

            if (!checarCamposUnicosJaCadastrados(usuario) && maiorDeIdade) {
                USUARIO_DAO.adicionar(usuario);
                System.out.println();
                System.out.println("USUÁRIO criado com sucesso!");
            }

        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    public void removerPessoa(Integer id) {
        try {
            USUARIO_DAO.remover(id);
            System.out.println();
            System.out.println("USUÁRIO removido com sucesso!");
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    // atualização de um objeto
    public Usuario editarPessoa(Usuario usuario) {
        Usuario usuarioEditado = null;
        try {
            usuarioEditado = USUARIO_DAO.editar(usuario);
            System.out.println();
            System.out.println("USUÁRIO Alterada com sucesso!");

        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return usuarioEditado;
    }

    // leitura
    public List<Usuario> listarPessoas() {
        try {
            List<Usuario> listar = USUARIO_DAO.listar(null);
            return listar;
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Usuario buscarUsuarioPeloId(Integer idUsuario) throws BancoDeDadosException {

        return USUARIO_DAO.listar(idUsuario)
                .stream()
                .findFirst()
                .orElseThrow(() -> new BancoDeDadosException("Nenhum usuário foi encontrado."));

    }

    private boolean checarCamposUnicosJaCadastrados(Usuario usuario) throws SQLException {
        boolean camposExistentes = false;

        String emailUsuario = usuario.getEmail();
        String cpfUsuario = usuario.getCpf();

        System.out.println();
        if (USUARIO_DAO.validarEmail(emailUsuario)) {
            System.out.println(emailUsuario + " já cadastrado");
            camposExistentes = true;
        }
        if (USUARIO_DAO.validarCPF(cpfUsuario)) {
            System.out.println(cpfUsuario + " já cadastrado");
            camposExistentes = true;
        }

        return camposExistentes;
    }

}
