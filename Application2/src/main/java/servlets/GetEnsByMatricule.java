package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.EnseignantBeanDAO;
import model.EnseignantBean;
import ws.EnseignantWebServiceStub;

/**
 * Servlet implementation class GetEnsByMatricule
 */
public class GetEnsByMatricule extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetEnsByMatricule() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve the matricule parameter from the request
        String matriculeParam = request.getParameter("matricule");

        if (matriculeParam != null && !matriculeParam.isEmpty()) {
            try {
                // Parse the matricule parameter to a Long
                Long matricule = Long.parseLong(matriculeParam);

                // Create an instance of the EnseignantBeanDAO
                EnseignantBeanDAO enseignantBeanDao = new EnseignantBeanDAO();

                // Check if enseignantBean with the matricule already exists in the database
                EnseignantBean existingEnseignantBean = enseignantBeanDao.getEnseignantByMatricule(matricule);

                if (existingEnseignantBean == null) {
                    // If not exists, create an instance of the EnseignantWebServiceStub
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

                    // Map the Enseignant object to an EnseignantBean
                    EnseignantBean enseignantBean = new EnseignantBean();
                    enseignantBean.setMatricule(enseignantWS.getMatricule());
                    enseignantBean.setNom(enseignantWS.getNom());
                    enseignantBean.setPrenom(enseignantWS.getPrenom());
                    enseignantBean.setSexe(enseignantWS.getSexe());
                    enseignantBean.setDateNaissance(enseignantWS.getDateNaissace() != null
                            ? new java.util.Date(enseignantWS.getDateNaissace().getTime()) : null);
                    enseignantBean.setLieuNaissance(enseignantWS.getLieuNaissance());
                    enseignantBean.setSituationFamille(enseignantWS.getSituationFamille());
                    enseignantBean.setDateRecrutement(enseignantWS.getDateRecrutment() != null
                            ? new java.util.Date(enseignantWS.getDateRecrutment().getTime()) : null);
                    enseignantBean.setDiplomeRecrutement(enseignantWS.getDiplomeRecrutment());
                    enseignantBean.setDepartementAffectation(enseignantWS.getDepartementAffectation());

                    // Save the EnseignantBean using the EnseignantBeanDao
                    enseignantBeanDao.insertEnseignantBean(enseignantBean);
                }

                // Set the enseignantBean as a request attribute for further processing in JSP
                request.setAttribute("enseignantBean", existingEnseignantBean);

                // Forward the request to the AddResearch servlet
                request.getRequestDispatcher("/AddResearch").forward(request, response);

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
        // Forward the request to the doGet method
        doGet(request, response);
    }
}
