/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.transaksi;
import model.produk.Produk;
import model.produk.ProdukMinuman.Options;
/**
 *
 * @author acer
 */
public class CartItem {
    private Produk produk;
    private Object options; // null or ProdukMinuman.Options
    private int qty = 1;
    private double hargaAsli;
    private double hargaSetelahDiskon;
    private double diskonLokal;
    private double subtotal;

    public CartItem(Produk p) {
        this.produk = p; recalc(); 
    }
    public CartItem(Produk p, Object options) {
        this.produk = p; this.options = options; recalc();
    }

    public void recalc() {
        this.hargaAsli = (produk.getPriceWithOptions(options));
        this.hargaSetelahDiskon = produk.getPriceAfterLocalDiscount(hargaAsli);
        this.diskonLokal = hargaAsli - hargaSetelahDiskon;
        this.subtotal = hargaSetelahDiskon * qty;
    }

    public Produk getProduk() {
        return produk;
    }
    
    public Object getOptions() {
        return options;
    }
    
    public int getQty() {return qty;
    }
    public void setQty(int q) {
        this.qty=q; recalc();
    }
    
    public double getHargaAsli() {
        return hargaAsli;
    }
    
    public double getHargaSetelahDiskon() {
        return hargaSetelahDiskon;
    }
    
    public double getDiskonLokal() {
        return diskonLokal;
    }
    
    public double getSubtotal() {
        return subtotal;
    }
}