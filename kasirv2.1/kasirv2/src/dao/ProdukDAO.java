/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import model.produk.Produk;
import util.DBUtil;
import java.sql.*;
import java.util.*;
/**
 *
 * @author acer
 */
public class ProdukDAO {
    public List<Produk> findAllActive() {
        List<Produk> list = new ArrayList<>();
        try (PreparedStatement ps = DBUtil.getConnection().prepareStatement("SELECT * FROM produk WHERE aktif=1 ORDER BY nama")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Produk p = mapRowToProduk(rs);
                list.add(p);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public Produk findById(int id) {
        try (PreparedStatement ps = DBUtil.getConnection().prepareStatement("SELECT * FROM produk WHERE id=?")) {
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapRowToProduk(rs);
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    private Produk mapRowToProduk(ResultSet rs) throws SQLException {
        String kategori = rs.getString("kategori");
        Produk p;
        if ("MINUMAN".equalsIgnoreCase(kategori)) p = new model.produk.ProdukMinuman();
        else p = new model.produk.ProdukMakanan();
        p.setId(rs.getInt("id"));
        p.setKode(rs.getString("kode"));
        p.setNama(rs.getString("nama"));
        p.setKategori(kategori);
        p.setHarga(rs.getDouble("harga"));
        p.setPathGambar(rs.getString("path_gambar"));
        p.setTipeDiskon(rs.getString("tipe_diskon"));
        p.setDiskonPersen(rs.getDouble("diskon_persen"));
        p.setDiskonNilai(rs.getDouble("diskon_nilai"));
        p.setAktif(rs.getInt("aktif"));
        return p;
    }

    public boolean insert(Produk p) {
        String sql = "INSERT INTO produk(kode,nama,kategori,harga,path_gambar,tipe_diskon,diskon_persen,diskon_nilai,aktif) VALUES(?,?,?,?,?,?,?,?,1)";
        try (PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql)) {
            ps.setString(1, p.getKode());
            ps.setString(2, p.getNama());
            ps.setString(3, p.getKategori());
            ps.setDouble(4, p.getHarga());
            ps.setString(5, p.getPathGambar());
            ps.setString(6, p.getTipeDiskon());
            ps.setDouble(7, p.getDiskonPersen());
            ps.setDouble(8, p.getDiskonNilai());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace(); return false; }
    }

    public boolean update(Produk p) {
        String sql = "UPDATE produk SET kode=?,nama=?,kategori=?,harga=?,path_gambar=?,tipe_diskon=?,diskon_persen=?,diskon_nilai=?,aktif=? WHERE id=?";
        try (PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql)) {
            ps.setString(1,p.getKode());
            ps.setString(2,p.getNama());
            ps.setString(3,p.getKategori());
            ps.setDouble(4,p.getHarga());
            ps.setString(5,p.getPathGambar());
            ps.setString(6,p.getTipeDiskon());
            ps.setDouble(7,p.getDiskonPersen());
            ps.setDouble(8,p.getDiskonNilai());
            ps.setInt(9,p.getAktif());
            ps.setInt(10,p.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public boolean softDelete(int id) {
        try (PreparedStatement ps = DBUtil.getConnection().prepareStatement("UPDATE produk SET aktif=0 WHERE id=?")) {
            ps.setInt(1,id); return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }
}