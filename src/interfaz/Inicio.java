package interfaz;

import algoritmos.Archivos;
import datos.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Inicio {
    JFrame f = new JFrame("Inicio de sesión");
    JTextField t1 = new JTextField("Usuario");
    JTextField t2 = new JTextField("Contraseña");
    JButton b1= new JButton("Iniciar Sesión");
    JButton b2= new JButton("Crear Nuevo Usuario");
    JLabel l1 = new JLabel("La combinación de usuario y contraseña no fue encontrada.");
    public Inicio(){
        f.setSize(400,500);
        t1.setBounds(90,40, 200,30);
        t2.setBounds(90,70, 200,30);
        b1.setBounds(90,100,200, 40);
        b2.setBounds(90,130,200, 40);
        l1.setBounds(30,145,400, 40);
        f.setLayout(null);
        f.setVisible(true);
        l1.setVisible(false);
        b2.setContentAreaFilled(false);
        b2.setBorderPainted(false);
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String us=t1.getText();
                String pw=t2.getText();
                if (Archivos.buscarUsuario(us)){
                    Usuario u = Archivos.cargarArchivos(us);
                    if (u.contraseña.equals(pw)){
                        new MenuPrincipal();
                        f.dispose();
                    } else {
                        l1.setVisible(true);
                    }
                } else {
                    l1.setVisible(true);
                }
            }
        });
        b2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){

            }
        });
        f.add(t1);
        f.add(t2);
        f.add(b1);
        f.add(b2);
        f.add(l1);
    }
}