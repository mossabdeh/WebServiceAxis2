package servlet;

import DAO.EnseignantDAO;
import model.Enseignant;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Home
 */
public class Home extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Assuming you have an EnseignantDAO class
        EnseignantDAO enseignantDAO = new EnseignantDAO();

        // Call DAO method to get all enseignants
        List<Enseignant> enseignantBeans = enseignantDAO.getAllEnseignants();

        // Set the list of EnseignantBean objects as a request attribute
        request.setAttribute("enseignantBeans", enseignantBeans);

        // Forward the request to a JSP page to display the enseignants
        RequestDispatcher dispatcher = request.getRequestDispatcher("/HomeAPP2B.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}
