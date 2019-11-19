package interfaz;

import algoritmos.Archivos;
import datos.Venta;
import datos.Usuario;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class RegistroVenta {
    ArrayList<Venta> ventas = new ArrayList<>();
    JDialog d;
    JComboBox JComboBoxClients = new JComboBox();
    JComboBox JComboBoxProducts = new JComboBox();
    JComboBox JComboBoxIterations = new JComboBox();
    JTextField JTextFieldQuantity = new JTextField("0");
    JTextField JTextFieldTotal = new JTextField("0");
    JButton JButtonAdd = new JButton("Añadir");
    JButton JButtonRegister = new JButton("Registrar");
    JTextArea ta = new JTextArea();
    JScrollPane sp = new JScrollPane(ta);
    JLabel JLabelProduct = new JLabel("Producto:");
    JLabel JLabelIteration = new JLabel("Iteración:");
    JLabel JLabelQuantity = new JLabel("Cantidad:");
    JLabel JLabelTotal = new JLabel("Total:");
    JLabel JLabelError1 = new JLabel("Insertar valores validos.");
    JLabel JLabelError2 = new JLabel("Ingresar al menos un producto a comprar.");
    public RegistroVenta(JFrame f, Usuario u){
        d = new JDialog(f);
        d.setSize(600,400);
        JComboBoxClients.setBounds(325,30, 200,30);
        JLabelProduct.setBounds(35,30, 100,30);
        JComboBoxProducts.setBounds(95,30, 200,30);
        JComboBoxIterations.setBounds(95,60, 200,30);
        JLabelIteration.setBounds(35,60, 100,30);
        JTextFieldQuantity.setBounds(95,90, 200,30);
        JLabelQuantity.setBounds(10,90, 100,30);
        JLabelTotal.setBounds(10,120, 100,30);
        JTextFieldTotal.setBounds(95,120, 200,30);
        sp.setBounds(325, 60, 200, 90);
        JButtonAdd.setBounds(95,150, 200,30);
        JButtonRegister.setBounds(325,150, 200,30);
        JLabelError1.setBounds(130,175, 200,30);
        JLabelError2.setBounds(305,175, 400,30);
        d.setLayout(null);
        d.setResizable(false);
        ta.setEditable(false);
        JLabelError1.setVisible(false);
        JLabelError2.setVisible(false);
        JTextFieldQuantity.selectAll();
        for (int i=0;i<u.clientes.size();i++) {
            JComboBoxClients.addItem(u.clientes.get(i).nombre);
        }
        for (int i=0;i<u.productos.size();i++) {
            JComboBoxProducts.addItem(u.productos.get(i).nombre);
        }
        if (u.productos.get(JComboBoxProducts.getSelectedIndex()).expirables.size()>0){
            JComboBoxIterations.setEnabled(true);
            for (int i=0;i<u.productos.get(JComboBoxProducts.getSelectedIndex()).expirables.size();i++) {
                JComboBoxIterations.addItem(u.productos.get(JComboBoxProducts.getSelectedIndex()).expirables.get(i).nombre+" "+u.productos.get(JComboBoxProducts.getSelectedIndex()).expirables.get(i).expiracion.get(Calendar.DAY_OF_MONTH)+"/"+u.productos.get(JComboBoxProducts.getSelectedIndex()).expirables.get(i).expiracion.get(Calendar.MONTH)+"/"+u.productos.get(JComboBoxProducts.getSelectedIndex()).expirables.get(i).expiracion.get(Calendar.YEAR));
            }
        } else {
            JComboBoxIterations.setEnabled(false);
        }
        d.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                f.setEnabled(true);
            }
        });
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JComboBoxProducts.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBoxIterations.removeAllItems();
                if (u.productos.get(JComboBoxProducts.getSelectedIndex()).expirables.size()>0){
                    JComboBoxIterations.setEnabled(true);
                    for (int i=0;i<u.productos.get(JComboBoxProducts.getSelectedIndex()).expirables.size();i++) {
                        JComboBoxIterations.addItem(u.productos.get(JComboBoxProducts.getSelectedIndex()).expirables.get(i).nombre+" "+u.productos.get(JComboBoxProducts.getSelectedIndex()).expirables.get(i).expiracion.get(Calendar.DAY_OF_MONTH)+"/"+u.productos.get(JComboBoxProducts.getSelectedIndex()).expirables.get(i).expiracion.get(Calendar.MONTH)+"/"+u.productos.get(JComboBoxProducts.getSelectedIndex()).expirables.get(i).expiracion.get(Calendar.YEAR));
                    }
                } else {
                    JComboBoxIterations.setEnabled(false);
                }
            }
        });
        JTextFieldQuantity.getDocument().addDocumentListener(new DocumentListener() {
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
                    float cantidad = Integer.parseInt(JTextFieldQuantity.getText());
                    JTextFieldTotal.setText(String.valueOf(u.productos.get(JComboBoxProducts.getSelectedIndex()).precio*cantidad));
                    JLabelError1.setVisible(false);
                    JButtonAdd.setEnabled(true);
                } catch (NumberFormatException i) {
                    JLabelError1.setVisible(true);
                    JButtonAdd.setEnabled(false);
                }
            }
        });
        JTextFieldQuantity.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                JTextFieldQuantity.selectAll();
            }
        });
        JTextFieldTotal.getDocument().addDocumentListener(new DocumentListener() {
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
                    float cantidad = Integer.parseInt(JTextFieldQuantity.getText());
                    float total = Float.parseFloat(JTextFieldTotal.getText());
                    JLabelError1.setVisible(false);
                    JButtonAdd.setEnabled(true);
                } catch (NumberFormatException i) {
                    JLabelError1.setVisible(true);
                    JButtonAdd.setEnabled(false);
                }
            }
        });
        JTextFieldTotal.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                JTextFieldTotal.selectAll();
            }
        });
        JTextFieldTotal.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                JTextFieldTotal.selectAll();
            }
        });
        JButtonAdd.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JComboBoxClients.setEnabled(false);
                if (JComboBoxIterations.isEnabled()){
                    ventas.add(new Venta(u.productos.get(JComboBoxProducts.getSelectedIndex()).expirables.get(JComboBoxIterations.getSelectedIndex()), Integer.parseInt(JTextFieldQuantity.getText()), Float.parseFloat(JTextFieldTotal.getText())));
                } else {
                    ventas.add(new Venta(u.productos.get(JComboBoxProducts.getSelectedIndex()), Integer.parseInt(JTextFieldQuantity.getText()), Float.parseFloat(JTextFieldTotal.getText())));
                }
                JLabelError1.setVisible(false);
                ta.append(JTextFieldQuantity.getText()+" "+u.productos.get(JComboBoxProducts.getSelectedIndex()).nombre+" - "+JTextFieldTotal.getText()+"$\n");
            }
        });
        JButtonRegister.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (ventas.size()>0){
                    u.ventas.add(new Venta(u, JComboBoxClients.getSelectedIndex(), ventas));
                    Archivos.guardarArchivo(u,  "\\Usuarios\\"+u.usuario+"\\datos.txt");
                    f.setEnabled(true);
                    d.dispose();
                } else {
                    JLabelError1.setVisible(true);
                }
            }
        });
        d.add(JComboBoxClients);
        d.add(JComboBoxProducts);
        d.add(JComboBoxIterations);
        d.add(JTextFieldQuantity);
        d.add(JTextFieldTotal);
        d.add(JButtonAdd);
        d.add(JButtonRegister);
        d.add(sp);
        d.add(JLabelProduct);
        d.add(JLabelIteration);
        d.add(JLabelQuantity);
        d.add(JLabelTotal);
        d.add(JLabelError1);
        d.add(JLabelError2);
        d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        d.setVisible(true);
    }
}