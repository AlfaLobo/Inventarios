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
    JTextField t3 = new JTextField("Confirmar Contraseña");
    JTextField t4 = new JTextField("Nombre");
    JTextField t5 = new JTextField("Apellidos");
    JTextField t6 = new JTextField("Empresa");
    JTextField t7 = new JTextField("Saldo Inicial");
    JButton b1= new JButton("Iniciar Sesión");
    JButton b2= new JButton("Crear Nuevo Usuario");
    JButton b3= new JButton("Registrarse");
    JButton b4= new JButton("Regresar");
    JLabel l = new JLabel();

    public Inicio(){
        f.setSize(400,500);
        t1.setBounds(90,40, 200,30);
        t2.setBounds(90,70, 200,30);
        t3.setBounds(90,100, 200,30);
        t4.setBounds(90,130, 200,30);
        t5.setBounds(90,160, 200,30);
        t6.setBounds(90,190, 200,30);
        t7.setBounds(90,220, 200,30);
        b1.setBounds(90,100,200, 40);
        b2.setBounds(90,130,200, 40);
        b3.setBounds(90,250,200, 40);
        b4.setBounds(90,280,200, 40);
        f.setLayout(null);
        f.setVisible(true);
        l.setVisible(false);
        t3.setVisible(false);
        t4.setVisible(false);
        t5.setVisible(false);
        t6.setVisible(false);
        t7.setVisible(false);
        b3.setVisible(false);
        b4.setVisible(false);
        b2.setContentAreaFilled(false);
        b2.setBorderPainted(false);
        b4.setContentAreaFilled(false);
        b4.setBorderPainted(false);
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                l.setText("La combinación de usuario y contraseña no fue encontrada.");
                l.setBounds(30,145,400, 40);
                String ID=t1.getText();
                String pw=t2.getText();
                if (Archivos.buscarUsuario(ID)){
                    Usuario u = Archivos.cargarArchivos(ID);
                    if (u.contraseña.equals(pw)){
                        new MenuPrincipal(u);
                        f.dispose();
                    } else {
                        l.setVisible(true);
                    }
                } else {
                    l.setVisible(true);
                }
            }
        });
        b2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                b1.setVisible(false);
                b2.setVisible(false);
                l.setVisible(false);
                t3.setVisible(true);
                t4.setVisible(true);
                t5.setVisible(true);
                t6.setVisible(true);
                t7.setVisible(true);
                b3.setVisible(true);
                b4.setVisible(true);
            }
        });
        b3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String ID=t1.getText();
                String pw1=t2.getText();
                String pw2=t3.getText();
                String nm=t4.getText();
                String ln=t5.getText();
                String ep=t6.getText();
                if (Archivos.buscarUsuario(ID)){
                    l.setText("El usuario ya existe.");
                    l.setBounds(135,300,400, 40);
                    l.setVisible(true);
                } else if (pw2.equals(pw1)) {
                    float bl = 0.0f;
                    try {
                        bl = Float.parseFloat(t7.getText());
                    } catch (NumberFormatException i) {
                        l.setText("Insertar un saldo valido.");
                        l.setBounds(120,300,400, 40);
                        l.setVisible(true);
                    }
                    if (bl!=0.0f) {
                        Usuario u = new Usuario(ID,pw1,nm,ln,ep,bl);
                        new MenuPrincipal(u);
                        f.dispose();
                    }
                } else {
                    l.setText("Las contraseñas no coinciden.");
                    l.setBounds(110,300,400, 40);
                    l.setVisible(true);
                }
            }
        });
        b4.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                b1.setVisible(true);
                b2.setVisible(true);
                t3.setVisible(false);
                t4.setVisible(false);
                t5.setVisible(false);
                t6.setVisible(false);
                t7.setVisible(false);
                b3.setVisible(false);
                b4.setVisible(false);
            }
        });
        f.add(t1);
        f.add(t2);
        f.add(t3);
        f.add(t4);
        f.add(t5);
        f.add(t6);
        f.add(t7);
        f.add(b1);
        f.add(b2);
        f.add(b3);
        f.add(b4);
        f.add(l);
    }
}