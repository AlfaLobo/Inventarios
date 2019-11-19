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
    JTextField JTextFieldUser = new JTextField();
    JPasswordField JPasswordFieldPassword = new JPasswordField();
    JButton JButtonLogin = new JButton("Iniciar Sesión");
    JButton JButtonNewAccount = new JButton("Crear nuevo usuario");
    JCheckBox JCheckBoxRemember = new JCheckBox("Recordar");
    JLabel JLabelUser = new JLabel("Usuario:");
    JLabel JLabelPassword = new JLabel("Contraseña:");
    JLabel JLabelError = new JLabel("La combinación de usuario y contraseña no fue encontrada.");

    public Inicio(){
        if (Archivos.buscarArchivo("\\sesion.txt")) {
            Sesion s;
            String directorio = System.getProperty("user.dir");
            directorio = directorio + "\\sesion.txt";
            try {
                FileInputStream fis = new FileInputStream(directorio);
                ObjectInputStream ois = new ObjectInputStream(fis);
                s = (Sesion) ois.readObject();
                fis.close();
                ois.close();
                JTextFieldUser.setText(s.id);
                JPasswordFieldPassword.setText(s.contraseña);
                JCheckBoxRemember.setSelected(true);
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
        JLabelUser.setBounds(35,60, 200,30);
        JTextFieldUser.setBounds(90,60, 200,30);
        JLabelPassword.setBounds(15,90, 200,30);
        JPasswordFieldPassword.setBounds(90,90, 200,30);
        JButtonLogin.setBounds(90,120,200, 40);
        JCheckBoxRemember.setBounds(10,120,80, 40);
        JButtonNewAccount.setBounds(90,160,200, 20);
        JLabelError.setBounds(30,165,400, 40);
        f.getContentPane().setBackground(new java.awt.Color(255,196,112,100));
        JTextFieldUser.setBackground(new java.awt.Color(232,160,102,91));
        JPasswordFieldPassword.setBackground(new java.awt.Color(232,160,102,91));
        JButtonLogin.setBackground(new java.awt.Color(153,146,136,60));
        f.setLayout(null);
        f.setResizable(false);
        JLabelError.setVisible(false);
        JButtonNewAccount.setContentAreaFilled(false);
        JButtonNewAccount.setBorderPainted(false);
        JCheckBoxRemember.setContentAreaFilled(false);
        JCheckBoxRemember.setBorderPainted(false);
        JButtonLogin.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){

                if (Archivos.buscarArchivo("\\Usuarios\\"+JTextFieldUser.getText()+"\\datos.txt")){
                    Usuario u = (Usuario) Archivos.cargarUsuario("\\Usuarios\\"+JTextFieldUser.getText()+"\\datos.txt");
                    if (u.contraseña.equals(String.valueOf(JPasswordFieldPassword.getPassword()))){
                        if (JCheckBoxRemember.isSelected()){
                            Sesion s = new Sesion(JTextFieldUser.getText(), u.contraseña);
                        } else if (Archivos.buscarArchivo("\\sesion.txt")){
                            String directorio = System.getProperty("user.dir");
                            directorio = directorio + "\\sesion.txt";
                            File f = new File(directorio);
                            f.delete();
                        }
                        new MenuPrincipal(u);
                        f.dispose();
                    } else {
                        JLabelError.setVisible(true);
                    }
                } else {
                    JLabelError.setVisible(true);
                }
            }
        });
        JButtonNewAccount.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new CrearCuenta(f, JTextFieldUser.getText(), String.valueOf(JPasswordFieldPassword.getPassword()));
            }
        });
        f.add(JTextFieldUser);
        f.add(JPasswordFieldPassword);
        f.add(JButtonLogin);
        f.add(JButtonNewAccount);
        f.add(JCheckBoxRemember);
        f.add(JLabelUser);
        f.add(JLabelPassword);
        f.add(JLabelError);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
    public void run() {

    }
}