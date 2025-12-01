/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.user;
/**
 *
 * @author acer
 */
public abstract class User {
    protected int id;
    protected String username;
    protected String fullname;

    protected String role; // "ADMIN" or "KASIR"

    public User() {}
    public User(int id, String username, String fullname, String role) {
        this.id = id; this.username = username; this.fullname = fullname; this.role = role;
    }

    public int getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getFullname() {
        return fullname;
    }
    public String getRole() {
        return role;
    }

    public abstract boolean canManageProducts();
    public abstract boolean canProcessTransactions();
    public boolean isAdmin() {
        return "ADMIN".equalsIgnoreCase(role);
    }
}
