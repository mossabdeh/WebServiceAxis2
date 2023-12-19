package servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.EnseignantDAO;
import model.Enseignant;
import model.MatriculeGenerator;

/**
 * Servlet implementation class AddEns
 */
@WebServlet("/addEns")
public class AddEns extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward the request to the addEnseignant.jsp page
        request.getRequestDispatcher("/AddEns2.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle the form submission
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String sexe = request.getParameter("sexe");  // Changed to String
        Date dateNaissance = Date.valueOf(request.getParameter("dateNaissance"));
        String lieuNaissance = request.getParameter("lieuNaissance");
        String situationFamille = request.getParameter("situationFamille");  // Changed to String
       
       
        Date dateRecrutement = Date.valueOf(request.getParameter("dateRecrutement"));
        String diplomeRecrutement = request.getParameter("diplomeRecrutement");
        String departementAffectation = request.getParameter("departementAffectation");  // Changed to String
       

        // Call EnseignantDAO to get the last used sequential number for the given year
        EnseignantDAO enseignantDAO = new EnseignantDAO();
        int recruitmentYear = getYearFromDate(dateRecrutement);

        // Generate a unique Matricule using MatriculeGenerator
        Long matricule = generateUniqueMatricule(dateRecrutement, recruitmentYear, enseignantDAO);

        Enseignant enseignant = new Enseignant(
                matricule, nom, prenom, sexe, dateNaissance, lieuNaissance,
                situationFamille,  dateRecrutement, diplomeRecrutement,
                departementAffectation
        );

        // Call EnseignantDAO to add the enseignant to the database
        enseignantDAO.insertEnseignant(enseignant);

        // Set a success message to be displayed in home.jsp
        request.setAttribute("successMessage", "Enseignant added successfully!");

        // Redirect to the home page after adding enseignant
        response.sendRedirect(request.getContextPath() + "/Home");
    }

    // Utility method to extract the year from a Date
    private int getYearFromDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    // Utility method to generate a unique Matricule
    private Long generateUniqueMatricule(Date dateRecrutement, int recruitmentYear, EnseignantDAO enseignantDAO) {
        // Get the last used Matricule for the given year
        Long lastMatricule = enseignantDAO.getLastMatricule(recruitmentYear);

        // Extract the sequential number from the last Matricule
        long sequentialNumber = extractSequentialNumber(lastMatricule);

        // Increment the sequential number if necessary
        sequentialNumber = Math.max(sequentialNumber + 1, enseignantDAO.getHighestSequentialNumber(recruitmentYear) + 1);

        return MatriculeGenerator.generateMatricule(recruitmentYear, sequentialNumber);
    }

    // Utility method to extract the sequential number from a Matricule
    private long extractSequentialNumber(Long matricule) {
        if (matricule != null) {
            String matriculeStr = String.valueOf(matricule);
            // Check if the matriculeStr has enough characters before extracting the substring
            if (matriculeStr.length() >= 5) {
                return Long.parseLong(matriculeStr.substring(4)); // Assuming the sequential number is in positions 5 to 8
            }
        }
        return 0;
    }
}
