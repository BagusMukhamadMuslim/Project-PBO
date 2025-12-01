/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;
import model.produk.Produk;
import model.produk.ProdukMakanan;
import model.produk.ProdukMinuman;
import service.ProdukService;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
/**
 *
 * @author acer
 */
public class FormProdukDialog extends JDialog {
    private ProdukService ps = new ProdukService();
    private Produk data;
    private AdminFrame parent;

    public FormProdukDialog(AdminFrame parent, Produk data) {
        super(parent, true);
        this.parent = parent;
        this.data = data;
        setTitle(data == null ? "Tambah Produk" : "Edit Produk");
        setSize(450, 480);
        setLocationRelativeTo(parent);
        init();
    }

    private void init() {
        JPanel p = UIStyle.createCard();
        p.setLayout(new GridLayout(0, 2, 8, 8));

        JTextField tfKode = new JTextField(data == null ? "" : data.getKode());
        JTextField tfNama = new JTextField(data == null ? "" : data.getNama());
        JComboBox<String> cbKat = new JComboBox<>(new String[]{"MAKANAN", "MINUMAN"});
        JTextField tfHarga = new JTextField(data == null ? "" : "" + data.getHarga());
        JTextField tfGambar = new JTextField(data == null ? "" : data.getPathGambar());
        JComboBox<String> cbTipeDiskon = new JComboBox<>(new String[]{"NONE", "PERSEN", "FLAT"});
        JTextField tfDiskonPersen = new JTextField(data == null ? "0" : String.valueOf(data.getDiskonPersen()));
        JTextField tfDiskonNilai = new JTextField(data == null ? "0" : String.valueOf(data.getDiskonNilai()));

        JButton bSelectGambar = UIStyle.createButton("Select Gambar");
        bSelectGambar.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            fc.setDialogTitle("Pilih Gambar Produk");
            fc.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png"));

            int result = fc.showOpenDialog(FormProdukDialog.this);
            if (result == JFileChooser.APPROVE_OPTION) {
                String path = fc.getSelectedFile().getAbsolutePath();
                tfGambar.setText(path);
            }
        });

        p.add(new JLabel("Kode:")); p.add(tfKode);
        p.add(new JLabel("Nama:")); p.add(tfNama);
        p.add(new JLabel("Kategori:")); p.add(cbKat);
        p.add(new JLabel("Harga:")); p.add(tfHarga);

        p.add(new JLabel("Path Gambar:"));
        JPanel pg = new JPanel(new BorderLayout(5,0));
        pg.add(tfGambar, BorderLayout.CENTER);
        pg.add(bSelectGambar, BorderLayout.EAST);
        p.add(pg);

        p.add(new JLabel("Tipe Diskon:")); p.add(cbTipeDiskon);
        p.add(new JLabel("Diskon %:")); p.add(tfDiskonPersen);
        p.add(new JLabel("Diskon Rp:")); p.add(tfDiskonNilai);

        JButton bSave = UIStyle.createButton("Simpan");
        bSave.addActionListener(e -> {
            try {
                Produk pmodel;
                if (data == null) {
                    pmodel = "MINUMAN".equals(cbKat.getSelectedItem()) ?
                            new ProdukMinuman() : new ProdukMakanan();
                } else {
                    pmodel = data;
                }

                pmodel.setKode(tfKode.getText().trim());
                pmodel.setNama(tfNama.getText().trim());
                pmodel.setKategori((String) cbKat.getSelectedItem());
                pmodel.setHarga(Double.parseDouble(tfHarga.getText().trim()));
                pmodel.setPathGambar(tfGambar.getText().trim());
                pmodel.setTipeDiskon((String) cbTipeDiskon.getSelectedItem());
                pmodel.setDiskonPersen(Double.parseDouble(tfDiskonPersen.getText().trim()));
                pmodel.setDiskonNilai(Double.parseDouble(tfDiskonNilai.getText().trim()));

                if (data == null) ps.add(pmodel); else ps.update(pmodel);

                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        JPanel bp = new JPanel();
        bp.add(bSave);

        getContentPane().add(p, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.SOUTH);
    }
}
