package br.com.dbc.walletlife.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dbc.walletlife.exceptions.BancoDeDadosException;
import br.com.dbc.walletlife.modelos.Investimento;

public class InvestimentoDAO extends DAO implements Repositorio<Integer, Investimento> {

	@Override
	public Investimento adicionar(Investimento investimento) throws BancoDeDadosException {
		Connection con = null;
		try {
			con = ConexaoBancoDeDados.getConnection();
			con.setAutoCommit(false);

			String sql = "INSERT INTO INVESTIMENTO\n" +
					"(ID_INVESTIMENTO, CORRETORA, TIPO, VALOR, DATA_INICIAL, DESCRICAO, ID_USUARIO)\n" +
					"VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(sql);

//			stmt.setInt(1, investimento.getId());
			stmt.setString(2, investimento.getCorretora());
			stmt.setString(3, investimento.getTipo());
			stmt.setDouble(4, investimento.getValor());
			stmt.setDate(5, Date.valueOf(investimento.getDataInicio()));
			stmt.setString(6, investimento.getDescricao());
			stmt.setInt(7, investimento.getIdFK());

			stmt.executeUpdate();
			con.commit();
			return investimento;
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

			String sql = "DELETE FROM INVESTIMENTO WHERE ID_USUARIO = ?";

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
	public Investimento editar(Investimento investimento) throws BancoDeDadosException {
		Connection con = null;
		try {
			con = ConexaoBancoDeDados.getConnection();
			con.setAutoCommit(false);

			String sql = "UPDATE INVESTIMENTO SET VALOR = ?, DESCRICAO = ? WHERE ID_USUARIO = ?";

			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setDouble(1, investimento.getValor());
			stmt.setString(2, investimento.getDescricao());
			stmt.setInt(3, investimento.getIdFK());

			stmt.executeUpdate();
			con.commit();
			return investimento;
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
	public List<Investimento> listar(Integer idUsuario) throws BancoDeDadosException {
		List<Investimento> investimentos = new ArrayList<>();
		Connection con = null;
		try {
			con = ConexaoBancoDeDados.getConnection();

			String sql = "SELECT * FROM INVESTIMENTO WHERE ID_USUARIO = ?";

			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, idUsuario);

			ResultSet res = stmt.executeQuery();

			while (res.next()) {
				Investimento investimento = new Investimento();
//				investimento.setId(res.getInt("ID_INVESTIMENTO"));
				investimento.setCorretora(res.getString("CORRETORA"));
				investimento.setTipo(res.getString("TIPO"));
				investimento.setValor(res.getDouble("VALOR"));
				investimento.setDataInicio(res.getDate("DATA_INICIAL").toLocalDate());
				investimento.setDescricao(res.getString("DESCRICAO"));
				investimento.setIdFK(res.getInt("ID_USUARIO"));
				investimentos.add(investimento);
			}
			return investimentos;
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


