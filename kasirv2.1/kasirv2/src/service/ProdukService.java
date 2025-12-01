/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import dao.ProdukDAO;
import model.produk.Produk;
import java.util.List;
/**
 *
 * @author acer
 */
public class ProdukService {
    private final ProdukDAO dao = new ProdukDAO();
    public List<Produk> listActive() {
        return dao.findAllActive();
    }
    public Produk getById(int id) {
        return dao.findById(id);
    }
    public boolean add(Produk p) {
        return dao.insert(p);
    }
    public boolean update(Produk p) {
        return dao.update(p);
    }
    public boolean delete(int id) {
        return dao.softDelete(id);
    }
}