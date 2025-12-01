/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;
import app.Database;
import java.sql.Connection;
import java.sql.SQLException;
/**
 *
 * @author acer
 */
public class DBUtil {
    public static Connection getConnection() {
        try {
            return Database.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

