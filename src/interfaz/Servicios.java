package interfaz;

import datos.Usuario;

import javax.swing.*;

public class Servicios {
    JDialog d;
    public Servicios (Usuario u, JFrame f){
        d = new JDialog(f);
        f.setEnabled(false);
        String col[] = {"Nombre","Costo","Precio","Total Invertido","Ganancias"};
        String[][] datos;
        if (u.proveedores.size()<10){
            datos = new String[10][col.length];
        } else {
            datos = new String[u.proveedores.size()][col.length];
        }
        for (int i = 0;i<u.servicios.size();i++){
            datos[i][0]=u.servicios.get(i).nombre;
            datos[i][1]=Float.toString(u.servicios.get(i).costo);
            datos[i][2]=Float.toString(u.servicios.get(i).precio);
            datos[i][3]=Float.toString(u.servicios.get(i).inversion);
            datos[i][4]=Float.toString(u.servicios.get(i).ganancias);
        }
        JTable tb = new JTable(datos,col);
        tb.setRowHeight(38);
        tb.setEnabled(false);
        JScrollPane sp = new JScrollPane(tb);
        d.setSize(720,480);
        sp.setBounds(0,55,715,400);
        d.setLayout(null);
        d.setResizable(false);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        d.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                f.setEnabled(true);
            }
        });
        d.add(sp);
        d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        d.setVisible(true);
    }
}