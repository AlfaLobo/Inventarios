package interfaz;

import datos.Usuario;

import javax.swing.*;
import java.util.Calendar;

public class Ventas {
    JDialog d;
    public Ventas(Usuario u, JFrame f){
        /*d = new JDialog(f);
        f.setEnabled(false);
        String col[] = {"ID","Cliente","Forma de Pago","Total","Fecha"};
        String[][] datos;
        if (u.ventas.size()<10){
            datos = new String[10][col.length];
        } else {
            datos = new String[u.ventas.size()][col.length];
        }
        for (int i = 0;i<u.ventas.size();i++){
            datos[i][0]=Integer.toString(u.ventas.get(i).id);
            datos[i][1]=u.ventas.get(i).cliente;
            datos[i][2]=u.ventas.get(i).formapago;
            datos[i][3]=u.ventas.get(i).total +"$";
            datos[i][4]=u.compras.get(i).fecha.get(Calendar.DAY_OF_MONTH)+"-"+u.compras.get(i).fecha.get(Calendar.MONTH)+"-"+u.compras.get(i).fecha.get(Calendar.YEAR);
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
        d.setVisible(true);*/
    }
}