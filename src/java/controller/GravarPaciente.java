package controller;

import dao.PacienteDAO;
import dao.PessoaDAO;
import model.Profissao;
import model.Paciente;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Pessoa;

@WebServlet(name = "GravarPaciente", urlPatterns = {"/GravarPaciente"})
public class GravarPaciente extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        try {
            int codigoPessoa = request.getParameter("codigoPessoa").isEmpty() ? 0 : Integer.parseInt(request.getParameter("codigoPessoa"));

            int codigoPaciente = request.getParameter("codigoPaciente").isEmpty() ? 0 : Integer.parseInt(request.getParameter("codigoPaciente"));

            String nomePessoa = request.getParameter("nomePessoa");

            String dataNascimentoPessoa = request.getParameter("dataNascimentoPessoa");

            String cpfPessoa = request.getParameter("cpfPessoa");

            String numeroCartaoSusPaciente = request.getParameter("numeroCartaoSusPaciente");

            boolean statusPaciente = request.getParameter("statusPaciente").equals("Ativo");

            int codigoProfissao = Integer.parseInt(request.getParameter("codigoProfissao"));

            Profissao profissao = new Profissao();
            
            profissao.setCodigoProfissao(codigoProfissao);

            Paciente paciente = new Paciente(codigoPaciente, numeroCartaoSusPaciente, statusPaciente, profissao, codigoPessoa, nomePessoa, dataNascimentoPessoa, cpfPessoa, cpfPessoa.replaceAll("[./]",""));

            PessoaDAO pessoaDAO = new PessoaDAO();
            
            Pessoa pessoa = (Pessoa) pessoaDAO.consultar(cpfPessoa);
            
            if(pessoa != null) {
                paciente.setCodigoPessoa(pessoa.getCodigoPessoa());
            }
            
            PacienteDAO pacienteDAO = new PacienteDAO();

            pacienteDAO.gravar(paciente);

            request.setAttribute("mensagem", "Paciente gravado com sucesso!");
        } catch (SQLException | ClassNotFoundException ex) {
            request.setAttribute("mensagem", ex.getMessage());
        }

        request.getRequestDispatcher("ListarPaciente").forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
