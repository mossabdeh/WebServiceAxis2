package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class EnseignantBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long Matricule;
    private String Nom;
    private String Prenom;
    private String Sexe;
    private Date DateNaissance;
    private String LieuNaissance;
    private String SituationFamille;
    private Date DateRecrutement;
    private String DiplomeRecrutement;
    private String DepartementAffectation;

    private List<Research> researchList;
        
    

	public EnseignantBean() {
		super();
	}

	public EnseignantBean(Long matricule, String nom, String prenom, String sexe, Date dateNaissance,
			String lieuNaissance, String situationFamille, Date dateRecrutement, String diplomeRecrutement,
			String departementAffectation) {
		super();
		Matricule = matricule;
		Nom = nom;
		Prenom = prenom;
		Sexe = sexe;
		DateNaissance = dateNaissance;
		LieuNaissance = lieuNaissance;
		SituationFamille = situationFamille;
		DateRecrutement = dateRecrutement;
		DiplomeRecrutement = diplomeRecrutement;
		DepartementAffectation = departementAffectation;
	}

	public EnseignantBean(Long matricule, String nom, String prenom, String sexe, Date dateNaissance,
			String lieuNaissance, String situationFamille, Date dateRecrutement, String diplomeRecrutement,
			String departementAffectation, List<Research> researchList) {
		super();
		Matricule = matricule;
		Nom = nom;
		Prenom = prenom;
		Sexe = sexe;
		DateNaissance = dateNaissance;
		LieuNaissance = lieuNaissance;
		SituationFamille = situationFamille;
		DateRecrutement = dateRecrutement;
		DiplomeRecrutement = diplomeRecrutement;
		DepartementAffectation = departementAffectation;
		this.researchList = researchList;
	}

	public Long getMatricule() {
		return Matricule;
	}

	public void setMatricule(Long matricule) {
		Matricule = matricule;
	}

	public String getNom() {
		return Nom;
	}

	public void setNom(String nom) {
		Nom = nom;
	}

	public String getPrenom() {
		return Prenom;
	}

	public void setPrenom(String prenom) {
		Prenom = prenom;
	}

	public String getSexe() {
		return Sexe;
	}

	public void setSexe(String sexe) {
		Sexe = sexe;
	}

	public Date getDateNaissance() {
		return DateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		DateNaissance = dateNaissance;
	}

	public String getLieuNaissance() {
		return LieuNaissance;
	}

	public void setLieuNaissance(String lieuNaissance) {
		LieuNaissance = lieuNaissance;
	}

	public String getSituationFamille() {
		return SituationFamille;
	}

	public void setSituationFamille(String situationFamille) {
		SituationFamille = situationFamille;
	}

	public Date getDateRecrutement() {
		return DateRecrutement;
	}

	public void setDateRecrutement(Date dateRecrutement) {
		DateRecrutement = dateRecrutement;
	}

	public String getDiplomeRecrutement() {
		return DiplomeRecrutement;
	}

	public void setDiplomeRecrutement(String diplomeRecrutement) {
		DiplomeRecrutement = diplomeRecrutement;
	}

	public String getDepartementAffectation() {
		return DepartementAffectation;
	}

	public void setDepartementAffectation(String departementAffectation) {
		DepartementAffectation = departementAffectation;
	}

	public List<Research> getResearchList() {
		return researchList;
	}

	public void setResearchList(List<Research> researchList) {
		this.researchList = researchList;
	}

    
    
    
    
    
    
}
