package interfaz;

import algoritmos.Archivos;
import datos.Empleado;
import datos.Usuario;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.GregorianCalendar;

public class RegistroEmpleado {
    JDialog d;
    JComboBox JComboBoxDay = new JComboBox();
    JComboBox JComboBoxMonth = new JComboBox();
    JComboBox JComboBoxYear = new JComboBox();
    JTextField JTextFieldName = new JTextField();
    JTextField JTextFieldLastName = new JTextField();
    JTextField JTextFieldSalary = new JTextField("0");
    JTextField JTextFieldPhone = new JTextField();
    JTextField JTextFieldEmail = new JTextField();
    JButton JButtonRegister = new JButton("Registrar");
    JButton JButtonCancel = new JButton("Cancelar");
    JLabel JLabelName = new JLabel("Nombre:");
    JLabel JLabelLastName = new JLabel("Apellidos:");
    JLabel JLabelSalary = new JLabel("Salario:");
    JLabel JLabelPhone = new JLabel("Telefono:");
    JLabel JLabelEmail = new JLabel("Correo:");
    JLabel JLabelBirthday = new JLabel("Fecha de Nacimiento:");
    JLabel JLabelError = new JLabel("Salario no valido");
    public RegistroEmpleado(JFrame f, Usuario u) {
        d = new JDialog(f);
        f.setEnabled(false);
        d.setSize(400,500);
        JLabelName.setBounds(95,120, 200,30);
        JTextFieldName.setBounds(150,120, 200,30);
        JLabelLastName.setBounds(103,150, 200,30);
        JTextFieldLastName.setBounds(150,150, 200,30);
        JLabelSalary.setBounds(89,180, 200,30);
        JTextFieldSalary.setBounds(150,180, 200,30);
        JLabelPhone.setBounds(55,210, 200,30);
        JTextFieldPhone.setBounds(150,210, 200,30);
        JLabelEmail.setBounds(67,240, 200,30);
        JTextFieldEmail.setBounds(150,240, 200,30);
        JLabelBirthday.setBounds(67,270, 200,30);
        JComboBoxDay.setBounds(150,270, 100,30);
        JComboBoxMonth.setBounds(250,270, 50,30);
        JComboBoxYear.setBounds(300,270, 50,30);
        JButtonRegister.setBounds(150,300, 200,30);
        JButtonCancel.setBounds(150,325, 200,30);
        JLabelError.setBounds(190,255, 200,30);
        d.setLayout(null);
        d.setResizable(false);
        JButtonCancel.setContentAreaFilled(false);
        JButtonCancel.setBorderPainted(false);
        JLabelError.setVisible(false);
        for (int i=1;i<32;i++) {
            JComboBoxDay.addItem(i);
        }
        for (int i=1;i<13;i++) {
            JComboBoxMonth.addItem(i);
        }
        for (int i=2019;i>1899;i--) {
            JComboBoxYear.addItem(i);
        }
        d.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                f.setEnabled(true);
            }
        });
        JTextFieldSalary.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                JTextFieldSalary.selectAll();
            }
        });
        JTextFieldSalary.getDocument().addDocumentListener(new DocumentListener() {
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
                    float salario = Float.parseFloat(JTextFieldSalary.getText());
                    JLabelError.setVisible(false);
                    JButtonRegister.setEnabled(true);
                } catch (NumberFormatException i) {
                    JLabelError.setVisible(true);
                    JButtonRegister.setEnabled(false);
                }
            }
        });
        JButtonRegister.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                u.empleados.add(new Empleado(u, JTextFieldName.getText(), JTextFieldLastName.getText(), Float.parseFloat(JTextFieldPhone.getText()), JTextFieldPhone.getText(), JTextFieldEmail.getText(), new GregorianCalendar((int) JComboBoxYear.getItemAt(JComboBoxYear.getSelectedIndex()), (int) JComboBoxMonth.getItemAt(JComboBoxMonth.getSelectedIndex()), (int) JComboBoxDay.getItemAt(JComboBoxDay.getSelectedIndex()))));
                Archivos.guardarArchivo(u,  "\\Usuarios\\"+u.usuario+"\\datos.txt");
                d.dispose();
                f.setEnabled(true);
            }
        });
        JButtonCancel.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                d.dispose();
                f.setEnabled(true);
            }
        });
        d.add(JComboBoxDay);
        d.add(JComboBoxMonth);
        d.add(JComboBoxYear);
        d.add(JTextFieldName);
        d.add(JTextFieldLastName);
        d.add(JTextFieldSalary);
        d.add(JTextFieldPhone);
        d.add(JTextFieldEmail);
        d.add(JButtonRegister);
        d.add(JButtonCancel);
        d.add(JLabelName);
        d.add(JLabelLastName);
        d.add(JLabelSalary);
        d.add(JLabelPhone);
        d.add(JLabelEmail);
        d.add(JLabelBirthday);
        d.add(JLabelError);
        d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        d.setVisible(true);
    }
}