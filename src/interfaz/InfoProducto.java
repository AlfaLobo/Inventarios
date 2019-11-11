package interfaz;

import datos.Producto;

import javax.swing.*;
import java.util.Calendar;

public class InfoProducto {
    JDialog d;
    JTextArea ta = new JTextArea();
    JLabel l1 = new JLabel();
    JLabel l2 = new JLabel();
    JLabel l3 = new JLabel();
    public InfoProducto(JFrame f, JDialog j, Producto p) {
        d = new JDialog(f);
        j.setEnabled(false);
        l1.setText(p.nombre);
        l2.setText("Total: "+p.cantidad);
        l3.setText("Expirables:");
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
        l3.setBounds(55,120, 200,30);
        sp.setBounds(50,130,255,183);
        d.setLayout(null);
        d.setResizable(false);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        d.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                j.setEnabled(true);
            }
        });
        d.add(sp);
        d.add(l1);
        d.add(l2);
        d.add(l3);
        d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        d.setVisible(true);
    }
}
