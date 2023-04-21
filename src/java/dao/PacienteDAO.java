package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.Profissao;
import model.Paciente;
import utils.Conexao;

public class PacienteDAO {

    private Connection conexao;

    public PacienteDAO() throws ClassNotFoundException, SQLException {
        conexao = Conexao.abrirConexao();
    }

    public void gravar(Object objeto) throws SQLException {
        Paciente paciente = (Paciente) objeto;
        if (paciente.getCodigoPaciente() == 0) {
            this.inserir(paciente);
        } else {
            this.alterar(paciente);
        }
    }

    public void inserir(Object objeto) throws SQLException {
        String sql = "insert into paciente values(default, ?, ?, ?, ?)";
        Paciente paciente = (Paciente) objeto;
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, new PessoaDAO().gravar(paciente));
            stmt.setString(2, paciente.getNumeroCartaoSusPaciente());
            stmt.setBoolean(3, paciente.isStatusPaciente());
            stmt.setInt(4, paciente.getProfissao().getCodigoProfissao());
            stmt.execute();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new SQLException("Erro ao gravar paciente");
        } finally {
            Conexao.encerrarConexao(conexao, stmt);
        }
    }

    public void alterar(Object objeto) throws SQLException {
        String sql = "update paciente set codigopessoa = ?, numerocartaosuspaciente = ?, statuspaciente = ?, codigoprofissao = ? where codigopaciente = ?";
        Paciente paciente = (Paciente) objeto;
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, new PessoaDAO().gravar(paciente));
            stmt.setString(2, paciente.getNumeroCartaoSusPaciente());
            stmt.setBoolean(3, paciente.isStatusPaciente());
            stmt.setInt(4, paciente.getProfissao().getCodigoProfissao());
            stmt.setInt(5, paciente.getCodigoPaciente());
            stmt.execute();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new SQLException("Erro ao alterar paciente");
        } finally {
            Conexao.encerrarConexao(conexao, stmt);
        }
    }

    public Object consultar(int codigo) throws SQLException {
        String sql = "select * from paciente pa inner join pessoa pe "
                + "on pa.codigopessoa = pe.codigopessoa where codigopaciente = ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Paciente paciente = null;
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, codigo);
            rs = stmt.executeQuery();
            while (rs.next()) {
                paciente = new Paciente(
                        rs.getInt("codigopaciente"),
                        rs.getString("numerocartaosuspaciente"),
                        rs.getBoolean("statuspaciente"),
                        (Profissao) new ProfissaoDAO().consultar(rs.getInt("codigoprofissao")),
                        rs.getInt("codigopessoa"),
                        rs.getString("nomepessoa"),
                        new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate("datanascimentopessoa")),
                        rs.getString("cpfpessoa"),
                        null
                );
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new SQLException("Erro ao consultar paciente");
        } finally {
            Conexao.encerrarConexao(conexao, stmt, rs);
        }
        return paciente;
    }

    public List<Object> listar() throws SQLException {
        String sql = "select * from paciente pa inner join pessoa pe "
                + "on pa.codigopessoa = pe.codigopessoa order by codigopaciente";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Paciente paciente = null;
        List<Object> lista = new ArrayList<>();
        try {
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                paciente = new Paciente(
                        rs.getInt("codigopaciente"),
                        rs.getString("numerocartaosuspaciente"),
                        rs.getBoolean("statuspaciente"),
                        (Profissao) new ProfissaoDAO().consultar(rs.getInt("codigoprofissao")),
                        rs.getInt("codigopessoa"),
                        rs.getString("nomepessoa"),
                        new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("datanascimentopessoa")),
                        rs.getString("cpfpessoa"),
                        null
                );
                lista.add(paciente);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new SQLException("Erro ao listar pacientes");
        } finally {
            Conexao.encerrarConexao(conexao, stmt, rs);
        }
        return lista;
    }

    public void excluir(int codigo) throws SQLException {
        String sql = "delete from paciente where codigopaciente = ?";
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, codigo);
            stmt.execute();
        } catch (SQLException ex) {
            throw new SQLException("Erro ao excluir paciente");
        } finally {
            Conexao.encerrarConexao(conexao, stmt);
        }
    }
}