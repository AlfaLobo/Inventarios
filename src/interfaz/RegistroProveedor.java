package interfaz;

import algoritmos.Archivos;
import datos.Proveedor;
import datos.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistroProveedor {
    JDialog d;
    JTextField JTextFieldName = new JTextField();
    JTextField JTextFieldPhone = new JTextField();
    JTextField JTextFieldEmail = new JTextField();
    JTextField JTextFieldAddress = new JTextField();
    JButton JButtonRegister = new JButton("Registrar");
    JButton JButtonCancel = new JButton("Cancelar");
    JLabel JLabelName = new JLabel("Nombre:");
    JLabel JLabelPhone = new JLabel("Telefono:");
    JLabel JLabelEmail = new JLabel("Correo:");
    JLabel JLabelAddress = new JLabel("Dirección:");
    JLabel JLabelError = new JLabel();
    public RegistroProveedor(JFrame f, Usuario u) {
        d = new JDialog(f);
        f.setEnabled(false);
        d.setSize(400,500);
        JLabelName.setBounds(95,120, 200,30);
        JTextFieldName.setBounds(150,120, 200,30);
        JLabelPhone.setBounds(103,150, 200,30);
        JTextFieldPhone.setBounds(150,150, 200,30);
        JLabelEmail.setBounds(89,180, 200,30);
        JTextFieldEmail.setBounds(150,180, 200,30);
        JLabelAddress.setBounds(55,210, 200,30);
        JTextFieldAddress.setBounds(150,210, 200,30);
        JButtonRegister.setBounds(150,240, 200,30);
        JButtonCancel.setBounds(150,265, 200,30);
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
        JButtonRegister.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (JTextFieldName.getText().equals("")){
                    JLabelError.setText("Nombre no valido.");
                    JLabelError.setBounds(200,275,400, 40);
                    JLabelError.setVisible(true);
                } else if (Archivos.buscarProveedor(u, JTextFieldName.getText())) {
                    JLabelError.setText("El prveedor ya existe.");
                    JLabelError.setBounds(190,275,400, 40);
                    JLabelError.setVisible(true);
                } else {
                    u.proveedores.add(new Proveedor(u, JTextFieldName.getText(), JTextFieldPhone.getText(), JTextFieldEmail.getText(), JTextFieldAddress.getText()));
                    Archivos.guardarArchivo(u,  "\\Usuarios\\"+u.usuario+"\\datos.txt");
                    f.setEnabled(true);
                    d.dispose();
                }
            }
        });
        JButtonCancel.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                f.setEnabled(true);
                d.dispose();
            }
        });
        d.add(JTextFieldName);
        d.add(JTextFieldPhone);
        d.add(JTextFieldEmail);
        d.add(JTextFieldAddress);
        d.add(JButtonRegister);
        d.add(JButtonCancel);
        d.add(JLabelName);
        d.add(JLabelPhone);
        d.add(JLabelEmail);
        d.add(JLabelAddress);
        d.add(JLabelError);
        d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        d.setVisible(true);
    }
}