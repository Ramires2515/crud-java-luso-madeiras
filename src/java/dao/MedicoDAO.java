package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.Medico;
import utils.Conexao;

public class MedicoDAO {

    private Connection conexao;

    public MedicoDAO() throws ClassNotFoundException, SQLException {
        conexao = Conexao.abrirConexao();
    }

    public void gravar(Object objeto) throws SQLException {
        Medico medico = (Medico) objeto;
        if (medico.getCodigoMedico() == 0) {
            this.inserir(medico);
        } else {
            this.alterar(medico);
        }
    }

    public void inserir(Object objeto) throws SQLException {
        String sql = "insert into medico values(default, ?, ?, ?)";
        Medico medico = (Medico) objeto;
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, new PessoaDAO().gravar(medico));
            stmt.setString(2, medico.getCrmMedico());
            stmt.setBoolean(3, medico.isStatusMedico());
            stmt.execute();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new SQLException("Erro ao gravar médico");
        } finally {
            Conexao.encerrarConexao(conexao, stmt);
        }
    }

    public void alterar(Object objeto) throws SQLException {
        String sql = "update medico set codigopessoa = ?, crmmedico = ?, "
                + "statusmedico = ? where codigomedico = ?";
        Medico medico = (Medico) objeto;
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, new PessoaDAO().gravar(medico));
            stmt.setString(2, medico.getCrmMedico());
            stmt.setBoolean(3, medico.isStatusMedico());
            stmt.setInt(4, medico.getCodigoMedico());
            stmt.execute();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new SQLException("Erro ao alterar médico");
        } finally {
            Conexao.encerrarConexao(conexao, stmt);
        }
    }

    public Object consultar(int codigo) throws SQLException {
        String sql = "select * from medico me inner join pessoa pe "
                + "on me.codigopessoa = pe.codigopessoa where codigomedico = ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Medico medico = null;
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, codigo);
            rs = stmt.executeQuery();
            while (rs.next()) {
                medico = new Medico(
                        rs.getInt("codigomedico"),
                        rs.getString("crmmedico"),
                        rs.getBoolean("statusmedico"),
                        rs.getInt("codigopessoa"),
                        rs.getString("nomepessoa"),
                        new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate("datanascimentopessoa")),
                        rs.getString("cpfpessoa"),
                        null
                );
            }
        } catch (SQLException ex) {
            throw new SQLException("Erro ao consultar médico");
        } finally {
            Conexao.encerrarConexao(conexao, stmt, rs);
        }
        return medico;
    }

    public List<Object> listar() throws SQLException {
        String sql = "select * from medico me inner join pessoa pe "
                + "on me.codigopessoa = pe.codigopessoa order by codigomedico";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Medico medico = null;
        List<Object> lista = new ArrayList<>();
        try {
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                medico = new Medico(
                        rs.getInt("codigomedico"),
                        rs.getString("crmmedico"),
                        rs.getBoolean("statusmedico"),
                        rs.getInt("codigopessoa"),
                        rs.getString("nomepessoa"),
                        new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("datanascimentopessoa")),
                        rs.getString("cpfpessoa"),
                        null
                );
                lista.add(medico);
            }
        } catch (SQLException ex) {
            throw new SQLException("Erro ao listar médico");
        } finally {
            Conexao.encerrarConexao(conexao, stmt, rs);
        }
        return lista;
    }

    public void excluir(int codigo) throws SQLException {
        String sql = "delete from medico where codigomedico = ?";
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, codigo);
            stmt.execute();
        } catch (SQLException ex) {
            throw new SQLException("Erro ao excluir médico");
        } finally {
            Conexao.encerrarConexao(conexao, stmt);
        }
    }
}