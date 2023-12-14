package ws;

import java.util.List;

import DAO.EnseignantDAO;
import model.Enseignant;

public class EnseignantWebService {

	private EnseignantDAO enseignantDAO = new EnseignantDAO();

    public Enseignant getEnseignantByMatricule(Long matricule) {
        // Call the EnseignantDAO method to get an Enseignant by Matricule
        return enseignantDAO.getEnseignantByMatricule(matricule);
    }

    public Enseignant[] getAllEnseignants() {
        // Call the EnseignantDAO method to get all enseignants
        List<Enseignant> allEnseignants = enseignantDAO.getAllEnseignants();
        
        // Convert the list to an array
        Enseignant[] enseignantsArray = new Enseignant[allEnseignants.size()];
        allEnseignants.toArray(enseignantsArray);
        
        // Return the array
        return enseignantsArray;
    }
    }