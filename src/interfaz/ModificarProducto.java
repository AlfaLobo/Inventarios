package interfaz;

import datos.Usuario;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;

public class ModificarProducto {
    JDialog d;
    JComboBox JComboBoxProviders = new JComboBox();
    JComboBox JComboBoxProducts = new JComboBox();
    JTextField JTextFieldName = new JTextField();
    JTextField JTextFieldQuantity = new JTextField();
    JTextField JTextFieldCost = new JTextField();
    JTextField JTextFieldPrice = new JTextField();
    JTextField JTextFieldInvestment = new JTextField();
    JTextField JTextFieldProfit = new JTextField();
    JButton JButtonEdit = new JButton("Modificar");
    JButton JButtonCancel = new JButton("Regresar");
    JLabel JLabelProvider = new JLabel("Proveedor:");
    JLabel JLabelProduct = new JLabel("Producto:");
    JLabel JLabelName = new JLabel("Nombre:");
    JLabel JLabelQuantity = new JLabel("Cantidad:");
    JLabel JLabelCost = new JLabel("Costo:");
    JLabel JLabelPrice = new JLabel("Precio:");
    JLabel JLabelInvestment = new JLabel("Inversión:");
    JLabel JLabelProfit = new JLabel("Ganancia:");
    JLabel JLabelError = new JLabel("Insertar valores validos.");

