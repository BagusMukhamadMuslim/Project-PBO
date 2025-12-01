/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;
import model.produk.Produk;
import util.ImageUtil;
import util.FormatUtil;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author acer
 */
public class ProdukCard extends JPanel {
    private Produk produk;
    public ProdukCard(Produk p) {
        this.produk = p;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(new Color(220,220,220)));
        JLabel img = new JLabel(ImageUtil.loadScaled(p.getPathGambar(),140,100));
        JLabel lbl = new JLabel("<html><center><b>" + p.getNama() + "</b><br/>Rp " + FormatUtil.rupiah(p.getHarga()) + "</center></html>", SwingConstants.CENTER);
        JButton btn = new JButton("Tambah");
        btn.setBackground(UIStyle.PRIMARY);
        btn.setForeground(Color.WHITE);
        btn.addActionListener(e -> firePropertyChange("add", null, produk)); // listeners can listen
        add(img, BorderLayout.NORTH);
        add(lbl, BorderLayout.CENTER);
        add(btn, BorderLayout.SOUTH);
        setPreferredSize(new Dimension(160,180));
    }
}