package controller;

import dao.ConsultaDAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Consulta;
import model.Medico;
import model.Paciente;

@WebServlet(name = "GravarConsulta", urlPatterns = {"/GravarConsulta"})
public class GravarConsulta extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try {
            int codigoConsulta = request.getParameter("codigoConsulta").isEmpty() ? 0 : Integer.parseInt(request.getParameter("codigoConsulta"));

            String dataConsulta = request.getParameter("dataConsulta");

            String horarioConsulta = request.getParameter("horarioConsulta") + ":00";

            String situacaoConsulta = request.getParameter("situacaoConsulta");
            
            String observacoesConsulta = request.getParameter("observacoesConsulta");
            
            int codigoPaciente = Integer.parseInt(request.getParameter("codigoPaciente"));
            
            int codigoMedico = Integer.parseInt(request.getParameter("codigoMedico"));
            
            Paciente paciente = new Paciente();
            paciente.setCodigoPaciente(codigoPaciente);
            
            Medico medico = new Medico();
            medico.setCodigoMedico(codigoMedico);
            
            Consulta consulta = new Consulta(codigoConsulta, dataConsulta, horarioConsulta, situacaoConsulta, observacoesConsulta, paciente, medico);
            
            ConsultaDAO consultaDAO = new ConsultaDAO();
            
            consultaDAO.gravar(consulta);

            request.setAttribute("mensagem", "Consulta gravada com sucesso!");
        } catch (SQLException | ClassNotFoundException ex) {
            request.setAttribute("mensagem", ex.getMessage());
        }
        request.getRequestDispatcher("ListarConsulta").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
