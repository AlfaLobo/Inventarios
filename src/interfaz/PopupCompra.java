package interfaz;

import datos.Producto;
import datos.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PopupCompra {
    JFrame f = new JFrame("Elegir una opción");
    JLabel l = new JLabel("Desea registrar una compra de este producto?");
    JButton b1 = new JButton("Si");
    JButton b2 = new JButton("No");
    public PopupCompra(Usuario u, JFrame m, Producto p) {
        f.setSize(420,160);
        l.setBounds(70,30,300,30);
        b1.setBounds(100,70,80,30);
        b2.setBounds(220,70,80,30);
        f.setLayout(null);
        f.setResizable(false);
        f.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                m.setEnabled(true);
            }
        });
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new CompraProducto(u, m, p);
                f.dispose();
            }
        });
        b2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                f.dispose();
                m.setEnabled(true);
            }
        });
        f.add(l);
        f.add(b1);
        f.add(b2);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setVisible(true);
    }
}
