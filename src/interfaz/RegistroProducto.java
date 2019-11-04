package interfaz;

import algoritmos.Archivos;
import datos.Producto;
import datos.Proveedor;
import datos.Sesion;
import datos.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RegistroProducto {
    JFrame f = new JFrame("Registro de producto");
    JComboBox cb = new JComboBox();
    JTextField t1 = new JTextField();
    JTextField t2 = new JTextField();
    JTextField t3 = new JTextField("0");
    JTextField t4 = new JTextField("0");
    JTextField t5 = new JTextField("0");
    JButton b1 = new JButton("Registrar");
    JButton b2 = new JButton("Cancelar");
    JLabel l1 = new JLabel("Nombre:");
    JLabel l2 = new JLabel("Marca:");
    JLabel l3 = new JLabel("Cantidad:");
    JLabel l4 = new JLabel("Precio compra:");
    JLabel l5 = new JLabel("Precio venta:");
    JLabel l6 = new JLabel("Proveedor:");
    JLabel error = new JLabel();
    public RegistroProducto(Usuario u) {
        f.setSize(400,500);
        cb.setBounds(150,270, 200,30);
        t1.setBounds(150,120, 200,30);
        t2.setBounds(150,150, 200,30);
        t3.setBounds(150,180, 200,30);
        t4.setBounds(150,210, 200,30);
        t5.setBounds(150,240, 200,30);
        b1.setBounds(150,300, 200,30);
        b2.setBounds(150,325, 200,30);
        l1.setBounds(95,120, 200,30);
        l2.setBounds(103,150, 200,30);
        l3.setBounds(89,180, 200,30);
        l4.setBounds(55,210, 200,30);
        l5.setBounds(67,240, 200,30);
        l6.setBounds(78,270, 200,30);
        f.setLayout(null);
        f.setResizable(false);
        b2.setContentAreaFilled(false);
        b2.setBorderPainted(false);
        for (int i=0;i<u.proveedores.size();i++) {
            cb.addItem(u.proveedores.get(i).nombre);
        }
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String name=t1.getText();
                String brand=t2.getText();
                if (name.equals("")){
                    error.setText("Nombre no valido.");
                    error.setBounds(200,335,400, 40);
                    error.setVisible(true);
                } else if (Archivos.buscarProducto(u, name, brand)) {
                    error.setText("Un producto con el mismo nombre de esta marca ya existe.");
                    error.setBounds(210,335,400, 40);
                    error.setVisible(true);
                } else {
                    int quantity = -1;
                    float cprice = -1;
                    float sprice = -1;
                    try {
                        quantity = Integer.parseInt(t3.getText());
                        cprice = Float.parseFloat(t4.getText());
                        sprice = Float.parseFloat(t5.getText());
                        if (quantity>=0) {
                            if (cprice>=0&&sprice>=0){
                                Random rand = new Random();
                                u.productos.add(new Producto(rand.nextInt(100000), name, brand, quantity, cprice, sprice, u.proveedores.get(cb.getSelectedIndex())));
                                Archivos.guardarArchivo(u);
                                f.dispose();
                            } else {
                                error.setText("Insertar precios validos.");
                                error.setBounds(180,335,400, 40);
                                error.setVisible(true);
                            }
                        } else {
                            error.setText("Insertar una cantidad valida.");
                            error.setBounds(175,335,400, 40);
                            error.setVisible(true);
                        }

                    } catch (NumberFormatException i) {
                        error.setText("Insertar valores validos.");
                        error.setBounds(180,335,400, 40);
                        error.setVisible(true);
                    }
                }
            }
        });
        b2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                f.dispose();
            }
        });
        f.add(cb);
        f.add(t1);
        f.add(t2);
        f.add(t3);
        f.add(t4);
        f.add(t5);
        f.add(b1);
        f.add(b2);
        f.add(l1);
        f.add(l2);
        f.add(l3);
        f.add(l4);
        f.add(l5);
        f.add(l6);
        f.add(error);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setVisible(true);
    }
}
