package br.com.dbc.walletlife.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.com.dbc.walletlife.exceptions.BancoDeDadosException;
import br.com.dbc.walletlife.modelos.Investimento;

public class InvestimentoRepository extends DAO implements Repositorio<Integer, Investimento> {

    @Override
    public Investimento adicionar(Investimento investimento) throws BancoDeDadosException {
        EntityManager con = null;
        EntityTransaction transactional = null;
        try {
            con = this.getEntityManager();
            transactional = con.getTransaction();

            transactional.begin();
            con.persist(investimento);

            transactional.commit();

            return investimento;
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
    public void remover(Integer idInvestimento) throws BancoDeDadosException {
        EntityManager con = null;
        EntityTransaction transection = null;
        try {
            con = this.getEntityManager();
            transection = con.getTransaction();

            transection.begin();

            Investimento investimento = con.find(Investimento.class, idInvestimento);

            con.remove(investimento);

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
    public Investimento editar(Investimento investimento) throws BancoDeDadosException {
        EntityManager con = null;
        EntityTransaction transection = null;
        try {
            con = this.getEntityManager();
            transection = con.getTransaction();

            transection.begin();

            Investimento investimentoAlteracaoRetornadaDoBanco = con.merge(investimento);

            transection.commit();

            return investimentoAlteracaoRetornadaDoBanco;
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
    public List<Investimento> listar(Integer idInvestimento) throws BancoDeDadosException {
        List<Investimento> investimentos;
        EntityManager con = null;
        try {
            con = this.getEntityManager();

            String sql = "Select u From Receita u where (:idReceita is null or u.idReceita = :idReceita)";

            TypedQuery<Investimento> query = con.createQuery(sql, Investimento.class);
            query.setParameter("idInvestimento", idInvestimento);

            investimentos = query.getResultList();

            return investimentos;
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
