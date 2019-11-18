package interfaz;

import algoritmos.Archivos;
import datos.Venta;
import datos.Usuario;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class RegistroVenta {
    GregorianCalendar fecha = new GregorianCalendar();
    ArrayList<Venta> ventas = new ArrayList<>();
    JDialog d;
    JComboBox cb1 = new JComboBox();
    JComboBox cb2 = new JComboBox();
    JComboBox cb3 = new JComboBox();
    JTextField t1 = new JTextField("0");
    JTextField t2 = new JTextField("0");
    JButton b1 = new JButton("Añadir");
    JButton b2 = new JButton("Registrar");
    JTextArea ta = new JTextArea();
    JScrollPane sp = new JScrollPane(ta);
    JLabel l1 = new JLabel("Producto:");
    JLabel l2 = new JLabel("Iteración:");
    JLabel l3 = new JLabel("Cantidad:");
    JLabel l4 = new JLabel("Total:");
    JLabel l5 = new JLabel("Insertar valores validos.");
    JLabel l6 = new JLabel("Ingresar al menos un producto a comprar.");
    public RegistroVenta(JFrame f, Usuario u){
        d = new JDialog(f);
        d.setSize(600,400);
        cb1.setBounds(325,30, 200,30);
        l1.setBounds(35,30, 100,30);
        cb2.setBounds(95,30, 200,30);
        cb3.setBounds(95,60, 200,30);
        l2.setBounds(35,60, 100,30);
        t1.setBounds(95,90, 200,30);
        l3.setBounds(10,90, 100,30);
        l4.setBounds(10,120, 100,30);
        t2.setBounds(95,120, 200,30);
        sp.setBounds(325, 60, 200, 90);
        b1.setBounds(95,150, 200,30);
        b2.setBounds(325,150, 200,30);
        l5.setBounds(130,175, 200,30);
        l6.setBounds(305,175, 400,30);
        d.setLayout(null);
        d.setResizable(false);
        ta.setEditable(false);
        l5.setVisible(false);
        l6.setVisible(false);
        t1.selectAll();
        for (int i=0;i<u.clientes.size();i++) {
            cb1.addItem(u.clientes.get(i).nombre);
        }
        for (int i=0;i<u.productos.size();i++) {
            cb2.addItem(u.productos.get(i).nombre);
        }
        if (u.productos.get(cb2.getSelectedIndex()).expirables.size()>0){
            cb3.setEnabled(true);
            for (int i=0;i<u.productos.get(cb2.getSelectedIndex()).expirables.size();i++) {
                cb3.addItem(u.productos.get(cb2.getSelectedIndex()).expirables.get(i).nombre+" "+u.productos.get(cb2.getSelectedIndex()).expirables.get(i).expiracion.get(Calendar.DAY_OF_MONTH)+"/"+u.productos.get(cb2.getSelectedIndex()).expirables.get(i).expiracion.get(Calendar.MONTH)+"/"+u.productos.get(cb2.getSelectedIndex()).expirables.get(i).expiracion.get(Calendar.YEAR));
            }
        } else {
            cb3.setEnabled(false);
        }
        d.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                f.setEnabled(true);
            }
        });
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        cb2.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cb3.removeAllItems();
                if (u.productos.get(cb2.getSelectedIndex()).expirables.size()>0){
                    cb3.setEnabled(true);
                    for (int i=0;i<u.productos.get(cb2.getSelectedIndex()).expirables.size();i++) {
                        cb3.addItem(u.productos.get(cb2.getSelectedIndex()).expirables.get(i).nombre+" "+u.productos.get(cb2.getSelectedIndex()).expirables.get(i).expiracion.get(Calendar.DAY_OF_MONTH)+"/"+u.productos.get(cb2.getSelectedIndex()).expirables.get(i).expiracion.get(Calendar.MONTH)+"/"+u.productos.get(cb2.getSelectedIndex()).expirables.get(i).expiracion.get(Calendar.YEAR));
                    }
                } else {
                    cb3.setEnabled(false);
                }
            }
        });
        t1.getDocument().addDocumentListener(new DocumentListener() {
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
                    float cantidad = Integer.parseInt(t1.getText());
                    t2.setText(String.valueOf(u.productos.get(cb2.getSelectedIndex()).precio*cantidad));
                    l5.setVisible(false);
                    b1.setEnabled(true);
                } catch (NumberFormatException i) {
                    l5.setVisible(true);
                    b1.setEnabled(false);
                }
            }
        });
        t1.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                t1.selectAll();
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
                    float cantidad = Integer.parseInt(t1.getText());
                    float total = Float.parseFloat(t2.getText());
                    l5.setVisible(false);
                    b1.setEnabled(true);
                } catch (NumberFormatException i) {
                    l5.setVisible(true);
                    b1.setEnabled(false);
                }
            }
        });
        t2.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                t2.selectAll();
            }
        });
        t2.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                t2.selectAll();
            }
        });
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                cb1.setEnabled(false);
                if (cb3.isEnabled()){
                    ventas.add(new Venta(u.productos.get(cb2.getSelectedIndex()).expirables.get(cb3.getSelectedIndex()), Integer.parseInt(t1.getText()), Float.parseFloat(t2.getText())));
                } else {
                    ventas.add(new Venta(u.productos.get(cb2.getSelectedIndex()), Integer.parseInt(t1.getText()), Float.parseFloat(t2.getText())));
                }
                l5.setVisible(false);
                ta.append(t1.getText()+" "+u.productos.get(cb2.getSelectedIndex()).nombre+" - "+t2.getText()+"$\n");
            }
        });
        b2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (ventas.size()>0){
                    u.ventas.add(new Venta(u, cb1.getSelectedIndex(), ventas));
                    Archivos.guardarArchivo(u,  "\\Usuarios\\"+u.usuario+"\\datos.txt");
                    f.setEnabled(true);
                    d.dispose();
                } else {
                    l5.setVisible(true);
                }
            }
        });
        d.add(cb1);
        d.add(cb2);
        d.add(cb3);
        d.add(t1);
        d.add(t2);
        d.add(b1);
        d.add(b2);
        d.add(sp);
        d.add(l1);
        d.add(l2);
        d.add(l3);
        d.add(l4);
        d.add(l5);
        d.add(l6);
        d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        d.setVisible(true);
    }
}