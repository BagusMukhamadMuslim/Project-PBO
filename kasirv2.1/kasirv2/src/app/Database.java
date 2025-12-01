/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;
import util.SecurityUtil;
import java.sql.*;
/**
 *
 * @author acer
 */
public class Database {
    private static String URL = "jdbc:sqlite:kafe.db";
    private static Connection conn;

    public static void init() {
        try {
            conn = DriverManager.getConnection(URL);
            initSchema();
            seedUsers();
            System.out.println("Database initialized: kafe.db");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private static void initSchema() throws SQLException {
        try (Statement st = conn.createStatement()) {
            st.execute("""
                CREATE TABLE IF NOT EXISTS users (
                  id INTEGER PRIMARY KEY AUTOINCREMENT,
                  username TEXT UNIQUE,
                  password_hash TEXT,
                  fullname TEXT,
                  role TEXT
                );""");

            st.execute("""
                CREATE TABLE IF NOT EXISTS produk (
                  id INTEGER PRIMARY KEY AUTOINCREMENT,
                  kode TEXT UNIQUE,
                  nama TEXT,
                  kategori TEXT,
                  harga REAL,
                  path_gambar TEXT,
                  tipe_diskon TEXT DEFAULT 'NONE',
                  diskon_persen REAL DEFAULT 0,
                  diskon_nilai REAL DEFAULT 0,
                  aktif INTEGER DEFAULT 1
                );""");

            st.execute("""
                CREATE TABLE IF NOT EXISTS transaksi (
                  id INTEGER PRIMARY KEY AUTOINCREMENT,
                  no_struk TEXT UNIQUE,
                  kasir_username TEXT,
                  subtotal REAL,
                  diskon_global REAL,
                  pajak REAL,
                  total REAL,
                  tunai REAL,
                  kembali REAL,
                  waktu TEXT DEFAULT CURRENT_TIMESTAMP
                );""");

            st.execute("""
                CREATE TABLE IF NOT EXISTS transaksi_detail (
                  id INTEGER PRIMARY KEY AUTOINCREMENT,
                  transaksi_id INTEGER,
                  produk_id INTEGER,
                  nama_snapshot TEXT,
                  opsi TEXT,
                  harga_asli REAL,
                  diskon_lokal REAL,
                  harga_setelah_diskon REAL,
                  qty INTEGER,
                  subtotal REAL
                );""");
        }
    }

    private static void seedUsers() {
        try (PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM users")) {
            ResultSet rs = ps.executeQuery();
            if (rs.next() && rs.getInt(1) == 0) {
                try (PreparedStatement ins = conn.prepareStatement(
                        "INSERT INTO users(username,password_hash,fullname,role) VALUES(?,?,?,?)")) {
                    ins.setString(1, "admin");
                    ins.setString(2, SecurityUtil.sha256("admin123"));
                    ins.setString(3, "Administrator");
                    ins.setString(4, "ADMIN");
                    ins.executeUpdate();

                    ins.setString(1, "kasir");
                    ins.setString(2, SecurityUtil.sha256("kasir123"));
                    ins.setString(3, "Kasir A");
                    ins.setString(4, "KASIR");
                    ins.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        if (conn == null || conn.isClosed()) conn = DriverManager.getConnection(URL);
        return conn;
    }
}