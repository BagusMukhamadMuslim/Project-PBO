/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.produk;
/**
 *
 * @author acer
 */
public class ProdukMakanan extends Produk {
    public ProdukMakanan() { 
        this.kategori = "MAKANAN"; 
    }
    
    @Override
    public double getPriceWithOptions(Object options) {
        // makanan tidak punya options
        return this.harga;
    }
}