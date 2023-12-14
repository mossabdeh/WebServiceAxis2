package service;

import java.io.IOException;
import java.sql.Date;



import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.EnseignantDAO;

import model.Enseignant;


/**
 * Servlet implementation class UpdateEnseignant
 */
public class UpdateEnseignant extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateEnseignant() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the Matricule parameter from the request
        Long matricule = Long.parseLong(request.getParameter("matricule"));

        // Call EnseignantDAO to get the Enseignant by Matricule
        EnseignantDAO enseignantDAO = new EnseignantDAO();
        Enseignant enseignant = enseignantDAO.getEnseignantByMatricule(matricule);

        // Set the Enseignant object as a request attribute
        request.setAttribute("enseignant", enseignant);

        // Forward the request to the update form JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminUpdateE.jsp");
        dispatcher.forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve the form data from the request
        Long matricule = Long.parseLong(request.getParameter("matricule"));
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        // Retrieve other form fields similarly
        String sexe = request.getParameter("sexe");
        Date dateNaissance = Date.valueOf(request.getParameter("dateNaissance"));
        String lieuNaissance = request.getParameter("lieuNaissance");
        String situationFamille = request.getParameter("situationFamille");
        String conjoint = request.getParameter("conjoint");
        int enfantCharge = Integer.parseInt(request.getParameter("enfantCharge"));
        Date dateRecrutement = Date.valueOf(request.getParameter("dateRecrutement"));//
        String diplomeRecrutement = request.getParameter("diplomeRecrutement");
        String departementAffectation = request.getParameter("departementAffectation");
        String etatActual = request.getParameter("etatActual");

        // Create an Enseignant object with the updated data
        Enseignant updatedEnseignant = new Enseignant(matricule, nom, prenom, sexe, dateNaissance, lieuNaissance,
                situationFamille, conjoint, enfantCharge, dateRecrutement, diplomeRecrutement,
                departementAffectation, null, null, etatActual);

        // Call EnseignantDAO to update the Enseignant in the database
        EnseignantDAO enseignantDAO = new EnseignantDAO();
        enseignantDAO.updateEnseignantByMatricule(updatedEnseignant);

        // Redirect to the home page after updating
        response.sendRedirect(request.getContextPath() + "/home");
    }
}