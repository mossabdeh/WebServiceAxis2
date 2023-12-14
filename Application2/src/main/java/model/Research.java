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
    private Long enseignantMatricule;
     
    
    
    
    
    
	public Research() {
		super();
	}
	public Research(Long id, String laboratoire, Date datePromotionResearch, String gradeResearch,
			Long enseignantMatricule) {
		super();
		this.id = id;
		this.laboratoire = laboratoire;
		this.datePromotionResearch = datePromotionResearch;
		this.gradeResearch = gradeResearch;
		this.enseignantMatricule = enseignantMatricule;
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
	public Long getEnseignantMatricule() {
		return enseignantMatricule;
	}
	public void setEnseignantMatricule(Long enseignantMatricule) {
		this.enseignantMatricule = enseignantMatricule;
	}

    // Getters, setters, constructors, etc.
    
    
    
}
