/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;
import java.text.NumberFormat;
import java.util.Locale;
/**
 *
 * @author acer
 */
public class FormatUtil {
    public static String rupiah(double value) {
        NumberFormat nf = NumberFormat.getInstance(new Locale("in","ID"));
        nf.setMaximumFractionDigits(0);
        return nf.format(value);
    }
}

