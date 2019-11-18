package interfaz;

import algoritmos.Archivos;
import datos.Proveedor;
import datos.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistroProveedor {
    JDialog d;
    JTextField t1 = new JTextField();
    JTextField t2 = new JTextField();
    JTextField t3 = new JTextField();
    JTextField t4 = new JTextField();
    JButton b1 = new JButton("Registrar");
    JButton b2 = new JButton("Cancelar");
    JLabel l1 = new JLabel("Nombre:");
    JLabel l2 = new JLabel("Telefono:");
    JLabel l3 = new JLabel("Correo:");
    JLabel l4 = new JLabel("Dirección:");
    JLabel l5 = new JLabel("");
    JLabel error = new JLabel();
    public RegistroProveedor(JFrame f, Usuario u) {
        d = new JDialog(f);
        f.setEnabled(false);
        d.setSize(400,500);
        l1.setBounds(95,120, 200,30);
        t1.setBounds(150,120, 200,30);
        l2.setBounds(103,150, 200,30);
        t2.setBounds(150,150, 200,30);
        l3.setBounds(89,180, 200,30);
        t3.setBounds(150,180, 200,30);
        l4.setBounds(55,210, 200,30);
        t4.setBounds(150,210, 200,30);
        b1.setBounds(150,240, 200,30);
        b2.setBounds(150,265, 200,30);
        d.setLayout(null);
        d.setResizable(false);
        b2.setContentAreaFilled(false);
        b2.setBorderPainted(false);
        l5.setVisible(false);
        d.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                f.setEnabled(true);
            }
        });
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (t1.getText().equals("")){
                    l5.setText("Nombre no valido.");
                    l5.setBounds(200,275,400, 40);
                    l5.setVisible(true);
                } else if (Archivos.buscarProveedor(u, t1.getText())) {
                    l5.setText("El prveedor ya existe.");
                    l5.setBounds(190,275,400, 40);
                    l5.setVisible(true);
                } else {
                    u.proveedores.add(new Proveedor(u, t1.getText(), t2.getText(), t3.getText(), t4.getText()));
                    Archivos.guardarArchivo(u,  "\\Usuarios\\"+u.usuario+"\\datos.txt");
                    f.setEnabled(true);
                    d.dispose();
                }
            }
        });
        b2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                f.setEnabled(true);
                d.dispose();
            }
        });
        d.add(t1);
        d.add(t2);
        d.add(t3);
        d.add(t4);
        d.add(b1);
        d.add(b2);
        d.add(l1);
        d.add(l2);
        d.add(l3);
        d.add(l4);
        d.add(l5);
        d.add(error);
        d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        d.setVisible(true);
    }
}