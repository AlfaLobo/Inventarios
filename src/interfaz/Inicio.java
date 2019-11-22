package interfaz;

import algoritmos.Archivos;
import datos.Sesion;
import datos.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Inicio implements Runnable {
    JFrame f = new JFrame("Inicio de Sesión");
    JPanel JPanelLogin = new JPanel();
    JLabel JLabelUser = new JLabel("Usuario:");
    JTextField JTextFieldUser = new JTextField();
    JLabel JLabelPassword = new JLabel("Contraseña:");
    JPasswordField JPasswordFieldPassword = new JPasswordField();
    JButton JButtonLogin = new JButton("Iniciar Sesión");
    JCheckBox JCheckBoxRemember = new JCheckBox("Recordar");
    JButton JButtonNewAccount = new JButton("Crear Nuevo Usuario");
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
        f.setLocationRelativeTo(null);
        f.getContentPane().setBackground(new java.awt.Color(171,213,217));
        f.setResizable(false);
        JPanelLogin.setLayout(new BoxLayout(JPanelLogin, BoxLayout.Y_AXIS));
        JPanelLogin.setBorder(BorderFactory.createEmptyBorder(30, 90, 50, 90));
        JPanelLogin.setOpaque(false);
        JCheckBoxRemember.setContentAreaFilled(false);
        Dimension d1 = new Dimension(190, 30);
        JButtonLogin.setPreferredSize(d1);
        JButtonLogin.setMaximumSize(d1);
        Dimension d2 = new Dimension(190, 20);
        JButtonNewAccount.setPreferredSize(d2);
        JButtonNewAccount.setMaximumSize(d2);
        JButtonNewAccount.setContentAreaFilled(false);
        JButtonNewAccount.setBorderPainted(false);
        JLabelError.setVisible(false);
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
        JPanelLogin.add(JLabelUser);
        JPanelLogin.add(JTextFieldUser);
        JPanelLogin.add(JLabelPassword);
        JPanelLogin.add(JPasswordFieldPassword);
        JPanelLogin.add(JCheckBoxRemember);
        JPanelLogin.add(JButtonLogin);
        JPanelLogin.add(JButtonNewAccount);
        f.getContentPane().add(JPanelLogin);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
    public void run() {

    }
}