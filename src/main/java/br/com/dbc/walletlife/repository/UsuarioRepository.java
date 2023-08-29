package br.com.dbc.walletlife.repository;


import br.com.dbc.walletlife.exceptions.BancoDeDadosException;
import br.com.dbc.walletlife.modelos.Usuario;

import javax.persistence.*;
import java.sql.*;
import java.util.List;

public class UsuarioRepository extends DAO implements Repositorio<Integer, Usuario> {

    public boolean validarEmail(String email) throws SQLException {
        EntityManager con = this.getEntityManager();
        String sql = "SELECT u FROM Usuario u " +
                "WHERE u.email = '" + email + "'";

        TypedQuery<Usuario> usuario = con.createQuery(sql, Usuario.class);

        List<Usuario> usuarioStream = usuario.getResultList();

        boolean campoExistente = usuarioStream.size() > 0;

        con.close();
        return campoExistente;
    }

    public Usuario loginUsuario(String email, String senha) throws SQLException {
        EntityManager con = this.getEntityManager();

        String sql = "SELECT u FROM Usuario u " +
                "WHERE u.email = :email AND u.senha = :senha";

        TypedQuery<Usuario> query = con.createQuery(sql, Usuario.class);

        query.setParameter("email", email);
        query.setParameter("senha", senha);

        List<Usuario> usuario = query.getResultList();

        con.close();

        return usuario.stream().findFirst().orElse(null);
    }

    @Override
    public Usuario adicionar(Usuario usuario) throws BancoDeDadosException {
        EntityManager con = null;
        EntityTransaction transactional = null;
        try {
            con = this.getEntityManager();
            transactional = con.getTransaction();

            transactional.begin();
            con.persist(usuario);

            transactional.commit();
            return usuario;

        } catch (PersistenceException e){
            if (transactional.isActive()) {
                transactional.rollback();
            }
//            e.printStackTrace();
            throw new BancoDeDadosException(e.getCause().getMessage());
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    @Override
    public void remover(Integer idUsuario) throws BancoDeDadosException {
        EntityManager con = null;
        EntityTransaction transection = null;
        try {
            con = this.getEntityManager();
            transection = con.getTransaction();

            transection.begin();

            Usuario usuario = con.find(Usuario.class, idUsuario);

            con.remove(usuario);

            transection.commit();

        } catch (PersistenceException e) {
            if (transection != null && transection.isActive()) {
                transection.rollback();
            }
            e.printStackTrace();
            throw new BancoDeDadosException("Não foi possível deletar o usuário do banco.");
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    @Override
    public Usuario editar(Usuario usuario) throws BancoDeDadosException {
        EntityManager con = null;
        EntityTransaction transection = null;
        try {
            con = this.getEntityManager();
            transection = con.getTransaction();

            transection.begin();

            Usuario usuarioRetornadoDoBanco = con.merge(usuario);

            transection.commit();

            return usuarioRetornadoDoBanco;
        } catch (PersistenceException e) {
            if (transection != null && transection.isActive()) {
                transection.rollback();
            }
            e.printStackTrace();
            throw new BancoDeDadosException("Não foi possível editar o usuário do banco.");
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    @Override
    public List<Usuario> listar(Integer idUsuario) throws BancoDeDadosException {
        List<Usuario> usuarios;
        EntityManager con = null;
        try {
            con = this.getEntityManager();

            String sql = "Select u From Usuario u where (:idUsuario is null or u.idUsuario = :idUsuario)";

            TypedQuery<Usuario> query = con.createQuery(sql, Usuario.class);
            query.setParameter("idUsuario", idUsuario);
            usuarios = query.getResultList();

        } catch (PersistenceException e) {
            e.printStackTrace();
            throw new BancoDeDadosException("Não foi possível listar os usuários.");
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return usuarios;
    }

    public boolean validarCPF(String cpf) throws BancoDeDadosException {
        EntityManager con = null;
        try {
            con = this.getEntityManager();
            String sql = "SELECT u FROM Usuario u " +
                    "WHERE u.cpf = :cpf";

            TypedQuery<Usuario> usuario = con.createQuery(sql, Usuario.class);
            usuario.setParameter("cpf", cpf);

            List<Usuario> usuarioStream = usuario.getResultList();

            boolean campoExistente = usuarioStream.size() > 0;

            return campoExistente;
        } catch (PersistenceException e) {
            e.printStackTrace();
            throw new BancoDeDadosException("Ocorreu algum erro ao conectar-se com o banco de dados.");
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }
}
