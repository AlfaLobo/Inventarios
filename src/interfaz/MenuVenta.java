package interfaz;

import datos.Usuario;

import javax.swing.*;

public class MenuVenta {
    JDialog d;
    public MenuVenta(JFrame f, Usuario u){
        d = new JDialog(f);
        f.setEnabled(false);
        d.setSize(400,500);
        d.setLayout(null);
        d.setResizable(false);
        d.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                f.setEnabled(true);
            }
        });
        d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        d.setVisible(true);
    }
}