<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Enseignant" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Update Enseignant</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-5">
    <div class="card">
        <div class="card-header">
            <h2 class="mb-0">Update Enseignant</h2>
        </div>
        <div class="card-body">
            <%
            Enseignant enseignant = (Enseignant) request.getAttribute("enseignant");
            %>

            <form action="updateEns" method="post">
                <div class="form-group">
                    <label for="matricule">Matricule:</label>
                    <input type="text" class="form-control" id="matricule" name="matricule" value="<%=enseignant.getMatricule()%>" readonly>
                </div>

                <div class="form-group">
                    <label for="nom">Nom:</label>
                    <input type="text" class="form-control" id="nom" name="nom" value="<%=enseignant.getNom()%>" required>
                </div>

                <div class="form-group">
                    <label for="prenom">Prenom:</label>
                    <input type="text" class="form-control" id="prenom" name="prenom" value="<%=enseignant.getPrenom()%>" required>
                </div>

                <div class="form-group">
                    <label for="sexe">Sexe:</label>
                    <select class="form-control" id="sexe" name="sexe" required>
                        <option value="Male" <%=enseignant.getSexe().equals("Male") ? "selected" : ""%>>Male</option>
                        <option value="Female" <%=enseignant.getSexe().equals("Female") ? "selected" : ""%>>Female</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="dateNaissance">Date Naissance:</label>
                    <input type="date" class="form-control" id="dateNaissance" name="dateNaissance" value="<%=enseignant.getDateNaissance()%>" required>
                </div>

                <div class="form-group">
                    <label for="lieuNaissance">Lieu Naissance:</label>
                    <input type="text" class="form-control" id="lieuNaissance" name="lieuNaissance" value="<%=enseignant.getLieuNaissance()%>" required>
                </div>

                <div class="form-group">
                    <label for="situationFamille">Situation Famille:</label>
                    <select class="form-control" id="situationFamille" name="situationFamille" required>
                        <option value="C" <%=enseignant.getSituationFamille().equals("C") ? "selected" : ""%>>Single</option>
                        <option value="M" <%=enseignant.getSituationFamille().equals("M") ? "selected" : ""%>>Married</option>
                        <option value="D" <%=enseignant.getSituationFamille().equals("D") ? "selected" : ""%>>Divorced</option>
                        <option value="V" <%=enseignant.getSituationFamille().equals("V") ? "selected" : ""%>>V idk</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="departementAffectation">Departement Affectation:</label>
                    <select class="form-control" id="departementAffectation" name="departementAffectation" required>
                        <option value="TLSI" <%= "TLSI".equals(enseignant.getDepartementAffectation()) ? "selected" : ""%>>TLSI</option>
                        <option value="IFA" <%= "IFA".equals(enseignant.getDepartementAffectation()) ? "selected" : ""%>>IFA</option>
                        <option value="NTIC" <%= "NTIC".equals(enseignant.getDepartementAffectation()) ? "selected" : ""%>>NTIC</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="dateRecrutement">Date Recrutement:</label>
                    <input type="date" class="form-control" id="dateRecrutement" name="dateRecrutement" value="<%=enseignant.getDateRecrutement()%>" readonly>
                </div>

                <div class="form-group">
                    <label for="diplomeRecrutement">Diplome Recrutement:</label>
                    <input type="text" class="form-control" id="diplomeRecrutement" name="diplomeRecrutement" value="<%=enseignant.getDiplomeRecrutement()%>" required>
                </div>

               

                <button type="submit" class="btn btn-primary">Update Enseignant</button>
            </form>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>
