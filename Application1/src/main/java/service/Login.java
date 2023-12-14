package service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Forward the request to the addEnseignant.jsp page
        request.getRequestDispatcher("/Login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        String username = request.getParameter("username");
	        String password = request.getParameter("password");

	        // Check if username and password match your database values
	        if ("admin".equals(username) && "admin".equals(password)) {
	            // Successful login
	            response.sendRedirect(request.getContextPath() + "/home");
	        } else {
	            // Failed login
	            request.setAttribute("errorMessage", "Bad credentials. Please try again.");
	            request.getRequestDispatcher("/Login.jsp").forward(request, response);
	        }
	    }
	}
