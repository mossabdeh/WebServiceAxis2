package servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ResearchDAO;
import model.Research;

@WebServlet("/ResearchServlet")
public class AddReserch extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get matricule parameter from the request
        Long matricule = Long.parseLong(request.getParameter("matricule"));

        // Call ResearchDAO to get all research for the specified enseignant
        ResearchDAO researchDAO = new ResearchDAO();
        List<Research> researchList = researchDAO.getResearchByMatricule(matricule);

        // Set researchList as a request attribute
        request.setAttribute("researchList", researchList);

        // Set matricule as a request attribute
        request.setAttribute("matricule", matricule);

        // Forward the request to the research JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("/AddResearch.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form data from the request
        Long matricule = Long.parseLong(request.getParameter("matricule"));
        String laboratoire = request.getParameter("laboratoire");
        Date datePromotionResearch = Date.valueOf(request.getParameter("datePromotionResearch"));
        String gradeResearch = request.getParameter("gradeResearch");

        // Create a Research object with the form data
        Research research = new Research(laboratoire, datePromotionResearch, gradeResearch);

        // Call ResearchDAO to add the research for the specified enseignant
        ResearchDAO researchDAO = new ResearchDAO();
        researchDAO.addResearch(research , matricule);

        // Redirect to the research servlet to display all research for the enseignant
        response.sendRedirect(request.getContextPath() + "/ResearchServlet?matricule=" + matricule);
    }
}
