package interfaz;

import algoritmos.Archivos;
import datos.Servicio;
import datos.Usuario;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistroServicio {
    JDialog d;
    JTextField t1 = new JTextField();
    JTextField t2 = new JTextField("0");
    JButton b1 = new JButton("Registrar");
    JButton b2 = new JButton("Cancelar");
    JLabel l1 = new JLabel("Nombre:");
    JLabel l2 = new JLabel("Precio:");
    JLabel l3 = new JLabel("Precio no valido.");
    public RegistroServicio(JFrame f, Usuario u) {
        d = new JDialog(f);
        f.setEnabled(false);
        d.setSize(400,500);
        l1.setBounds(95,120, 200,30);
        t1.setBounds(150,120, 200,30);
        l2.setBounds(89,150, 200,30);
        t2.setBounds(150,150, 200,30);
        b1.setBounds(150,180, 200,30);
        b2.setBounds(150,205, 200,30);
        l3.setBounds(150,225, 200,30);
        d.setLayout(null);
        d.setResizable(false);
        b2.setContentAreaFilled(false);
        b2.setBorderPainted(false);
        l3.setVisible(false);
        d.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                f.setEnabled(true);
            }
        });
        t2.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                cambio();
            }
            public void removeUpdate(DocumentEvent e) {
                cambio();
            }
            public void insertUpdate(DocumentEvent e) {
                cambio();
            }
            public void cambio() {
                try {
                    float costo = Float.parseFloat(t2.getText());
                    float precio = Float.parseFloat(l3.getText());
                    l3.setVisible(false);
                    b1.setEnabled(true);
                    if (costo<0||precio<0) {
                        l3.setVisible(true);
                        b1.setEnabled(false);
                    } else  {
                        l3.setVisible(false);
                        b1.setEnabled(true);
                    }
                } catch (NumberFormatException i) {
                    l3.setVisible(true);
                    b1.setEnabled(false);
                }
            }
        });
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                u.servicios.add(new Servicio(u, t1.getText(), Float.parseFloat(t2.getText())));
                Archivos.guardarArchivo(u,  "\\Usuarios\\"+u.usuario+"\\datos.txt");
                f.setEnabled(true);
                d.dispose();
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
        d.add(b1);
        d.add(b2);
        d.add(l1);
        d.add(l2);
        d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        d.setVisible(true);
    }
}