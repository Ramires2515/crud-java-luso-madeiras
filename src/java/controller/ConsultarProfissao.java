package controller;

import dao.ProfissaoDAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Profissao;

@WebServlet(name = "ConsultarProfissao", urlPatterns = {"/ConsultarProfissao"})
public class ConsultarProfissao extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            int codigoProfissao = Integer.parseInt(request.getParameter("codigoProfissao"));
            ProfissaoDAO profissaoDAO = new ProfissaoDAO();
            Profissao profissao = (Profissao) profissaoDAO.consultar(codigoProfissao);
            request.setAttribute("profissao", profissao);
        } catch (SQLException | ClassNotFoundException ex) {
            request.setAttribute("mensagem", ex.getMessage());
        }
        request.getRequestDispatcher("gravarProfissao.jsp").forward(request, response);
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