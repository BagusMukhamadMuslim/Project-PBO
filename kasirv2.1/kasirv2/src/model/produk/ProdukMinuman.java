/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.produk;
import model.enums.Size;
import model.enums.Temperature;
/**
 *
 * @author acer
 */
public class ProdukMinuman extends Produk {
    public ProdukMinuman() {
        this.kategori = "MINUMAN";
    }

    // options expected as a small POJO or map — we'll use a simple OptionsMinuman
    public static class Options {
        public Size size = Size.SMALL;
        public Temperature temp = Temperature.HOT;
        public Options() {}
        public Options(Size s, Temperature t) {
            this.size = s; this.temp = t;
        }
    }

    @Override
    public double getPriceWithOptions(Object options) {
        double base = this.harga;
        if (options instanceof Options o) {
            base += Size.priceDelta(o.size);
            // iced does not add price by design beyond size; if needed, add here
        }
        return base;
    }
}