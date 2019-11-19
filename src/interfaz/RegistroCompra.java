package interfaz;

import algoritmos.Archivos;
import datos.Compra;
import datos.Expirable;
import datos.Usuario;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class RegistroCompra {
    ArrayList<Compra> compras = new ArrayList<>();
    JDialog d;
    JComboBox JComboBoxProviders = new JComboBox();
    JComboBox JComboBoxProducts = new JComboBox();
    JComboBox JComboBoxDay = new JComboBox();
    JComboBox JComboBoxMonth = new JComboBox();
    JComboBox JComboBoxYear = new JComboBox();
    JTextField JTextFieldQuantity = new JTextField("0");
    JTextField JTextFieldTotal = new JTextField("0");
    JButton JButtonAdd = new JButton("Añadir");
    JButton JButtonRegister = new JButton("Registrar");
    JCheckBox JCheckBoxExpiration = new JCheckBox("Expiración:");
    JTextArea ta = new JTextArea();
    JScrollPane sp = new JScrollPane(ta);
    JLabel JLabelProduct = new JLabel("Producto:");
    JLabel JLabelQuantity = new JLabel("Cantidad:");
    JLabel JLabelTotal = new JLabel("Total:");
    JLabel JLabelError1 = new JLabel("Insertar valores validos.");
    JLabel JLabelError2 = new JLabel("Ingresar al menos un producto a comprar.");
    public RegistroCompra(JFrame f, Usuario u){
        d = new JDialog(f);
        d.setSize(600,400);
        JComboBoxProviders.setBounds(325,30, 200,30);
        JLabelProduct.setBounds(35,30, 100,30);
        JComboBoxProducts.setBounds(95,30, 200,30);
        JLabelQuantity.setBounds(35,60, 100,30);
        JTextFieldQuantity.setBounds(95,60, 200,30);
        JLabelTotal.setBounds(10,90, 100,30);
        JTextFieldTotal.setBounds(95,90, 200,30);
        JCheckBoxExpiration.setBounds(5,120, 100,30);
        JComboBoxDay.setBounds(95,120, 50,30);
        JComboBoxMonth.setBounds(145,120, 50,30);
        JComboBoxYear.setBounds(195,120, 100,30);
        sp.setBounds(325, 60, 200, 90);
        JButtonAdd.setBounds(95,150, 200,30);
        JButtonRegister.setBounds(325,150, 200,30);
        JLabelError1.setBounds(130,175, 200,30);
        JLabelError2.setBounds(305,175, 400,30);
        d.setLayout(null);
        d.setResizable(false);
        JComboBoxDay.setEnabled(false);
        JComboBoxMonth.setEnabled(false);
        JComboBoxYear.setEnabled(false);
        JCheckBoxExpiration.setContentAreaFilled(false);
        ta.setEditable(false);
        JLabelError1.setVisible(false);
        JLabelError2.setVisible(false);
        for (int i=0;i<u.proveedores.size();i++) {
            JComboBoxProviders.addItem(u.proveedores.get(i).nombre);
        }
        for (int i=0;i<u.proveedores.get(JComboBoxProviders.getSelectedIndex()).productos.size();i++) {
            JComboBoxProducts.addItem(u.productos.get(u.proveedores.get(JComboBoxProviders.getSelectedIndex()).productos.get(i)).nombre);
        }
        for (int i=1;i<32;i++) {
            JComboBoxDay.addItem(i);
        }
        for (int i=1;i<13;i++) {
            JComboBoxMonth.addItem(i);
        }
        for (int i=2019;i<2100;i++) {
            JComboBoxYear.addItem(i);
        }
        d.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                f.setEnabled(true);
            }
        });
        JComboBoxProviders.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBoxProducts.removeAllItems();
                for (int i=0;i<u.proveedores.get(JComboBoxProviders.getSelectedIndex()).productos.size();i++) {
                    JComboBoxProducts.addItem(u.productos.get(u.proveedores.get(JComboBoxProviders.getSelectedIndex()).productos.get(i)).nombre);
                }
            }
        });
        sp.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
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
                    float total = Float.parseFloat(JTextFieldTotal.getText());
                    JTextFieldTotal.setText(String.valueOf(u.productos.get(u.proveedores.get(JComboBoxProviders.getSelectedIndex()).productos.get(JComboBoxProducts.getSelectedIndex())).costo*cantidad));
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
        JButtonAdd.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JComboBoxProviders.setEnabled(false);
                if (JCheckBoxExpiration.isSelected()){
                    compras.add(new Compra(new Expirable(u.productos.get(u.proveedores.get(JComboBoxProviders.getSelectedIndex()).productos.get(JComboBoxProducts.getSelectedIndex())), new GregorianCalendar((Integer) JComboBoxYear.getItemAt(JComboBoxYear.getSelectedIndex()), JComboBoxMonth.getSelectedIndex(), JComboBoxDay.getSelectedIndex())), Integer.parseInt(JTextFieldQuantity.getText()), Float.parseFloat(JTextFieldTotal.getText())));
                } else {
                    compras.add(new Compra(u.productos.get(u.proveedores.get(JComboBoxProviders.getSelectedIndex()).productos.get(JComboBoxProducts.getSelectedIndex())), Integer.parseInt(JTextFieldQuantity.getText()), Float.parseFloat(JTextFieldTotal.getText())));
                }
                JLabelError2.setVisible(false);
                ta.append(JTextFieldQuantity.getText()+" "+u.productos.get(u.proveedores.get(JComboBoxProviders.getSelectedIndex()).productos.get(JComboBoxProducts.getSelectedIndex())).nombre+" - "+JTextFieldTotal.getText()+"$\n");
            }
        });
        JButtonRegister.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (compras.size()>0){
                    u.compras.add(new Compra(u, JComboBoxProviders.getSelectedIndex(), compras));
                    Archivos.guardarArchivo(u,  "\\Usuarios\\"+u.usuario+"\\datos.txt");
                    f.setEnabled(true);
                    d.dispose();
                } else {
                    JLabelError2.setVisible(true);
                }
            }
        });
        JCheckBoxExpiration.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e){
                JComboBoxDay.setEnabled(e.getStateChange() == ItemEvent.SELECTED);
                JComboBoxMonth.setEnabled(e.getStateChange() == ItemEvent.SELECTED);
                JComboBoxYear.setEnabled(e.getStateChange() == ItemEvent.SELECTED);
            }
        });
        d.add(JComboBoxProviders);
        d.add(JComboBoxProducts);
        d.add(JComboBoxDay);
        d.add(JComboBoxMonth);
        d.add(JComboBoxYear);
        d.add(JTextFieldQuantity);
        d.add(JTextFieldTotal);
        d.add(JButtonAdd);
        d.add(JButtonRegister);
        d.add(JCheckBoxExpiration);
        d.add(sp);
        d.add(JLabelProduct);
        d.add(JLabelQuantity);
        d.add(JLabelTotal);
        d.add(JLabelError1);
        d.add(JLabelError2);
        d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        d.setVisible(true);
    }
    public RegistroCompra(JFrame f, Usuario u, int proveedor) {
        this(f, u);
        JComboBoxProviders.setSelectedIndex(proveedor);
        JComboBoxProducts.setSelectedIndex(u.proveedores.get(proveedor).productos.size()-1);
        JComboBoxProviders.setEnabled(false);
    }
    public RegistroCompra(JFrame f, Usuario u, int proveedor, int product) {
        this(f, u, proveedor);
        JComboBoxProducts.setSelectedIndex(product);
    }
}