package interfaz;

import datos.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistroServicio {
    JDialog d;
    JTextField t1 = new JTextField();
    JTextField t2 = new JTextField("0");
    JTextField t3 = new JTextField("0");
    JButton b1 = new JButton("Registrar");
    JButton b2 = new JButton("Cancelar");
    JLabel l1 = new JLabel("Nombre:");
    JLabel l2 = new JLabel("Costo:");
    JLabel l3 = new JLabel("Precio:");
    JLabel error = new JLabel();
    public RegistroServicio(JFrame f, Usuario u) {
        /*d = new JDialog(f);
        f.setEnabled(false);
        d.setSize(400,500);
        t1.setBounds(150,120, 200,30);
        t2.setBounds(150,150, 200,30);
        t3.setBounds(150,180, 200,30);
        b1.setBounds(150,300, 200,30);
        b2.setBounds(150,325, 200,30);
        l1.setBounds(95,120, 200,30);
        l2.setBounds(103,150, 200,30);
        l3.setBounds(89,180, 200,30);
        d.setLayout(null);
        d.setResizable(false);
        b2.setContentAreaFilled(false);
        b2.setBorderPainted(false);
        d.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                f.setEnabled(true);
            }
        });
        b2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                d.dispose();
                f.setEnabled(true);
            }
        });
        d.add(t1);
        d.add(t2);
        d.add(t3);
        d.add(b1);
        d.add(b2);
        d.add(l1);
        d.add(l2);
        d.add(l3);
        d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        d.setVisible(true);*/
    }
}