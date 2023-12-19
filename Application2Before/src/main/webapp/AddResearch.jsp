<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Research" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Research Details</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <!-- Add your custom styles.css if needed -->
    <style>
        .research-card {
            margin-bottom: 20px;
        }

        .research-list {
            list-style: none;
            padding: 0;
        }

        .research-list-item {
            border: 1px solid #ccc;
            margin-bottom: 10px;
            padding: 10px;
        }
    </style>
</head>
<body>

<div class="container mt-5">
    <div class="row">
        <div class="col-md-4">
            <!-- Research Card -->
            <div class="card research-card">
                <div class="card-header">
                    <h2 class="mb-0">Add Research</h2>
                </div>
                <div class="card-body">
                    <form action="AddReserch" method="post">
                        <!-- Add a hidden field for matricule -->
                        <input type="hidden" name="matricule" value="<%= request.getAttribute("matricule") %>">

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
        <div class="col-md-8">
   <%
    // Retrieve the list of research from the request attribute
    List<Research> researchList = (List<Research>) request.getAttribute("researchList");
   %>

   <!-- Research List -->
   <ul class="research-list">
      <!-- Iterate over the list of research and display each one -->
      <% for (Research research : researchList) { %>
         <li class="research-list-item">
            <h5>Research ID: <%= research.getId() %></h5>
            <p>Laboratoire: <%= research.getLaboratoire() %></p>
            <p>Date: <%= research.getDatePromotionResearch() %></p>
            <p>Grade: <%= research.getGradeResearch() %></p>
            <!-- Add other research details as needed -->
         </li>
      <% } %>
   </ul>
</div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

</body>
</html>
