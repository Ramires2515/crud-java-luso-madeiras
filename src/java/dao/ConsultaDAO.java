package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.Consulta;
import model.Medico;
import model.Paciente;
import utils.Conexao;

public class ConsultaDAO {

    private Connection conexao;

    public ConsultaDAO() throws ClassNotFoundException, SQLException {
        conexao = Conexao.abrirConexao();
    }

    public void gravar(Object objeto) throws SQLException {
        Consulta consulta = (Consulta) objeto;
        if (consulta.getCodigoConsulta() == 0) {
            this.inserir(consulta);
        } else {
            this.alterar(consulta);
        }
    }

    public void inserir(Object objeto) throws SQLException {
        String sql = "insert into consulta values(default, ?, ?, ?, ?, ?, ?)";
        Consulta consulta = (Consulta) objeto;
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setDate(1, new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(consulta.getDataConsulta()).getTime()));
            stmt.setTime(2, new java.sql.Time(new SimpleDateFormat("HH:mm:ss").parse(consulta.getHorarioConsulta()).getTime()));
            stmt.setString(3, consulta.getSituacaoConsulta());
            stmt.setString(4, consulta.getObservacoesConsulta());
            stmt.setInt(5, consulta.getPaciente().getCodigoPaciente());
            stmt.setInt(6, consulta.getMedico().getCodigoMedico());
            stmt.execute();
        } catch (SQLException | ParseException ex) {
            throw new SQLException("Erro ao inserir consulta");
        } finally {
            Conexao.encerrarConexao(conexao, stmt);
        }
    }

    public void alterar(Object objeto) throws SQLException {
        String sql = "update consulta set dataconsulta = ?, horarioconsulta = ?, situacaoconsulta = ?, "
                + "observacoesconsulta = ?, codigopaciente = ?, codigomedico = ? where codigoconsulta = ?";
        Consulta consulta = (Consulta) objeto;
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setDate(1, new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(consulta.getDataConsulta()).getTime()));
            stmt.setTime(2, new java.sql.Time(new SimpleDateFormat("HH:mm:ss").parse(consulta.getHorarioConsulta()).getTime()));
            stmt.setString(3, consulta.getSituacaoConsulta());
            stmt.setString(4, consulta.getObservacoesConsulta());
            stmt.setInt(5, consulta.getPaciente().getCodigoPaciente());
            stmt.setInt(6, consulta.getMedico().getCodigoMedico());
            stmt.setInt(7, consulta.getCodigoConsulta());
            stmt.execute();
        } catch (SQLException | ParseException ex) {
            throw new SQLException("Erro ao alterar consulta");
        } finally {
            Conexao.encerrarConexao(conexao, stmt);
        }
    }

    public Object consultar(int codigo) throws SQLException {
        String sql = "select * from consulta where codigoconsulta = ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Consulta consulta = null;
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, codigo);
            rs = stmt.executeQuery();
            while (rs.next()) {
                consulta = new Consulta(
                        rs.getInt("codigoconsulta"),
                        new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate("dataconsulta")),
                        new SimpleDateFormat("HH:mm:ss").format(rs.getTime("horarioconsulta")),
                        rs.getString("situacaoconsulta"),
                        rs.getString("observacoesconsulta"),
                        (Paciente) new PacienteDAO().consultar(rs.getInt("codigopaciente")),
                        (Medico) new MedicoDAO().consultar(rs.getInt("codigomedico"))
                );
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new SQLException("Erro ao consultar consulta");
        } finally {
            Conexao.encerrarConexao(conexao, stmt, rs);
        }
        return consulta;
    }

    public List<Object> listar() throws SQLException {
        String sql = "select * from consulta order by codigoconsulta";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Consulta consulta = null;
        List<Object> lista = new ArrayList<>();
        try {
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                consulta = new Consulta(
                        rs.getInt("codigoconsulta"),
                        new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("dataconsulta")),
                        new SimpleDateFormat("HH:mm:ss").format(rs.getTime("horarioconsulta")),
                        rs.getString("situacaoconsulta"),
                        rs.getString("observacoesconsulta"),
                        (Paciente) new PacienteDAO().consultar(rs.getInt("codigopaciente")),
                        (Medico) new MedicoDAO().consultar(rs.getInt("codigomedico"))
                );
                lista.add(consulta);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new SQLException("Erro ao listar consultas");
        } finally {
            Conexao.encerrarConexao(conexao, stmt, rs);
        }
        return lista;
    }

    public void excluir(int codigo) throws SQLException {
        String sql = "delete from consulta where codigoconsulta = ?";
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, codigo);
            stmt.execute();
        } catch (SQLException ex) {
            throw new SQLException("Erro ao excluir consulta");
        } finally {
            Conexao.encerrarConexao(conexao, stmt);
        }
    }
}