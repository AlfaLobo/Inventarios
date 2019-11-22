package interfaz;

import algoritmos.Archivos;
import algoritmos.Busqueda;
import datos.Producto;
import datos.Usuario;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegistroProducto {
    JDialog d;
    JLabel JLabelProvider = new JLabel("Proveedor:");
    JComboBox JComboBoxProviders = new JComboBox();
    JLabel JLabelName = new JLabel("Nombre:");
    JTextField JTextFieldName = new JTextField();
    JLabel JLabelCost = new JLabel("Costo:");
    JTextField JTextFieldCost = new JTextField("0");
    JLabel JLabelPrice = new JLabel("Precio:");
    JTextField JTextFieldPrice = new JTextField("0");
    JButton JButtonRegister = new JButton("Registrar");
    JButton JButtonCancel = new JButton("Cancelar");
    JLabel JLabelError = new JLabel();

    public RegistroProducto(JFrame f, Usuario u) {
        f.setEnabled(false);
        d = new JDialog(f);
        d.setSize(400,500);
        JLabelName.setBounds(95,120, 200,30);
        JTextFieldName.setBounds(150,120, 200,30);
        JLabelCost.setBounds(103,150, 200,30);
        JTextFieldCost.setBounds(150,150, 200,30);
        JLabelPrice.setBounds(89,180, 200,30);
        JTextFieldPrice.setBounds(150,180, 200,30);
        JLabelProvider.setBounds(55,210, 200,30);
        JComboBoxProviders.setBounds(150,210, 200,30);
        JButtonRegister.setBounds(150,240, 200,30);
        JButtonCancel.setBounds(150,265, 200,30);
        d.setLayout(null);
        d.setResizable(false);
        JButtonCancel.setContentAreaFilled(false);
        JButtonCancel.setBorderPainted(false);
        for (int i=0;i<u.proveedores.size();i++) {
            JComboBoxProviders.addItem(u.proveedores.get(i).nombre);
        }
        d.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                f.setEnabled(true);
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
                JLabelError.setText("Insertar valores validos.");
                JLabelError.setBounds(180,275,400, 40);
                try {
                    float costo = Float.parseFloat(JTextFieldCost.getText());
                    float precio = Float.parseFloat(JTextFieldPrice.getText());
                    JLabelError.setVisible(false);
                    JButtonRegister.setEnabled(true);
                    if (costo<0||precio<0) {
                        JLabelError.setVisible(true);
                        JButtonRegister.setEnabled(false);
                    } else {
                        JLabelError.setVisible(false);
                        JButtonRegister.setEnabled(true);
                    }
                } catch (NumberFormatException i) {
                    JLabelError.setVisible(true);
                    JButtonRegister.setEnabled(false);
                }
            }
        });
        JTextFieldCost.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                JTextFieldCost.selectAll();
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
                JLabelError.setText("Insertar valores validos.");
                JLabelError.setBounds(180,175,400, 40);
                try {
                    float costo = Float.parseFloat(JTextFieldCost.getText());
                    float precio = Float.parseFloat(JTextFieldPrice.getText());
                    JLabelError.setVisible(false);
                    JButtonRegister.setEnabled(true);
                    if (costo<0||precio<0) {
                        JLabelError.setVisible(true);
                        JButtonRegister.setEnabled(false);
                    } else  {
                        JLabelError.setVisible(false);
                        JButtonRegister.setEnabled(true);
                    }
                } catch (NumberFormatException i) {
                    JLabelError.setVisible(true);
                    JButtonRegister.setEnabled(false);
                }
            }
        });
        JTextFieldPrice.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                JTextFieldPrice.selectAll();
            }
        });
        JButtonRegister.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (JTextFieldName.getText().equals("")){
                    JLabelError.setText("Nombre no valido.");
                    JLabelError.setBounds(200,305,400, 40);
                    JLabelError.setVisible(true);
                } else if (Busqueda.buscarProducto(u, JComboBoxProviders.getSelectedIndex(), JTextFieldName.getText())) {
                    JLabelError.setText("El producto ya existe.");
                    JLabelError.setBounds(190,305,400, 40);
                    JLabelError.setVisible(true);
                } else {
                    u.productos.add(new Producto(u, JTextFieldName.getText(), Float.parseFloat(JTextFieldCost.getText()), Float.parseFloat(JTextFieldPrice.getText()), JComboBoxProviders.getSelectedIndex()));
                    Archivos.guardarArchivo(u,  "\\Usuarios\\"+u.usuario+"\\datos.txt");
                    Object[] opciones = {"Si", "No"};
                    int opcion = JOptionPane.showOptionDialog(d, "Desea registrar una compra de este producto?", "Elegir una opción", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
                    if (opcion == JOptionPane.YES_OPTION){
                        new RegistroCompra(f, u, JComboBoxProviders.getSelectedIndex());
                    } else {
                        f.setEnabled(true);
                    }
                    d.dispose();
                }
            }
        });
        JButtonCancel.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                d.dispose();
                f.setEnabled(true);
            }
        });
        d.add(JComboBoxProviders);
        d.add(JTextFieldName);
        d.add(JTextFieldCost);
        d.add(JTextFieldPrice);
        d.add(JButtonRegister);
        d.add(JButtonCancel);
        d.add(JLabelName);
        d.add(JLabelCost);
        d.add(JLabelPrice);
        d.add(JLabelProvider);
        d.add(JLabelError);
        d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        d.setVisible(true);
    }
}