package interfaz;

import datos.Usuario;

import javax.swing.*;
import java.util.Calendar;

public class Compras {
    JDialog d;
    public Compras(Usuario u, JFrame f){
        d= new JDialog(f);
        f.setEnabled(false);
        String col[] = {"ID","Proveedor","Total","Fecha"};
        String[][] datos;
        if (u.compras.size()<10){
            datos = new String[10][col.length];
        } else {
            datos = new String[u.compras.size()][col.length];
        }
        for (int i = 0;i<u.compras.size();i++){
            datos[i][0]=Integer.toString(u.compras.get(i).id);
            datos[i][1]=u.compras.get(i).proveedor;
            datos[i][2]= u.compras.get(i).total +"$";
            datos[i][3]= u.compras.get(i).fecha.get(Calendar.DAY_OF_MONTH)+"-"+u.compras.get(i).fecha.get(Calendar.MONTH)+"-"+u.compras.get(i).fecha.get(Calendar.YEAR);
        }
        JTable tb = new JTable(datos,col){
            public boolean isCellEditable(int row, int column) {
                return false;
            };
        };
        tb.setRowHeight(38);
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