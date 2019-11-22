package interfaz;

import datos.Usuario;

import javax.swing.*;
import java.awt.*;

public class Proveedores {
    GridBagConstraints c = new GridBagConstraints();
    JDialog d;
    JPanel JPanelProviders = new JPanel();
    JPanel JPanelAddProvider = new JPanel();
    public Proveedores(JFrame f, Usuario u){
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
            datos[i][5]=Float.toString(u.proveedores.get(i).ganancia);
        }
        JTable tb = new JTable(datos,col);
        tb.setRowHeight(38);
        tb.setEnabled(false);
        JScrollPane sp = new JScrollPane(tb);
        d.setSize(850,600);
        d.getContentPane().setBackground(new java.awt.Color(171,213,217));
        d.setResizable(false);
        JPanelProviders.setLayout(new FlowLayout());
        JPanelProviders.setOpaque(false);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        d.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                f.setEnabled(true);
            }
        });
        JPanelProviders.add(JPanelAddProvider);
        JPanelProviders.add(sp);
        d.add(JPanelProviders);
        d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        d.setVisible(true);
    }
}