/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import model.user.Admin;
import model.user.Kasir;
import model.user.User;
import util.DBUtil;
import java.sql.*;
/**
 *
 * @author acer
 */
public class UserDAO {
    public User findByUsernameAndPassword(String username, String passwordHash) {
        try (PreparedStatement ps = DBUtil.getConnection()
            .prepareStatement("SELECT id,username,fullname,role FROM users WHERE username=? AND password_hash=?")) {
            ps.setString(1, username);
            ps.setString(2, passwordHash);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String uname = rs.getString("username");
                String fullname = rs.getString("fullname");
                String role = rs.getString("role");
                if ("ADMIN".equalsIgnoreCase(role)) return new Admin(id, uname, fullname);
                else return new Kasir(id, uname, fullname);
            }
        } catch (SQLException e) {e.printStackTrace(); }
        return null;
    }
}