    public ModificarProducto(Usuario u, JFrame f) {
        d = new JDialog(f);
        d.setSize(600,400);
        JComboBoxProviders.setBounds(95,30, 200,30);
        JLabelProvider.setBounds(35,30, 100,30);
        JComboBoxProducts.setBounds(95,60, 200,30);
        JLabelProduct.setBounds(35,60, 100,30);
        JLabelName.setBounds(35,90, 100,30);
        JTextFieldName.setBounds(95,90, 200,30);
        JLabelQuantity.setBounds(35,120, 100,30);
        JTextFieldQuantity.setBounds(95,120, 200,30);
        JLabelCost.setBounds(35,150, 100,30);
        JTextFieldCost.setBounds(95,150, 200,30);
        JLabelPrice.setBounds(35,180, 100,30);
        JTextFieldPrice.setBounds(95,180, 200,30);
        JLabelInvestment.setBounds(35,210, 100,30);
        JTextFieldInvestment.setBounds(95,210, 200,30);
        JLabelProfit.setBounds(35,240, 100,30);
        JTextFieldProfit.setBounds(95,240, 200,30);
        JButtonEdit.setBounds(95,270, 200,30);
        JButtonCancel.setBounds(95,295, 200,30);
        JLabelError.setBounds(95,310, 200,30);
        d.setLayout(null);
        d.setResizable(false);
        JButtonCancel.setContentAreaFilled(false);
        JButtonCancel.setBorderPainted(false);
        JLabelError.setVisible(false);
        for (int i=0;i<u.proveedores.size();i++) {
            JComboBoxProviders.addItem(u.proveedores.get(i).nombre);
        }
        for (int i=0;i<u.proveedores.get(JComboBoxProviders.getSelectedIndex()).productos.size();i++) {
            JComboBoxProducts.addItem(u.productos.get(u.proveedores.get(JComboBoxProviders.getSelectedIndex()).productos.get(i)).nombre);
        }
        JTextFieldName.setText(u.productos.get(u.proveedores.get(JComboBoxProviders.getSelectedIndex()).productos.get(JComboBoxProducts.getSelectedIndex())).nombre);
        JTextFieldQuantity.setText(String.valueOf(u.productos.get(u.proveedores.get(JComboBoxProviders.getSelectedIndex()).productos.get(JComboBoxProducts.getSelectedIndex())).cantidad));
        JTextFieldCost.setText(String.valueOf(u.productos.get(u.proveedores.get(JComboBoxProviders.getSelectedIndex()).productos.get(JComboBoxProducts.getSelectedIndex())).costo));
        JTextFieldPrice.setText(String.valueOf(u.productos.get(u.proveedores.get(JComboBoxProviders.getSelectedIndex()).productos.get(JComboBoxProducts.getSelectedIndex())).precio));
        JTextFieldInvestment.setText(String.valueOf(u.productos.get(u.proveedores.get(JComboBoxProviders.getSelectedIndex()).productos.get(JComboBoxProducts.getSelectedIndex())).inversion));
        JTextFieldProfit.setText(String.valueOf(u.productos.get(u.proveedores.get(JComboBoxProviders.getSelectedIndex()).productos.get(JComboBoxProducts.getSelectedIndex())).ganancia));
        d.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                f.setEnabled(true);
            }
        });
        JTextFieldName.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                JTextFieldName.selectAll();
            }
        });
        JTextFieldQuantity.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                JTextFieldQuantity.selectAll();
            }
        });
        JTextFieldCost.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                JTextFieldCost.selectAll();
            }
        });
        JTextFieldPrice.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                JTextFieldPrice.selectAll();
            }
        });
        JTextFieldInvestment.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                JTextFieldInvestment.selectAll();
            }
        });
        JTextFieldProfit.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                JTextFieldProfit.selectAll();
            }
        });
        JComboBoxProviders.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBoxProducts.removeAllItems();
                for (int i=0;i<u.proveedores.get(JComboBoxProviders.getSelectedIndex()).productos.size();i++) {
                    JComboBoxProducts.addItem(u.productos.get(u.proveedores.get(JComboBoxProviders.getSelectedIndex()).productos.get(i)).nombre);
                }
                JTextFieldName.setText(u.productos.get(u.proveedores.get(JComboBoxProviders.getSelectedIndex()).productos.get(0)).nombre);
                JTextFieldQuantity.setText(String.valueOf(u.productos.get(u.proveedores.get(JComboBoxProviders.getSelectedIndex()).productos.get(0)).cantidad));
                JTextFieldCost.setText(String.valueOf(u.productos.get(u.proveedores.get(JComboBoxProviders.getSelectedIndex()).productos.get(0)).costo));
                JTextFieldPrice.setText(String.valueOf(u.productos.get(u.proveedores.get(JComboBoxProviders.getSelectedIndex()).productos.get(0)).precio));
                JTextFieldInvestment.setText(String.valueOf(u.productos.get(u.proveedores.get(JComboBoxProviders.getSelectedIndex()).productos.get(0)).inversion));
                JTextFieldProfit.setText(String.valueOf(u.productos.get(u.proveedores.get(JComboBoxProviders.getSelectedIndex()).productos.get(0)).ganancia));
            }
        });
        JComboBoxProducts.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    JTextFieldName.setText(u.productos.get(u.proveedores.get(JComboBoxProviders.getSelectedIndex()).productos.get(JComboBoxProducts.getSelectedIndex())).nombre);
                    JTextFieldQuantity.setText(String.valueOf(u.productos.get(u.proveedores.get(JComboBoxProviders.getSelectedIndex()).productos.get(JComboBoxProducts.getSelectedIndex())).cantidad));
                    JTextFieldCost.setText(String.valueOf(u.productos.get(u.proveedores.get(JComboBoxProviders.getSelectedIndex()).productos.get(JComboBoxProducts.getSelectedIndex())).costo));
                    JTextFieldPrice.setText(String.valueOf(u.productos.get(u.proveedores.get(JComboBoxProviders.getSelectedIndex()).productos.get(JComboBoxProducts.getSelectedIndex())).precio));
                    JTextFieldInvestment.setText(String.valueOf(u.productos.get(u.proveedores.get(JComboBoxProviders.getSelectedIndex()).productos.get(JComboBoxProducts.getSelectedIndex())).inversion));
                    JTextFieldProfit.setText(String.valueOf(u.productos.get(u.proveedores.get(JComboBoxProviders.getSelectedIndex()).productos.get(JComboBoxProducts.getSelectedIndex())).ganancia));
                } catch (IndexOutOfBoundsException i) {

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
                    float costo = Float.parseFloat(JTextFieldCost.getText());
                    float precio = Float.parseFloat(JTextFieldPrice.getText());
                    float inversion = Float.parseFloat(JTextFieldInvestment.getText());
                    float ganancia = Float.parseFloat(JTextFieldProfit.getText());
                    JLabelError.setVisible(false);
                    JButtonEdit.setEnabled(true);
                } catch (NumberFormatException i) {
                    JLabelError.setVisible(true);
                    JButtonEdit.setEnabled(false);
                }
            }
        });
        JTextFieldCost.getDocument().addDocumentListener(new DocumentListener() {
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
                    float costo = Float.parseFloat(JTextFieldCost.getText());
                    float precio = Float.parseFloat(JTextFieldPrice.getText());
                    float inversion = Float.parseFloat(JTextFieldInvestment.getText());
                    float ganancia = Float.parseFloat(JTextFieldProfit.getText());
                    JLabelError.setVisible(false);
                    JButtonEdit.setEnabled(true);
                } catch (NumberFormatException i) {
                    JLabelError.setVisible(true);
                    JButtonEdit.setEnabled(false);
                }
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
                    float cantidad = Integer.parseInt(JTextFieldQuantity.getText());
                    float costo = Float.parseFloat(JTextFieldCost.getText());
                    float precio = Float.parseFloat(JTextFieldPrice.getText());
                    float inversion = Float.parseFloat(JTextFieldInvestment.getText());
                    float ganancia = Float.parseFloat(JTextFieldProfit.getText());
                    JLabelError.setVisible(false);
                    JButtonEdit.setEnabled(true);
                } catch (NumberFormatException i) {
                    JLabelError.setVisible(true);
                    JButtonEdit.setEnabled(false);
                }
            }
        });
        JTextFieldInvestment.getDocument().addDocumentListener(new DocumentListener() {
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
                    float costo = Float.parseFloat(JTextFieldCost.getText());
                    float precio = Float.parseFloat(JTextFieldPrice.getText());
                    float inversion = Float.parseFloat(JTextFieldInvestment.getText());
                    float ganancia = Float.parseFloat(JTextFieldProfit.getText());
                    JLabelError.setVisible(false);
                    JButtonEdit.setEnabled(true);
                } catch (NumberFormatException i) {
                    JLabelError.setVisible(true);
                    JButtonEdit.setEnabled(false);
                }
            }
        });
        JTextFieldProfit.getDocument().addDocumentListener(new DocumentListener() {
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
                    float costo = Float.parseFloat(JTextFieldCost.getText());
                    float precio = Float.parseFloat(JTextFieldPrice.getText());
                    float inversion = Float.parseFloat(JTextFieldInvestment.getText());
                    float ganancia = Float.parseFloat(JTextFieldProfit.getText());
                    JLabelError.setVisible(false);
                    JButtonEdit.setEnabled(true);
                } catch (NumberFormatException i) {
                    JLabelError.setVisible(true);
                    JButtonEdit.setEnabled(false);
                }
            }
        });
        JButtonEdit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                d.dispose();
                f.setEnabled(true);
            }
        });
        JButtonEdit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                u.productos.get(u.proveedores.get(JComboBoxProviders.getSelectedIndex()).productos.get(JComboBoxProducts.getSelectedIndex())).nombre=JTextFieldName.getText();
                u.productos.get(u.proveedores.get(JComboBoxProviders.getSelectedIndex()).productos.get(JComboBoxProducts.getSelectedIndex())).cantidad=Integer.parseInt(JTextFieldQuantity.getText());
                u.productos.get(u.proveedores.get(JComboBoxProviders.getSelectedIndex()).productos.get(JComboBoxProducts.getSelectedIndex())).costo=Float.parseFloat(JTextFieldCost.getText());
                u.productos.get(u.proveedores.get(JComboBoxProviders.getSelectedIndex()).productos.get(JComboBoxProducts.getSelectedIndex())).precio=Float.parseFloat(JTextFieldPrice.getText());
                u.productos.get(u.proveedores.get(JComboBoxProviders.getSelectedIndex()).productos.get(JComboBoxProducts.getSelectedIndex())).inversion=Float.parseFloat(JTextFieldInvestment.getText());
                u.productos.get(u.proveedores.get(JComboBoxProviders.getSelectedIndex()).productos.get(JComboBoxProducts.getSelectedIndex())).ganancia=Float.parseFloat(JTextFieldProfit.getText());
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
        d.add(JComboBoxProviders);
        d.add(JComboBoxProducts);
        d.add(JTextFieldName);
        d.add(JTextFieldQuantity);
        d.add(JTextFieldCost);
        d.add(JTextFieldPrice);
        d.add(JTextFieldInvestment);
        d.add(JTextFieldProfit);
        d.add(JButtonEdit);
        d.add(JButtonCancel);
        d.add(JLabelProvider);
        d.add(JLabelProduct);
        d.add(JLabelName);
        d.add(JLabelQuantity);
        d.add(JLabelCost);
        d.add(JLabelPrice);
        d.add(JLabelInvestment);
        d.add(JLabelProfit);
        d.add(JLabelError);
        d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        d.setVisible(true);
    }
}
