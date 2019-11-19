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
    JTextField JTextFieldUser = new JTextField();
    JTextField JTextFieldName = new JTextField();
    JTextField JTextFieldLastName = new JTextField();
    JTextField JTextFieldBalance = new JTextField("0");
    JPasswordField JPasswordFieldPassword = new JPasswordField();
    JPasswordField JPasswordFieldConfirmPassword = new JPasswordField();
    JButton JButtonRegister = new JButton("Registrarse");
    JButton JButtonCancel = new JButton("Cancelar");
    JLabel JLabelUser = new JLabel("Usuario:");
    JLabel JLabelPassword = new JLabel("Contraseña:");
    JLabel JLabelConfirmPassword = new JLabel("Confirmar Contraseña:");
    JLabel JLabelName = new JLabel("Nombre:");
    JLabel JLabelLastName = new JLabel("Apellidos:");
    JLabel JLabelBalance = new JLabel("Saldo Inicial:");
    JLabel JLabelError = new JLabel();

    public CrearCuenta(JFrame f) {
        f.setEnabled(false);
        d = new JDialog(f);
        d.setSize(400,500);
        JLabelUser.setBounds(95,60, 200,30);
        JTextFieldUser.setBounds(150,60, 200,30);
        JLabelPassword.setBounds(75,90, 200,30);
        JPasswordFieldPassword.setBounds(150,90, 200,30);
        JLabelConfirmPassword.setBounds(15,120, 200,30);
        JPasswordFieldConfirmPassword.setBounds(150,120, 200,30);
        JLabelName.setBounds(95,150, 200,30);
        JTextFieldName.setBounds(150,150, 200,30);
        JLabelLastName.setBounds(90,180, 200,30);
        JTextFieldLastName.setBounds(150,180, 200,30);
        JLabelBalance.setBounds(90,210, 200,30);
        JTextFieldBalance.setBounds(150,210, 200,30);
        JButtonRegister.setBounds(150,240,200, 40);
        JButtonCancel.setBounds(150,280,200, 20);
        d.getContentPane().setBackground(new java.awt.Color(204,153,80,80));
        d.setLayout(null);
        d.setResizable(false);
        JButtonCancel.setContentAreaFilled(false);
        JButtonCancel.setBorderPainted(false);
        JLabelError.setVisible(false);
        d.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                f.setEnabled(true);
            }
        });
        JTextFieldBalance.getDocument().addDocumentListener(new DocumentListener() {
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
                JLabelError.setText("Insertar un saldo valido.");
                JLabelError.setBounds(180,315,400, 40);
                try {
                    float temp = Float.parseFloat(JTextFieldBalance.getText());
                    JLabelError.setVisible(false);
                    JButtonRegister.setEnabled(true);
                } catch (NumberFormatException i) {
                    JLabelError.setVisible(true);
                    JButtonRegister.setEnabled(false);
                }
            }
        });
        JTextFieldBalance.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                JTextFieldBalance.selectAll();
            }
        });
        JButtonRegister.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (JTextFieldUser.getText().equals("")) {
                    JLabelError.setText("Nombre de usuario no valido.");
                    JLabelError.setBounds(170,315,400, 40);
                    JLabelError.setVisible(true);
                } else if (Archivos.buscarDirectorio("\\Usuarios\\"+JTextFieldUser.getText())) {
                    JLabelError.setText("El usuario ya existe.");
                    JLabelError.setBounds(195,315,400, 40);
                    JLabelError.setVisible(true);
                } else if (String.valueOf(JPasswordFieldConfirmPassword.getPassword()).equals(String.valueOf(JPasswordFieldPassword.getPassword()))) {
                    Usuario u = new Usuario(JTextFieldUser.getText(),String.valueOf(JPasswordFieldPassword.getPassword()),JTextFieldName.getText(),JTextFieldLastName.getText(),Float.parseFloat(JTextFieldBalance.getText()));
                    Sesion s = new Sesion(JTextFieldUser.getText(), u.contraseña);
                    f.setEnabled(true);
                    d.dispose();
                } else {
                    JLabelError.setText("Las contraseñas no coinciden.");
                    JLabelError.setBounds(165,315,400, 40);
                    JLabelError.setVisible(true);
                }
            }
        });
        JButtonCancel.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                f.setEnabled(true);
                d.dispose();
            }
        });
        d.add(JTextFieldUser);
        d.add(JTextFieldName);
        d.add(JTextFieldLastName);
        d.add(JTextFieldBalance);
        d.add(JPasswordFieldPassword);
        d.add(JPasswordFieldConfirmPassword);
        d.add(JButtonRegister);
        d.add(JButtonCancel);
        d.add(JLabelUser);
        d.add(JLabelPassword);
        d.add(JLabelConfirmPassword);
        d.add(JLabelName);
        d.add(JLabelLastName);
        d.add(JLabelBalance);
        d.add(JLabelError);
        d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        d.setVisible(true);
    }
    public CrearCuenta(JFrame f, String user, String password){
        this(f);
        JTextFieldUser.setText(user);
        JPasswordFieldPassword.setText(password);
    }
}