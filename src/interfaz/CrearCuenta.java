package interfaz;

import algoritmos.Archivos;
import datos.Sesion;
import datos.Usuario;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CrearCuenta {
    JDialog d;
    JTextField t1 = new JTextField();
    JTextField t2 = new JTextField();
    JTextField t3 = new JTextField();
    JTextField t4 = new JTextField();
    JTextField t5 = new JTextField("0");
    JPasswordField p1 = new JPasswordField();
    JPasswordField p2 = new JPasswordField();
    JButton b1= new JButton("Registrarse");
    JButton b2= new JButton("Cancelar");
    JLabel l1 = new JLabel("Usuario:");
    JLabel l2 = new JLabel("Contraseña:");
    JLabel l3 = new JLabel("Confirmar Contraseña:");
    JLabel l4 = new JLabel("Nombre:");
    JLabel l5 = new JLabel("Apellidos:");
    JLabel l6 = new JLabel("Empresa:");
    JLabel l7 = new JLabel("Saldo Inicial:");
    JLabel l8 = new JLabel();

    public CrearCuenta(JFrame f, String user, String password) {
        f.setEnabled(false);
        d = new JDialog();
        d.setSize(400,500);
        l1.setBounds(95,60, 200,30);
        t1.setBounds(150,60, 200,30);
        l2.setBounds(75,90, 200,30);
        p1.setBounds(150,90, 200,30);
        l3.setBounds(15,120, 200,30);
        p2.setBounds(150,120, 200,30);
        l4.setBounds(95,150, 200,30);
        t2.setBounds(150,150, 200,30);
        l5.setBounds(90,180, 200,30);
        t3.setBounds(150,180, 200,30);
        l6.setBounds(90,210, 200,30);
        t4.setBounds(150,210, 200,30);
        l7.setBounds(75,240, 200,30);
        t5.setBounds(150,240, 200,30);
        b1.setBounds(150,270,200, 40);
        b2.setBounds(150,310,200, 20);
        d.getContentPane().setBackground(new java.awt.Color(204,153,80,80));
        d.setLayout(null);
        d.setResizable(false);
        t1.setText(user);
        p1.setText(password);
        b2.setContentAreaFilled(false);
        b2.setBorderPainted(false);
        l8.setVisible(false);
        d.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                f.setEnabled(true);
            }
        });
        t5.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                cambio();
            }
            public void removeUpdate(DocumentEvent e) {
                cambio();
            }
            public void insertUpdate(DocumentEvent e) {
                cambio();
            }
            public void cambio() {
                l8.setText("Insertar un saldo valido.");
                l8.setBounds(180,315,400, 40);
                try {
                    float temp = Float.parseFloat(t5.getText());
                    l8.setVisible(false);
                    b1.setEnabled(true);
                } catch (NumberFormatException i) {
                    l8.setVisible(true);
                    b1.setEnabled(false);
                }
            }
        });
        t5.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                t5.selectAll();
            }
        });
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (t1.getText().equals("")){
                    l8.setText("Nombre de usuario no valido.");
                    l8.setBounds(170,315,400, 40);
                    l8.setVisible(true);
                } else if (Archivos.buscarArchivo(t1.getText())) {
                    l8.setText("El usuario ya existe.");
                    l8.setBounds(195,315,400, 40);
                    l8.setVisible(true);
                } else if (String.valueOf(p2.getPassword()).equals(String.valueOf(p1.getPassword()))) {
                    Usuario u = new Usuario(t1.getText(),String.valueOf(p1.getPassword()),t2.getText(),t3.getText(),t4.getText(),Float.parseFloat(t5.getText()));
                    Sesion s = new Sesion(t1.getText(), u.contraseña);
                    d.dispose();
                    f.setEnabled(true);
                } else {
                    l8.setText("Las contraseñas no coinciden.");
                    l8.setBounds(165,315,400, 40);
                    l8.setVisible(true);
                }
            }
        });
        b2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                d.dispose();
                f.setEnabled(true);
            }
        });
        d.add(t1);
        d.add(t2);
        d.add(t3);
        d.add(t4);
        d.add(t5);
        d.add(p1);
        d.add(p2);
        d.add(b1);
        d.add(b2);
        d.add(l1);
        d.add(l2);
        d.add(l3);
        d.add(l4);
        d.add(l5);
        d.add(l6);
        d.add(l7);
        d.add(l8);
        d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        d.setVisible(true);
    }
}