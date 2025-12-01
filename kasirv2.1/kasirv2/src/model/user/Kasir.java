/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.user;
/**
 *
 * @author acer
 */
public class Kasir extends User {
    public Kasir() {
        this.role = "KASIR";
    }
    public Kasir(int id, String username, String fullname) {
        super(id, username, fullname, "KASIR");
    }

    @Override
    public boolean canManageProducts() {
        return false; 
    }
    @Override
    public boolean canProcessTransactions() {
        return true; 
    }
}