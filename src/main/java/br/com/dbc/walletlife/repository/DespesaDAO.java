package br.com.dbc.walletlife.repository;

import java.util.List;

import br.com.dbc.walletlife.exceptions.BancoDeDadosException;
import br.com.dbc.walletlife.modelos.Despesa;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

public class DespesaDAO extends DAO implements Repositorio<Integer, Despesa> {

	@Override
	public Despesa adicionar(Despesa despesa) throws BancoDeDadosException {
		EntityManager con = null;
		EntityTransaction transactional = null;
		try {
			con = this.getEntityManager();
			transactional = con.getTransaction();

			transactional.begin();
			con.persist(despesa);

			transactional.commit();

			return despesa;
		} catch (PersistenceException e){
			if (transactional.isActive()) {
				transactional.rollback();
			}
//            e.printStackTrace();
			throw new BancoDeDadosException("Não foi possível adicionar a despesa ao banco.");
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	@Override
	public void remover(Integer idDespesa) throws BancoDeDadosException {
		EntityManager con = null;
		EntityTransaction transection = null;
		try {
			con = this.getEntityManager();
			transection = con.getTransaction();

			transection.begin();

			Despesa receita = con.find(Despesa.class, idDespesa);

			con.remove(receita);

			transection.commit();
		} catch (PersistenceException e) {
			if (transection != null && transection.isActive()) {
				transection.rollback();
			}
			e.printStackTrace();
			throw new BancoDeDadosException("Não foi possível deletar a despesa do banco.");
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	@Override
	public Despesa editar(Despesa despesa) throws BancoDeDadosException {
		EntityManager con = null;
		EntityTransaction transection = null;
		try {
			con = this.getEntityManager();
			transection = con.getTransaction();

			transection.begin();

			Despesa despesaAlteradaRetornadaDoBanco = con.merge(despesa);

			transection.commit();

			return despesaAlteradaRetornadaDoBanco;
		} catch (PersistenceException e) {
			if (transection != null && transection.isActive()) {
				transection.rollback();
			}
			e.printStackTrace();
			throw new BancoDeDadosException("Não foi possível editar a despesa do banco.");
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	@Override
	public List<Despesa> listar(Integer idUsuario) throws BancoDeDadosException {
		List<Despesa> despesas;
		EntityManager con = null;
		try {
			con = this.getEntityManager();

			String sql = "Select u From Despesa u where (:idUsuario is null or u.usuarioFK.idUsuario = :idUsuario)";

			TypedQuery<Despesa> query = con.createQuery(sql, Despesa.class);
			query.setParameter("idUsuario", idUsuario);

			despesas = query.getResultList();

			return despesas;
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new BancoDeDadosException("Não foi possível listar as despesas.");
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}
}

