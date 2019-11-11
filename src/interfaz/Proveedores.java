package interfaz;

import datos.Usuario;

import javax.swing.*;

public class Proveedores {
    JDialog d;
    public Proveedores(Usuario u, JFrame f){
        d = new JDialog(f);
        f.setEnabled(false);
        String col[] = {"Nombre","Telefono","Correo","Dirección","Total Invertido","Ganancias Generadas"};
        String[][] datos;
        if (u.proveedores.size()<10){
            datos = new String[10][col.length];
        } else {
            datos = new String[u.proveedores.size()][col.length];
        }
        for (int i = 0;i<u.proveedores.size();i++){
            datos[i][0]=u.proveedores.get(i).nombre;
            datos[i][1]=u.proveedores.get(i).telefono;
            datos[i][2]=u.proveedores.get(i).correo;
            datos[i][3]=u.proveedores.get(i).direccion;
            datos[i][4]=Float.toString(u.proveedores.get(i).inversion);
            datos[i][5]=Float.toString(u.proveedores.get(i).ganancias);
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