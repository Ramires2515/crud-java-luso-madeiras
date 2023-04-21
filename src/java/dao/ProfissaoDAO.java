package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Profissao;
import utils.Conexao;

public class ProfissaoDAO implements DAOGenerica {

    private Connection conexao;

    public ProfissaoDAO() throws SQLException, ClassNotFoundException {
        this.conexao = Conexao.abrirConexao();
    }

    @Override
    public void gravar(Object objeto) throws SQLException {
        Profissao profissao = (Profissao) objeto;
        if (profissao.getCodigoProfissao() == 0) {
            this.inserir(profissao);
        } else {
            this.alterar(profissao);
        }
    }

    @Override
    public void inserir(Object objeto) throws SQLException {
        Profissao profissao = (Profissao) objeto;
        String sql = "insert into profissao values (default, ?)";
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, profissao.getNomeProfissao());
            stmt.execute();
        } catch (SQLException ex) {
            throw new SQLException("Erro ao inserir profissão");
        } finally {
            Conexao.encerrarConexao(conexao, stmt);
        }
    }

    @Override
    public void alterar(Object objeto) throws SQLException {
        Profissao profissao = (Profissao) objeto;
        String sql = "update profissao set nomeprofissao = ? where codigoprofissao = ?";
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, profissao.getNomeProfissao());
            stmt.setInt(2, profissao.getCodigoProfissao());
            stmt.execute();
        } catch (SQLException ex) {
            throw new SQLException("Erro ao alterar profissão");
        } finally {
            Conexao.encerrarConexao(conexao, stmt);
        }
    }

    @Override
    public Object consultar(int codigo) throws SQLException {
        String sql = "select * from profissao where codigoprofissao = ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Profissao profissao = null;
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, codigo);
            rs = stmt.executeQuery();
            while (rs.next()) {
                profissao = new Profissao(rs.getInt("codigoprofissao"), rs.getString("nomeprofissao"));
            }
        } catch (SQLException ex) {
            throw new SQLException("Erro ao consultar profissão");
        } finally {
            Conexao.encerrarConexao(conexao, stmt, rs);
        }
        return profissao;
    }

    @Override
    public List<Object> listar() throws SQLException {
        String sql = "select * from profissao";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Object> lista = new ArrayList<>();
        try {
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Profissao profissao = new Profissao(rs.getInt("codigoprofissao"), rs.getString("nomeprofissao"));
                lista.add(profissao);
            }
        } catch (SQLException ex) {
            throw new SQLException("Erro ao listar profissão");
        } finally {
            Conexao.encerrarConexao(conexao, stmt, rs);
        }
        return lista;
    }

    @Override
    public void excluir(int codigo) throws SQLException {
        String sql = "delete from profissao where codigoprofissao = ?";
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, codigo);
            stmt.execute();
        } catch (SQLException ex) {
            throw new SQLException("Erro ao excluir profissão");
        } finally {
            Conexao.encerrarConexao(conexao, stmt);
        }
    }
}