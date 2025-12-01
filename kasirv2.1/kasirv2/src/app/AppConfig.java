/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;
/**
 *
 * @author acer
 */
public class AppConfig {

    // Konfigurasi Database
    public static String DB_URL = "jdbc:sqlite:database_kafe.db";
    public static String DB_DRIVER = "org.sqlite.JDBC";
    
    // aturan diskon global otomatis (threshold)
    public static double DISCOUNT_GLOBAL_THRESHOLD = 0.0;

    // Tipe diskon global: 
    public static String DISCOUNT_GLOBAL_TYPE = "PERCENT";

    // bila tipe PERCENT
    public static double DISCOUNT_GLOBAL_PERCENT = 0.0;

    // bila tipe NOMINAL
    public static double DISCOUNT_GLOBAL_NOMINAL = 0.0;

    // tarif pajak (misal 0.10 untuk 10%)
    public static double TAX_RATE = 0.10;

    // Metadata aplikasi
    public static String APP_NAME = "Sistem Kasir Kafe";
}