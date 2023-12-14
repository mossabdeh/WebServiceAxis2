package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ResearchDAO;
import model.Research;
import ws.EnseignantWebServiceStub;

/**
 * Servlet implementation class AddResearch
 */
public class AddResearch extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve the matricule parameter from the request
        String matriculeParam = request.getParameter("matricule");

        if (matriculeParam != null && !matriculeParam.isEmpty()) {
            try {
                // Parse the matricule parameter to a Long
                Long matricule = Long.parseLong(matriculeParam);

                // Create an instance of the EnseignantWebServiceStub
                EnseignantWebServiceStub stub = new EnseignantWebServiceStub();

                // Call the web service method to get an enseignant by matricule
                EnseignantWebServiceStub.GetEnseignantByMatricule getEnseignantByMatricule = 
                        new EnseignantWebServiceStub.GetEnseignantByMatricule();
                
                // Set the matricule parameter
                getEnseignantByMatricule.setMatricule(matricule);

                // Call the web service method
                EnseignantWebServiceStub.GetEnseignantByMatriculeResponse responseEnseignant =
                        stub.getEnseignantByMatricule(getEnseignantByMatricule);

                // Retrieve the Enseignant object from the response
                EnseignantWebServiceStub.Enseignant enseignantWS = responseEnseignant.get_return();

                // Set the enseignantWS as a request attribute for further processing in JSP
                request.setAttribute("enseignantWS", enseignantWS);

                // Call ResearchDAO to get all research records for the specified enseignant
                ResearchDAO researchDAO = new ResearchDAO();
                List<Research> researchList = researchDAO.getResearchByEnseignantMatricule(matricule);

                // Set researchList as a request attribute
                request.setAttribute("researchList", researchList);

                // Forward the request to the research form JSP
                request.getRequestDispatcher("/researchForm.jsp").forward(request, response);
                
            } catch (NumberFormatException e) {
                e.printStackTrace();
                // Handle the case where matriculeParam is not a valid Long
            }
        } else {
            // Handle the case where matricule parameter is missing
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the parameters from the research form
        String matricule = request.getParameter("matricule");
        String laboratoire = request.getParameter("laboratoire");
        String datePromotionResearchStr = request.getParameter("datePromotionResearch");
        String gradeResearch = request.getParameter("gradeResearch");

        // Parse the date string to Date
        Date datePromotionResearch = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            datePromotionResearch = sdf.parse(datePromotionResearchStr);
        } catch (ParseException e) {
            e.printStackTrace(); // Handle parsing exception as needed
        }

        // Assuming you have a ResearchDao for database operations
        ResearchDAO researchDao = new ResearchDAO();

        // Create a Research object
        Research research = new Research();
        research.setEnseignantMatricule(Long.parseLong(matricule));
        research.setLaboratoire(laboratoire);
        research.setDatePromotionResearch(datePromotionResearch);
        research.setGradeResearch(gradeResearch);

        // Save the research to the database
        researchDao.addResearch(research);

     // Redirect to the same page with updated research list
        response.sendRedirect(request.getContextPath() + "/AddResearch?matricule=" + matricule);
    }
}
