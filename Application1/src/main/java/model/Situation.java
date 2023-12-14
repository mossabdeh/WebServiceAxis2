package model;




public class Situation {
    public static final String C = "C";
    public static final String M = "M";
    public static final String D = "D";
    public static final String V = "V";
    
    private String value;

    public Situation() {
        // Default constructor required for JAXB
    }

    public Situation(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
