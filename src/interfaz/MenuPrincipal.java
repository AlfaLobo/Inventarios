package interfaz;

import datos.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipal {
    JFrame f = new JFrame("temp");
    JButton b = new JButton("UvU");
    JLabel l = new JLabel("Bienvenido OvO");
    public MenuPrincipal(Usuario u) {
        f.setSize(400,500);
        l.setBounds(30,145,400, 40);
        b.setBounds(30,175,100, 40);
        f.setLayout(null);
        f.setVisible(true);
        b.addActionListener(new ActionListener(){
            String UvU = "UvU";
            int i = 70;
            int j = 145;
            public void actionPerformed(ActionEvent e){
                l.setText(UvU);
                l.setBounds(i,j,400, 40);
                UvU=UvU+"vU";
                i++;
                j--;
            }
        });
        f.add(b);
        f.add(l);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
