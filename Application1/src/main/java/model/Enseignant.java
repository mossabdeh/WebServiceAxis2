package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Enseignant  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	private Long Matricule;
    private String Nom;
    private String Prenom;
    private String sexe; // Using String instead of Gender
    private Date DateNaissace;
    private String LieuNaissance;
    private String SituationFamille; // Using String instead of SituationEnum
    private String Conjoint;
    private int EnfantCharge;

    private Date DateRecrutment;
    private String DiplomeRecrutment;
    private String DepartementAffectation; // Using String instead of DepartmentEnum

    // list des promotions
    private List<Promotion> promotions;
    // list des echelons
    private List<Echelon> echelons; // for every new promotion counter = 0

    private String EtatActual; // Using String instead of Etat
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

	public Enseignant(Long matricule, String nom, String prenom, String sexe, Date dateNaissace, String lieuNaissance,
			String situationFamille, String conjoint, int enfantCharge, Date dateRecrutment, String diplomeRecrutment,
			String departementAffectation, String etatActual) {
		super();
		Matricule = matricule;
		Nom = nom;
		Prenom = prenom;
		this.sexe = sexe;
		DateNaissace = dateNaissace;
		LieuNaissance = lieuNaissance;
		SituationFamille = situationFamille;
		Conjoint = conjoint;
		EnfantCharge = enfantCharge;
		DateRecrutment = dateRecrutment;
		DiplomeRecrutment = diplomeRecrutment;
		DepartementAffectation = departementAffectation;
		EtatActual = etatActual;
	}

	public Enseignant(Long matricule, String nom, String prenom, String sexe, Date dateNaissace, String lieuNaissance,
			String situationFamille, String conjoint, int enfantCharge, Date dateRecrutment, String diplomeRecrutment,
			String departementAffectation, List<Promotion> promotions, List<Echelon> echelons, String etatActual) {
		super();
		Matricule = matricule;
		Nom = nom;
		Prenom = prenom;
		this.sexe = sexe;
		DateNaissace = dateNaissace;
		LieuNaissance = lieuNaissance;
		SituationFamille = situationFamille;
		Conjoint = conjoint;
		EnfantCharge = enfantCharge;
		DateRecrutment = dateRecrutment;
		DiplomeRecrutment = diplomeRecrutment;
		DepartementAffectation = departementAffectation;
		this.promotions = promotions;
		this.echelons = echelons;
		EtatActual = etatActual;
	}

	public Enseignant() {
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
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public Date getDateNaissace() {
		return DateNaissace;
	}

	public void setDateNaissace(Date dateNaissace) {
		DateNaissace = dateNaissace;
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

	public String getConjoint() {
		return Conjoint;
	}

	public void setConjoint(String conjoint) {
		Conjoint = conjoint;
	}

	public int getEnfantCharge() {
		return EnfantCharge;
	}

	public void setEnfantCharge(int enfantCharge) {
		EnfantCharge = enfantCharge;
	}

	public Date getDateRecrutment() {
		return DateRecrutment;
	}

	public void setDateRecrutment(Date dateRecrutment) {
		DateRecrutment = dateRecrutment;
	}

	public String getDiplomeRecrutment() {
		return DiplomeRecrutment;
	}

	public void setDiplomeRecrutment(String diplomeRecrutment) {
		DiplomeRecrutment = diplomeRecrutment;
	}

	public String getDepartementAffectation() {
		return DepartementAffectation;
	}

	public void setDepartementAffectation(String departementAffectation) {
		DepartementAffectation = departementAffectation;
	}

	public List<Promotion> getPromotions() {
		return promotions;
	}

	public void setPromotions(List<Promotion> promotions) {
		this.promotions = promotions;
	}

	public List<Echelon> getEchelons() {
		return echelons;
	}

	public void setEchelons(List<Echelon> echelons) {
		this.echelons = echelons;
	}

	public String getEtatActual() {
		return EtatActual;
	}

	public void setEtatActual(String etatActual) {
		EtatActual = etatActual;
	}

    // Constructors, Getters, and Setters (omitted for brevity)

	// Constructor All fields
	
	
	
	//
	
	
	
}
  

