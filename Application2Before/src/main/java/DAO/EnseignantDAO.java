package DAO;

import config.PostgreSQLJDBCConfig;
import model.Enseignant;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnseignantDAO {
	
	 private final Connection connection;
	
	 public EnseignantDAO() {
	        // Establish a database connection using the PostgreSQLJDBCConfig class
	        this.connection = PostgreSQLJDBCConfig.connect();
	    }
	
	// Method to retrieve all Enseignants from the database
    public List<Enseignant> getAllEnseignants() {
        List<Enseignant> enseignantList = new ArrayList<>();
        String getAllEnseignantsQuery = "SELECT * FROM enseignant";

        try (Connection connection = PostgreSQLJDBCConfig.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(getAllEnseignantsQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Enseignant enseignantBean = mapResultSetToEnseignant(resultSet);
                enseignantList.add(enseignantBean);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

        return enseignantList;
    }

    // Method to insert an EnseignantBean into the database
    public void insertEnseignant(Enseignant enseignantBean) {
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
    

    // Method to get an Enseignant by Matricule
    public Enseignant getEnseignantByMatricule(Long matricule) {
        String sql = "SELECT * FROM enseignant WHERE Matricule = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // Set Matricule value for the prepared statement
            preparedStatement.setLong(1, matricule);

            // Execute the SQL statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if a row is returned
            if (resultSet.next()) {
                // Create and return an Enseignant object based on the retrieved data
                return mapResultSetToEnseignant(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
        return null; // Return null if no Enseignant is found
    }

    // Method to update an EnseignantBean in the database
 // Method to update an EnseignantBean in the database
    public void updateEnseignantBean(Enseignant enseignantBean) {
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

 // Method to update an EnseignantBean in the database by Matricule
    public void updateEnseignantByMatricule(Enseignant enseignantBean) {
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
    private Enseignant mapResultSetToEnseignant(ResultSet resultSet) throws SQLException {
        Enseignant enseignantBean = new Enseignant();

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
    public void insertIfNotExists(Enseignant enseignantBean) {
        Long matricule = enseignantBean.getMatricule();

        // Check if enseignant with the given matricule already exists
        if (!exists(matricule)) {
            // If not, save the EnseignantBean
            insertEnseignant(enseignantBean);
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
    
    // Method to check if a Matricule already exists in the database
    public boolean doesMatriculeExist(Long matricule) {
        boolean exists = false;

        // Use a SQL query to check if the Matricule exists
        String sql = "SELECT 1 FROM enseignant WHERE Matricule = ?";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, matricule);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            exists = resultSet.next(); // If there's a result, the Matricule exists
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

        return exists;
    }

    public Long getLastMatricule(int year) {
        Long lastMatricule = null;

        // Use a SQL query to retrieve the last used Matricule for the given year
        String sql = "SELECT MAX(Matricule) FROM enseignant WHERE EXTRACT(YEAR FROM DateRecrutement) = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, year);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                lastMatricule = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

        return lastMatricule;
    }

 // Method to get the highest sequential number for a given year
    public long getHighestSequentialNumber(int year) {
        long highestSequentialNumber = 0;

        // Use a SQL query to retrieve the highest sequential number for the given year
        String sql = "SELECT MAX(CAST(SUBSTRING(CAST(matricule AS TEXT), 5) AS INTEGER)) " +
                     "FROM enseignant WHERE EXTRACT(YEAR FROM DateRecrutement) = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, year);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                highestSequentialNumber = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

        return highestSequentialNumber;
    }
    
    
}
