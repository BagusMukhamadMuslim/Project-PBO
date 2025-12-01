/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;
import model.user.User;
import model.produk.Produk;
import service.ProdukService;
import javax.swing.*;
import java.awt.*;
import java.util.List;
/**
 *
 * @author acer
 */
public class AdminFrame extends JFrame {
    private final ProdukService ps = new ProdukService();
    private final User user;
    private JTable tbl;
    private ProdukTableModel tableModel;

    public AdminFrame(User user) {
        this.user = user;
        setTitle("Admin - " + user.getFullname());
        setSize(900,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        init();
    }

    private void init() {
        getContentPane().setBackground(UIStyle.BG);
        setLayout(new BorderLayout());
        JPanel top = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        top.setBackground(UIStyle.BG);
        JButton bAdd = UIStyle.createButton("Tambah Produk");
        JButton bEdit = UIStyle.createButton("Edit Produk");
        JButton bDel = UIStyle.createButton("Hapus");
        JButton bLogout = UIStyle.createButton("Logout");
        top.add(bAdd); top.add(bEdit); top.add(bDel); top.add(bLogout);
        add(top, BorderLayout.NORTH);

        tableModel = new ProdukTableModel();
        tbl = new JTable(tableModel);
        add(new JScrollPane(tbl), BorderLayout.CENTER);

        bAdd.addActionListener(e -> {
            FormProdukDialog dlg = new FormProdukDialog(this, null);
            dlg.setVisible(true);
            refreshTable();
        });
        bEdit.addActionListener(e -> {
            int r = tbl.getSelectedRow();
            if (r==-1) return;
            Produk p = tableModel.getAt(r);
            FormProdukDialog dlg = new FormProdukDialog(this, p);
            dlg.setVisible(true);
            refreshTable();
        });
        bDel.addActionListener(e -> {
            int r = tbl.getSelectedRow();
            if (r==-1) return;
            Produk p = tableModel.getAt(r);
            if (JOptionPane.showConfirmDialog(this,"Hapus produk?")==0) {
                ps.delete(p.getId());
                refreshTable();
            }
        });
        bLogout.addActionListener(e -> { dispose(); new LoginFrame().setVisible(true); });

        refreshTable();
    }

    private void refreshTable() {
        List<Produk> list = ps.listActive();
        tableModel.setData(list);
    }
}
