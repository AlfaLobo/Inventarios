package interfaz;

import algoritmos.Archivos;
import algoritmos.Interfaces;
import datos.Sesion;
import datos.Usuario;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CrearCuenta {
    GridBagConstraints c = new GridBagConstraints();
    JDialog d;
    JPanel JPanelLogin = new JPanel();
    JLabel JLabelUser = new JLabel("Usuario:");
    JTextField JTextFieldUser = new JTextField();
    JLabel JLabelPassword = new JLabel("Contraseña:");
    JPasswordField JPasswordFieldPassword = new JPasswordField();
    JLabel JLabelConfirmPassword = new JLabel("Confirmar Contraseña:");
    JPasswordField JPasswordFieldConfirmPassword = new JPasswordField();
    JLabel JLabelName = new JLabel("Nombre:");
    JTextField JTextFieldName = new JTextField();
    JLabel JLabelLastName = new JLabel("Apellidos:");
    JTextField JTextFieldLastName = new JTextField();
    JLabel JLabelBalance = new JLabel("Saldo Inicial:");
    JTextField JTextFieldBalance = new JTextField("0");
    JButton JButtonRegister = new JButton("Registrarse");
    JButton JButtonCancel = new JButton("Cancelar");
    JLabel JLabelError = new JLabel();
    public CrearCuenta(JFrame f) {
        f.setEnabled(false);
        d = new JDialog(f);
        d.setSize(400,500);
        d.getContentPane().setBackground(new java.awt.Color(171,213,217));
        d.setResizable(false);
        JPanelLogin.setLayout(new GridBagLayout());
        JPanelLogin.setOpaque(false);
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
                    JLabelError.setVisible(true);
                } else if (Archivos.buscarDirectorio("\\Usuarios\\"+JTextFieldUser.getText())) {
                    JLabelError.setText("El usuario ya existe.");
                    JLabelError.setVisible(true);
                } else if (String.valueOf(JPasswordFieldConfirmPassword.getPassword()).equals(String.valueOf(JPasswordFieldPassword.getPassword()))) {
                    Usuario u = new Usuario(JTextFieldUser.getText(),String.valueOf(JPasswordFieldPassword.getPassword()),JTextFieldName.getText(),JTextFieldLastName.getText(),Float.parseFloat(JTextFieldBalance.getText()));
                    Sesion s = new Sesion(JTextFieldUser.getText(), u.contraseña);
                    f.setEnabled(true);
                    d.dispose();
                } else {
                    JLabelError.setText("Las contraseñas no coinciden.");
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
        Interfaces.addLabel(JPanelLogin, JLabelUser, c, 0, 0);
        Interfaces.addTextField(JPanelLogin, JTextFieldUser, c, 1, 0);
        Interfaces.addLabel(JPanelLogin, JLabelPassword, c, 0, 1);
        Interfaces.addTextField(JPanelLogin, JPasswordFieldPassword, c, 1, 1);
        Interfaces.addLabel(JPanelLogin, JLabelConfirmPassword, c, 0, 2);
        Interfaces.addTextField(JPanelLogin, JPasswordFieldConfirmPassword, c, 1, 2);
        Interfaces.addLabel(JPanelLogin, JLabelName, c, 0, 3);
        Interfaces.addTextField(JPanelLogin, JTextFieldName, c, 1, 3);
        Interfaces.addLabel(JPanelLogin, JLabelLastName, c, 0, 4);
        Interfaces.addTextField(JPanelLogin, JTextFieldLastName, c, 1, 4);
        Interfaces.addLabel(JPanelLogin, JLabelBalance, c, 0, 5);
        Interfaces.addTextField(JPanelLogin, JTextFieldBalance, c, 1, 5);
        Interfaces.addButton(JPanelLogin, JButtonRegister, c, 1, 6);
        Interfaces.addButton(JPanelLogin, JButtonCancel, c, 1, 7);
        Interfaces.addLabel(JPanelLogin, JLabelError, c, 1, 8);
        d.add(JPanelLogin);
        d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        d.setVisible(true);
    }
    public CrearCuenta(JFrame f, String user, String password){
        this(f);
        JTextFieldUser.setText(user);
        JPasswordFieldPassword.setText(password);
    }
}