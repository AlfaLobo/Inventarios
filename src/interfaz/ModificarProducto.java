package interfaz;

import datos.Usuario;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;

public class ModificarProducto {
    JDialog d;
    JComboBox cb1 = new JComboBox();
    JComboBox cb2 = new JComboBox();
    JTextField t1 = new JTextField();
    JTextField t2 = new JTextField();
    JTextField t3 = new JTextField();
    JTextField t4 = new JTextField();
    JTextField t5 = new JTextField();
    JTextField t6 = new JTextField();
    JButton b1 = new JButton("Modificar");
    JButton b2 = new JButton("Regresar");
    JLabel l1 = new JLabel("Proveedor:");
    JLabel l2 = new JLabel("Producto:");
    JLabel l3 = new JLabel("Nombre:");
    JLabel l4 = new JLabel("Cantidad:");
    JLabel l5 = new JLabel("Costo:");
    JLabel l6 = new JLabel("Precio:");
    JLabel l7 = new JLabel("Inversión:");
    JLabel l8 = new JLabel("Ganancia:");
    JLabel l9 = new JLabel("Insertar valores validos.");

    public ModificarProducto(Usuario u, JFrame f) {
        d = new JDialog(f);
        d.setSize(600,400);
        cb1.setBounds(95,30, 200,30);
        l1.setBounds(35,30, 100,30);
        cb2.setBounds(95,60, 200,30);
        l2.setBounds(35,60, 100,30);
        l3.setBounds(35,90, 100,30);
        t1.setBounds(95,90, 200,30);
        l4.setBounds(35,120, 100,30);
        t2.setBounds(95,120, 200,30);
        l5.setBounds(35,150, 100,30);
        t3.setBounds(95,150, 200,30);
        l6.setBounds(35,180, 100,30);
        t4.setBounds(95,180, 200,30);
        l7.setBounds(35,210, 100,30);
        t5.setBounds(95,210, 200,30);
        l8.setBounds(35,240, 100,30);
        t6.setBounds(95,240, 200,30);
        b1.setBounds(95,270, 200,30);
        b2.setBounds(95,295, 200,30);
        l9.setBounds(95,310, 200,30);
        d.setLayout(null);
        d.setResizable(false);
        b2.setContentAreaFilled(false);
        b2.setBorderPainted(false);
        l9.setVisible(false);
        for (int i=0;i<u.proveedores.size();i++) {
            cb1.addItem(u.proveedores.get(i).nombre);
        }
        for (int i=0;i<u.proveedores.get(cb1.getSelectedIndex()).productos.size();i++) {
            cb2.addItem(u.productos.get(u.proveedores.get(cb1.getSelectedIndex()).productos.get(i)).nombre);
        }
        t1.setText(u.productos.get(u.proveedores.get(cb1.getSelectedIndex()).productos.get(cb2.getSelectedIndex())).nombre);
        t2.setText(String.valueOf(u.productos.get(u.proveedores.get(cb1.getSelectedIndex()).productos.get(cb2.getSelectedIndex())).cantidad));
        t3.setText(String.valueOf(u.productos.get(u.proveedores.get(cb1.getSelectedIndex()).productos.get(cb2.getSelectedIndex())).costo));
        t4.setText(String.valueOf(u.productos.get(u.proveedores.get(cb1.getSelectedIndex()).productos.get(cb2.getSelectedIndex())).precio));
        t5.setText(String.valueOf(u.productos.get(u.proveedores.get(cb1.getSelectedIndex()).productos.get(cb2.getSelectedIndex())).inversion));
        t6.setText(String.valueOf(u.productos.get(u.proveedores.get(cb1.getSelectedIndex()).productos.get(cb2.getSelectedIndex())).ganancia));
        d.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                f.setEnabled(true);
            }
        });
        t1.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                t1.selectAll();
            }
        });
        t2.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                t2.selectAll();
            }
        });
        t3.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                t3.selectAll();
            }
        });
        t4.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                t4.selectAll();
            }
        });
        t5.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                t5.selectAll();
            }
        });
        t6.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                t6.selectAll();
            }
        });
        cb1.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cb2.removeAllItems();
                for (int i=0;i<u.proveedores.get(cb1.getSelectedIndex()).productos.size();i++) {
                    cb2.addItem(u.productos.get(u.proveedores.get(cb1.getSelectedIndex()).productos.get(i)).nombre);
                }
                t1.setText(u.productos.get(u.proveedores.get(cb1.getSelectedIndex()).productos.get(0)).nombre);
                t2.setText(String.valueOf(u.productos.get(u.proveedores.get(cb1.getSelectedIndex()).productos.get(0)).cantidad));
                t3.setText(String.valueOf(u.productos.get(u.proveedores.get(cb1.getSelectedIndex()).productos.get(0)).costo));
                t4.setText(String.valueOf(u.productos.get(u.proveedores.get(cb1.getSelectedIndex()).productos.get(0)).precio));
                t5.setText(String.valueOf(u.productos.get(u.proveedores.get(cb1.getSelectedIndex()).productos.get(0)).inversion));
                t6.setText(String.valueOf(u.productos.get(u.proveedores.get(cb1.getSelectedIndex()).productos.get(0)).ganancia));
            }
        });
        cb2.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    t1.setText(u.productos.get(u.proveedores.get(cb1.getSelectedIndex()).productos.get(cb2.getSelectedIndex())).nombre);
                    t2.setText(String.valueOf(u.productos.get(u.proveedores.get(cb1.getSelectedIndex()).productos.get(cb2.getSelectedIndex())).cantidad));
                    t3.setText(String.valueOf(u.productos.get(u.proveedores.get(cb1.getSelectedIndex()).productos.get(cb2.getSelectedIndex())).costo));
                    t4.setText(String.valueOf(u.productos.get(u.proveedores.get(cb1.getSelectedIndex()).productos.get(cb2.getSelectedIndex())).precio));
                    t5.setText(String.valueOf(u.productos.get(u.proveedores.get(cb1.getSelectedIndex()).productos.get(cb2.getSelectedIndex())).inversion));
                    t6.setText(String.valueOf(u.productos.get(u.proveedores.get(cb1.getSelectedIndex()).productos.get(cb2.getSelectedIndex())).ganancia));
                } catch (IndexOutOfBoundsException i) {

                }
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
                    float cantidad = Integer.parseInt(t2.getText());
                    float costo = Float.parseFloat(t3.getText());
                    float precio = Float.parseFloat(t4.getText());
                    float inversion = Float.parseFloat(t5.getText());
                    float ganancia = Float.parseFloat(t6.getText());
                    l9.setVisible(false);
                    b1.setEnabled(true);
                } catch (NumberFormatException i) {
                    l9.setVisible(true);
                    b1.setEnabled(false);
                }
            }
        });
        t3.getDocument().addDocumentListener(new DocumentListener() {
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
                    float cantidad = Integer.parseInt(t2.getText());
                    float costo = Float.parseFloat(t3.getText());
                    float precio = Float.parseFloat(t4.getText());
                    float inversion = Float.parseFloat(t5.getText());
                    float ganancia = Float.parseFloat(t6.getText());
                    l9.setVisible(false);
                    b1.setEnabled(true);
                } catch (NumberFormatException i) {
                    l9.setVisible(true);
                    b1.setEnabled(false);
                }
            }
        });
        t4.getDocument().addDocumentListener(new DocumentListener() {
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
                    float cantidad = Integer.parseInt(t2.getText());
                    float costo = Float.parseFloat(t3.getText());
                    float precio = Float.parseFloat(t4.getText());
                    float inversion = Float.parseFloat(t5.getText());
                    float ganancia = Float.parseFloat(t6.getText());
                    l9.setVisible(false);
                    b1.setEnabled(true);
                } catch (NumberFormatException i) {
                    l9.setVisible(true);
                    b1.setEnabled(false);
                }
            }
        });
        t5.getDocument().addDocumentListener(new DocumentListener() {
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
                    float cantidad = Integer.parseInt(t2.getText());
                    float costo = Float.parseFloat(t3.getText());
                    float precio = Float.parseFloat(t4.getText());
                    float inversion = Float.parseFloat(t5.getText());
                    float ganancia = Float.parseFloat(t6.getText());
                    l9.setVisible(false);
                    b1.setEnabled(true);
                } catch (NumberFormatException i) {
                    l9.setVisible(true);
                    b1.setEnabled(false);
                }
            }
        });
        t6.getDocument().addDocumentListener(new DocumentListener() {
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
                    float cantidad = Integer.parseInt(t2.getText());
                    float costo = Float.parseFloat(t3.getText());
                    float precio = Float.parseFloat(t4.getText());
                    float inversion = Float.parseFloat(t5.getText());
                    float ganancia = Float.parseFloat(t6.getText());
                    l9.setVisible(false);
                    b1.setEnabled(true);
                } catch (NumberFormatException i) {
                    l9.setVisible(true);
                    b1.setEnabled(false);
                }
            }
        });
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                d.dispose();
                f.setEnabled(true);
            }
        });
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                u.productos.get(u.proveedores.get(cb1.getSelectedIndex()).productos.get(cb2.getSelectedIndex())).nombre=t1.getText();
                u.productos.get(u.proveedores.get(cb1.getSelectedIndex()).productos.get(cb2.getSelectedIndex())).cantidad=Integer.parseInt(t2.getText());
                u.productos.get(u.proveedores.get(cb1.getSelectedIndex()).productos.get(cb2.getSelectedIndex())).costo=Float.parseFloat(t3.getText());
                u.productos.get(u.proveedores.get(cb1.getSelectedIndex()).productos.get(cb2.getSelectedIndex())).precio=Float.parseFloat(t4.getText());
                u.productos.get(u.proveedores.get(cb1.getSelectedIndex()).productos.get(cb2.getSelectedIndex())).inversion=Float.parseFloat(t5.getText());
                u.productos.get(u.proveedores.get(cb1.getSelectedIndex()).productos.get(cb2.getSelectedIndex())).ganancia=Float.parseFloat(t6.getText());
                d.dispose();
                f.setEnabled(true);
            }
        });
        b2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                d.dispose();
                f.setEnabled(true);
            }
        });
        d.add(cb1);
        d.add(cb2);
        d.add(t1);
        d.add(t2);
        d.add(t3);
        d.add(t4);
        d.add(t5);
        d.add(t6);
        d.add(b1);
        d.add(b2);
        d.add(l1);
        d.add(l2);
        d.add(l3);
        d.add(l4);
        d.add(l5);
        d.add(l6);
        d.add(l7);
        d.add(l8);
        d.add(l9);
        d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        d.setVisible(true);
    }
}
