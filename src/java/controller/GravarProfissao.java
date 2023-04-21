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

@WebServlet(name = "GravarProfissao", urlPatterns = {"/GravarProfissao"})
public class GravarProfissao extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        try {
            int codigoProfissao = request.getParameter("codigoProfissao").isEmpty() ? 0 : Integer.parseInt(request.getParameter("codigoProfissao"));
            String nomeProfissao = request.getParameter("nomeProfissao");
            Profissao profissao = new Profissao(codigoProfissao, nomeProfissao);
            ProfissaoDAO profissaoDAO = new ProfissaoDAO();
            profissaoDAO.gravar(profissao);
            request.setAttribute("mensagem", "Profissão gravada com sucesso");
        } catch (SQLException | ClassNotFoundException ex) {
            request.setAttribute("mensagem", ex.getMessage());
        }
        request.getRequestDispatcher("ListarProfissao").forward(request, response);
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