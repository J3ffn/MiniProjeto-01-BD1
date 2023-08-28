package br.com.dbc.walletlife.service;

import br.com.dbc.walletlife.exceptions.BancoDeDadosException;
import br.com.dbc.walletlife.modelos.Usuario;
import br.com.dbc.walletlife.repository.UsuarioRepository;

import java.sql.SQLException;
import java.util.List;

public class UsuarioService {

    private static final UsuarioRepository usuarioRepository;

    static {
        usuarioRepository = new UsuarioRepository();
    }

    public UsuarioService() {
    }

    public boolean validarEmail(String email) {
        try {
            return usuarioRepository.validarEmail(email);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Usuario login(String email, String senha) {
        try {
            return usuarioRepository.loginUsuario(email, senha);
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

            if (!checarCamposUnicos(usuario)) {
                usuarioRepository.adicionar(usuario);
                System.out.println();
                System.out.println("USUÁRIO criado com sucesso!");
            }

        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
//            System.out.println("TRACE: ");
//            e.printStackTrace();
        }
    }

    public void removerPessoa(Integer id) {
        try {
            usuarioRepository.remover(id);
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
            usuarioEditado = usuarioRepository.editar(usuario);
            System.out.println();
            System.out.println("USUARIO Alterada com sucesso!");
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return usuarioEditado;
    }

    // leitura
    public List<Usuario> listarPessoas() {
        try {
            List<Usuario> listar = usuarioRepository.listar(null);
            listar.forEach(System.out::println);
            return listar;
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Usuario buscarUsuarioPeloId(Integer idUsuario) throws BancoDeDadosException {

        return usuarioRepository.listar(idUsuario)
                .stream()
                .findFirst()
                .orElseThrow(() -> new BancoDeDadosException("Nenhum usuário foi encontrado."));

    }

    private boolean checarCamposUnicos(Usuario usuario) throws SQLException {
        boolean camposExistentes = false;

        String emailUsuario = usuario.getEmail();
        String cpfUsuario = usuario.getCpf();

        if (usuarioRepository.validarEmail(emailUsuario)) {
            System.out.println("email Já cadastrado");
            camposExistentes = true;
        }
        if (usuarioRepository.validarCPF(cpfUsuario)) {
            System.out.println("cpf Já cadastrado");
            camposExistentes = true;
        }

        return camposExistentes;
    }

}
