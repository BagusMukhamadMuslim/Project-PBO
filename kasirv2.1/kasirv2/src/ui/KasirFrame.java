/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;
import model.user.User;
import model.produk.Produk;
import model.produk.ProdukMinuman.Options;
import model.enums.Size;
import model.enums.Temperature;
import model.transaksi.CartItem;
import service.ProdukService;
import service.TransaksiService;
import util.FormatUtil;
import util.WrapLayout;
import javax.swing.*;
import java.awt.*;
import java.util.List;
/**
 *
 * @author acer
 */
public class KasirFrame extends JFrame {
    private ProdukService ps = new ProdukService();
    private TransaksiService ts = new TransaksiService();
    private User user;
    private JPanel pnlProduk;
    private DefaultListModel<CartItem> listModel = new DefaultListModel<>();
    private JList<CartItem> listCart;
    private JLabel lblTotal;
 

    public KasirFrame(User user) {
        this.user = user;
        setTitle("Kasir - " + user.getFullname());
        setSize(1100,700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        init();
    }

    private void init() {
        getContentPane().setBackground(UIStyle.BG);
        setLayout(new BorderLayout()); 
        
        pnlProduk = new JPanel(new WrapLayout(FlowLayout.LEFT, 10, 10));
        pnlProduk.setBackground(UIStyle.BG);
        JScrollPane sp = new JScrollPane(pnlProduk);
        sp.setPreferredSize(new Dimension(720, 600));
        add(sp, BorderLayout.CENTER);
            
        JPanel right = UIStyle.createCard();
        right.setPreferredSize(new Dimension(350, 600));
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS)); 
        right.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        JPanel pnlLogoutTop = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnlLogoutTop.setMaximumSize(new Dimension(340, 40));
        JButton btnLogout = UIStyle.createButton("Logout");
        btnLogout.addActionListener(e -> { dispose(); new LoginFrame().setVisible(true); });
        pnlLogoutTop.add(btnLogout);
        right.add(pnlLogoutTop);  
        
        right.add(Box.createVerticalStrut(5));
    
        listCart = new JList<>(listModel);
        listCart.setCellRenderer((list, value, index, isSelected, cellHasFocus) -> {
            CartItem it = value;
            return new JLabel(it.getProduk().getNama() + " x" + it.getQty() + " - Rp " + FormatUtil.rupiah(it.getSubtotal()));
        });
        JScrollPane spCart = new JScrollPane(listCart);
        
        spCart.setAlignmentX(Component.LEFT_ALIGNMENT); 
        spCart.setMaximumSize(new Dimension(340, Integer.MAX_VALUE));
        right.add(spCart);
        
        right.add(Box.createVerticalStrut(10));
    
        lblTotal = new JLabel("Total: Rp 0");
        lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 18));
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5)); 
        bottom.setMaximumSize(new Dimension(340, 50));
        bottom.add(lblTotal);
        JButton btnPay = UIStyle.createButton("Bayar");
        btnPay.addActionListener(e -> doPay());
        JButton btnClear = UIStyle.createButton("Bersihkan");
        btnClear.addActionListener(e -> { ts.clearCart(); refreshCart(); });
    
        bottom.add(btnPay); 
        bottom.add(btnClear);
        right.add(bottom); 
    
        add(right, BorderLayout.EAST);
        
        loadProducts();
        refreshCart();
    }

    private void loadProducts() {
        pnlProduk.removeAll();
        List<Produk> list = ps.listActive();
        for (Produk p : list) {
            ProdukCard card = new ProdukCard(p);
            card.addPropertyChangeListener("add", evt -> {
                Produk pr = (Produk) evt.getNewValue();
                if ("MINUMAN".equalsIgnoreCase(pr.getKategori())) {
                    Options opt = askOptions();
                    ts.addToCart(new CartItem(pr, opt));
                } else {
                    ts.addToCart(new CartItem(pr));
                }
                refreshCart();
            });
            pnlProduk.add(card);
        }
        pnlProduk.revalidate();
        pnlProduk.repaint();
    }

    private Options askOptions() {
        // Simple dialog with size + temperature
        Size[] sizes = Size.values();
        Temperature[] temps = Temperature.values();
        Size chosenSize = (Size) JOptionPane.showInputDialog(this, "Pilih ukuran:", "Ukuran",
                JOptionPane.PLAIN_MESSAGE, null, sizes, Size.SMALL);
        Temperature chosenTemp = (Temperature) JOptionPane.showInputDialog(this, "Pilih suhu:", "Suhu",
                JOptionPane.PLAIN_MESSAGE, null, temps, Temperature.HOT);
        if (chosenSize == null) chosenSize = Size.SMALL;
        if (chosenTemp == null) chosenTemp = Temperature.HOT;
        return new Options(chosenSize, chosenTemp);
    }

    private void refreshCart() {
        listModel.clear();
        for (CartItem it : ts.getCart()) listModel.addElement(it);
        lblTotal.setText("Total: Rp " + FormatUtil.rupiah(ts.getTotal()));
    }

    private void doPay() {
        String sTunai = JOptionPane.showInputDialog(this, "Masukkan tunai (angka):");
        if (sTunai == null) return;
        try {
            double tunai = Double.parseDouble(sTunai.replaceAll("[^0-9]",""));
            int tid = ts.checkout(user.getUsername(), tunai);
            if (tid <= 0) {
                JOptionPane.showMessageDialog(this, "Tunai tidak cukup atau error.");
            } else {
                JOptionPane.showMessageDialog(this, "Transaksi sukses! ID: " + tid);
                refreshCart();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Input tunai tidak valid");
        }
    }
}
