/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author acer
 */
public class UIStyle {
    public static Color PRIMARY = new Color(66, 135, 245);
    public static Color BG = new Color(245, 247, 250);
    public static Color CARD = Color.WHITE;
    public static Font FONT = new Font("Segoe UI", Font.PLAIN, 13);

    public static JButton createButton(String text) {
        JButton b = new JButton(text);
        b.setBackground(PRIMARY);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setFont(FONT.deriveFont(Font.BOLD));
        b.setBorder(BorderFactory.createEmptyBorder(8,12,8,12));
        return b;
    }

    public static JPanel createCard() {
        JPanel p = new JPanel();
        p.setBackground(CARD);
        p.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        return p;
    }
}
