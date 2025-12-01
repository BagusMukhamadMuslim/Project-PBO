/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.awt.image.BufferedImage;
/**
 *
 * @author acer
 */
public class ImageUtil {
    public static ImageIcon loadScaled(String path, int w, int h) {
        try {
            if (path == null || path.isEmpty()) throw new Exception("no path");
            File f = new File(path);
            Image img;
            if (f.exists()) img = new ImageIcon(path).getImage();
            else img = new ImageIcon(ImageUtil.class.getResource("/placeholder.png")).getImage();
            Image scaled = img.getScaledInstance(w,h,Image.SCALE_SMOOTH);
            return new ImageIcon(scaled);
        } catch (Exception e) {
            BufferedImage bi = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = bi.createGraphics();
            g.setPaint(Color.LIGHT_GRAY);
            g.fillRect(0,0,w,h);
            g.dispose();
            return new ImageIcon(bi);
        }
    }
}

