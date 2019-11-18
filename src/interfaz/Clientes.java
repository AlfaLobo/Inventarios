package interfaz;

import datos.Usuario;

import javax.swing.*;
import java.util.Calendar;

public class Clientes {
    JDialog d;
    public Clientes(JFrame f, Usuario u) {
        d = new JDialog(f);
        f.setEnabled(false);
        String col[] = {"ID","Nombre","Telefono","Correo","Cumpleaños","Ganancia"};
        String[][] datos;
        if (u.clientes.size()<10){
            datos = new String[10][col.length];
        } else {
            datos = new String[u.clientes.size()][col.length];
        }
        datos[0][5]=Float.toString(u.clientes.get(0).ganancia);
        for (int i = 0;i<u.clientes.size();i++){
            datos[i][0]=Integer.toString(u.clientes.get(i).id);
            datos[i][1]=u.clientes.get(i).nombre;
            datos[i][2]=u.clientes.get(i).telefono;
            datos[i][3]=u.clientes.get(i).correo;
            try {
                datos[i][4]=u.clientes.get(i).nacimiento.get(Calendar.DAY_OF_MONTH)+"/"+u.clientes.get(i).nacimiento.get(Calendar.MONTH)+"/"+u.clientes.get(i).nacimiento.get(Calendar.YEAR);
            } catch (NullPointerException e) {
                datos[i][4]="";
            }
            datos[i][5]=Float.toString(u.clientes.get(i).ganancia);
        }
        JTable tb = new JTable(datos, col);
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