/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;
import model.transaksi.CartItem;
import javax.swing.table.AbstractTableModel;
import java.util.List;
/**
 *
 * @author acer
 */
public class CartTableModel extends AbstractTableModel {
    private String[] cols = {"Produk","Opts","Qty","Harga","Diskon","Subtotal"};
    private List<CartItem> data;
    public CartTableModel(List<CartItem> data){ this.data = data; }
    public void setData(List<CartItem> d){ this.data = d; fireTableDataChanged(); }
    public int getRowCount(){ return data.size(); }
    public int getColumnCount(){ return cols.length; }
    public String getColumnName(int c){ return cols[c]; }
    public Object getValueAt(int r,int c){
        CartItem it = data.get(r);
        return switch(c) {
            case 0 -> it.getProduk().getNama();
            case 1 -> it.getOptions()==null? "-" : it.getOptions().toString();
            case 2 -> it.getQty();
            case 3 -> it.getHargaAsli();
            case 4 -> it.getDiskonLokal();
            case 5 -> it.getSubtotal();
            default -> null;
        };
    }
}

