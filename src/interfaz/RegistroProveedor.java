package interfaz;

import datos.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistroProveedor {
    JFrame f = new JFrame("Registro ");
    JTextField t1 = new JTextField();
    JButton b1 = new JButton("Registrar");
    JButton b2 = new JButton("Cancelar");
    public RegistroProveedor(Usuario u, JFrame m) {
        m.setEnabled(false);
        f.setSize(400,500);
        f.setLayout(null);
        f.setResizable(false);
        b2.setContentAreaFilled(false);
        b2.setBorderPainted(false);
        f.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                m.setEnabled(true);
            }
        });
        b2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                f.dispose();
                m.setEnabled(true);
            }
        });
        f.add(t1);
        f.add(b1);
        f.add(b2);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setVisible(true);
    }
}
