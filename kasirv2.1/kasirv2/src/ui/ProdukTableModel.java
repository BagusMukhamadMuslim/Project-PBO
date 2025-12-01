/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;
import model.produk.Produk;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author acer
 */
public class ProdukTableModel extends AbstractTableModel {
    private String[] cols = {"ID","Kode","Nama","Kategori","Harga","Diskon"};
    private List<Produk> data = new ArrayList<>();
    public void setData(List<Produk> d){this.data=d; fireTableDataChanged();}
    public Produk getAt(int r){ return data.get(r); }
    public int getRowCount(){ return data.size(); }
    public int getColumnCount(){ return cols.length; }
    public String getColumnName(int c){ return cols[c]; }
    public Object getValueAt(int r,int c){
        Produk p = data.get(r);
        return switch(c) {
            case 0 -> p.getId();
            case 1 -> p.getKode();
            case 2 -> p.getNama();
            case 3 -> p.getKategori();
            case 4 -> p.getHarga();
            case 5 -> p.getTipeDiskon().equalsIgnoreCase("PERSEN")? p.getDiskonPersen()+"%": (p.getTipeDiskon().equalsIgnoreCase("FLAT")? "Rp "+p.getDiskonNilai() : "-");
            default -> null;
        };
    }
}

