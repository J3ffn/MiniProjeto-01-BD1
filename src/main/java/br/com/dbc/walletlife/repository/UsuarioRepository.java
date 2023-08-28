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
        try {
            con = this.getEntityManager();
            con.persist(usuario);

            return usuario;

        } catch (PersistenceException e){
            e.printStackTrace();
            throw new BancoDeDadosException("Não foi possível adicionar o usuário ao banco de dados.");
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
}
