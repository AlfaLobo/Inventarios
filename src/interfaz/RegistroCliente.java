package interfaz;

import algoritmos.Archivos;
import datos.Cliente;
import datos.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

public class RegistroCliente {
    JDialog d;
    JComboBox JComboBoxDay = new JComboBox();
    JComboBox JComboBoxMonth = new JComboBox();
    JComboBox JComboBoxYear = new JComboBox();
    JTextField JTextFieldName = new JTextField();
    JTextField JTextFieldLastName = new JTextField();
    JTextField JTextFieldPhone = new JTextField();
    JTextField JTextFieldEmail = new JTextField();
    JButton JButtonRegister = new JButton("Registrar");
    JButton JButtonCancel = new JButton("Cancelar");
    JLabel JLabelName = new JLabel("Nombre:");
    JLabel JLabelLastName = new JLabel("Apellidos:");
    JLabel JLabelPhone = new JLabel("Telefono:");
    JLabel JLabelEmail = new JLabel("Correo:");
    JLabel JLabelBirthday = new JLabel("Fecha de Nacimiento:");
    JLabel JLabelError = new JLabel();
    public RegistroCliente(JFrame f, Usuario u){
        d = new JDialog(f);
        f.setEnabled(false);
        d.setSize(400,500);
        JTextFieldName.setBounds(150,120, 200,30);
        JTextFieldLastName.setBounds(150,150, 200,30);
        JTextFieldPhone.setBounds(150,180, 200,30);
        JTextFieldEmail.setBounds(150,210, 200,30);
        JComboBoxDay.setBounds(150,240, 100,30);
        JComboBoxMonth.setBounds(250,240, 50,30);
        JComboBoxYear.setBounds(300,240, 50,30);
        JButtonRegister.setBounds(150,270, 200,30);
        JButtonCancel.setBounds(150,295, 200,30);
        JLabelName.setBounds(95,120, 200,30);
        JLabelLastName.setBounds(103,150, 200,30);
        JLabelPhone.setBounds(89,180, 200,30);
        JLabelEmail.setBounds(55,210, 200,30);
        JLabelBirthday.setBounds(55,240, 200,30);
        d.setLayout(null);
        d.setResizable(false);
        JButtonCancel.setContentAreaFilled(false);
        JButtonCancel.setBorderPainted(false);
        d.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                f.setEnabled(true);
            }
        });
        for (int i=1;i<32;i++) {
            JComboBoxDay.addItem(i);
        }
        for (int i=1;i<13;i++) {
            JComboBoxMonth.addItem(i);
        }
        for (int i=2019;i>1899;i--) {
            JComboBoxYear.addItem(i);
        }
        JButtonRegister.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                u.clientes.add(new Cliente(u, JTextFieldName.getText(), JTextFieldLastName.getText(), JTextFieldPhone.getText(), JTextFieldEmail.getText(), new GregorianCalendar((int) JComboBoxYear.getItemAt(JComboBoxYear.getSelectedIndex()), (int) JComboBoxMonth.getItemAt(JComboBoxMonth.getSelectedIndex()), (int) JComboBoxDay.getItemAt(JComboBoxDay.getSelectedIndex()))));
                Archivos.guardarArchivo(u,  "\\Usuarios\\"+u.usuario+"\\datos.txt");
                f.setEnabled(true);
                d.dispose();
            }
        });
        JButtonCancel.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                f.dispose();
                f.setEnabled(true);
            }
        });
        d.add(JComboBoxDay);
        d.add(JComboBoxMonth);
        d.add(JComboBoxYear);
        d.add(JTextFieldName);
        d.add(JTextFieldLastName);
        d.add(JTextFieldPhone);
        d.add(JTextFieldEmail);
        d.add(JButtonRegister);
        d.add(JButtonCancel);
        d.add(JLabelName);
        d.add(JLabelLastName);
        d.add(JLabelPhone);
        d.add(JLabelEmail);
        d.add(JLabelBirthday);
        d.add(JLabelError);
        d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        d.setVisible(true);
    }
}