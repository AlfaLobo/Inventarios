package interfaz;

import datos.Usuario;

import javax.swing.*;

public class Historial {
    JFrame f = new JFrame("Historial");
    public Historial(Usuario u, JFrame m) {
        m.setEnabled(false);
        f.setSize(400,500);
        f.setLayout(null);
        f.setResizable(false);
        f.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                m.setEnabled(true);
            }
        });
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setVisible(true);
    }
}