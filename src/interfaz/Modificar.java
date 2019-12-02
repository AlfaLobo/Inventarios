package interfaz;

import algoritmos.Archivos;
import algoritmos.Interfaces;
import datos.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Modificar {
    GridBagConstraints c = new GridBagConstraints();
    JDialog d;
    JPanel JPanelModify = new JPanel();
    JLabel JLabel1 = new JLabel();
    JLabel JLabel2 = new JLabel();
    JLabel JLabel3 = new JLabel();
    JLabel JLabel4 = new JLabel();
    JLabel JLabel5 = new JLabel();
    JLabel JLabel6 = new JLabel();
    JLabel JLabel7 = new JLabel();
    JLabel JLabelNotes = new JLabel("Notas:");
    JPasswordField JPasswordField1 = new JPasswordField();
    JPasswordField JPasswordField2 = new JPasswordField();
    JTextField JTextField1 = new JTextField();
    JTextField JTextField2 = new JTextField();
    JTextField JTextField3 = new JTextField();
    JTextField JTextField4 = new JTextField();
    JTextField JTextField5 = new JTextField();
    JTextField JTextField6 = new JTextField();
    JComboBox JComboBoxDay = new JComboBox();
    JComboBox JComboBoxMonth = new JComboBox();
    JComboBox JComboBoxYear = new JComboBox();
    JTextArea JTextAreaNotes = new JTextArea();
    JScrollPane JScrollPaneNotes = new JScrollPane(JTextAreaNotes);
    JButton JButtonEdit = new JButton("Guardar Cambios");

    public Modificar(JDialog j, int width, int height){
        d = new JDialog(j);
        d.setSize(width,height);
        d.setLocationRelativeTo(j);
        d.getRootPane().setBackground(new java.awt.Color(171,213,217));
        d.getContentPane().setBackground(new java.awt.Color(171,213,217));
        d.setResizable(false);
        JPanelModify.setLayout(new GridBagLayout());
        JPanelModify.setOpaque(false);
        Dimension JScrollPaneNotesDimension = new Dimension(189, 75);
        JScrollPaneNotes.setPreferredSize(JScrollPaneNotesDimension);
        JScrollPaneNotes.setMinimumSize(JScrollPaneNotesDimension);
        c.weightx = 0.1;
        c.weighty = 0.1;
        c.anchor = GridBagConstraints.LINE_START;
        d.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                j.setEnabled(true);
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
        JTextAreaNotes.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                JTextAreaNotes.selectAll();
            }
        });
        d.add(JPanelModify);
        d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        d.setVisible(true);
    }
    public Modificar(JDialog j, Usuario u, Cliente p) {
        this(j, 375, 500);
        d.getRootPane().setBorder(BorderFactory.createEmptyBorder(15, 90, 15, 90));
        JLabel1.setText("Nombre:");
        JTextField1.setText(p.nombre);
        JLabel2.setText("Apellidos:");
        JTextField2.setText(p.apellidos);
        JLabel3.setText("Teléfono:");
        JTextField3.setText(p.telefono);
        JLabel4.setText("Correo:");
        JTextField4.setText(p.correo);
        JLabel5.setText("Ganancia:");
        JTextField5.setText(String.valueOf(p.ganancia));
        JTextAreaNotes.setText(p.notas);
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
                    float profit = Float.parseFloat(JTextField5.getText());
                    JButtonEdit.setEnabled(true);
                } catch (NumberFormatException i) {
                    JButtonEdit.setEnabled(false);
                }
            }
        });
        JButtonEdit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                u.clientes.get(p.id).nombre=JTextField1.getText();
                u.clientes.get(p.id).apellidos=JTextField2.getText();
                u.clientes.get(p.id).telefono=JTextField3.getText();
                u.clientes.get(p.id).correo=JTextField4.getText();
                u.clientes.get(p.id).ganancia=Float.parseFloat(JTextField5.getText());
                u.clientes.get(p.id).notas=JTextAreaNotes.getText();
                Archivos.guardarArchivo(u);
                JOptionPane.showMessageDialog(d, "Cambios guardados.");
                d.dispose();
                j.setEnabled(true);
            }
        });
        Interfaces.addLabel(JPanelModify, JLabel1, c, 0, 0);
        Interfaces.addTextField(JPanelModify, JTextField1, c, 0, 1);
        Interfaces.addLabel(JPanelModify, JLabel2, c, 0, 2);
        Interfaces.addTextField(JPanelModify, JTextField2, c, 0, 3);
        Interfaces.addLabel(JPanelModify, JLabel3, c, 0, 4);
        Interfaces.addTextField(JPanelModify, JTextField3, c, 0, 5);
        Interfaces.addLabel(JPanelModify, JLabel4, c, 0, 6);
        Interfaces.addTextField(JPanelModify, JTextField4, c, 0, 7);
        Interfaces.addLabel(JPanelModify, JLabel5, c, 0, 8);
        Interfaces.addTextField(JPanelModify, JTextField5, c, 0, 9);
        Interfaces.addLabel(JPanelModify, JLabelNotes, c, 0, 10);
        Interfaces.addScrollPane(JPanelModify, JScrollPaneNotes, c, 0, 11);
        c.anchor = GridBagConstraints.CENTER;
        Interfaces.addButton(JPanelModify, JButtonEdit, c, 0, 12);
    }
    public Modificar(JDialog j, Usuario u, Empleado p){
        this(j, 375, 550);
        for (int i=1;i<32;i++) {
            JComboBoxDay.addItem(i);
        }
        for (int i=1;i<13;i++) {
            JComboBoxMonth.addItem(i);
        }
        for (int i=2019;i>1899;i--) {
            JComboBoxYear.addItem(i);
        }
        d.getRootPane().setBorder(BorderFactory.createEmptyBorder(15, 90, 15, 90));
        Dimension d1 = new Dimension(47, 25);
        Dimension d2 = new Dimension(94, 25);
        JComboBoxDay.setPreferredSize(d1);
        JComboBoxDay.setMinimumSize(d1);
        JComboBoxMonth.setPreferredSize(d1);
        JComboBoxMonth.setMinimumSize(d1);
        JComboBoxYear.setPreferredSize(d2);
        JComboBoxYear.setMinimumSize(d2);
        JLabel1.setText("Nombre:");
        JTextField1.setText(p.nombre);
        JLabel2.setText("Apellidos:");
        JTextField2.setText(p.apellidos);
        JLabel3.setText("Teléfono:");
        JTextField3.setText(p.telefono);
        JLabel4.setText("Correo:");
        JTextField4.setText(p.correo);
        JLabel5.setText("Fecha de Nacimiento:");
        JComboBoxDay.setSelectedIndex(p.nacimiento.get(Calendar.DAY_OF_MONTH)-1);
        JComboBoxMonth.setSelectedIndex(p.nacimiento.get(Calendar.MONTH)-1);
        JComboBoxYear.setSelectedIndex(-p.nacimiento.get(Calendar.YEAR)+1899+120);
        JLabel6.setText("Salario:");
        JTextField5.setText(String.valueOf(p.salario));

        JTextAreaNotes.setText(p.notas);
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
                    float salary = Float.parseFloat(JTextField5.getText());
                    JButtonEdit.setEnabled(true);
                } catch (NumberFormatException i) {
                    JButtonEdit.setEnabled(false);
                }
            }
        });
        JButtonEdit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                u.empleados.get(p.id).nombre=JTextField1.getText();
                u.empleados.get(p.id).apellidos=JTextField2.getText();
                u.empleados.get(p.id).telefono=JTextField3.getText();
                u.empleados.get(p.id).correo=JTextField4.getText();
                u.empleados.get(p.id).nacimiento=new GregorianCalendar((int) JComboBoxYear.getItemAt(JComboBoxYear.getSelectedIndex()), (int) JComboBoxMonth.getItemAt(JComboBoxMonth.getSelectedIndex()), (int) JComboBoxDay.getItemAt(JComboBoxDay.getSelectedIndex()));
                u.empleados.get(p.id).salario=Float.parseFloat(JTextField5.getText());
                u.empleados.get(p.id).notas=JTextAreaNotes.getText();
                Archivos.guardarArchivo(u);
                JOptionPane.showMessageDialog(d, "Cambios guardados.");
                d.dispose();
                j.setEnabled(true);
            }
        });
        c.gridwidth=3;
        Interfaces.addLabel(JPanelModify, JLabel1, c, 0, 0);
        Interfaces.addTextField(JPanelModify, JTextField1, c, 0, 1);
        Interfaces.addLabel(JPanelModify, JLabel2, c, 0, 2);
        Interfaces.addTextField(JPanelModify, JTextField2, c, 0, 3);
        Interfaces.addLabel(JPanelModify, JLabel3, c, 0, 4);
        Interfaces.addTextField(JPanelModify, JTextField3, c, 0, 5);
        Interfaces.addLabel(JPanelModify, JLabel4, c, 0, 6);
        Interfaces.addTextField(JPanelModify, JTextField4, c, 0, 7);
        Interfaces.addLabel(JPanelModify, JLabel5, c, 0, 8);
        Interfaces.addLabel(JPanelModify, JLabel6, c, 0, 10);
        Interfaces.addTextField(JPanelModify, JTextField5, c, 0, 11);
        Interfaces.addLabel(JPanelModify, JLabelNotes, c, 0, 12);
        Interfaces.addScrollPane(JPanelModify, JScrollPaneNotes, c, 0, 13);
        c.anchor = GridBagConstraints.CENTER;
        Interfaces.addButton(JPanelModify, JButtonEdit, c, 0, 14);
        c.anchor = GridBagConstraints.LINE_START;
        c.weightx = 0;
        c.gridwidth = 1;
        Interfaces.addComboBox(JPanelModify, JComboBoxDay, c, 0, 9);
        Interfaces.addComboBox(JPanelModify, JComboBoxMonth, c, 1, 9);
        Interfaces.addComboBox(JPanelModify, JComboBoxYear, c, 2, 9);

    }
    public Modificar(JDialog j, Usuario u, Producto p) {
        this(j, 375, 500);
        d.getRootPane().setBorder(BorderFactory.createEmptyBorder(15, 90, 15, 90));
        JLabel1.setText("Nombre:");
        JTextField1.setText(p.nombre);
        JLabel2.setText("Cantidad:");
        JTextField2.setText(String.valueOf(p.cantidad));
        JLabel3.setText("Costo:");
        JTextField3.setText(String.valueOf(p.costo));
        JLabel4.setText("Precio:");
        JTextField4.setText(String.valueOf(p.precio));
        JLabel5.setText("Inversión:");
        JTextField5.setText(String.valueOf(p.inversion));
        JLabel6.setText("Ganancia:");
        JTextField6.setText(String.valueOf(p.ganancia));
        JTextAreaNotes.setText(p.notas);
        JTextField2.getDocument().addDocumentListener(new DocumentListener() {
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
                    int quantity = Integer.parseInt(JTextField2.getText());
                    float cost = Float.parseFloat(JTextField3.getText());
                    float price = Float.parseFloat(JTextField4.getText());
                    float salary = Float.parseFloat(JTextField5.getText());
                    float profit = Float.parseFloat(JTextField6.getText());
                    JButtonEdit.setEnabled(true);
                } catch (NumberFormatException i) {
                    JButtonEdit.setEnabled(false);
                }
            }
        });
        JTextField3.getDocument().addDocumentListener(new DocumentListener() {
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
                    int quantity = Integer.parseInt(JTextField2.getText());
                    float cost = Float.parseFloat(JTextField3.getText());
                    float price = Float.parseFloat(JTextField4.getText());
                    float salary = Float.parseFloat(JTextField5.getText());
                    float profit = Float.parseFloat(JTextField6.getText());
                    JButtonEdit.setEnabled(true);
                } catch (NumberFormatException i) {
                    JButtonEdit.setEnabled(false);
                }
            }
        });JTextField4.getDocument().addDocumentListener(new DocumentListener() {
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
                    int quantity = Integer.parseInt(JTextField2.getText());
                    float cost = Float.parseFloat(JTextField3.getText());
                    float price = Float.parseFloat(JTextField4.getText());
                    float salary = Float.parseFloat(JTextField5.getText());
                    float profit = Float.parseFloat(JTextField6.getText());
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
                    int quantity = Integer.parseInt(JTextField2.getText());
                    float cost = Float.parseFloat(JTextField3.getText());
                    float price = Float.parseFloat(JTextField4.getText());
                    float salary = Float.parseFloat(JTextField5.getText());
                    float profit = Float.parseFloat(JTextField6.getText());
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
                    int quantity = Integer.parseInt(JTextField2.getText());
                    float cost = Float.parseFloat(JTextField3.getText());
                    float price = Float.parseFloat(JTextField4.getText());
                    float salary = Float.parseFloat(JTextField5.getText());
                    float profit = Float.parseFloat(JTextField6.getText());
                    JButtonEdit.setEnabled(true);
                } catch (NumberFormatException i) {
                    JButtonEdit.setEnabled(false);
                }
            }
        });
        JButtonEdit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                u.productos.get(p.id).nombre=JTextField1.getText();
                u.productos.get(p.id).cantidad=Integer.parseInt(JTextField2.getText());
                u.productos.get(p.id).costo=Float.parseFloat(JTextField3.getText());
                u.productos.get(p.id).precio=Float.parseFloat(JTextField4.getText());
                u.productos.get(p.id).inversion=Float.parseFloat(JTextField5.getText());
                u.productos.get(p.id).ganancia=Float.parseFloat(JTextField6.getText());
                u.productos.get(p.id).notas=JTextAreaNotes.getText();
                Archivos.guardarArchivo(u);
                JOptionPane.showMessageDialog(d, "Cambios guardados.");
                d.dispose();
                j.setEnabled(true);
            }
        });
        Interfaces.addLabel(JPanelModify, JLabel1, c, 0, 0);
        Interfaces.addTextField(JPanelModify, JTextField1, c, 0, 1);
        Interfaces.addLabel(JPanelModify, JLabel2, c, 0, 2);
        Interfaces.addTextField(JPanelModify, JTextField2, c, 0, 3);
        Interfaces.addLabel(JPanelModify, JLabel3, c, 0, 4);
        Interfaces.addTextField(JPanelModify, JTextField3, c, 0, 5);
        Interfaces.addLabel(JPanelModify, JLabel4, c, 0, 6);
        Interfaces.addTextField(JPanelModify, JTextField4, c, 0, 7);
        Interfaces.addLabel(JPanelModify, JLabel5, c, 0, 8);
        Interfaces.addTextField(JPanelModify, JTextField5, c, 0, 9);
        Interfaces.addLabel(JPanelModify, JLabel6, c, 0, 10);
        Interfaces.addTextField(JPanelModify, JTextField6, c, 0, 11);
        Interfaces.addLabel(JPanelModify, JLabelNotes, c, 0, 12);
        Interfaces.addScrollPane(JPanelModify, JScrollPaneNotes, c, 0, 13);
        c.anchor = GridBagConstraints.CENTER;
        Interfaces.addButton(JPanelModify, JButtonEdit, c, 0, 14);
    }
    public Modificar(JDialog j, Usuario u, Proveedor p){
        this(j, 375, 500);
        d.getRootPane().setBorder(BorderFactory.createEmptyBorder(15, 90, 15, 90));
        JLabel1.setText("Nombre:");
        JTextField1.setText(p.nombre);
        JLabel2.setText("Teléfono:");
        JTextField2.setText(p.telefono);
        JLabel3.setText("Correo:");
        JTextField3.setText(p.correo);
        JLabel4.setText("Dirección:");
        JTextField4.setText(p.direccion);
        JLabel5.setText("Inversión:");
        JTextField5.setText(String.valueOf(p.inversion));
        JLabel6.setText("Ganancia:");
        JTextField6.setText(String.valueOf(p.ganancia));
        JTextAreaNotes.setText(p.notas);
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
                    float investment = Float.parseFloat(JTextField5.getText());
                    float profit = Float.parseFloat(JTextField6.getText());
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
                    float investment = Float.parseFloat(JTextField5.getText());
                    float profit = Float.parseFloat(JTextField6.getText());
                    JButtonEdit.setEnabled(true);
                } catch (NumberFormatException i) {
                    JButtonEdit.setEnabled(false);
                }
            }
        });
        JButtonEdit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                u.proveedores.get(p.id).nombre=JTextField1.getText();
                u.proveedores.get(p.id).telefono=JTextField2.getText();
                u.proveedores.get(p.id).correo=JTextField3.getText();
                u.proveedores.get(p.id).direccion=JTextField4.getText();
                u.proveedores.get(p.id).inversion=Float.parseFloat(JTextField5.getText());
                u.proveedores.get(p.id).ganancia=Float.parseFloat(JTextField6.getText());
                u.proveedores.get(p.id).notas=JTextAreaNotes.getText();
                Archivos.guardarArchivo(u);
                JOptionPane.showMessageDialog(d, "Cambios guardados.");
                d.dispose();
                j.setEnabled(true);
            }
        });
        Interfaces.addLabel(JPanelModify, JLabel1, c, 0, 0);
        Interfaces.addTextField(JPanelModify, JTextField1, c, 0, 1);
        Interfaces.addLabel(JPanelModify, JLabel2, c, 0, 2);
        Interfaces.addTextField(JPanelModify, JTextField2, c, 0, 3);
        Interfaces.addLabel(JPanelModify, JLabel3, c, 0, 4);
        Interfaces.addTextField(JPanelModify, JTextField3, c, 0, 5);
        Interfaces.addLabel(JPanelModify, JLabel4, c, 0, 6);
        Interfaces.addTextField(JPanelModify, JTextField4, c, 0, 7);
        Interfaces.addLabel(JPanelModify, JLabel5, c, 0, 8);
        Interfaces.addTextField(JPanelModify, JTextField5, c, 0, 9);
        Interfaces.addLabel(JPanelModify, JLabel6, c, 0, 10);
        Interfaces.addTextField(JPanelModify, JTextField6, c, 0, 11);
        Interfaces.addLabel(JPanelModify, JLabelNotes, c, 0, 12);
        Interfaces.addScrollPane(JPanelModify, JScrollPaneNotes, c, 0, 13);
        c.anchor = GridBagConstraints.CENTER;
        Interfaces.addButton(JPanelModify, JButtonEdit, c, 0, 14);
    }
}