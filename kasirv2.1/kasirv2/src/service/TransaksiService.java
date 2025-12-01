/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import model.transaksi.CartItem;
import dao.TransaksiDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import app.AppConfig;
/**
 *
 * @author acer
 */
public class TransaksiService {
    private List<CartItem> cart = new ArrayList<>();
    private TransaksiDAO dao = new TransaksiDAO();

    public void addToCart(CartItem it) {
        // merge simple by product id + same options (string)
        for (CartItem c : cart) {
            boolean sameProduct = c.getProduk().getId() == it.getProduk().getId();
            boolean sameOpt = (c.getOptions()==null && it.getOptions()==null) || (c.getOptions()!=null && c.getOptions().equals(it.getOptions()));
            if (sameProduct && sameOpt) {
                c.setQty(c.getQty() + it.getQty());
                return;
            }
        }
        cart.add(it);
    }
    public List<CartItem> getCart(){ return cart; }
    public void clearCart(){ cart.clear(); }

    public double getSubtotal() {
        return cart.stream().mapToDouble(CartItem::getSubtotal).sum();
    }

    public double getGlobalDiscount() {
        double sub = getSubtotal();
        if (AppConfig.DISCOUNT_GLOBAL_TYPE.equalsIgnoreCase("PERCENT") && sub >= AppConfig.DISCOUNT_GLOBAL_THRESHOLD) {
            return sub * AppConfig.DISCOUNT_GLOBAL_PERCENT / 100.0;
        } else if (AppConfig.DISCOUNT_GLOBAL_TYPE.equalsIgnoreCase("NOMINAL") && sub >= AppConfig.DISCOUNT_GLOBAL_THRESHOLD) {
            return AppConfig.DISCOUNT_GLOBAL_NOMINAL;
        }
        return 0;
    }

    public double getTax(double afterDiscount) { return afterDiscount * AppConfig.TAX_RATE; }

    public double getTotal() {
        double sub = getSubtotal();
        double g = getGlobalDiscount();
        double after = sub - g;
        double tax = getTax(after);
        return after + tax;
    }

    public int checkout(String kasirUsername, double tunai) {
        double subtotal = getSubtotal();
        double g = getGlobalDiscount();
        double after = subtotal - g;
        double tax = getTax(after);
        double total = after + tax;
        double kembali = tunai - total;
        if (tunai < total) return -1;
        String no = "STRK-" + UUID.randomUUID().toString().substring(0,8).toUpperCase();
        int tid = dao.saveTransaction(no, kasirUsername, subtotal, g, tax, total, tunai, kembali, new ArrayList<>(cart));
        if (tid > 0) clearCart();
        return tid;
    }
}