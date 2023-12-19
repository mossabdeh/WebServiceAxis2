package model;

import java.io.Serializable;
import java.util.Date;


public class Research implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
    private String laboratoire;
    private Date datePromotionResearch;
    private String gradeResearch;
    private Enseignant enseignant;
     
    
    
    
    
    
	public Research() {
		super();
	}
	
	
	
	
	public Research(String laboratoire, Date datePromotionResearch, String gradeResearch, Enseignant enseignant) {
		super();
		this.laboratoire = laboratoire;
		this.datePromotionResearch = datePromotionResearch;
		this.gradeResearch = gradeResearch;
		this.enseignant = enseignant;
	}




	




	public Research(String laboratoire, Date datePromotionResearch, String gradeResearch) {
		super();
		this.laboratoire = laboratoire;
		this.datePromotionResearch = datePromotionResearch;
		this.gradeResearch = gradeResearch;
	}




	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public String getLaboratoire() {
		return laboratoire;
	}




	public void setLaboratoire(String laboratoire) {
		this.laboratoire = laboratoire;
	}




	public Date getDatePromotionResearch() {
		return datePromotionResearch;
	}




	public void setDatePromotionResearch(Date datePromotionResearch) {
		this.datePromotionResearch = datePromotionResearch;
	}




	public String getGradeResearch() {
		return gradeResearch;
	}




	public void setGradeResearch(String gradeResearch) {
		this.gradeResearch = gradeResearch;
	}




	public Enseignant getEnseignant() {
		return enseignant;
	}




	public void setEnseignant(Enseignant enseignant) {
		this.enseignant = enseignant;
	}

}
