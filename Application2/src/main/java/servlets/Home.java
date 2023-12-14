package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.EnseignantBean;
import ws.EnseignantWebServiceStub;
import ws.EnseignantWebServiceStub.Enseignant;

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
        // Create an instance of the EnseignantWebServiceStub
        EnseignantWebServiceStub stub = new EnseignantWebServiceStub();

        // Create an instance of the GetAllEnseignants method
        EnseignantWebServiceStub.GetAllEnseignants getAllEnseignants = new EnseignantWebServiceStub.GetAllEnseignants();

        // Call the web service method to get all enseignants
        EnseignantWebServiceStub.GetAllEnseignantsResponse getAllEnseignantsResponse = stub
                .getAllEnseignants(getAllEnseignants);

        // Retrieve the array of Enseignant objects from the response
        Enseignant[] enseignants = getAllEnseignantsResponse.get_return();

        // Convert Enseignant objects to EnseignantBean objects
        List<EnseignantBean> enseignantBeans = new ArrayList<>();
        for (Enseignant enseignant : enseignants) {
            EnseignantBean enseignantBean = new EnseignantBean();
            enseignantBean.setMatricule(enseignant.getMatricule());
            enseignantBean.setNom(enseignant.getNom());
            enseignantBean.setPrenom(enseignant.getPrenom());
            enseignantBean.setSexe(enseignant.getSexe());
            enseignantBean.setDateNaissance(enseignant.getDateNaissace());
            enseignantBean.setLieuNaissance(enseignant.getLieuNaissance());
            enseignantBean.setSituationFamille(enseignant.getSituationFamille());
         // Assuming DateRecrutement in EnseignantBean is of type java.util.Date
            enseignantBean.setDateRecrutement(enseignant.getDateRecrutment() != null
                    ? new java.util.Date(enseignant.getDateRecrutment().getTime()) : null);

            // Assuming DiplomeRecrutement in EnseignantBean is of type String
            enseignantBean.setDiplomeRecrutement(enseignant.getDiplomeRecrutment());

            enseignantBean.setDepartementAffectation(enseignant.getDepartementAffectation());

            enseignantBeans.add(enseignantBean);
        }

        // Set the list of EnseignantBean objects as a request attribute
        request.setAttribute("enseignantBeans", enseignantBeans);

        // Forward the request to a JSP page to display the enseignants
        RequestDispatcher dispatcher = request.getRequestDispatcher("/GetAll.jsp");
        dispatcher.forward(request, response);
    }

 

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
