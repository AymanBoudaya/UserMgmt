<%@ page import="models.User, java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit User</title>
    <link rel="stylesheet" href="css/bootstrap.css">
</head>
<body>
    <div class="container">
        <h2 class="text-primary text-center mt-4">Edit User</h2>
        
        <% 
            User user = (User) request.getAttribute("user");
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null) { 
        %>
            <div class="alert alert-danger"><%= errorMessage %></div>
        <% } %>
        
        <form action="UserController" method="post">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="id" value="<%= user.getId() %>">
            
            <table class="table table-hover table-striped mt-3">
                <tr>
                    <td>Name</td>
                    <td><input type="text" name="name" value="<%= user.getName() %>" class="form-control"></td>
                </tr>
                <tr>
                    <td>Email</td>
                    <td><input type="email" name="email" value="<%= user.getEmail() %>" class="form-control"></td>
                </tr>
                <tr>
                    <td>Mobile</td>
                    <td><input type="text" name="mobile" value="<%= user.getMobile() %>" class="form-control"></td>
                </tr>
                <tr>
                    <td>DOB</td>
                    <td>
                        <%
                            String formattedDate = "";
                            if (user.getDob() != null) {
                                formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(user.getDob());
                            }
                        %>
                        <input type="date" name="dob" value="<%= formattedDate %>" class="form-control">
                    </td>
                </tr>
                <tr>
                    <td>City</td>
                    <td><input type="text" name="city" value="<%= user.getCity() %>" class="form-control"></td>
                </tr>
                <tr>
                    <td>Gender</td>
                    <td>
                        <select name="gender" class="form-control">
                            <option value="Male" <%= "Male".equals(user.getGender()) ? "selected" : "" %>>Male</option>
                            <option value="Female" <%= "Female".equals(user.getGender()) ? "selected" : "" %>>Female</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <button type="submit" class="btn btn-warning">Update</button>
                    </td>
                    <td>
                        <a href="UserController?action=list" class="btn btn-danger">Cancel</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>