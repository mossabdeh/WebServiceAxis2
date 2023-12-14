package service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.EnseignantDAO;
import model.Enseignant;

/**
 * Servlet implementation class Home
 */
@WebServlet("/home")
public class Home extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	// Create a dummy enseignant
        // Enseignant dummyEnseignant = createDummyEnseignant();

        // Call EnseignantDAO to add the dummy enseignant to the database
        EnseignantDAO enseignantDAO = new EnseignantDAO();
       // enseignantDAO.addEnseignant(dummyEnseignant);
        // Call EnseignantDAO to get all enseignants
    
        List<Enseignant> allEnseignants = enseignantDAO.getAllEnseignants();

        // Set the list of enseignants as a request attribute
        request.setAttribute("allEnseignants", allEnseignants);

        // Forward the request to enseignant.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminHome.jsp");
        dispatcher.forward(request, response);
    }




        
    

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

  
}