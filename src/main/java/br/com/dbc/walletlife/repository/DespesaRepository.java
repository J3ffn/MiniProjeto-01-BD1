//package br.com.dbc.walletlife.repository;
//
//import br.com.dbc.walletlife.enumerators.TipoDespesaEReceita;
//import br.com.dbc.walletlife.exceptions.BancoDeDadosException;
//import br.com.dbc.walletlife.modelos.Despesa;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class DespesaRepository implements Repositorio<Integer, Despesa>{
//
//    @Override
//    public Despesa adicionar(Despesa despesa) throws BancoDeDadosException {
//        Connection con = null;
//        try {
//            con = ConexaoBancoDeDados.getConnection();
//
//            String sql = "INSERT INTO DESPESA\n" +
//                    "(ID_DESPESA, TIPO, VALOR, DESCRICAO, DATA_PAGAMENTO, ID_USUARIO )\n" +
//                    "VALUES(?, ?, ?, ?, ?, ?)\n";
//
//            PreparedStatement stmt = con.prepareStatement(sql);
//
//            stmt.setInt(1, despesa.getId());
//            stmt.setString(2, despesa.getTipo().toString());
//            stmt.setDouble(3, despesa.getValor());
//            stmt.setString(4, despesa.getDescricao()); // RESIDENCIAL(1) //1
//            stmt.setDate(5, Date.valueOf(despesa.getDataPagamento()));
//            stmt.setInt(6, despesa.getUsuarioFK());
//
//            int res = stmt.executeUpdate();
//            return despesa;
//        } catch (SQLException e) {
//            throw new BancoDeDadosException(e.getCause());
//        } finally {
//            try {
//                if (con != null) {
//                    con.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    @Override
//    public void remover(Integer id) throws BancoDeDadosException {
//        Connection con = null;
//        try {
//            con = ConexaoBancoDeDados.getConnection();
//
//            String sql = "DELETE FROM DESPESA WHERE ID_DESPESA = ?";
//
//            PreparedStatement stmt = con.prepareStatement(sql);
//
//            stmt.setInt(1, id);
//
//            // Executa-se a consulta
//            int res = stmt.executeUpdate();
//
//        } catch (SQLException e) {
//            throw new BancoDeDadosException(e.getCause());
//        } finally {
//            try {
//                if (con != null) {
//                    con.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//    }
//
//    @Override
//    public Despesa editar(Despesa despesa) throws BancoDeDadosException {
//        Connection con = null;
//        try {
//            con =ConexaoBancoDeDados.getConnection();
//
//            StringBuilder sql = new StringBuilder();
//            sql.append("UPDATE DESPESA SET ");
//            sql.append(" valor = ?,");
//            sql.append(" descricao = ?, ");
//            sql.append(" WHERE id_usuario = ?");
//
//            PreparedStatement stmt = con.prepareStatement(sql.toString());
//
//            stmt.setDouble(1, despesa.getValor());
//            stmt.setString(2, despesa.getDescricao());
//            stmt.setInt(3, despesa.getUsuarioFK());
//
//
//            // Executa-se a consulta
//            int res = stmt.executeUpdate();
//
////            return res > 0;
//            return null;
//        } catch (SQLException e) {
//            throw new BancoDeDadosException(e.getCause());
//        } finally {
//            try {
//                if (con != null) {
//                    con.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    @Override
//    public List<Despesa> listar(Integer idUsuario) throws BancoDeDadosException {
//        List<Despesa> despesas = new ArrayList<>();
//        Connection con = null;
//        try {
//            con = ConexaoBancoDeDados.getConnection();
//            Statement stmt = con.createStatement();
//
//            String sql = "SELECT * FROM DESPESA where id_usuario = " + idUsuario;
//
//            // Executa-se a consulta
//            ResultSet res = stmt.executeQuery(sql);
//
//            while (res.next()) {
//                Despesa despesa = new Despesa();
//                despesa.setId(res.getInt("id_despesa"));
//                despesa.setTipo(TipoDespesaEReceita.valueOf(res.getString("Tipo")));
//                despesa.setValor(res.getDouble("valor"));
//                despesa.setDecricao(res.getString("descricao"));
//                despesa.setDataPagamento(res.getDate("data_pagamento").toLocalDate());
//                despesa.setUsuarioFK(res.getInt("id_usuario"));
//                despesas.add(despesa);
//            }
//        } catch (SQLException e) {
//            throw new BancoDeDadosException(e.getCause());
//        } finally {
//            try {
//                if (con != null) {
//                    con.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return despesas;
//
//    }
//}
