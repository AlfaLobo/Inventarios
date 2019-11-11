package interfaz;

import algoritmos.Archivos;
import datos.Producto;
import datos.Usuario;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegistroProducto {
    JDialog d;
    JComboBox cb = new JComboBox();
    JTextField t1 = new JTextField();
    JTextField t2 = new JTextField();
    JTextField t3 = new JTextField("0");
    JTextField t4 = new JTextField("0");
    JButton b1 = new JButton("Registrar");
    JButton b2 = new JButton("Cancelar");
    JLabel l1 = new JLabel("Nombre:");
    JLabel l2 = new JLabel("Marca:");
    JLabel l3 = new JLabel("Costo:");
    JLabel l4 = new JLabel("Precio:");
    JLabel l5 = new JLabel("Proveedor:");
    JLabel l6 = new JLabel();
    public RegistroProducto(Usuario u, JFrame f) {
        f.setEnabled(false);
        d = new JDialog(f);
        d.setSize(400,500);
        l1.setBounds(95,120, 200,30);
        t1.setBounds(150,120, 200,30);
        l2.setBounds(103,150, 200,30);
        t2.setBounds(150,150, 200,30);
        l3.setBounds(89,180, 200,30);
        t3.setBounds(150,180, 200,30);
        l4.setBounds(55,210, 200,30);
        t4.setBounds(150,210, 200,30);
        l5.setBounds(67,240, 200,30);
        cb.setBounds(150,240, 200,30);
        b1.setBounds(150,270, 200,30);
        b2.setBounds(150,295, 200,30);
        d.setLayout(null);
        d.setResizable(false);
        b2.setContentAreaFilled(false);
        b2.setBorderPainted(false);
        for (int i=0;i<u.proveedores.size();i++) {
            cb.addItem(u.proveedores.get(i).nombre);
        }
        d.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                f.setEnabled(true);
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
                l6.setText("Insertar valores validos.");
                l6.setBounds(180,305,400, 40);
                try {
                    float costo = Float.parseFloat(t3.getText());
                    float precio = Float.parseFloat(t4.getText());
                    l6.setVisible(false);
                    b1.setEnabled(true);
                    if (costo<0||precio<0) {
                        l6.setVisible(true);
                        b1.setEnabled(false);
                    } else {
                        l6.setVisible(false);
                        b1.setEnabled(true);
                    }
                } catch (NumberFormatException i) {
                    l6.setVisible(true);
                    b1.setEnabled(false);
                }
            }
        });
        t3.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                t3.selectAll();
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
                l6.setText("Insertar valores validos.");
                l6.setBounds(180,305,400, 40);
                try {
                    float costo = Float.parseFloat(t3.getText());
                    float precio = Float.parseFloat(t4.getText());
                    l6.setVisible(false);
                    b1.setEnabled(true);
                    if (costo<0||precio<0) {
                        l6.setVisible(true);
                        b1.setEnabled(false);
                    } else  {
                        l6.setVisible(false);
                        b1.setEnabled(true);
                    }
                } catch (NumberFormatException i) {
                    l6.setVisible(true);
                    b1.setEnabled(false);
                }
            }
        });
        t4.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                t4.selectAll();
            }
        });
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (t1.getText().equals("")){
                    l6.setText("Nombre no valido.");
                    l6.setBounds(200,305,400, 40);
                    l6.setVisible(true);
                } else if (Archivos.buscarProducto(u, t1.getText(), t2.getText())) {
                    l6.setText("El producto ya existe.");
                    l6.setBounds(190,305,400, 40);
                    l6.setVisible(true);
                } else {
                    u.productos.add(new Producto(u.productos.size(), t1.getText(), t2.getText(), Float.parseFloat(t3.getText()), Float.parseFloat(t4.getText()), u.proveedores.get(cb.getSelectedIndex()).nombre));
                    Archivos.guardarArchivo(u);
                    Object[] opciones = {"Si", "No"};
                    int opcion = JOptionPane.showOptionDialog(d, "Desea registrar una compra de este producto?", "Elegir una opción", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
                    if (opcion == JOptionPane.YES_OPTION){
                        new CompraProducto(u, f, u.productos.get(u.productos.size()-1));
                    } else {
                        f.setEnabled(true);
                    }
                    d.dispose();
                }
            }
        });
        b2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                d.dispose();
                f.setEnabled(true);
            }
        });
        d.add(cb);
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
        d.add(l6);
        d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        d.setVisible(true);
    }
}