package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.PostgreSQLJDBCConfig;
import model.EnseignantBean;
import model.Research;

public class ResearchDAO {
	private Connection connection;

    public ResearchDAO() {
        this.connection = PostgreSQLJDBCConfig.connect();
    }

    public void close() {
        PostgreSQLJDBCConfig.close();
    }

    // Insert a Research record into the database
    public void addResearch(Research research) {
        String sql = "INSERT INTO research (laboratoire, date_promotion_research, grade_research, enseignant_matricule) " +
                     "VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, research.getLaboratoire());
            preparedStatement.setDate(2, new java.sql.Date(research.getDatePromotionResearch().getTime()));
            preparedStatement.setString(3, research.getGradeResearch());
            preparedStatement.setLong(4, research.getEnseignantMatricule());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }
    }
    
 // Retrieve a list of Research records for a specific enseignantMatricule
    public List<Research> getResearchByEnseignantMatricule(Long enseignantMatricule) {
        List<Research> researchList = new ArrayList<>();
        String sql = "SELECT * FROM research WHERE enseignant_matricule = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, enseignantMatricule);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Research research = new Research();
                    research.setId(resultSet.getLong("id"));
                    research.setLaboratoire(resultSet.getString("laboratoire"));
                    research.setDatePromotionResearch(resultSet.getDate("date_promotion_research"));
                    research.setGradeResearch(resultSet.getString("grade_research"));
                    research.setEnseignantMatricule(resultSet.getLong("enseignant_matricule"));

                    researchList.add(research);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }

        return researchList;
    }
    
    // Method to get all research records for a specific enseignant
    public List<Research> getResearchByMatricule(Long matricule) {
        List<Research> researchList = new ArrayList<>();

        // First, retrieve the enseignant
        EnseignantBeanDAO enseignantBeanDAO = new EnseignantBeanDAO();
        EnseignantBean enseignant = enseignantBeanDAO.getEnseignantByMatricule(matricule);

        if (enseignant != null) {
            String sql = "SELECT * FROM research WHERE enseignant_matricule = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setLong(1, matricule);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Research research = mapResultSetToResearch(resultSet);
                        // Do not set enseignant if there's no setEnseignant method
                        researchList.add(research);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Handle the exception appropriately
            }
        }

        return researchList;
    }

    // Helper method to map a ResultSet to a Research object
    private Research mapResultSetToResearch(ResultSet resultSet) throws SQLException {
        Research research = new Research();
        research.setId(resultSet.getLong("id"));
        research.setLaboratoire(resultSet.getString("laboratoire"));
        research.setDatePromotionResearch(resultSet.getDate("date_promotion_research"));
        research.setGradeResearch(resultSet.getString("grade_research"));
        research.setEnseignantMatricule(resultSet.getLong("enseignant_matricule"));
        return research;
    }
}