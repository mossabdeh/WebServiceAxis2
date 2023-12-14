package DAO;

import config.PostgreSQLJDBCConfig;
import model.EnseignantBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnseignantBeanDAO {
	
	// Method to retrieve all Enseignants from the database
    public List<EnseignantBean> getAllEnseignants() {
        List<EnseignantBean> enseignantList = new ArrayList<>();
        String getAllEnseignantsQuery = "SELECT * FROM enseignant";

        try (Connection connection = PostgreSQLJDBCConfig.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(getAllEnseignantsQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                EnseignantBean enseignantBean = mapResultSetToEnseignantBean(resultSet);
                enseignantList.add(enseignantBean);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

        return enseignantList;
    }

    // Method to insert an EnseignantBean into the database
    public void insertEnseignantBean(EnseignantBean enseignantBean) {
        String insertEnseignantQuery = "INSERT INTO enseignant (Matricule, Nom, Prenom, Sexe, DateNaissance, " +
                "LieuNaissance, SituationFamille, DateRecrutement, DiplomeRecrutement, DepartementAffectation) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = PostgreSQLJDBCConfig.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(insertEnseignantQuery)) {

            // Set values for the prepared statement using EnseignantBean properties
            preparedStatement.setLong(1, enseignantBean.getMatricule());
            preparedStatement.setString(2, enseignantBean.getNom());
            preparedStatement.setString(3, enseignantBean.getPrenom());
            preparedStatement.setString(4, enseignantBean.getSexe());

            // Convert java.util.Date to java.sql.Date for DateNaissance
            java.sql.Date sqlDateNaissance = new java.sql.Date(enseignantBean.getDateNaissance().getTime());
            preparedStatement.setDate(5, sqlDateNaissance);

            preparedStatement.setString(6, enseignantBean.getLieuNaissance());
            preparedStatement.setString(7, enseignantBean.getSituationFamille());

            // Convert java.util.Date to java.sql.Date for DateRecrutement
            java.sql.Date sqlDateRecrutement = new java.sql.Date(enseignantBean.getDateRecrutement().getTime());
            preparedStatement.setDate(8, sqlDateRecrutement);

            preparedStatement.setString(9, enseignantBean.getDiplomeRecrutement());
            preparedStatement.setString(10, enseignantBean.getDepartementAffectation());

            // Execute the SQL statement
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }
    
    // ... (other methods)
    

    // Method to get an Enseignant by Matricule from the database
    public EnseignantBean getEnseignantByMatricule(Long matricule) {
        EnseignantBean enseignantBean = null;
        String getEnseignantByMatriculeQuery = "SELECT * FROM enseignant WHERE Matricule = ?";

        try (Connection connection = PostgreSQLJDBCConfig.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(getEnseignantByMatriculeQuery)) {

            preparedStatement.setLong(1, matricule);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    enseignantBean = mapResultSetToEnseignantBean(resultSet);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

        return enseignantBean;
    }

    // Method to update an EnseignantBean in the database
 // Method to update an EnseignantBean in the database
    public void updateEnseignantBean(EnseignantBean enseignantBean) {
        String updateEnseignantQuery = "UPDATE enseignant SET Nom = ?, Prenom = ?, Sexe = ?, " +
                "DateNaissance = ?, LieuNaissance = ?, SituationFamille = ?, " +
                "DateRecrutement = ?, DiplomeRecrutement = ?, DepartementAffectation = ? WHERE Matricule = ?";

        try (Connection connection = PostgreSQLJDBCConfig.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(updateEnseignantQuery)) {

            // Set values for the prepared statement using EnseignantBean properties
            preparedStatement.setString(1, enseignantBean.getNom());
            preparedStatement.setString(2, enseignantBean.getPrenom());
            preparedStatement.setString(3, enseignantBean.getSexe());
            preparedStatement.setDate(4, new java.sql.Date(enseignantBean.getDateNaissance().getTime()));
            preparedStatement.setString(5, enseignantBean.getLieuNaissance());
            preparedStatement.setString(6, enseignantBean.getSituationFamille());
            preparedStatement.setDate(7, new java.sql.Date(enseignantBean.getDateRecrutement().getTime()));
            preparedStatement.setString(8, enseignantBean.getDiplomeRecrutement());
            preparedStatement.setString(9, enseignantBean.getDepartementAffectation());
            preparedStatement.setLong(10, enseignantBean.getMatricule());

            // Execute the SQL statement
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }


    // Helper method to map ResultSet to EnseignantBean
    private EnseignantBean mapResultSetToEnseignantBean(ResultSet resultSet) throws SQLException {
        EnseignantBean enseignantBean = new EnseignantBean();

        enseignantBean.setMatricule(resultSet.getLong("Matricule"));
        enseignantBean.setNom(resultSet.getString("Nom"));
        enseignantBean.setPrenom(resultSet.getString("Prenom"));
        enseignantBean.setSexe(resultSet.getString("Sexe"));
        enseignantBean.setDateNaissance(resultSet.getDate("DateNaissance"));
        enseignantBean.setLieuNaissance(resultSet.getString("LieuNaissance"));
        enseignantBean.setSituationFamille(resultSet.getString("SituationFamille"));
        enseignantBean.setDateRecrutement(resultSet.getDate("DateRecrutement"));
        enseignantBean.setDiplomeRecrutement(resultSet.getString("DiplomeRecrutement"));
        enseignantBean.setDepartementAffectation(resultSet.getString("DepartementAffectation"));

        return enseignantBean;
    }
    
    
    
 // Method to check if an EnseignantBean with the given matricule exists
    public boolean exists(Long matricule) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Get a database connection (You need to implement getConnection() method)
            connection = getConnection();

            // Check if the enseignant with the given matricule already exists
            if (isEnseignantExists(connection, matricule)) {
                return true; // If exists, return true
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception as needed
        } finally {
            // Close resources
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }

        return false; // Return false if an exception occurs
    }

    // Utility method to check if an EnseignantBean with the given matricule exists
    private boolean isEnseignantExists(Connection connection, Long matricule) throws SQLException {
        // SQL query to check if an EnseignantBean with the given matricule exists
        String query = "SELECT COUNT(*) FROM enseignant WHERE matricule = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, matricule);

            // Execute the query
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Check the result
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0; // If count is greater than 0, it exists
                }
            }
        }

        return false; // Return false by default
    }

    // Method to insert an EnseignantBean if it doesn't exist already
    public void insertIfNotExists(EnseignantBean enseignantBean) {
        Long matricule = enseignantBean.getMatricule();

        // Check if enseignant with the given matricule already exists
        if (!exists(matricule)) {
            // If not, save the EnseignantBean
            insertEnseignantBean(enseignantBean);
        }
    }

    // Your other methods...

    // You may need to implement the getConnection() method based on your database configuration
    private Connection getConnection() throws SQLException {
        // Implement this method to return a database connection
        // Example: return DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database", "username", "password");
        return null;
    }

    // Utility method to close the database resources
    private void close(AutoCloseable resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (Exception e) {
                e.printStackTrace(); // Log or handle the exception as needed
            }
        }
    }
    
    
}
