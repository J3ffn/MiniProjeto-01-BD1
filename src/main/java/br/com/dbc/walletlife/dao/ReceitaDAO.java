package br.com.dbc.walletlife.dao;

import br.com.dbc.walletlife.exceptions.BancoDeDadosException;
import br.com.dbc.walletlife.modelos.Receita;
import br.com.dbc.walletlife.modelos.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

public class ReceitaDAO extends PersistenciaDAO implements CrudDAO<Integer, Receita> {

    @Override
    public Receita adicionar(Receita receita) throws BancoDeDadosException {
        EntityManager con = null;
        EntityTransaction transactional = null;
        try {
            con = this.getEntityManager();
            transactional = con.getTransaction();

            transactional.begin();
            con.persist(receita);

            transactional.commit();

            return receita;
        } catch (PersistenceException e){
            if (transactional.isActive()) {
                transactional.rollback();
            }
//            e.printStackTrace();
            throw new BancoDeDadosException("Não foi possível adicionar a receita ao banco.");
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    @Override
    public void remover(Integer idReceita) throws BancoDeDadosException {
        EntityManager con = null;
        EntityTransaction transection = null;
        try {
            con = this.getEntityManager();
            transection = con.getTransaction();

            transection.begin();

            Receita receita = con.find(Receita.class, idReceita);

            con.remove(receita);

            transection.commit();
        } catch (PersistenceException e) {
            if (transection != null && transection.isActive()) {
                transection.rollback();
            }
            e.printStackTrace();
            throw new BancoDeDadosException("Não foi possível deletar a receita do banco.");
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    @Override
    public Receita editar(Receita receita) throws BancoDeDadosException {
        EntityManager con = null;
        EntityTransaction transection = null;
        try {
            con = this.getEntityManager();
            transection = con.getTransaction();

            transection.begin();

            Receita receitaAlteradaRetornadaDoBanco = con.merge(receita);

            transection.commit();

            return receitaAlteradaRetornadaDoBanco;
        } catch (PersistenceException e) {
            if (transection != null && transection.isActive()) {
                transection.rollback();
            }
            e.printStackTrace();
            throw new BancoDeDadosException("Não foi possível editar a receita do banco.");
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    @Override
    public List<Receita> listar(Integer idReceita) throws BancoDeDadosException {
        List<Receita> receitas;
        EntityManager con = null;
        try {
            con = this.getEntityManager();

            String sql = "Select u From Receita u where (:idReceita is null or u.idReceita = :idReceita)";

            TypedQuery<Receita> query = con.createQuery(sql, Receita.class);
            query.setParameter("idReceita", idReceita);

            receitas = query.getResultList();

            return receitas;
        } catch (PersistenceException e) {
            e.printStackTrace();
            throw new BancoDeDadosException("Não foi possível listar as receitas.");
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    public List<Receita> listarReceitaPeloUsuario(Usuario usuario) throws BancoDeDadosException {
        List<Receita> receitas;
        EntityManager con = null;
        try {
            con = this.getEntityManager();

            String sql = "Select u From Receita u where (:usuario is null or u.usuarioFK = :usuario)";

            TypedQuery<Receita> query = con.createQuery(sql, Receita.class);
            query.setParameter("usuario", usuario);

            receitas = query.getResultList();

            return receitas;
        } catch (PersistenceException e) {
            e.printStackTrace();
            throw new BancoDeDadosException("Não foi possível listar as receitas.");
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }
}
