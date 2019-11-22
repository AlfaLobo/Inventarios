package interfaz;

import algoritmos.Archivos;
import algoritmos.Interfaces;
import datos.Sesion;
import datos.Usuario;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
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
        d.setSize(375,400);
        d.getRootPane().setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
        d.getRootPane().setBackground(new java.awt.Color(171,213,217));
        d.getContentPane().setBackground(new java.awt.Color(171,213,217));
        d.setResizable(false);
        JPanelLogin.setLayout(new BoxLayout(JPanelLogin, BoxLayout.Y_AXIS));
        JPanelLogin.setOpaque(false);
        Border border = BorderFactory.createLoweredBevelBorder();
        TitledBorder title = BorderFactory.createTitledBorder(border, "Menú Proveedores");
        title.setTitlePosition(TitledBorder.ABOVE_TOP);
        JPanelLogin.setBorder(title);
        Dimension d1 = new Dimension(180, 30);
        JButtonRegister.setPreferredSize(d1);
        JButtonRegister.setMaximumSize(d1);
        Dimension d2 = new Dimension(180, 20);
        JButtonCancel.setPreferredSize(d2);
        JButtonCancel.setMaximumSize(d2);
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
        JPanelLogin.add(JLabelUser);
        JPanelLogin.add(JTextFieldUser);
        JPanelLogin.add(JLabelPassword);
        JPanelLogin.add(JPasswordFieldPassword);
        JPanelLogin.add(JLabelConfirmPassword);
        JPanelLogin.add(JPasswordFieldConfirmPassword);
        JPanelLogin.add(JLabelName);
        JPanelLogin.add(JTextFieldName);
        JPanelLogin.add(JLabelLastName);
        JPanelLogin.add(JTextFieldLastName);
        JPanelLogin.add(JLabelBalance);
        JPanelLogin.add(JTextFieldBalance);
        JPanelLogin.add(JButtonRegister);
        JPanelLogin.add(JButtonCancel);
        JPanelLogin.add(JLabelError);
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