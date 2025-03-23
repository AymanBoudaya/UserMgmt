<%@ page import="java.util.List, models.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User List</title>
    <link rel="stylesheet" href="css/bootstrap.css">
</head>
<body>
    <div class="container">
        <marquee><h2 class="text-primary">User List</h2></marquee>
            
        <% String errorMessage = (String) request.getAttribute("errorMessage"); 
           if (errorMessage != null) { %>
            <div class="alert alert-danger"><%= errorMessage %></div>
        <% } %>
        
        <table class="table table-hover table-striped mt-3">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Mobile</th>
                    <th>DOB</th>
                    <th>City</th>
                    <th>Gender</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<User> users = (List<User>) request.getAttribute("users");
                    if (users != null && !users.isEmpty()) {
                        for (User user : users) {
                %>
                    <tr>
                        <td><%= user.getId() %></td>
                        <td><%= user.getName() %></td>
                        <td><%= user.getEmail() %></td>
                        <td><%= user.getMobile() %></td>
                        <td><%= user.getDob() %></td>
                        <td><%= user.getCity() %></td>
                        <td><%= user.getGender() %></td>
                        <td>
                            <a href="UserController?action=edit&id=<%= user.getId() %>" class="btn btn-warning">Edit</a>
                        </td>
                        <td>
                            <a href="UserController?action=delete&id=<%= user.getId() %>" class="btn btn-danger">Delete</a>
                        </td>
                    </tr>
                <%
                        }
                    } else {
                %>
                    <tr>
                        <td colspan="9" class="text-center">No users found.</td>
                    </tr>
                <% } %>
            </tbody>
        </table>
        <a href="form.jsp" class="btn btn-outline-success">Back to Registration</a>
    </div>
</body>
</html>
