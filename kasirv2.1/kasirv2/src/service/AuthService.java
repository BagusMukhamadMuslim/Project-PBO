/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import dao.UserDAO;
import model.user.User;
import util.SecurityUtil;
/**
 *
 * @author acer
 */
public class AuthService {
    private final UserDAO dao = new UserDAO();
    public User login(String username, String passwordPlain) {
        String hash = SecurityUtil.sha256(passwordPlain);
        return dao.findByUsernameAndPassword(username, hash);
    }
}