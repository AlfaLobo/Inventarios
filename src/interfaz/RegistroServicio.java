package interfaz;

import algoritmos.Archivos;
import datos.Servicio;
import datos.Usuario;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistroServicio {
    JDialog d;
    JTextField JTextFieldName = new JTextField();
    JTextField JTextFieldPrice = new JTextField("0");
    JButton JButtonRegister = new JButton("Registrar");
    JButton JButtonCancel = new JButton("Cancelar");
    JLabel JLabelName = new JLabel("Nombre:");
    JLabel JLabelPrice = new JLabel("Precio:");
    JLabel JLabelError = new JLabel("Precio no valido.");
    public RegistroServicio(JFrame f, Usuario u) {
        d = new JDialog(f);
        f.setEnabled(false);
        d.setSize(400,500);
        JLabelName.setBounds(95,120, 200,30);
        JTextFieldName.setBounds(150,120, 200,30);
        JLabelPrice.setBounds(89,150, 200,30);
        JTextFieldPrice.setBounds(150,150, 200,30);
        JButtonRegister.setBounds(150,180, 200,30);
        JButtonCancel.setBounds(150,205, 200,30);
        JLabelError.setBounds(150,225, 200,30);
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
        JTextFieldPrice.getDocument().addDocumentListener(new DocumentListener() {
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
                    float costo = Float.parseFloat(JTextFieldPrice.getText());
                    float precio = Float.parseFloat(JLabelError.getText());
                    JLabelError.setVisible(false);
                    JButtonRegister.setEnabled(true);
                    if (costo<0||precio<0) {
                        JLabelError.setVisible(true);
                        JButtonRegister.setEnabled(false);
                    } else  {
                        JLabelError.setVisible(false);
                        JButtonRegister.setEnabled(true);
                    }
                } catch (NumberFormatException i) {
                    JLabelError.setVisible(true);
                    JButtonRegister.setEnabled(false);
                }
            }
        });
        JButtonRegister.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                u.servicios.add(new Servicio(u, JTextFieldName.getText(), Float.parseFloat(JTextFieldPrice.getText())));
                Archivos.guardarArchivo(u,  "\\Usuarios\\"+u.usuario+"\\datos.txt");
                f.setEnabled(true);
                d.dispose();
            }
        });
        JButtonCancel.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                f.setEnabled(true);
                d.dispose();
            }
        });
        d.add(JTextFieldName);
        d.add(JTextFieldPrice);
        d.add(JButtonRegister);
        d.add(JButtonCancel);
        d.add(JLabelName);
        d.add(JLabelPrice);
        d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        d.setVisible(true);
    }
}