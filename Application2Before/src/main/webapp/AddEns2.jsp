<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.Date" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Enseignant" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Enseignant</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>

    <div class="container mt-5">
        <h2>Add Enseignant</h2>

        <%-- Display error message if available --%>
        <% String errorMessage = (String) request.getAttribute("errorMessage"); %>
        <% if (errorMessage != null && !errorMessage.isEmpty()) { %>
            <div class="alert alert-danger" role="alert">
                <%= errorMessage %>
            </div>
        <% } %>

        <form action="addEns" method="post">
            <div class="form-group">
                <label for="nom">Nom:</label>
                <input type="text" class="form-control" id="nom" name="nom" required>
            </div>
            <div class="form-group">
                <label for="prenom">Prenom:</label>
                <input type="text" class="form-control" id="prenom" name="prenom" required>
            </div>
            <div class="form-group">
                    <label for="sexe">Sexe:</label>
                    <select class="form-control" id="sexe" name="sexe" required>
                        <option value="Male">Male</option>
                        <option value="Female">Female</option>
                    </select>
                </div>            <div class="form-group">
                <label for="dateNaissance">Date Naissance:</label>
                <input type="date" class="form-control" id="dateNaissance" name="dateNaissance" required>
            </div>
            <div class="form-group">
                <label for="lieuNaissance">Lieu Naissance:</label>
                <input type="text" class="form-control" id="lieuNaissance" name="lieuNaissance" required>
            </div>
           <div class="form-group">
                    <label for="situationFamille">Situation Famille:</label>
                    <select class="form-control" id="situationFamille" name="situationFamille" required>
                        <option value="C">Single</option>
                        <option value="M">Married</option>
                        <option value="D">Divorced</option>
                        <option value="V">V idk</option>
                    </select>
                </div>
            <div class="form-group">
                <label for="dateRecrutement">Date Recrutement:</label>
                <input type="date" class="form-control" id="dateRecrutement" name="dateRecrutement" required>
            </div>
            <div class="form-group">
                <label for="diplomeRecrutement">Diplome Recrutement:</label>
                <input type="text" class="form-control" id="diplomeRecrutement" name="diplomeRecrutement" required>
            </div>
             <div class="form-group">
                    <label for="departementAffectation">Departement Affectation:</label>
                    <select class="form-control" id="departementAffectation" name="departementAffectation" required>
                        <option value="TLSI">TLSI</option>
                        <option value="IFA">IFA</option>
                        <option value="NTIC">NTIC</option>
                    </select>
                </div>

            <button type="submit" class="btn btn-primary">Add Enseignant</button>
        </form>
    </div>

</body>
</html>
