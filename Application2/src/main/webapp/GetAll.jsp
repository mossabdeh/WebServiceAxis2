<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.EnseignantBean" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Enseignant List</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>

    <div class="container mt-5">
        <h2>Enseignant List</h2>

        <%-- Check if there are enseignantBeans in the request --%>
        <% List<EnseignantBean> enseignantBeans = (List<EnseignantBean>) request.getAttribute("enseignantBeans");
        if (enseignantBeans != null && !enseignantBeans.isEmpty()) { %>

            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Matricule</th>
                        <th>Nom</th>
                        <th>Prenom</th>
                        <th>Sexe</th>
                        <th>Date Naissance</th>
                        <th>Lieu Naissance</th>
                        <th>Situation Famille</th>
                        <th>Date Recrutement</th>
                        <th>Diplome Recrutement</th>
                        <th>Departement Affectation</th>
                        <th>Action</th> <!-- New column for the button -->
                    </tr>
                </thead>
                <tbody>
                    <% for (EnseignantBean enseignantBean : enseignantBeans) { %>
                        <tr>
                            <td><%= enseignantBean.getMatricule() %></td>
                            <td><%= enseignantBean.getNom() %></td>
                            <td><%= enseignantBean.getPrenom() %></td>
                            <td><%= enseignantBean.getSexe() %></td>
                            <td><%= enseignantBean.getDateNaissance() %></td>
                            <td><%= enseignantBean.getLieuNaissance() %></td>
                            <td><%= enseignantBean.getSituationFamille() %></td>
                            <td><%= enseignantBean.getDateRecrutement() %></td>
                            <td><%= enseignantBean.getDiplomeRecrutement() %></td>
                            <td><%= enseignantBean.getDepartementAffectation() %></td>
                            <td>
                                <!-- Button to view more details -->
                                <a href="GetEnsByMatricule?matricule=<%= enseignantBean.getMatricule() %>" 
                                   class="btn btn-info" role="button">
                                    View Details
                                </a>
                            </td>
                        </tr>
                    <% } %>
                </tbody>
            </table>

        <% } else { %>

            <p>No enseignant information available.</p>

        <% } %>

    </div>

</body>
</html>
