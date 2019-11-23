package interfaz;

import algoritmos.Archivos;
import algoritmos.Interfaces;
import datos.Empleado;
import datos.Usuario;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Empleados {
    GridBagConstraints c = new GridBagConstraints();
    JDialog d;
    JPanel JPanelEmployees = new JPanel();
    JPanel JPanelAddEmployee = new JPanel();
    JLabel JLabelName = new JLabel("Nombre:");
    JTextField JTextFieldName = new JTextField();
    JLabel JLabelLastName = new JLabel("Apellidos:");
    JTextField JTextFieldLastName = new JTextField();
    JLabel JLabelPhone = new JLabel("Telefono:");
    JLabel JLabelSalary = new JLabel("Salario:");
    JTextField JTextFieldSalary = new JTextField("0");
    JTextField JTextFieldPhone = new JTextField();
    JLabel JLabelEmail = new JLabel("Correo:");
    JTextField JTextFieldEmail = new JTextField();
    JLabel JLabelBirthday = new JLabel("Fecha de Nacimiento:");
    JComboBox JComboBoxDay = new JComboBox();
    JComboBox JComboBoxMonth = new JComboBox();
    JComboBox JComboBoxYear = new JComboBox();
    JButton JButtonRegister = new JButton("Registrar");
    JButton JButtonReturn = new JButton();

    public Empleados(JFrame f, Usuario u){
        d = new JDialog(f);
        d.setSize(850,600);
        d.setLocationRelativeTo(f);
        f.setVisible(false);
        d.getRootPane().setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
        d.getRootPane().setBackground(new java.awt.Color(171,213,217));
        d.getContentPane().setBackground(new java.awt.Color(171,213,217));
        d.setResizable(false);
        JPanelEmployees.setLayout(new GridBagLayout());
        JPanelEmployees.setOpaque(false);
        Border border = BorderFactory.createLoweredBevelBorder();
        TitledBorder title = BorderFactory.createTitledBorder(border, "Menú Empleados");
        title.setTitlePosition(TitledBorder.ABOVE_TOP);
        JPanelEmployees.setBorder(title);
        JPanelAddEmployee.setLayout(new BoxLayout(JPanelAddEmployee, BoxLayout.Y_AXIS));
        DefaultTableModel model = new DefaultTableModel();
        JTable tb = new JTable(model);
        tb.setRowHeight(38);
        model.addColumn("Nombre");
        model.addColumn("Apellidos");
        model.addColumn("Salario");
        model.addColumn("Telefono");
        model.addColumn("Correo");
        model.addColumn("Nacimiento");
        model.addColumn("Total Invertido");
        for (int i = 0;i<u.empleados.size();i++){
            String fecha = "";
            try {
                fecha = u.empleados.get(i).nacimiento.get(Calendar.DAY_OF_MONTH)+"/"+u.empleados.get(i).nacimiento.get(Calendar.MONTH)+"/"+u.empleados.get(i).nacimiento.get(Calendar.YEAR);
            } catch (NullPointerException e) {

            }
            model.addRow(new Object[]{u.empleados.get(i).nombre, u.empleados.get(i).apellidos, u.empleados.get(i).salario, u.empleados.get(i).telefono, u.empleados.get(i).correo, fecha, u.empleados.get(i).inversion});
        }
        JScrollPane sp = new JScrollPane(tb);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
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
                f.dispose();
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
                    JButtonRegister.setEnabled(true);
                    if (salario>0) {
                        JButtonRegister.setEnabled(true);
                    } else  {
                        JButtonRegister.setEnabled(false);
                    }
                } catch (NumberFormatException i) {
                    JButtonRegister.setEnabled(false);
                }
            }
        });
        JTextFieldSalary.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                JTextFieldSalary.selectAll();
            }
        });
        JButtonRegister.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (JTextFieldName.getText().equals("")){
                    JOptionPane.showMessageDialog(d, "Nombre no valido.");
                } else {
                    u.empleados.add(new Empleado(u, JTextFieldName.getText(), JTextFieldLastName.getText(), Float.parseFloat(JTextFieldSalary.getText()), JTextFieldPhone.getText(), JTextFieldEmail.getText(), new GregorianCalendar((int) JComboBoxYear.getItemAt(JComboBoxYear.getSelectedIndex()), (int) JComboBoxMonth.getItemAt(JComboBoxMonth.getSelectedIndex()), (int) JComboBoxDay.getItemAt(JComboBoxDay.getSelectedIndex()))));
                    Archivos.guardarArchivo(u,  "\\Usuarios\\"+u.usuario+"\\datos.txt");
                    JOptionPane.showMessageDialog(d, "El empleado ha sido añadido.");
                    JTextFieldName.setText("");
                    JTextFieldLastName.setText("");
                    JTextFieldPhone.setText("");
                    JTextFieldEmail.setText("");
                    JComboBoxDay.setSelectedIndex(0);
                    JComboBoxMonth.setSelectedIndex(0);
                    JComboBoxYear.setSelectedIndex(0);
                    model.addRow(new Object[]{u.empleados.get(u.empleados.size()-1).nombre, u.empleados.get(u.empleados.size()-1).apellidos, u.empleados.get(u.empleados.size()-1).salario, u.empleados.get(u.empleados.size()-1).telefono, u.empleados.get(u.empleados.size()-1).correo, u.empleados.get(u.empleados.size()-1).nacimiento.get(Calendar.DAY_OF_MONTH)+"/"+u.empleados.get(u.empleados.size()-1).nacimiento.get(Calendar.MONTH)+"/"+u.empleados.get(u.empleados.size()-1).nacimiento.get(Calendar.YEAR), u.empleados.get(u.empleados.size()-1).inversion});
                    sp.repaint();
                }
            }
        });
        JButtonReturn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                f.setVisible(true);
                f.setLocationRelativeTo(d);
                d.dispose();
            }
        });
        JPanelAddEmployee.add(JLabelName);
        JPanelAddEmployee.add(JTextFieldName);
        JPanelAddEmployee.add(JLabelLastName);
        JPanelAddEmployee.add(JTextFieldLastName);
        JPanelAddEmployee.add(JLabelPhone);
        JPanelAddEmployee.add(JTextFieldPhone);
        JPanelAddEmployee.add(JLabelSalary);
        JPanelAddEmployee.add(JTextFieldSalary);
        JPanelAddEmployee.add(JLabelEmail);
        JPanelAddEmployee.add(JTextFieldEmail);
        JPanelAddEmployee.add(JLabelBirthday);
        JPanelAddEmployee.add(JComboBoxDay);
        JPanelAddEmployee.add(JComboBoxMonth);
        JPanelAddEmployee.add(JComboBoxYear);
        JPanelAddEmployee.add(JButtonRegister);
        JPanelAddEmployee.setPreferredSize(new Dimension(150, 300));
        c.weightx = 0.5;
        Interfaces.addPanel(JPanelEmployees, JPanelAddEmployee, c, 0, 0);
        Interfaces.addScrollPane(JPanelEmployees, sp, c, 1, 0);
        c.anchor = GridBagConstraints.LAST_LINE_START;
        Interfaces.addImageButton(JPanelEmployees, JButtonReturn, c, 0, 0, "arrow-34.png");
        d.add(JPanelEmployees);
        d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        d.setVisible(true);
    }
}