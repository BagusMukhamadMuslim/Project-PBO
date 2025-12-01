/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import model.transaksi.CartItem;
import util.FormatUtil;
import java.util.List;
/**
 *
 * @author acer
 */
public class PrinterService {
    public static void printReceiptSimple(String noStruk, String kasir, List<CartItem> items, double subtotal, double discGlobal, double tax, double total, double tunai, double kembali) {
        System.out.println("======STRUK KAFE======");
        System.out.println("No: " + noStruk);
        System.out.println("Kasir: " + kasir);
        System.out.println("----------------------");
        for (CartItem it : items) {
            System.out.printf("%s x%d  Rp %s%n", it.getProduk().getNama(), it.getQty(), FormatUtil.rupiah(it.getHargaSetelahDiskon()));
        }
        System.out.println("----------------------");
        System.out.println("Subtotal: Rp " + FormatUtil.rupiah(subtotal));
        System.out.println("Diskon Global: Rp " + FormatUtil.rupiah(discGlobal));
        System.out.println("Pajak: Rp " + FormatUtil.rupiah(tax));
        System.out.println("Total: Rp " + FormatUtil.rupiah(total));
        System.out.println("Tunai: Rp " + FormatUtil.rupiah(tunai));
        System.out.println("Kembali: Rp " + FormatUtil.rupiah(kembali));
        System.out.println("======================");
    }
}