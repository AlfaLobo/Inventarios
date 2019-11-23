package interfaz;

import algoritmos.Archivos;
import algoritmos.Interfaces;
import datos.Producto;
import datos.Usuario;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ModificarProducto {
    GridBagConstraints c = new GridBagConstraints();
    JDialog d;
    JPanel JPanelModify = new JPanel();
    JLabel JLabelName = new JLabel("Nombre:");
    JTextField JTextFieldName = new JTextField();
    JLabel JLabelQuantity = new JLabel("Cantidad:");
    JTextField JTextFieldQuantity = new JTextField();
    JLabel JLabelCost = new JLabel("Costo:");
    JTextField JTextFieldCost = new JTextField();
    JLabel JLabelPrice = new JLabel("Precio:");
    JTextField JTextFieldPrice = new JTextField();
    JLabel JLabelInvestment = new JLabel("Inversión:");
    JTextField JTextFieldInvestment = new JTextField();
    JLabel JLabelProfit = new JLabel("Ganancia:");
    JTextField JTextFieldProfit = new JTextField();
    JButton JButtonEdit = new JButton("Guardar Cambios");
    JButton JButtonCancel = new JButton();

    public ModificarProducto(Usuario u, JDialog j, Producto p) {
        d = new JDialog(j);
        d.setSize(600,400);
        d.setLocationRelativeTo(j);
        d.getRootPane().setBorder(BorderFactory.createEmptyBorder(25, 90, 20, 90));
        d.getRootPane().setBackground(new java.awt.Color(171,213,217));
        d.getContentPane().setBackground(new java.awt.Color(171,213,217));
        d.setResizable(false);
        JPanelModify.setLayout(new GridBagLayout());
        JPanelModify.setOpaque(false);
        JTextFieldName.setText(p.nombre);
        JTextFieldQuantity.setText(String.valueOf(p.cantidad));
        JTextFieldCost.setText(String.valueOf(p.costo));
        JTextFieldPrice.setText(String.valueOf(p.precio));
        JTextFieldInvestment.setText(String.valueOf(p.inversion));
        JTextFieldProfit.setText(String.valueOf(p.ganancia));
        d.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                j.setEnabled(true);
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
                    JButtonEdit.setEnabled(true);
                } catch (NumberFormatException i) {
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
                    JButtonEdit.setEnabled(true);
                } catch (NumberFormatException i) {
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
                    JButtonEdit.setEnabled(true);
                } catch (NumberFormatException i) {
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
                    JButtonEdit.setEnabled(true);
                } catch (NumberFormatException i) {
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
                    JButtonEdit.setEnabled(true);
                } catch (NumberFormatException i) {
                    JButtonEdit.setEnabled(false);
                }
            }
        });
        JButtonEdit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                d.dispose();
                j.setEnabled(true);
            }
        });
        JButtonEdit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                u.productos.get(p.id).nombre=JTextFieldName.getText();
                u.productos.get(p.id).cantidad=Integer.parseInt(JTextFieldQuantity.getText());
                u.productos.get(p.id).costo=Float.parseFloat(JTextFieldCost.getText());
                u.productos.get(p.id).precio=Float.parseFloat(JTextFieldPrice.getText());
                u.productos.get(p.id).inversion=Float.parseFloat(JTextFieldInvestment.getText());
                u.productos.get(p.id).ganancia=Float.parseFloat(JTextFieldProfit.getText());
                Archivos.guardarArchivo(u,  "\\Usuarios\\"+u.usuario+"\\datos.txt");
                JOptionPane.showMessageDialog(d, "Cambios guardados.");
                d.dispose();
                j.setEnabled(true);
            }
        });
        JButtonCancel.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                d.dispose();
                j.setEnabled(true);
            }
        });

        Interfaces.addLabel(JPanelModify, JLabelName, c, 0, 0);
        d.add(JLabelName);
        d.add(JTextFieldName);
        d.add(JLabelQuantity);
        d.add(JTextFieldQuantity);
        d.add(JLabelCost);
        d.add(JTextFieldCost);
        d.add(JLabelPrice);
        d.add(JTextFieldPrice);
        d.add(JLabelInvestment);
        d.add(JTextFieldInvestment);
        d.add(JLabelProfit);
        d.add(JTextFieldProfit);
        d.add(JButtonEdit);
        d.add(JButtonCancel);

        d.add(JPanelModify);
        d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        d.setVisible(true);
    }
}
