package br.com.dbc.walletlife.repository;

import br.com.dbc.walletlife.exceptions.BancoDeDadosException;
import br.com.dbc.walletlife.modelos.Investimento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InvestimentoRepository implements Repositorio<Integer, Investimento> {

    @Override
    public Investimento adicionar(Investimento investimento) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            String sql = "INSERT INTO INVESTIMENTO\n" +
                    "(ID_INVESTIMENTO, CORRETORA, TIPO, VALOR, DATA_INICIAL, DESCRICAO, ID_USUARIO)\n" +
                    "VALUES(?, ?, ?, ?, ?, ?, ?)\n";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, investimento.getId());
            stmt.setString(2, investimento.getCorretora());
            stmt.setString(3, investimento.getTipo());
            stmt.setDouble(4, investimento.getValor());
            stmt.setDate(5, Date.valueOf(investimento.getDataInicio()));
            stmt.setString(6, investimento.getDescricao());
            stmt.setInt(7, investimento.getIdFK());

            int res = stmt.executeUpdate();

            return investimento;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
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

    @Override
    public void remover(Integer id) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            String sql = "DELETE FROM INVESTIMENTO WHERE ID_USUARIO = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);

            // Executa-se a consulta
            int res = stmt.executeUpdate();

        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
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

    @Override
    public Investimento editar(Investimento investimento) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE INVESTIMENTO SET ");
            sql.append(" valor = ?, ");
            sql.append(" descricao = ? ");
            sql.append(" where id_usuario = ? ");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setDouble(1, investimento.getValor());
            stmt.setString(2, investimento.getDescricao());
            stmt.setInt(3, investimento.getIdFK());

            // Executa-se a consulta
            int res = stmt.executeUpdate();

//            return res > 0;
            return null; //TODO Mudar isso pq é temporário.
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
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

    @Override
    public List<Investimento> listar(Integer idUsuario) throws BancoDeDadosException {
        List<Investimento> investimentos = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM INVESTIMENTO WHERE ID_USUARIO = " + idUsuario;

            // Executa-se a consulta
            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Investimento investimento = new Investimento();
                investimento.setId(res.getInt("id_investimento"));
                investimento.setCorretora(res.getString("corretora"));
                investimento.setTipo(res.getString("tipo"));
                investimento.setValor(res.getDouble("valor"));
                investimento.setDataInicio(res.getDate("data_inicial").toLocalDate());
                investimento.setDescricao(res.getString("descricao"));
                investimento.setIdFK(res.getInt("id_usuario"));
                investimentos.add(investimento);
            }
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return investimentos;
    }

}
