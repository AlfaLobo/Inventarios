package interfaz;

import datos.Producto;
import datos.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class InfoProducto {
    JDialog d;
    JButton b1 = new JButton("Comprar");
    JButton b2 = new JButton("Modificar");
    JButton b3 = new JButton("Eliminar");
    JLabel l1 = new JLabel();
    JLabel l2 = new JLabel();
    public InfoProducto(JFrame f, JDialog j, Usuario u, Producto p) {
        d = new JDialog(f);
        j.setEnabled(false);
        l1.setText("Nombre: "+p.nombre);
        l2.setText("Total: "+p.cantidad);
        String col[] = {"Fecha de Expiración","Cantidad"};
        String[][] datos;
        if (p.expirables.size()==0) {
            datos = new String[10][col.length];
        } else if (p.expirables.size()<5) {
            datos = new String[10][col.length];
        } else {
            datos = new String[p.expirables.size()][col.length];
        }
        for (int i = 0;i<p.expirables.size();i++){
            datos[i][0]=p.expirables.get(i).expiracion.get(Calendar.DAY_OF_MONTH)+"/"+p.expirables.get(i).expiracion.get(Calendar.MONTH)+"/"+p.expirables.get(i).expiracion.get(Calendar.YEAR);
            datos[i][1]=Integer.toString(p.expirables.get(i).cantidad);
        }
        JTable tb = new JTable(datos,col){
            public boolean isCellEditable(int row, int column) {
                return false;
            };
        };

        JScrollPane sp = new JScrollPane(tb);
        d.setSize(375,400);
        l1.setBounds(55,90, 200,30);
        l2.setBounds(55,105, 200,30);
        b1.setBounds(180,35, 125,30);
        b2.setBounds(180,65, 125,30);
        b3.setBounds(180,95, 125,30);
        sp.setBounds(50,130,255,183);
        d.setLayout(null);
        d.setResizable(false);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        d.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                j.setEnabled(true);
            }
        });
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int i;
                for (i=0;i<u.proveedores.get(p.proveedor).productos.size();i++){
                    if (u.productos.get(u.proveedores.get(p.proveedor).productos.get(i)).id==p.id){
                        break;
                    }
                }
                new RegistroCompra(f, u, p.proveedor, i);
            }
        });
        b2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new ModificarProducto(u, f);
            }
        });
        d.add(sp);
        d.add(b1);
        d.add(b2);
        d.add(b3);
        d.add(l1);
        d.add(l2);
        d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        d.setVisible(true);
    }
}