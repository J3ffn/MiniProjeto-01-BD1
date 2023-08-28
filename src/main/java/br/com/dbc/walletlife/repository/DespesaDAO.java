package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dbc.walletlife.enumerators.TipoDespesaEReceita;
import br.com.dbc.walletlife.exceptions.BancoDeDadosException;
import br.com.dbc.walletlife.modelos.Despesa;
import br.com.dbc.walletlife.repository.ConexaoBancoDeDados;
import br.com.dbc.walletlife.repository.Repositorio;

public class DespesaDAO implements Repositorio<Integer, Despesa> {

	@Override
	public Despesa adicionar(Despesa despesa) throws BancoDeDadosException {
		Connection con = null;
		try {
			con = ConexaoBancoDeDados.getConnection();
			con.setAutoCommit(false);

			String sql = "INSERT INTO DESPESA\n" +
					"(ID_DESPESA, TIPO, VALOR, DESCRICAO, DATA_PAGAMENTO, ID_USUARIO)\n" +
					"VALUES (?, ?, ?, ?, ?, ?)";

			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setInt(1, despesa.getId());
			stmt.setString(2, despesa.getTipo().toString());
			stmt.setDouble(3, despesa.getValor());
			stmt.setString(4, despesa.getDescricao());
			stmt.setDate(5, Date.valueOf(despesa.getDataPagamento()));
			stmt.setInt(6, despesa.getIdFK());

			stmt.executeUpdate();
			con.commit();
			return despesa;
		} catch (SQLException e) {
			if (con != null) {
				try {
					con.rollback();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			throw new BancoDeDadosException(e);
		} finally {
			try {
				if (con != null) {
					con.setAutoCommit(true);
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void remover(Integer id) throws BancoDeDadosException {
		Connection con = null;
		try {
			con = ConexaoBancoDeDados.getConnection();
			con.setAutoCommit(false);

			String sql = "DELETE FROM DESPESA WHERE ID_DESPESA = ?";

			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);

			stmt.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			if (con != null) {
				try {
					con.rollback();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			throw new BancoDeDadosException(e);
		} finally {
			try {
				if (con != null) {
					con.setAutoCommit(true);
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Despesa editar(Despesa despesa) throws BancoDeDadosException {
		Connection con = null;
		try {
			con = ConexaoBancoDeDados.getConnection();
			con.setAutoCommit(false);

			String sql = "UPDATE DESPESA SET VALOR = ?, DESCRICAO = ? WHERE ID_USUARIO = ?";

			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setDouble(1, despesa.getValor());
			stmt.setString(2, despesa.getDescricao());
			stmt.setInt(3, despesa.getIdFK());

			stmt.executeUpdate();
			con.commit();
			return despesa;
		} catch (SQLException e) {
			if (con != null) {
				try {
					con.rollback();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			throw new BancoDeDadosException(e);
		} finally {
			try {
				if (con != null) {
					con.setAutoCommit(true);
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<Despesa> listar(Integer idUsuario) throws BancoDeDadosException {
		List<Despesa> despesas = new ArrayList<>();
		Connection con = null;
		try {
			con = ConexaoBancoDeDados.getConnection();

			String sql = "SELECT * FROM DESPESA WHERE ID_USUARIO = ?";

			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, idUsuario);

			ResultSet res = stmt.executeQuery();

			while (res.next()) {
				Despesa despesa = new Despesa();
				despesa.setId(res.getInt("ID_DESPESA"));
				despesa.setTipo(TipoDespesaEReceita.valueOf(res.getString("TIPO")));
				despesa.setValor(res.getDouble("VALOR"));
				despesa.setDescricao(res.getString("DESCRICAO"));
				despesa.setDataPagamento(res.getDate("DATA_PAGAMENTO").toLocalDate());
				despesa.setIdFK(res.getInt("ID_USUARIO"));
				despesas.add(despesa);
			}
			return despesas;
		} catch (SQLException e) {
			throw new BancoDeDadosException(e);
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

