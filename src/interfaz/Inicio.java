package interfaz;

import algoritmos.Archivos;
import datos.Sesion;
import datos.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Inicio implements Runnable {
    JFrame f = new JFrame("Inicio de Sesión");
    JTextField t1 = new JTextField();
    JPasswordField p1 = new JPasswordField();
    JButton b1= new JButton("Iniciar Sesión");
    JButton b2= new JButton("Crear nuevo usuario");
    JCheckBox c = new JCheckBox("Recordar");
    JLabel l1 = new JLabel("Usuario:");
    JLabel l2 = new JLabel("Contraseña:");
    JLabel l3 = new JLabel("La combinación de usuario y contraseña no fue encontrada.");

    public Inicio(){
        if (Archivos.buscarArchivo("\\sesion.txt")) {
            Sesion s = null;
            String directorio = System.getProperty("user.dir");
            directorio = directorio + "\\sesion.txt";
            try {
                FileInputStream fis = new FileInputStream(directorio);
                ObjectInputStream ois = new ObjectInputStream(fis);
                s = (Sesion) ois.readObject();
                fis.close();
                ois.close();
                t1.setText(s.id);
                p1.setText(s.contraseña);
                c.setSelected(true);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException i) {
                i.printStackTrace();
            }
        } else if (Archivos.buscarDirectorio("Usuarios")==false){
            Archivos.crearDirectorio("\\Usuarios");
            new CrearCuenta(f);
        }
        f.setSize(375,275);
        l1.setBounds(35,60, 200,30);
        t1.setBounds(90,60, 200,30);
        l2.setBounds(15,90, 200,30);
        p1.setBounds(90,90, 200,30);
        b1.setBounds(90,120,200, 40);
        c.setBounds(10,120,80, 40);
        b2.setBounds(90,160,200, 20);
        l3.setBounds(30,165,400, 40);
        f.getContentPane().setBackground(new java.awt.Color(255,196,112,100));
        t1.setBackground(new java.awt.Color(232,160,102,91));
        p1.setBackground(new java.awt.Color(232,160,102,91));
        b1.setBackground(new java.awt.Color(153,146,136,60));
        f.setLayout(null);
        f.setResizable(false);
        l3.setVisible(false);
        b2.setContentAreaFilled(false);
        b2.setBorderPainted(false);
        c.setContentAreaFilled(false);
        c.setBorderPainted(false);
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (Archivos.buscarArchivo("\\Usuarios\\"+t1.getText()+"\\datos.txt")){
                    Usuario u = (Usuario) Archivos.cargarUsuario("\\Usuarios\\"+t1.getText()+"\\datos.txt");
                    if (u.contraseña.equals(String.valueOf(p1.getPassword()))){
                        if (c.isSelected()){
                            Sesion s = new Sesion(t1.getText(), u.contraseña);
                        } else if (Archivos.buscarArchivo("\\sesion.txt")){
                            String directorio = System.getProperty("user.dir");
                            directorio = directorio + "\\sesion.txt";
                            File f = new File(directorio);
                            f.delete();
                        }
                        new MenuPrincipal(u);
                        f.dispose();
                    } else {
                        l3.setVisible(true);
                    }
                } else {
                    l3.setVisible(true);
                }
            }
        });
        b2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new CrearCuenta(f, t1.getText(), String.valueOf(p1.getPassword()));
            }
        });
        f.add(t1);
        f.add(p1);
        f.add(b1);
        f.add(b2);
        f.add(c);
        f.add(l1);
        f.add(l2);
        f.add(l3);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
    public void run() {

    }
}