/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import util.DBUtil;
import model.transaksi.CartItem;
import java.sql.*;
import java.util.List;
/**
 *
 * @author acer
 */
public class TransaksiDAO {
    public int saveTransaction(String noStruk, String kasirUsername, double subtotal, double diskonGlobal, double pajak, double total, double tunai, double kembali, List<CartItem> items) {
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);
            String headerSql = "INSERT INTO transaksi(no_struk,kasir_username,subtotal,diskon_global,pajak,total,tunai,kembali) VALUES(?,?,?,?,?,?,?,?)";
            try (PreparedStatement ph = conn.prepareStatement(headerSql, Statement.RETURN_GENERATED_KEYS)) {
                ph.setString(1,noStruk);
                ph.setString(2,kasirUsername);
                ph.setDouble(3,subtotal);
                ph.setDouble(4,diskonGlobal);
                ph.setDouble(5,pajak);
                ph.setDouble(6,total);
                ph.setDouble(7,tunai);
                ph.setDouble(8,kembali);
                ph.executeUpdate();
                ResultSet keys = ph.getGeneratedKeys();
                int tid = -1;
                if (keys.next()) tid = keys.getInt(1);

                String detailSql = "INSERT INTO transaksi_detail(transaksi_id,produk_id,nama_snapshot,opsi,harga_asli,diskon_lokal,harga_setelah_diskon,qty,subtotal) VALUES(?,?,?,?,?,?,?,?,?)";
                try (PreparedStatement pd = conn.prepareStatement(detailSql)) {
                    for (CartItem it : items) {
                        pd.setInt(1, tid);
                        pd.setInt(2, it.getProduk().getId());
                        pd.setString(3, it.getProduk().getNama());
                        pd.setString(4, it.getOptions()==null ? "" : it.getOptions().toString());
                        pd.setDouble(5, it.getHargaAsli());
                        pd.setDouble(6, it.getDiskonLokal());
                        pd.setDouble(7, it.getHargaSetelahDiskon());
                        pd.setInt(8, it.getQty());
                        pd.setDouble(9, it.getSubtotal());
                        pd.addBatch();
                    }
                    pd.executeBatch();
                }
                conn.commit();
                return tid;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try { 
                if (conn!=null) conn.rollback(); 
            } catch (SQLException ex) { ex.printStackTrace(); }
            return -1;
        } finally {
            try { 
                if (conn!=null) conn.setAutoCommit(true); 
            } catch (SQLException e) { e.printStackTrace(); }
        }
    }
}