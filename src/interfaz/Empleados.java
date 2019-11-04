package interfaz;

import datos.Usuario;

import javax.swing.*;

public class Empleados {
    JFrame f= new JFrame("Empleados");
    public Empleados(Usuario u, JFrame m){
        m.setEnabled(false);
        String col[] = {"ID","Nombre","Salario","Telefono","Correo","Cumpleaños","Total Invertido","Ganancia"};
        String[][] datos;
        if (u.empleados.size()<10){
            datos = new String[10][col.length];
        } else {
            datos = new String[u.empleados.size()][col.length];
        }
        for (int i = 0;i<u.empleados.size();i++){
            datos[i][0]=Integer.toString(u.empleados.get(i).id);
            datos[i][1]=u.empleados.get(i).nombre+" "+u.empleados.get(i).apellidos;
            datos[i][2]=Float.toString(u.empleados.get(i).salario);
            datos[i][3]=u.empleados.get(i).telefono;
            datos[i][4]=u.empleados.get(i).correo;
            datos[i][5]=u.empleados.get(i).nacimiento;
            datos[i][6]=Float.toString(u.empleados.get(i).inversion);
            datos[i][7]=Float.toString(u.empleados.get(i).ganancias);
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