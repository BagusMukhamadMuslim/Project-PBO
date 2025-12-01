/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.produk;
/**
 *
 * @author acer
 */
public abstract class Produk {
    protected int id;
    protected String kode;
    protected String nama;
    protected String kategori; // MAKANAN atau MINUMAN
    protected double harga;
    protected String pathGambar;
    // discount fields
    protected String tipeDiskon = "NONE"; // NONE | PERSEN | FLAT
    protected double diskonPersen = 0;
    protected double diskonNilai = 0;
    protected int aktif = 1;

    public Produk() {}

    // getters/setters
    public int getId() {
        return id;
    } public void setId(int id) {
        this.id=id;
    }
    
    public String getKode() {
        return kode;
    } public void setKode(String kode) {
        this.kode=kode;
    }
    
    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama=nama;
    }
    
    public String getKategori() {
        return kategori;
    }
    public void setKategori(String kategori) {
        this.kategori=kategori;
    }
    
    public double getHarga() {
        return harga;
     } 
    public void setHarga(double harga) {
        this.harga=harga;
    }
    
    public String getPathGambar() {
        return pathGambar;
    } public void setPathGambar(String p) {
        this.pathGambar=p;
    }
    
    public String getTipeDiskon() {
        return tipeDiskon;
    } 
    public void setTipeDiskon(String t) {
        this.tipeDiskon=t;
    }
    
    public double getDiskonPersen() {
        return diskonPersen;
    }
    public void setDiskonPersen(double d) {
            this.diskonPersen=d;
        }
    
    public double getDiskonNilai() {
        return diskonNilai;
    } public void setDiskonNilai(double d) {
        this.diskonNilai=d;
    }
    
    public int getAktif() {
        return aktif;
    }
    public void setAktif(int a) {
        this.aktif=a;
    }

    // compute base price for this product with options (override in subclasses)
    public abstract double getPriceWithOptions(Object options);

    // apply local discount (percent/flat) on base price
    public double getPriceAfterLocalDiscount(double basePrice) {
        if ("PERSEN".equalsIgnoreCase(tipeDiskon)) {
            return Math.max(0, basePrice - (basePrice * diskonPersen / 100.0));
        } else if ("FLAT".equalsIgnoreCase(tipeDiskon)) {
            return Math.max(0, basePrice - diskonNilai);
        } else {
            return basePrice;
        }
    }
}