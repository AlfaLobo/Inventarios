package interfaz;

import algoritmos.Archivos;
import algoritmos.Interfaces;
import datos.Usuario;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ModificarUsuario {
    GridBagConstraints c = new GridBagConstraints();
    JDialog d;
    JPanel JPanelModify = new JPanel();
    JLabel JLabel1 = new JLabel();
    JLabel JLabel2 = new JLabel("Contraseña:");
    JLabel JLabel3 = new JLabel("Confirmar Contraseña:");
    JLabel JLabel4 = new JLabel("Nombre:");
    JLabel JLabel5 = new JLabel("Apellidos:");
    JLabel JLabel6 = new JLabel("Negocio:");
    JLabel JLabel7 = new JLabel("Inversión:");
    JLabel JLabel8 = new JLabel("Ganancia:");
    JLabel JLabel9 = new JLabel("Saldo:");
    JPasswordField JPasswordField1 = new JPasswordField();
    JPasswordField JPasswordField2 = new JPasswordField();
    JTextField JTextField1 = new JTextField();
    JTextField JTextField2 = new JTextField();
    JTextField JTextField3 = new JTextField();
    JTextField JTextField4 = new JTextField();
    JTextField JTextField5 = new JTextField();
    JTextField JTextField6 = new JTextField();
    JButton JButtonEdit = new JButton("Guardar Cambios");

    public ModificarUsuario(JFrame f, Usuario u){
        d = new JDialog(f);
        d.setSize(375,550);
        d.setLocationRelativeTo(f);
        d.getRootPane().setBackground(new java.awt.Color(171,213,217));
        d.getContentPane().setBackground(new java.awt.Color(171,213,217));
        d.getRootPane().setBorder(BorderFactory.createEmptyBorder(35, 90, 45, 90));
        d.setResizable(false);
        JPanelModify.setLayout(new GridBagLayout());
        JPanelModify.setOpaque(false);
        d.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                f.setEnabled(true);
            }
        });
        JTextField1.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                JTextField1.selectAll();
            }
        });
        JTextField2.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                JTextField2.selectAll();
            }
        });
        JTextField3.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                JTextField3.selectAll();
            }
        });
        JTextField4.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                JTextField4.selectAll();
            }
        });
        JTextField5.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                JTextField5.selectAll();
            }
        });
        JTextField6.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                JTextField6.selectAll();
            }
        });
        JLabel1.setText("Usuario: "+u.usuario);
        JPasswordField1.setText(u.contraseña);
        JTextField1.setText(u.nombre);
        JTextField2.setText(u.apellidos);
        JTextField3.setText(u.negocio);
        JTextField4.setText(String.valueOf(u.inversion));
        JTextField5.setText(String.valueOf(u.ganancia));
        JTextField JTextField6 = new JTextField(String.valueOf(u.saldo));
        JTextField4.getDocument().addDocumentListener(new DocumentListener() {
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
                try {
                    float investment = Float.parseFloat(JTextField4.getText());
                    float profit = Float.parseFloat(JTextField5.getText());
                    float balance = Float.parseFloat(JTextField6.getText());
                    JButtonEdit.setEnabled(true);
                } catch (NumberFormatException i) {
                    JButtonEdit.setEnabled(false);
                }
            }
        });
        JTextField5.getDocument().addDocumentListener(new DocumentListener() {
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
                try {
                    float investment = Float.parseFloat(JTextField4.getText());
                    float profit = Float.parseFloat(JTextField5.getText());
                    float balance = Float.parseFloat(JTextField6.getText());
                    JButtonEdit.setEnabled(true);
                } catch (NumberFormatException i) {
                    JButtonEdit.setEnabled(false);
                }
            }
        });
        JTextField6.getDocument().addDocumentListener(new DocumentListener() {
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
                try {
                    float investment = Float.parseFloat(JTextField4.getText());
                    float profit = Float.parseFloat(JTextField5.getText());
                    float balance = Float.parseFloat(JTextField6.getText());
                    JButtonEdit.setEnabled(true);
                } catch (NumberFormatException i) {
                    JButtonEdit.setEnabled(false);
                }
            }
        });
        JButtonEdit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(String.valueOf(JPasswordField1.getPassword()).equals(String.valueOf(JPasswordField2.getPassword()))){
                    u.contraseña=String.valueOf(JPasswordField1.getPassword());
                    u.nombre=JTextField1.getText();
                    u.apellidos=JTextField2.getText();
                    u.negocio=JTextField3.getText();
                    u.inversion=Float.parseFloat(JTextField4.getText());
                    u.ganancia=Float.parseFloat(JTextField5.getText());
                    u.saldo=Float.parseFloat(JTextField6.getText());
                    Archivos.guardarArchivo(u);
                    JOptionPane.showMessageDialog(d, "Cambios guardados.");
                    d.dispose();
                    f.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(d, "Las contraseñas no coinciden.");
                }
            }
        });
        c.weightx = 0.1;
        c.weighty = 0.1;
        c.anchor = GridBagConstraints.LINE_START;
        Interfaces.addLabel(JPanelModify, JLabel1, c, 0, 0);
        Interfaces.addLabel(JPanelModify, JLabel2, c, 0, 1);
        Interfaces.addTextField(JPanelModify, JPasswordField1, c, 0, 2);
        Interfaces.addLabel(JPanelModify, JLabel3, c, 0, 3);
        Interfaces.addTextField(JPanelModify, JPasswordField2, c, 0, 4);
        Interfaces.addLabel(JPanelModify, JLabel4, c, 0, 5);
        Interfaces.addTextField(JPanelModify, JTextField1, c, 0, 6);
        Interfaces.addLabel(JPanelModify, JLabel5, c, 0, 7);
        Interfaces.addTextField(JPanelModify, JTextField2, c, 0, 8);
        Interfaces.addLabel(JPanelModify, JLabel6, c, 0, 9);
        Interfaces.addTextField(JPanelModify, JTextField3, c, 0, 10);
        Interfaces.addLabel(JPanelModify, JLabel7, c, 0, 11);
        Interfaces.addTextField(JPanelModify, JTextField4, c, 0, 12);
        Interfaces.addLabel(JPanelModify, JLabel8, c, 0, 13);
        Interfaces.addTextField(JPanelModify, JTextField5, c, 0, 14);
        Interfaces.addLabel(JPanelModify, JLabel9, c, 0, 15);
        Interfaces.addTextField(JPanelModify, JTextField6, c, 0, 16);
        c.anchor = GridBagConstraints.CENTER;
        Interfaces.addButton(JPanelModify, JButtonEdit, c, 0, 17);
        d.add(JPanelModify);
        d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        d.setVisible(true);
    }
}
