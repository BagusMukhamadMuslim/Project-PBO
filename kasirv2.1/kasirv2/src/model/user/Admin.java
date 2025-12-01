/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.user;
/**
 *
 * @author acer
 */
public class Admin extends User {
    public Admin() {
        this.role = "ADMIN";
    }
    public Admin(int id, String username, String fullname) {
        super(id, username, fullname, "ADMIN");
    }

    @Override
    public boolean canManageProducts() {
        return true;
    }
    
    @Override
    public boolean canProcessTransactions() {
        return true;
    }
}