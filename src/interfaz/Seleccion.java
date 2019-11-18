package interfaz;

import algoritmos.Archivos;
import datos.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Seleccion {
    JDialog d;
    JComboBox cb1 = new JComboBox();
    JComboBox cb2 = new JComboBox();
    JButton b = new JButton("Cerrar");
    JLabel l1 = new JLabel("Empresa:");
    JLabel l2 = new JLabel("Negocio:");
    public Seleccion(JFrame f, Usuario u, JLabel l){
        d = new JDialog(f);
        d.setSize(375,275);
        l1.setBounds(35,60, 200,30);
        cb1.setBounds(90,60, 200,30);
        l2.setBounds(15,90, 200,30);
        cb2.setBounds(90,90, 200,30);
        b.setBounds(90,120,200, 40);
        d.setLayout(null);
        d.setResizable(false);
        d.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                f.setEnabled(true);
            }
        });
        for (int i=0;i<u.empresas.size();i++) {
            cb1.addItem(u.empresas.get(i).nombre);
        }
        cb1.setSelectedIndex(u.empresa);
        for (int i=0;i<u.empresas.get(cb1.getSelectedIndex()).negocios.size();i++) {
            cb2.addItem(u.empresas.get(cb1.getSelectedIndex()).negocios.get(i).nombre);
        }
        cb2.setSelectedIndex(u.negocio);
        cb1.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cb2.removeAllItems();
                for (int i=0;i<u.empresas.get(cb1.getSelectedIndex()).negocios.size();i++) {
                    cb2.addItem(u.empresas.get(cb1.getSelectedIndex()).negocios.get(i).nombre);
                }
                u.empresa=cb1.getSelectedIndex();
                Archivos.guardarArchivo(u,  "\\Usuarios\\"+u.usuario+"\\datos.txt");
            }
        });
        cb2.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                u.negocio=cb2.getSelectedIndex();
                Archivos.guardarArchivo(u,  "\\Usuarios\\"+u.usuario+"\\datos.txt");
            }
        });
        b.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                l.setText("Administrando el negocio "+u.empresas.get(u.empresa).negocios.get(u.negocio).nombre+" de la empresa "+u.empresas.get(u.empresa).nombre);
                f.setEnabled(true);
                d.dispose();
            }
        });
        d.add(cb1);
        d.add(cb2);
        d.add(b);
        d.add(l1);
        d.add(l2);
        d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        d.setVisible(true);
    }
}