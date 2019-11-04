package interfaz;

import datos.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipal {
    JFrame f = new JFrame("temp");
    JButton b = new JButton("UvU");
    JButton b1 = new JButton("Registrar Producto");
    JButton b2 = new JButton("Revisar Inventario");
    JLabel l = new JLabel("Bienvenido OvO");
    public MenuPrincipal(Usuario u) {
        f.setSize(1280,720);
        l.setBounds(70,145,1280, 40);
        b.setBounds(50,175,100, 40);
        b1.setBounds(200,175,160, 40);
        b2.setBounds(410,175,160, 40);
        f.setLayout(null);
        f.setResizable(false);
        b.addActionListener(new ActionListener(){
            String UvU = "UvU";
            int i = 70;
            int j = 145;
            public void actionPerformed(ActionEvent e){
                l.setText(UvU);
                l.setBounds(i,j,1280, 40);
                UvU=UvU+"vU";
                i++;
                j--;
            }
        });
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new RegistroProducto(u, f);
                f.setEnabled(false);
            }
        });
        b2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new Inventario(u, f);
                f.setEnabled(false);
            }
        });
        f.add(b);
        f.add(b1);
        f.add(b2);
        f.add(l);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setVisible(true);
    }
}