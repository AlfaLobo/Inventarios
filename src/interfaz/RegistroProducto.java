package interfaz;

import algoritmos.Archivos;
import datos.Producto;
import datos.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class RegistroProducto {
    JFrame f = new JFrame("Registro de producto");
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
    JLabel error = new JLabel();
    public RegistroProducto(Usuario u, JFrame m) {
        m.setEnabled(false);
        f.setSize(400,500);
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
        f.setLayout(null);
        f.setResizable(false);
        b2.setContentAreaFilled(false);
        b2.setBorderPainted(false);
        for (int i=0;i<u.proveedores.size();i++) {
            cb.addItem(u.proveedores.get(i).nombre);
        }
        f.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                m.setEnabled(true);
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
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (t1.getText().equals("")){
                    error.setText("Nombre no valido.");
                    error.setBounds(200,305,400, 40);
                    error.setVisible(true);
                } else if (Archivos.buscarProducto(u, t1.getText(), t2.getText())) {
                    error.setText("El producto ya existe.");
                    error.setBounds(190,305,400, 40);
                    error.setVisible(true);
                } else {
                    float cost = -1;
                    float price = -1;
                    try {
                        cost=Float.parseFloat(t3.getText());
                        price=Float.parseFloat(t4.getText());
                        if (cost>=0&&price>=0){
                            Random rand = new Random();
                            u.productos.add(new Producto(rand.nextInt(100000), t1.getText(), t2.getText(), cost, price, u.proveedores.get(cb.getSelectedIndex()).nombre));
                            Archivos.guardarArchivo(u);
                            new PopupCompra(u, m, u.productos.get(u.productos.size()-1));
                            f.dispose();
                        } else {
                            error.setText("Insertar precios validos.");
                            error.setBounds(180,305,400, 40);
                            error.setVisible(true);
                        }
                    } catch (NumberFormatException i) {
                        error.setText("Insertar valores validos.");
                        error.setBounds(180,305,400, 40);
                        error.setVisible(true);
                    }
                }
            }
        });
        b2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                f.dispose();
                m.setEnabled(true);
            }
        });
        f.add(cb);
        f.add(t1);
        f.add(t2);
        f.add(t3);
        f.add(t4);
        f.add(b1);
        f.add(b2);
        f.add(l1);
        f.add(l2);
        f.add(l3);
        f.add(l4);
        f.add(l5);
        f.add(error);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setVisible(true);
    }
}