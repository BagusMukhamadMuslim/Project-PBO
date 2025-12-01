/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;
import service.AuthService;
import model.user.User;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author acer
 */
public class LoginFrame extends JFrame {
    private AuthService auth = new AuthService();

    public LoginFrame() {
        setTitle("KAFE POS - Login");
        setSize(420,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        init();
    }

    private void init() {
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(UIStyle.BG);
        JPanel card = UIStyle.createCard();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel title = new JLabel("KAFE POS SYSTEM", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(UIStyle.PRIMARY);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField tfUser = new JTextField();
        JPasswordField pf = new JPasswordField();

        tfUser.setMaximumSize(new Dimension(320,35));
        pf.setMaximumSize(new Dimension(320,35));

        JButton btnLogin = UIStyle.createButton("Login");
        btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(Box.createRigidArea(new Dimension(0,10)));
        card.add(title);
        card.add(Box.createRigidArea(new Dimension(0,15)));
        card.add(new JLabel("Username:"));
        card.add(tfUser);
        card.add(Box.createRigidArea(new Dimension(0,8)));
        card.add(new JLabel("Password:"));
        card.add(pf);
        card.add(Box.createRigidArea(new Dimension(0,12)));
        card.add(btnLogin);
        root.add(card, BorderLayout.CENTER);
        add(root);

        btnLogin.addActionListener(e -> {
            String u = tfUser.getText().trim();
            String p = new String(pf.getPassword()).trim();
            if (u.isEmpty() || p.isEmpty()) { JOptionPane.showMessageDialog(this,"Isi username & password"); return; }
            User user = auth.login(u,p);
            if (user == null) { JOptionPane.showMessageDialog(this,"Login gagal"); return; }
            dispose();
            if (user.isAdmin()) new AdminFrame(user).setVisible(true);
            else new KasirFrame(user).setVisible(true);
        });
    }
}