package interfaz;

import algoritmos.Archivos;
import algoritmos.Interfaces;
import datos.Sesion;
import datos.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Inicio implements Runnable {
    GridBagConstraints c = new GridBagConstraints();
    JFrame f = new JFrame("Inicio de Sesión");
    JPanel JPanelLogin = new JPanel();
    JLabel JLabelUser = new JLabel("Usuario:");
    JTextField JTextFieldUser = new JTextField();
    JLabel JLabelPassword = new JLabel("Contraseña:");
    JPasswordField JPasswordFieldPassword = new JPasswordField();
    JButton JButtonLogin = new JButton("Iniciar Sesión");
    JCheckBox JCheckBoxRemember = new JCheckBox("Recordar");
    JButton JButtonNewAccount = new JButton("Crear nuevo usuario");
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
        f.getContentPane().setBackground(new java.awt.Color(171,213,217));
        f.setResizable(false);
        JPanelLogin.setLayout(new GridBagLayout());
        JPanelLogin.setOpaque(false);
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
        Interfaces.addLabel(JPanelLogin, JLabelUser, c, 0, 0);
        Interfaces.addTextField(JPanelLogin, JTextFieldUser, c, 1, 0);
        Interfaces.addLabel(JPanelLogin, JLabelPassword, c, 0, 1);
        Interfaces.addTextField(JPanelLogin, JPasswordFieldPassword, c, 1, 1);
        Interfaces.addCheckBox(JPanelLogin, JCheckBoxRemember, c, 0, 2);
        Interfaces.addButton(JPanelLogin, JButtonLogin, c, 1, 2);
        Interfaces.addButton(JPanelLogin, JButtonNewAccount, c, 1, 3);
        f.add(JPanelLogin);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
    public void run() {

    }
}