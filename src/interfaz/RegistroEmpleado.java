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
    JComboBox cb1 = new JComboBox();
    JComboBox cb2 = new JComboBox();
    JComboBox cb3 = new JComboBox();
    JTextField t1 = new JTextField();
    JTextField t2 = new JTextField();
    JTextField t3 = new JTextField("0");
    JTextField t4 = new JTextField();
    JTextField t5 = new JTextField();
    JButton b1 = new JButton("Registrar");
    JButton b2 = new JButton("Cancelar");
    JLabel l1 = new JLabel("Nombre:");
    JLabel l2 = new JLabel("Apellidos:");
    JLabel l3 = new JLabel("Salario:");
    JLabel l4 = new JLabel("Telefono:");
    JLabel l5 = new JLabel("Correo:");
    JLabel l6 = new JLabel("Fecha de Nacimiento:");
    JLabel l7 = new JLabel("Salario no valido");
    public RegistroEmpleado(JFrame f, Usuario u) {
        d = new JDialog(f);
        f.setEnabled(false);
        d.setSize(400,500);
        l1.setBounds(95,120, 200,30);
        t1.setBounds(150,120, 200,30);
        l2.setBounds(103,150, 200,30);
        t2.setBounds(150,150, 200,30);
        l3.setBounds(89,180, 200,30);
        t3.setBounds(150,180, 200,30);
        l4.setBounds(55,210, 200,30);
        t4.setBounds(150,210, 200,30);
        l5.setBounds(67,240, 200,30);
        t5.setBounds(150,240, 200,30);
        l6.setBounds(67,270, 200,30);
        cb1.setBounds(150,270, 100,30);
        cb2.setBounds(250,270, 50,30);
        cb3.setBounds(300,270, 50,30);
        b1.setBounds(150,300, 200,30);
        b2.setBounds(150,325, 200,30);
        l7.setBounds(190,255, 200,30);
        d.setLayout(null);
        d.setResizable(false);
        b2.setContentAreaFilled(false);
        b2.setBorderPainted(false);
        l7.setVisible(false);
        for (int i=1;i<32;i++) {
            cb1.addItem(i);
        }
        for (int i=1;i<13;i++) {
            cb2.addItem(i);
        }
        for (int i=2019;i>1899;i--) {
            cb3.addItem(i);
        }
        d.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                f.setEnabled(true);
            }
        });
        t3.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                t3.selectAll();
            }
        });
        t3.getDocument().addDocumentListener(new DocumentListener() {
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
                    float salario = Float.parseFloat(t3.getText());
                    l7.setVisible(false);
                    b1.setEnabled(true);
                } catch (NumberFormatException i) {
                    l7.setVisible(true);
                    b1.setEnabled(false);
                }
            }
        });
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                u.empleados.add(new Empleado(u, t1.getText(), t2.getText(), Float.parseFloat(t3.getText()), t4.getText(), t5.getText(), new GregorianCalendar((int) cb3.getItemAt(cb3.getSelectedIndex()), (int) cb2.getItemAt(cb2.getSelectedIndex()), (int) cb1.getItemAt(cb1.getSelectedIndex()))));
                Archivos.guardarArchivo(u,  "\\Usuarios\\"+u.usuario+"\\datos.txt");
                d.dispose();
                f.setEnabled(true);
            }
        });
        b2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                d.dispose();
                f.setEnabled(true);
            }
        });
        d.add(cb1);
        d.add(cb2);
        d.add(cb3);
        d.add(t1);
        d.add(t2);
        d.add(t3);
        d.add(t4);
        d.add(t5);
        d.add(b1);
        d.add(b2);
        d.add(l1);
        d.add(l2);
        d.add(l3);
        d.add(l4);
        d.add(l5);
        d.add(l6);
        d.add(l7);
        d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        d.setVisible(true);
    }
}