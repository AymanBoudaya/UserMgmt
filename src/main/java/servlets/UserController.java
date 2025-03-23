package servlets;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dao.UserDB;
import models.User;
import java.util.Date;

@WebServlet("/UserController")
public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDB userDB;

    @Override
    public void init() throws ServletException {
        userDB = new UserDB();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "new":
                RequestDispatcher rdNew = request.getRequestDispatcher("form.jsp");
                rdNew.forward(request, response);
                break;
            case "edit":
                Long editId = Long.parseLong(request.getParameter("id"));
                User editUser = userDB.getUserById(editId);
                request.setAttribute("user", editUser);
                RequestDispatcher rdEdit = request.getRequestDispatcher("edit.jsp");
                rdEdit.forward(request, response);
                break;
            case "delete":
                Long id = Long.parseLong(request.getParameter("id"));
                userDB.deleteUser(id);
                response.sendRedirect("UserController?action=list");
                break;
            case "list":
            default:
                List<User> users = userDB.AllUser();
                request.setAttribute("users", users);
                RequestDispatcher rdList = request.getRequestDispatcher("list.jsp");
                rdList.forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if ("update".equals(action)) {
            try {
                Long id = Long.parseLong(request.getParameter("id"));
                String name = request.getParameter("name");
                String email = request.getParameter("email");
                String mobile = request.getParameter("mobile");
                String dobStr = request.getParameter("dob");
                String gender = request.getParameter("gender");
                String city = request.getParameter("city");

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date dob = sdf.parse(dobStr);

                User user = new User();
                user.setId(id);
                user.setName(name);
                user.setEmail(email);
                user.setMobile(mobile);
                user.setDob(dob);
                user.setGender(gender);
                user.setCity(city);

                userDB.updateUser(user);
                response.sendRedirect("UserController?action=list");

            } catch (ParseException e) {
                User user = new User();
                user.setId(Long.parseLong(request.getParameter("id")));
                user.setName(request.getParameter("name"));
                user.setEmail(request.getParameter("email"));
                user.setMobile(request.getParameter("mobile"));
                user.setGender(request.getParameter("gender"));
                user.setCity(request.getParameter("city"));
                
                request.setAttribute("errorMessage", "Format de date invalide (YYYY-MM-DD)");
                request.setAttribute("user", user);
                RequestDispatcher rd = request.getRequestDispatcher("edit.jsp");
                rd.forward(request, response);
            }
            
        } else {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String mobile = request.getParameter("mobile");
            String dobStr = request.getParameter("dob");
            String gender = request.getParameter("gender");
            String city = request.getParameter("city");

            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date dob = sdf.parse(dobStr);

                User user = new User();
                user.setName(name);
                user.setEmail(email);
                user.setMobile(mobile);
                user.setDob(dob);
                user.setGender(gender);
                user.setCity(city);

                userDB.saveUser(user);
                response.sendRedirect("UserController?action=list");

            } catch (ParseException e) {
                request.setAttribute("errorMessage", "Format de date invalide (YYYY-MM-DD)");
                RequestDispatcher rd = request.getRequestDispatcher("form.jsp");
                rd.forward(request, response);
            }
        }
    }
    
}
