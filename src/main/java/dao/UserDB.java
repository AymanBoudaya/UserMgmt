package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.User;

public class UserDB {
    private static final String URL = "jdbc:mysql://localhost:3306/usermgmt";
    private static final String USERNAME = "root";
    private static final String PASSWORD = ""; 

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(User user) {
        String sql = "INSERT INTO user (name, mobile, email, dob, city, gender) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, user.getName());    
            ps.setString(2, user.getMobile());  
            ps.setString(3, user.getEmail());  
            if (user.getDob() != null) {
                ps.setDate(4, new java.sql.Date(user.getDob().getTime())); // dob
            } else {
                ps.setDate(4, null);
            }
            ps.setString(5, user.getCity());    
            ps.setString(6, user.getGender()); 

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<User> AllUser() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT id, name, mobile, email, city, gender, dob FROM user";
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setMobile(rs.getString("mobile"));
                user.setEmail(rs.getString("email"));
                user.setCity(rs.getString("city"));
                user.setGender(rs.getString("gender"));
                user.setDob(rs.getDate("dob"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void deleteUser(Long id) {
        String sql = "DELETE FROM user WHERE id = ?";
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updateUser(User user) {
        String sql = "UPDATE user SET name=?, mobile=?, email=?, dob=?, city=?, gender=? WHERE id=?";
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, user.getName());
            ps.setString(2, user.getMobile());
            ps.setString(3, user.getEmail());
            ps.setDate(4, new java.sql.Date(user.getDob().getTime()));
            ps.setString(5, user.getCity());
            ps.setString(6, user.getGender());
            ps.setLong(7, user.getId());
            
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public User getUserById(Long id) {
        User user = null;
        String sql = "SELECT id, name, mobile, email, city, gender, dob FROM user WHERE id = ?";
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setMobile(rs.getString("mobile"));
                user.setEmail(rs.getString("email"));
                user.setCity(rs.getString("city"));
                user.setGender(rs.getString("gender"));
                user.setDob(rs.getDate("dob"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
