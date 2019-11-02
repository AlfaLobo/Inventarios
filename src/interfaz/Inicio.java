package interfaz;

import algoritmos.Archivos;
import datos.Sesion;
import datos.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Inicio {
    JFrame f = new JFrame("Inicio de sesión");
    JTextField t1 = new JTextField();
    JTextField t2 = new JTextField();
    JTextField t3 = new JTextField();
    JTextField t4 = new JTextField();
    JTextField t5 = new JTextField();
    JPasswordField p1 = new JPasswordField("");
    JPasswordField p2 = new JPasswordField("");
    JButton b1= new JButton("Iniciar Sesión");
    JButton b2= new JButton("Crear nuevo usuario");
    JButton b3= new JButton("Registrarse");
    JButton b4= new JButton("Regresar");
    JCheckBox c = new JCheckBox("Recordar");
    JLabel l1 = new JLabel();
    JLabel l2 = new JLabel("Usuario:");
    JLabel l3 = new JLabel("Contraseña:");
    JLabel l4 = new JLabel("Confirmar Contraseña:");
    JLabel l5 = new JLabel("Nombre:");
    JLabel l6 = new JLabel("Apellidos:");
    JLabel l7 = new JLabel("Empresa:");
    JLabel l8 = new JLabel("Saldo Inicial:");

    public Inicio(){
        if (Archivos.buscarArchivo("sesion")) {
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
        }
        f.setSize(400,500);
        t1.setBounds(90,60, 200,30);
        t2.setBounds(150,150, 200,30);
        t3.setBounds(150,180, 200,30);
        t4.setBounds(150,210, 200,30);
        t5.setBounds(150,240, 200,30);
        p1.setBounds(90,90, 200,30);
        p2.setBounds(150,120, 200,30);
        b1.setBounds(90,120,200, 40);
        b2.setBounds(90,150,200, 40);
        b3.setBounds(150,270,200, 40);
        b4.setBounds(150,300,200, 40);
        c.setBounds(10,120,80, 40);
        l2.setBounds(35,60, 200,30);
        l3.setBounds(15,90, 200,30);
        l4.setBounds(15,120, 200,30);
        l5.setBounds(95,150, 200,30);
        l6.setBounds(90,180, 200,30);
        l7.setBounds(90,210, 200,30);
        l8.setBounds(75,240, 200,30);
        f.setLayout(null);
        t2.setVisible(false);
        t3.setVisible(false);
        t4.setVisible(false);
        t5.setVisible(false);
        p2.setVisible(false);
        b3.setVisible(false);
        b4.setVisible(false);
        l1.setVisible(false);
        l4.setVisible(false);
        l5.setVisible(false);
        l6.setVisible(false);
        l7.setVisible(false);
        l8.setVisible(false);
        b2.setContentAreaFilled(false);
        b2.setBorderPainted(false);
        b4.setContentAreaFilled(false);
        b4.setBorderPainted(false);
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                l1.setText("La combinación de usuario y contraseña no fue encontrada.");
                l1.setBounds(30,165,400, 40);
                String ID=t1.getText();
                String pw= String.valueOf(p1.getPassword());
                if (Archivos.buscarArchivo(ID)){
                    Usuario u = Archivos.cargarUsuario(ID);
                    if (u.contraseña.equals(pw)){
                        if (c.isSelected()){
                            Sesion s = new Sesion(ID, u.contraseña);
                        } else if (Archivos.buscarArchivo("sesion")){
                            String directorio = System.getProperty("user.dir");
                            directorio = directorio + "\\sesion.txt";
                            File f = new File(directorio);
                            f.delete();
                        }
                        new MenuPrincipal(u);
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
                t1.setBounds(150,60, 200,30);
                p1.setBounds(150,90, 200,30);
                l2.setBounds(95,60, 200,30);
                l3.setBounds(75,90, 200,30);
                c.setBounds(70,270,80, 40);
                b1.setVisible(false);
                b2.setVisible(false);
                t2.setVisible(true);
                t3.setVisible(true);
                t4.setVisible(true);
                t5.setVisible(true);
                p2.setVisible(true);
                b3.setVisible(true);
                b4.setVisible(true);
                l1.setVisible(false);
                l4.setVisible(true);
                l5.setVisible(true);
                l6.setVisible(true);
                l7.setVisible(true);
                l8.setVisible(true);
            }
        });
        b3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String ID=t1.getText();
                String nm=t2.getText();
                String ln=t3.getText();
                String ep=t4.getText();
                String pw1= String.valueOf(p1.getPassword());
                String pw2= String.valueOf(p2.getPassword());
                if (ID.equals("")){
                    l1.setText("Nombre de usuario no valido.");
                    l1.setBounds(170,315,400, 40);
                    l1.setVisible(true);
                } else if (Archivos.buscarArchivo(ID)) {
                    l1.setText("El usuario ya existe.");
                    l1.setBounds(195,315,400, 40);
                    l1.setVisible(true);
                } else if (pw2.equals(pw1)) {
                    float bl = 0.0f;
                    try {
                        bl = Float.parseFloat(t5.getText());
                    } catch (NumberFormatException i) {
                        l1.setText("Insertar un saldo valido.");
                        l1.setBounds(180,315,400, 40);
                        l1.setVisible(true);
                    }
                    if (bl!=0.0f) {
                        Usuario u = new Usuario(ID,pw1,nm,ln,ep,bl);
                        if (c.isSelected()){
                            Sesion s = new Sesion(ID, u.contraseña);
                        }
                        new MenuPrincipal(u);
                        f.dispose();
                    }
                } else {
                    l1.setText("Las contraseñas no coinciden.");
                    l1.setBounds(165,315,400, 40);
                    l1.setVisible(true);
                }
            }
        });
        b4.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                t1.setBounds(90,60, 200,30);
                p1.setBounds(90,90, 200,30);
                c.setBounds(10,120,80, 40);
                l2.setBounds(35,60, 200,30);
                l3.setBounds(15,90, 200,30);
                b1.setVisible(true);
                b2.setVisible(true);
                t2.setVisible(false);
                t3.setVisible(false);
                t4.setVisible(false);
                t5.setVisible(false);
                p2.setVisible(false);
                b3.setVisible(false);
                b4.setVisible(false);
                l1.setVisible(false);
                l4.setVisible(false);
                l5.setVisible(false);
                l6.setVisible(false);
                l7.setVisible(false);
                l8.setVisible(false);
            }
        });
        f.add(t1);
        f.add(t2);
        f.add(t3);
        f.add(t4);
        f.add(t5);
        f.add(p1);
        f.add(p2);
        f.add(b1);
        f.add(b2);
        f.add(b3);
        f.add(b4);
        f.add(c);
        f.add(l1);
        f.add(l2);
        f.add(l3);
        f.add(l4);
        f.add(l5);
        f.add(l6);
        f.add(l7);
        f.add(l8);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setVisible(true);
    }
}