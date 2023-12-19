package DAO;

import model.Research;
import model.Enseignant;
import config.PostgreSQLJDBCConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResearchDAO {

    private Connection connection;

    public ResearchDAO() {
        connection = PostgreSQLJDBCConfig.connect();
    }

    // Method to add a research
    public void addResearch(Research research, Long matricule) {
        try {
            // First, check if the enseignant exists
            EnseignantDAO enseignantDAO = new EnseignantDAO();
            Enseignant enseignant = enseignantDAO.getEnseignantByMatricule(matricule);

            if (enseignant != null) {
                String sql = "INSERT INTO research (laboratoire, datepromotionresearch, graderesearch, enseignantmatricule) VALUES (?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, research.getLaboratoire());
                    preparedStatement.setDate(2, new java.sql.Date(research.getDatePromotionResearch().getTime()));
                    preparedStatement.setString(3, research.getGradeResearch());
                    preparedStatement.setLong(4, matricule);

                    preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    // Method to update a research
    public void updateResearch(Research research) {
        try {
            String sql = "UPDATE research SET laboratoire = ?, datepromotionresearch = ?, graderesearch = ? WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, research.getLaboratoire());
                preparedStatement.setDate(2, new java.sql.Date(research.getDatePromotionResearch().getTime()));
                preparedStatement.setString(3, research.getGradeResearch());
                preparedStatement.setLong(4, research.getId());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    // Method to delete a research by ID
    public void deleteResearchById(long id) {
        try {
            String sql = "DELETE FROM research WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    // Method to get all research for a specific enseignant
    public List<Research> getResearchByMatricule(Long matricule) {
        List<Research> researchList = new ArrayList<>();

        // First, retrieve the enseignant
        EnseignantDAO enseignantDAO = new EnseignantDAO();
        Enseignant enseignant = enseignantDAO.getEnseignantByMatricule(matricule);

        if (enseignant != null) {
            String sql = "SELECT * FROM research WHERE enseignantmatricule = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setLong(1, matricule);

                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Research research = mapResultSetToResearch(resultSet);
                    research.setEnseignant(enseignant);
                    researchList.add(research);
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Handle the exception appropriately
            }
        }

        return researchList;
    }

    // Method to map result set to Research object
    private Research mapResultSetToResearch(ResultSet resultSet) throws SQLException {
        Research research = new Research();
        research.setId(resultSet.getLong("id"));
        research.setLaboratoire(resultSet.getString("laboratoire"));
        research.setDatePromotionResearch(resultSet.getDate("datepromotionresearch"));
        research.setGradeResearch(resultSet.getString("graderesearch"));
        return research;
    }

    // Close the connection
    public void close() {
        PostgreSQLJDBCConfig.close();
    }
}
