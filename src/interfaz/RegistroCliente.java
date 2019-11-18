package interfaz;

import algoritmos.Archivos;
import datos.Cliente;
import datos.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

public class RegistroCliente {
    JDialog d;
    JComboBox cb1 = new JComboBox();
    JComboBox cb2 = new JComboBox();
    JComboBox cb3 = new JComboBox();
    JTextField t1 = new JTextField();
    JTextField t2 = new JTextField();
    JTextField t3 = new JTextField();
    JTextField t4 = new JTextField();
    JButton b1 = new JButton("Registrar");
    JButton b2 = new JButton("Cancelar");
    JLabel l1 = new JLabel("Nombre:");
    JLabel l2 = new JLabel("Apellidos:");
    JLabel l3 = new JLabel("Telefono:");
    JLabel l4 = new JLabel("Correo:");
    JLabel l5 = new JLabel("Fecha de Nacimiento:");
    JLabel error = new JLabel();
    public RegistroCliente(JFrame f, Usuario u){
        d = new JDialog(f);
        f.setEnabled(false);
        d.setSize(400,500);
        t1.setBounds(150,120, 200,30);
        t2.setBounds(150,150, 200,30);
        t3.setBounds(150,180, 200,30);
        t4.setBounds(150,210, 200,30);
        cb1.setBounds(150,240, 100,30);
        cb2.setBounds(250,240, 50,30);
        cb3.setBounds(300,240, 50,30);
        b1.setBounds(150,270, 200,30);
        b2.setBounds(150,295, 200,30);
        l1.setBounds(95,120, 200,30);
        l2.setBounds(103,150, 200,30);
        l3.setBounds(89,180, 200,30);
        l4.setBounds(55,210, 200,30);
        l5.setBounds(55,240, 200,30);
        d.setLayout(null);
        d.setResizable(false);
        b2.setContentAreaFilled(false);
        b2.setBorderPainted(false);
        d.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                f.setEnabled(true);
            }
        });
        for (int i=1;i<32;i++) {
            cb1.addItem(i);
        }
        for (int i=1;i<13;i++) {
            cb2.addItem(i);
        }
        for (int i=2019;i>1899;i--) {
            cb3.addItem(i);
        }
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                u.clientes.add(new Cliente(u, t1.getText(), t2.getText(), t3.getText(), t4.getText(), new GregorianCalendar((int) cb3.getItemAt(cb3.getSelectedIndex()), (int) cb2.getItemAt(cb2.getSelectedIndex()), (int) cb1.getItemAt(cb1.getSelectedIndex()))));
                Archivos.guardarArchivo(u,  "\\Usuarios\\"+u.usuario+"\\datos.txt");
                f.setEnabled(true);
                d.dispose();
            }
        });
        b2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                f.dispose();
                f.setEnabled(true);
            }
        });
        d.add(cb1);
        d.add(cb2);
        d.add(cb3);
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