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



 // Method to add an Enseignant to the database
    public void addEnseignant(Enseignant enseignant) {
        String sql = "INSERT INTO enseignant (Matricule, Nom, Prenom, Sexe, DateNaissance, LieuNaissance, " +
                     "SituationFamille, Conjoint, EnfantCharge, DateRecrutement, DiplomeRecrutement, " +
                     "DepartementAffectation, EtatActual) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // Set values for the prepared statement using the Enseignant object
            preparedStatement.setLong(1, enseignant.getMatricule());
            preparedStatement.setString(2, enseignant.getNom());
            preparedStatement.setString(3, enseignant.getPrenom());
            preparedStatement.setString(4, enseignant.getSexe()); // Using String instead of Gender
            
            // Convert java.util.Date to java.sql.Date
            java.sql.Date sqlDateNaissance = new java.sql.Date(enseignant.getDateNaissace().getTime());
            preparedStatement.setDate(5, sqlDateNaissance);
            preparedStatement.setString(6, enseignant.getLieuNaissance());
            preparedStatement.setString(7, enseignant.getSituationFamille()); // Using String instead of SituationEnum
            preparedStatement.setString(8, enseignant.getConjoint());
            preparedStatement.setInt(9, enseignant.getEnfantCharge());
            java.sql.Date sqlDateRecrutement = new java.sql.Date(enseignant.getDateRecrutment().getTime());
            preparedStatement.setDate(10, sqlDateRecrutement);
            preparedStatement.setString(11, enseignant.getDiplomeRecrutment());
            preparedStatement.setString(12, enseignant.getDepartementAffectation()); // Using String instead of DepartmentEnum
            preparedStatement.setString(13, enseignant.getEtatActual()); // Using String instead of Etat

            // Execute the SQL statement
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }


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

    // Method to delete an Enseignant by Matricule
    public void deleteEnseignantByMatricule(Long matricule) {
        String sql = "DELETE FROM enseignant WHERE Matricule = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // Set Matricule value for the prepared statement
            preparedStatement.setLong(1, matricule);

            // Execute the SQL statement
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

 // Method to update an Enseignant by Matricule
    public void updateEnseignantByMatricule(Enseignant enseignant) {
        String sql = "UPDATE enseignant SET Nom = ?, Prenom = ?, Sexe = ?, DateNaissance = ?, " +
                     "LieuNaissance = ?, SituationFamille = ?, Conjoint = ?, EnfantCharge = ?, " +
                     "DateRecrutement = ?, DiplomeRecrutement = ?, DepartementAffectation = ?, " +
                     "EtatActual = ? WHERE Matricule = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // Set values for the prepared statement using the Enseignant object
            preparedStatement.setString(1, enseignant.getNom());
            preparedStatement.setString(2, enseignant.getPrenom());
            preparedStatement.setString(3, enseignant.getSexe()); // Using String instead of Gender
            java.sql.Date sqlDateNaissance = new java.sql.Date(enseignant.getDateNaissace().getTime());
            preparedStatement.setDate(4, sqlDateNaissance);
            preparedStatement.setString(5, enseignant.getLieuNaissance());
            preparedStatement.setString(6, enseignant.getSituationFamille()); // Using String instead of SituationEnum
            preparedStatement.setString(7, enseignant.getConjoint());
            preparedStatement.setInt(8, enseignant.getEnfantCharge());
            java.sql.Date sqlDateRecrutement = new java.sql.Date(enseignant.getDateRecrutment().getTime());
            preparedStatement.setDate(9, sqlDateRecrutement);
            preparedStatement.setString(10, enseignant.getDiplomeRecrutment());
            preparedStatement.setString(11, enseignant.getDepartementAffectation()); // Using String instead of DepartmentEnum
            preparedStatement.setString(12, enseignant.getEtatActual()); // Using String instead of Etat
            preparedStatement.setLong(13, enseignant.getMatricule());

            // Execute the SQL statement
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }


 // Method to get all enseignants from the database
    public List<Enseignant> getAllEnseignants() {
        List<Enseignant> enseignants = new ArrayList<>();
        String sql = "SELECT * FROM enseignant";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // Execute the SQL statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // Iterate through the result set and add enseignants to the list
            while (resultSet.next()) {
                Enseignant enseignant = mapResultSetToEnseignant(resultSet);
                enseignants.add(enseignant);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

        return enseignants;
    }
    
    

 // Helper method to map a ResultSet to an Enseignant object
    private Enseignant mapResultSetToEnseignant(ResultSet resultSet) throws SQLException {
        // Create and return an Enseignant object based on the retrieved data
        return new Enseignant(
                resultSet.getLong("Matricule"),
                resultSet.getString("Nom"),
                resultSet.getString("Prenom"),
                resultSet.getString("Sexe"), // Using String instead of Gender
                resultSet.getDate("DateNaissance"),
                resultSet.getString("LieuNaissance"),
                resultSet.getString("SituationFamille"), // Using String instead of SituationEnum
                resultSet.getString("Conjoint"),
                resultSet.getInt("EnfantCharge"),
                resultSet.getDate("DateRecrutement"),
                resultSet.getString("DiplomeRecrutement"),
                resultSet.getString("DepartementAffectation"), // Using String instead of DepartmentEnum
                resultSet.getString("EtatActual") // Using String instead of Etat
        );
    }
}
