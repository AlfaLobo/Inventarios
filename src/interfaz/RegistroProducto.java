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
    JTextField t2 = new JTextField("0");
    JTextField t3 = new JTextField("0");
    JButton b1 = new JButton("Registrar");
    JButton b2 = new JButton("Cancelar");
    JLabel l1 = new JLabel("Nombre:");
    JLabel l2 = new JLabel("Costo:");
    JLabel l3 = new JLabel("Precio:");
    JLabel l4 = new JLabel("Proveedor:");
    JLabel l5 = new JLabel();
    public RegistroProducto(JFrame f, Usuario u) {
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
        cb.setBounds(150,210, 200,30);
        b1.setBounds(150,240, 200,30);
        b2.setBounds(150,265, 200,30);
        l5.setBounds(67,275, 200,30);
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
                l5.setText("Insertar valores validos.");
                l5.setBounds(180,275,400, 40);
                try {
                    float costo = Float.parseFloat(t2.getText());
                    float precio = Float.parseFloat(t3.getText());
                    l5.setVisible(false);
                    b1.setEnabled(true);
                    if (costo<0||precio<0) {
                        l5.setVisible(true);
                        b1.setEnabled(false);
                    } else {
                        l5.setVisible(false);
                        b1.setEnabled(true);
                    }
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
                l5.setText("Insertar valores validos.");
                l5.setBounds(180,175,400, 40);
                try {
                    float costo = Float.parseFloat(t2.getText());
                    float precio = Float.parseFloat(t3.getText());
                    l5.setVisible(false);
                    b1.setEnabled(true);
                    if (costo<0||precio<0) {
                        l5.setVisible(true);
                        b1.setEnabled(false);
                    } else  {
                        l5.setVisible(false);
                        b1.setEnabled(true);
                    }
                } catch (NumberFormatException i) {
                    l5.setVisible(true);
                    b1.setEnabled(false);
                }
            }
        });
        t3.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                t3.selectAll();
            }
        });
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (t1.getText().equals("")){
                    l5.setText("Nombre no valido.");
                    l5.setBounds(200,305,400, 40);
                    l5.setVisible(true);
                } else if (Archivos.buscarProducto(u, cb.getSelectedIndex(),t1.getText())) {
                    l5.setText("El producto ya existe.");
                    l5.setBounds(190,305,400, 40);
                    l5.setVisible(true);
                } else {
                    u.productos.add(new Producto(u, t1.getText(), Float.parseFloat(t2.getText()), Float.parseFloat(t3.getText()), cb.getSelectedIndex()));
                    Archivos.guardarArchivo(u,  "\\Usuarios\\"+u.usuario+"\\datos.txt");
                    Object[] opciones = {"Si", "No"};
                    int opcion = JOptionPane.showOptionDialog(d, "Desea registrar una compra de este producto?", "Elegir una opción", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
                    if (opcion == JOptionPane.YES_OPTION){
                        new RegistroCompra(f,u,cb.getSelectedIndex());
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
        d.add(b1);
        d.add(b2);
        d.add(l1);
        d.add(l2);
        d.add(l3);
        d.add(l4);
        d.add(l5);
        d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        d.setVisible(true);
    }
}