package interfaz;

import algoritmos.Archivos;
import algoritmos.Interfaces;
import algoritmos.Ordenamiento;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Empleados {
    String notes = "";
    GridBagConstraints c = new GridBagConstraints();
    JDialog d;
    JPanel JPanelEmployees = new JPanel();
    JPanel JPanelAddEmployeeBorder = new JPanel();
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
    JButton JButtonNotes = new JButton("Notas");
    JButton JButtonRegister = new JButton("Registrar");
    JButton JButtonReturn = new JButton();

    public Empleados(JFrame f, Usuario u){
        d = new JDialog(f);
        d.setSize(850,600);
        d.setLocationRelativeTo(f);
        f.setEnabled(false);
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
        JPanelAddEmployee.setLayout(new GridBagLayout());
        JPanelAddEmployee.setOpaque(false);
        TitledBorder title1 = BorderFactory.createTitledBorder(border, "Añadir Empleado");
        title.setTitlePosition(TitledBorder.ABOVE_TOP);
        JPanelAddEmployeeBorder.setBorder(title1);
        JPanelAddEmployeeBorder.add(JPanelAddEmployee);
        DefaultTableModel model = new DefaultTableModel();
        JTable tb = new JTable(model){
            public boolean isCellEditable(int row, int column) {
                return false;
            };
        };
        tb.setRowHeight(38);
        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("Apellidos");
        model.addColumn("Teléfono");
        model.addColumn("Correo");
        model.addColumn("Nacimiento");
        model.addColumn("Salario");
        for (int i = 0;i<u.empleados.size();i++){
            String fecha = "";
            try {
                fecha = u.empleados.get(i).nacimiento.get(Calendar.DAY_OF_MONTH)+"/"+u.empleados.get(i).nacimiento.get(Calendar.MONTH)+"/"+u.empleados.get(i).nacimiento.get(Calendar.YEAR);
            } catch (NullPointerException e) {

            }
            model.addRow(new Object[]{u.empleados.get(i).id, u.empleados.get(i).nombre, u.empleados.get(i).apellidos, u.empleados.get(i).telefono, u.empleados.get(i).correo, fecha, u.empleados.get(i).salario});
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
                f.setEnabled(true);
            }
        });
        tb.getTableHeader().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (tb.getColumnName(tb.columnAtPoint(e.getPoint())).equals("Nacimiento")){
                    ArrayList<GregorianCalendar> arr = new ArrayList<>();
                    for(int i=0;i<tb.getColumnCount();i++){
                        if(tb.getColumnName(i).equals("ID")){
                            for (int j=0;j<u.empleados.size();j++){
                                arr.add(u.empleados.get((Integer) tb.getValueAt(j, i)).nacimiento);
                            }
                            Ordenamiento.ordenarFechas(tb, model, arr);
                        }
                    }
                } else {
                    Ordenamiento.ordenarTabla(tb, model, tb.columnAtPoint(e.getPoint()));
                }
            }
        });
        tb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                for(int i=0;i<tb.getColumnCount();i++){
                    if(tb.getColumnName(i).equals("ID")){
                        new Informacion(d, u, u.empleados.get((Integer) model.getValueAt(tb.rowAtPoint(evt.getPoint()), i)), model, tb);
                    }
                }
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
        JButtonNotes.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                UIManager.put("OptionPane.cancelButtonText", "Cancelar");
                notes = (String) JOptionPane.showInputDialog(d, "Nota:", "Notas", JOptionPane.PLAIN_MESSAGE, null, null, null);
            }
        });
        JButtonRegister.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (JTextFieldName.getText().equals("")){
                    JOptionPane.showMessageDialog(d, "Nombre no valido.");
                } else {
                    u.empleados.add(new Empleado(u, JTextFieldName.getText(), JTextFieldLastName.getText(), Float.parseFloat(JTextFieldSalary.getText()), JTextFieldPhone.getText(), JTextFieldEmail.getText(), new GregorianCalendar((int) JComboBoxYear.getItemAt(JComboBoxYear.getSelectedIndex()), (int) JComboBoxMonth.getItemAt(JComboBoxMonth.getSelectedIndex()), (int) JComboBoxDay.getItemAt(JComboBoxDay.getSelectedIndex())), notes));
                    Archivos.guardarArchivo(u);
                    JOptionPane.showMessageDialog(d, "El empleado ha sido añadido.");
                    JTextFieldName.setText("");
                    JTextFieldLastName.setText("");
                    JTextFieldPhone.setText("");
                    JTextFieldEmail.setText("");
                    JTextFieldSalary.setText("0");
                    JComboBoxDay.setSelectedIndex(0);
                    JComboBoxMonth.setSelectedIndex(0);
                    JComboBoxYear.setSelectedIndex(0);
                    model.addRow(new Object[]{u.empleados.get(u.empleados.size()-1).id, u.empleados.get(u.empleados.size()-1).nombre, u.empleados.get(u.empleados.size()-1).apellidos, u.empleados.get(u.empleados.size()-1).telefono, u.empleados.get(u.empleados.size()-1).correo, u.empleados.get(u.empleados.size()-1).nacimiento.get(Calendar.DAY_OF_MONTH)+"/"+u.empleados.get(u.empleados.size()-1).nacimiento.get(Calendar.MONTH)+"/"+u.empleados.get(u.empleados.size()-1).nacimiento.get(Calendar.YEAR), u.empleados.get(u.empleados.size()-1).salario});
                    sp.repaint();
                }
            }
        });
        JButtonReturn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                f.setEnabled(true);
                d.dispose();
            }
        });
        JPanelAddEmployee.setPreferredSize(new Dimension(150, 325));
        c.weighty = 0.1;
        c.weightx = 0.1;
        c.anchor = GridBagConstraints.LINE_START;
        c.gridwidth=3;
        Interfaces.addLabel(JPanelAddEmployee, JLabelName, c, 0, 0);
        Interfaces.addTextField(JPanelAddEmployee, JTextFieldName, c, 0, 1);
        Interfaces.addLabel(JPanelAddEmployee, JLabelLastName, c, 0, 2);
        Interfaces.addTextField(JPanelAddEmployee, JTextFieldLastName, c, 0, 3);
        Interfaces.addLabel(JPanelAddEmployee, JLabelPhone, c, 0, 4);
        Interfaces.addTextField(JPanelAddEmployee, JTextFieldPhone, c, 0, 5);
        Interfaces.addLabel(JPanelAddEmployee, JLabelEmail, c, 0, 6);
        Interfaces.addTextField(JPanelAddEmployee, JTextFieldEmail, c, 0, 7);
        Interfaces.addLabel(JPanelAddEmployee, JLabelEmail, c, 0, 8);
        Interfaces.addTextField(JPanelAddEmployee, JTextFieldEmail, c, 0, 9);
        Interfaces.addLabel(JPanelAddEmployee, JLabelSalary, c, 0, 10);
        Interfaces.addTextField(JPanelAddEmployee, JTextFieldSalary, c, 0, 11);
        Interfaces.addLabel(JPanelAddEmployee, JLabelBirthday, c, 0, 12);
        Interfaces.addButton(JPanelAddEmployee, JButtonNotes, c, 0, 14);
        c.anchor = GridBagConstraints.LINE_END;
        Interfaces.addButton(JPanelAddEmployee, JButtonRegister, c, 0, 14);
        c.anchor = GridBagConstraints.LINE_START;
        Dimension d1 = new Dimension(45, 25);
        Dimension d2 = new Dimension(70, 25);
        JComboBoxDay.setPreferredSize(d1);
        JComboBoxDay.setMinimumSize(d1);
        JComboBoxMonth.setPreferredSize(d1);
        JComboBoxMonth.setMinimumSize(d1);
        JComboBoxYear.setPreferredSize(d2);
        JComboBoxYear.setMinimumSize(d2);
        c.weightx = 0;
        c.gridwidth = 1;
        Interfaces.addComboBox(JPanelAddEmployee, JComboBoxDay, c, 0, 13);
        Interfaces.addComboBox(JPanelAddEmployee, JComboBoxMonth, c, 1, 13);
        Interfaces.addComboBox(JPanelAddEmployee, JComboBoxYear, c, 2, 13);
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 0.5;
        Interfaces.addPanel(JPanelEmployees, JPanelAddEmployeeBorder, c, 0, 0);
        Interfaces.addScrollPane(JPanelEmployees, sp, c, 1, 0);
        c.anchor = GridBagConstraints.LAST_LINE_START;
        Interfaces.addImageButton(JPanelEmployees, JButtonReturn, c, 0, 0, "arrow-34.png");
        d.add(JPanelEmployees);
        d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        d.setVisible(true);
    }
}