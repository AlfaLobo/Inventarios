package interfaz;

import datos.Usuario;

import javax.swing.*;

public class Inventario {
    JFrame f= new JFrame("Inventario");
    public Inventario(Usuario u, JFrame m){
        m.setEnabled(false);
        String col[] = {"ID","Nombre","Marca","Proveedor","Cantidad","Costo","Precio","Ganancias"};
        String[][] datos;
        if (u.productos.size()<10){
            datos = new String[10][col.length];
        } else {
            datos = new String[u.productos.size()][col.length];
        }
        for (int i = 0;i<u.productos.size();i++){
            datos[i][0]=Integer.toString(u.productos.get(i).id);
            datos[i][1]=u.productos.get(i).nombre;
            datos[i][2]=u.productos.get(i).marca;
            datos[i][3]=u.productos.get(i).proveedor;
            datos[i][4]=Integer.toString(u.productos.get(i).cantidad);
            datos[i][5]=Float.toString(u.productos.get(i).costo);
            datos[i][6]=Float.toString(u.productos.get(i).precio);
            datos[i][7]=Float.toString(u.productos.get(i).ganancia);
        }
        JTable tb = new JTable(datos,col){
            public boolean isCellEditable(int row, int column) {
                return false;
            };
        };
        tb.setRowHeight(38);
        JScrollPane sp = new JScrollPane(tb);
        f.setSize(720,480);
        sp.setBounds(0,55,715,400);
        f.setLayout(null);
        f.setResizable(false);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        f.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                m.setEnabled(true);
            }
        });
        f.add(sp);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setVisible(true);
    }
}