package interfaz;

import datos.Usuario;

import javax.swing.*;

public class Clientes {
    JFrame f= new JFrame("Clientes");
    public Clientes(Usuario u, JFrame m) {
        m.setEnabled(false);
        String col[] = {"ID","Nombre","Telefono","Correo","Cumpleaños","Ganancia"};
        String[][] datos;
        if (u.clientes.size()<10){
            datos = new String[10][col.length];
        } else {
            datos = new String[u.clientes.size()][col.length];
        }
        for (int i = 0;i<u.clientes.size();i++){
            datos[i][0]=Integer.toString(u.clientes.get(i).id);
            datos[i][1]=u.clientes.get(i).nombre;
            datos[i][2]=u.clientes.get(i).telefono;
            datos[i][3]=u.clientes.get(i).correo;
            datos[i][4]=u.clientes.get(i).nacimiento;
            datos[i][5]=Float.toString(u.clientes.get(i).ganancias);
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
