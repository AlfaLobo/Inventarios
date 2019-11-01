package interfaz;

import javax.swing.*;

public class MenuPrincipal {
    JFrame f = new JFrame("temp");
    JLabel l = new JLabel("Bienvenido OvO");
    public MenuPrincipal() {
        f.setSize(400,500);
        l.setBounds(30,145,400, 40);
        f.setLayout(null);
        f.setVisible(true);
        f.add(l);
    }
}
