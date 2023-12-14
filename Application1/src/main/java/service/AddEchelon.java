package service;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.EchelonDAO;
import model.Echelon;

/**
 * Servlet implementation class AddEchelon
 */
@WebServlet("/EchelonServlet")
public class AddEchelon extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get matricule parameter from the request
        Long matricule = Long.parseLong(request.getParameter("matricule"));

        // Call EchelonDAO to get all echelons for the specified enseignant
        EchelonDAO echelonDAO = new EchelonDAO();
        List<Echelon> echelons = echelonDAO.getEchelonsByMatricule(matricule);

        // Set echelons as a request attribute
        request.setAttribute("echelons", echelons);
        // Assuming you have retrieved the matricule value
        request.setAttribute("matricule", matricule);

        // Forward the request to the echelons JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Echelons.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get form data from the request
        Long matricule = Long.parseLong(request.getParameter("matricule"));
        Date datePromotion = Date.valueOf(request.getParameter("datePromotion"));
        int nombreEchelon = Integer.parseInt(request.getParameter("nombreEchelon"));

        // Create an Echelon object with the form data
        Echelon echelon = new Echelon(datePromotion, nombreEchelon);

        // Call EchelonDAO to add the echelon for the specified enseignant
        EchelonDAO echelonDAO = new EchelonDAO();
        echelonDAO.addEchelon(echelon, matricule);

        // Redirect to the echelons servlet to display all echelons for the enseignant
        response.sendRedirect(request.getContextPath() + "/EchelonServlet?matricule=" + matricule);
    }
}
