/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.transaksi;
import java.util.List;
/**
 *
 * @author acer
 */
public class Transaksi {
    private int id;
    private String noStruk;
    private String kasirUsername;
    private double subtotal;
    private double diskonGlobal;
    private double pajak;
    private double total;
    private double tunai;
    private double kembali;
    private List<CartItem> items;
}