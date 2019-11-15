package interfaz;

import algoritmos.Archivos;
import datos.Negocio;
import datos.Usuario;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegistroNegocio {
    JDialog d;
    JComboBox c = new JComboBox();
    JTextField t1 = new JTextField();
    JTextField t2 = new JTextField("0");
    JButton b1= new JButton("Registrarse");
    JButton b2= new JButton("Cancelar");
    JLabel l1 = new JLabel("Empresa:");
    JLabel l2 = new JLabel("Nombre:");
    JLabel l3 = new JLabel("Saldo Inicial:");
    JLabel l = new JLabel("Insertar un saldo valido.");
    public RegistroNegocio(JFrame f, Usuario u){
        f.setEnabled(false);
        d = new JDialog(f, "Registrar un negocio");
        d.setSize(400,500);
        l1.setBounds(95,60,200,30);
        c.setBounds(150,60,200,30);
        l2.setBounds(75,90,200,30);
        t1.setBounds(150,90,200,30);
        l3.setBounds(75,120,200,30);
        t2.setBounds(150,120,200,30);
        b1.setBounds(150,150,200,30);
        b2.setBounds(150,180,200,20);
        l.setBounds(180,195,200,20);
        d.setLayout(null);
        d.setResizable(false);
        b2.setContentAreaFilled(false);
        b2.setBorderPainted(false);
        l.setVisible(false);
        for (int i=0;i<u.empresas.size();i++) {
            c.addItem(u.empresas.get(i).nombre);
        }
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
                    float temp = Float.parseFloat(t2.getText());
                    l.setVisible(false);
                    b1.setEnabled(true);
                } catch (NumberFormatException i) {
                    l.setVisible(true);
                    b1.setEnabled(false);
                }
            }
        });
        t2.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                t2.selectAll();
            }
        });
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (t1.getText().equals("")) {
                    l.setText("Insertar un nombre de empresa.");
                    l.setVisible(true);
                } else if (Archivos.buscarArchivo("\\Usuarios\\"+u.usuario+"\\"+t1.getText())) {
                    l.setText("La empresa ya existe.");
                    l.setVisible(true);
                } else {
                    u.negocios.add(new Negocio(u.negocios.size(), t1.getText(), Float.parseFloat(t2.getText()), c.getSelectedIndex()));
                    Archivos.guardarArchivo(u,  "\\Usuarios\\"+u.usuario+"\\datos.txt");
                    d.dispose();
                    f.setEnabled(true);
                }
            }
        });
        b2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                d.dispose();
                f.setEnabled(true);
            }
        });
        d.add(c);
        d.add(t1);
        d.add(t2);
        d.add(b1);
        d.add(b2);
        d.add(l1);
        d.add(l2);
        d.add(l3);
        d.add(l);
        d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        d.setVisible(true);
    }
}