package interfaz;

import algoritmos.Archivos;
import datos.Sesion;
import datos.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CrearCuenta {
    JFrame f = new JFrame("Crear cuenta");
    JTextField t1 = new JTextField();
    JTextField t2 = new JTextField();
    JTextField t3 = new JTextField();
    JTextField t4 = new JTextField();
    JTextField t5 = new JTextField("0");
    JPasswordField p1 = new JPasswordField();
    JPasswordField p2 = new JPasswordField();
    JButton b1= new JButton("Registrarse");
    JButton b2= new JButton("Cancelar");
    JLabel l1 = new JLabel();
    JLabel l2 = new JLabel("Usuario:");
    JLabel l3 = new JLabel("Contraseña:");
    JLabel l4 = new JLabel("Confirmar Contraseña:");
    JLabel l5 = new JLabel("Nombre:");
    JLabel l6 = new JLabel("Apellidos:");
    JLabel l7 = new JLabel("Empresa:");
    JLabel l8 = new JLabel("Saldo Inicial:");

    public CrearCuenta(String user, String password) {
        f.setSize(400,500);
        t1.setBounds(150,60, 200,30);
        t2.setBounds(150,150, 200,30);
        t3.setBounds(150,180, 200,30);
        t4.setBounds(150,210, 200,30);
        t5.setBounds(150,240, 200,30);
        p1.setBounds(150,90, 200,30);
        p2.setBounds(150,120, 200,30);
        b1.setBounds(150,270,200, 40);
        b2.setBounds(150,310,200, 20);
        l2.setBounds(95,60, 200,30);
        l3.setBounds(75,90, 200,30);
        l4.setBounds(15,120, 200,30);
        l5.setBounds(95,150, 200,30);
        l6.setBounds(90,180, 200,30);
        l7.setBounds(90,210, 200,30);
        l8.setBounds(75,240, 200,30);
        f.getContentPane().setBackground(new java.awt.Color(204,153,80,80));
        f.setLayout(null);
        f.setResizable(false);
        l1.setVisible(false);
        b2.setContentAreaFilled(false);
        b2.setBorderPainted(false);
        t1.setText(user);
        p1.setText(password);
        t5.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                t5.selectAll();
            }
        });
        b1.addActionListener(new ActionListener(){
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
                    float bl = -1;
                    try {
                        bl = Float.parseFloat(t5.getText());
                    } catch (NumberFormatException i) {
                        l1.setText("Insertar un saldo valido.");
                        l1.setBounds(180,315,400, 40);
                        l1.setVisible(true);
                    }
                    if (bl>=0) {
                        Usuario u = new Usuario(ID,pw1,nm,ln,ep,bl);
                        Sesion s = new Sesion(ID, u.contraseña);
                        new Inicio();
                        f.dispose();
                    }
                } else {
                    l1.setText("Las contraseñas no coinciden.");
                    l1.setBounds(165,315,400, 40);
                    l1.setVisible(true);
                }
            }
        });
        b2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new Inicio();
                f.dispose();
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