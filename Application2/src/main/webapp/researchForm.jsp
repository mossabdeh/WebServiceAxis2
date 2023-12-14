<%@page import="ws.EnseignantWebServiceStub"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="ws.EnseignantWebServiceStub.Enseignant" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@page import="model.Research"%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Research Form</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-5">
    <div class="row">
        <!-- Research Form -->
        <div class="col-md-6">
            <div class="card">
                <div class="card-header">
                    <h2 class="mb-0">Research Form</h2>
                </div>
                <div class="card-body">
                    <form action="AddResearch" method="post">
                        <!-- Add a hidden field for matricule -->
                        <!-- Assuming enseignantWS is an instance of Enseignant -->
                        <%
                            EnseignantWebServiceStub.Enseignant enseignantWS = (EnseignantWebServiceStub.Enseignant) request.getAttribute("enseignantWS");
                        %>
                        <input type="hidden" name="matricule" value="<%= enseignantWS.getMatricule() %>">

                        <div class="form-group">
                            <label for="laboratoire">Laboratoire:</label>
                            <input type="text" class="form-control" id="laboratoire" name="laboratoire" required>
                        </div>

                        <div class="form-group">
                            <label for="datePromotionResearch">Date Promotion Research:</label>
                            <input type="date" class="form-control" id="datePromotionResearch" name="datePromotionResearch" required>
                        </div>

                        <div class="form-group">
                            <label for="gradeResearch">Grade Research:</label>
                            <input type="text" class="form-control" id="gradeResearch" name="gradeResearch" required>
                        </div>

                        <!-- Add other form fields for research as needed -->

                        <button type="submit" class="btn btn-primary">Add Research</button>
                    </form>
                </div>
            </div>
        </div>

        <!-- Display List of Research Records -->
        <div class="col-md-6">
            <div class="mt-4">
                <h3>Research Records</h3>
                <ul class="list-group">
                    <% List<Research> researchList = (List<Research>) request.getAttribute("researchList");
                    if (researchList != null && !researchList.isEmpty()) {
                        for (Research research : researchList) { %>
                            <li class="list-group-item">
                                <strong>Research ID:</strong> <%= research.getId() %><br>
                                <strong>Laboratoire:</strong> <%= research.getLaboratoire() %><br>
                                <strong>Date Promotion Research:</strong> <%= new SimpleDateFormat("yyyy-MM-dd").format(research.getDatePromotionResearch()) %><br>
                                <strong>Grade Research:</strong> <%= research.getGradeResearch() %><br>
                                <!-- Add other research details as needed -->
                            </li>
                    <% } } else { %>
                        <li class="list-group-item">No research records available.</li>
                    <% } %>
                </ul>
            </div>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

</body>
</html>
