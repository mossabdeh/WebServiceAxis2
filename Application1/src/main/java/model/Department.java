package model;




public class Department {
    public static final String TLSI = "TLSI";
    public static final String IFA = "IFA";
    public static final String NTIC = "NTIC";
    
    private String value;

    public Department() {
        // Default constructor required for JAXB
    }

    public Department(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
