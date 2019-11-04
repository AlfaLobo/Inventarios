package interfaz;

import datos.Usuario;

import javax.swing.*;

public class Proveedores {
    JFrame f= new JFrame("Proveedores");
    public Proveedores(Usuario u, JFrame m){
        m.setEnabled(false);
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
