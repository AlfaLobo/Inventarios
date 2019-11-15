package interfaz;

import datos.Usuario;

import javax.swing.*;
import java.util.Calendar;

public class Empleados {
    JDialog d;
    public Empleados(JFrame f, Usuario u){
        /*d = new JDialog(f);
        f.setEnabled(false);
        String col[] = {"ID","Nombre","Salario","Telefono","Correo","Cumpleaños","Total Invertido","Ganancia"};
        String[][] datos;
        if (u.empresas.get(0).empleados.size()<10){
            datos = new String[10][col.length];
        } else {
            datos = new String[u.empresas.get(0).empleados.size()][col.length];
        }
        for (int i = 1;i<u.empresas.get(0).empleados.size();i++){
            datos[i][0]=Integer.toString(Integer.parseInt(u.empresas.get(0).empleados.get(i).id));
            datos[i][1]=u.empresas.get(0).empleados.get(i).nombre+" "+u.empresas.get(0).empleados.get(i).apellidos;
            datos[i][2]=Float.toString(u.empresas.get(0).empleados.get(i).salario);
            datos[i][3]=u.empresas.get(0).empleados.get(i).telefono;
            datos[i][4]=u.empresas.get(0).empleados.get(i).correo;
            datos[i][5]=u.empresas.get(0).empleados.get(i).nacimiento.get(Calendar.DAY_OF_MONTH)+"-"+u.empresas.get(0).empleados.get(i).nacimiento.get(Calendar.MONTH)+"-"+u.empresas.get(0).empleados.get(i).nacimiento.get(Calendar.YEAR);
            datos[i][6]=Float.toString(u.empresas.get(0).empleados.get(i).inversion);
            datos[i][7]=Float.toString(u.empresas.get(0).empleados.get(i).ganancia);
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

         */
    }
}